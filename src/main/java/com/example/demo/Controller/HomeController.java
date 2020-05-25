package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.AutocamperService;
import com.example.demo.Service.KontraktService;
import com.example.demo.Service.KundeService;
import com.example.demo.Service.TilbehorService;
import org.apache.el.parser.BooleanNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes("nyKontrakt")
public class HomeController {
    @Autowired
    KundeService kundeService;
    @Autowired
    AutocamperService autocamperService;
    @Autowired
    TilbehorService tilbehorService;
    @Autowired
    KontraktService kontraktService;

    @GetMapping("/")
    public String index(){
        return "home/index";
    }


    //Håndtering af kunder
    @GetMapping("/kunder")
    public String kunde() {
        return "home/kunder";
    }

    @GetMapping("/kundeliste")
    public String kundeliste(@ModelAttribute Kunde kunde, Model model) {
        List<Kunde> kundeliste = kundeService.listKunder();
        model.addAttribute("kundeliste", kundeliste);
        return "kunder/kundeliste";
    }

    @GetMapping("/opretKunde")
    public String opretKunde() {
        return "kunder/opretKunde";
    }

    @PostMapping("/opretKunde")
    public String opretKunde(Model model, @ModelAttribute Kunde kunde) {
        model.addAttribute("nyKunde", kunde);
        return "kunder/acceptKunde";
    }

    @PostMapping("/acceptKunde")
    public String acceptKunde(@ModelAttribute Kunde kunde) {
        if (kundeService.tilfojKunde(kunde)) {
            return "redirect:/";
        } else {
            return "/kunder";
        }
    }

    @GetMapping("/autocampere")
    public String Autocamper(@ModelAttribute Autocamper autocamper, Model model){
        List<Autocamper> autocamperliste = autocamperService.listAutocampere();
        model.addAttribute("autocamperliste", autocamperliste);
        return "home/autocampere";
    }

    @GetMapping("/opretAutocamper")
    public String opretAutocamper(){
        return "autocampere/opretAutocamper";
    }

    @PostMapping("/opretAutocamper")
    public String opretAutocamper(Model model, @ModelAttribute Autocamper autocamper){
        model.addAttribute("nyAutocamper", autocamper);
        return "autocampere/acceptAutocamper";
    }

    @PostMapping("/acceptAutocamper")
    public String acceptAutocamper(@ModelAttribute Autocamper autocamper){
        if(autocamperService.tilfojAutocamper(autocamper)) {
            return "redirect:/";
        } else {
            return "home/opretFejl";
        }
    }

    @GetMapping("/tilbehor")
    public String tilbehor(@ModelAttribute Tilbehor tilbehor, Model model) {
        List<Tilbehor> tilbehorliste = tilbehorService.listTilbehor();
        model.addAttribute("tilbehorliste", tilbehorliste);
        return "home/tilbehor";
    }

    @GetMapping("opretTilbehor")
    public String opretTilbehor(@ModelAttribute Tilbehor tilbehor) {
        if (tilbehorService.tilfojTilbehor(tilbehor)) {
            return "redirect:/tilbehor";
        } else {
            return "home/opretFejl";
        }
    }

    @GetMapping("/kontrakter")
    public String kontrakter() {
        return "kontrakter/kontrakter";
    }

    @GetMapping("/opretKontrakt")
    public String opretKontakt(@ModelAttribute Kunde kunde, Model model) {
        List<Kunde> kundeliste = kundeService.listKunder();
        model.addAttribute("kundeliste", kundeliste);
        return "kontrakter/opretKontrakt";
    }

    @GetMapping("/opretKontraktKunde")
    public String opretKontraktKunde() {
        return "kontrakter/opretKontraktKunde";
    }

    @PostMapping("opretKontraktKunde")
    public String opretKontraktKunde(@ModelAttribute Kunde kunde, Model model) {
        if (kundeService.tilfojKunde(kunde)) {
            Kontrakt nyKontrakt = new Kontrakt();
            nyKontrakt.setKu_id(kundeService.nyesteId());
            model.addAttribute("nyKontrakt", nyKontrakt);
            return "kontrakter/kontraktDato";
        } else {
            return "home/opretFejl";
        }
    }

    @GetMapping("/kontraktDato")
    public String kontraktDato(@ModelAttribute Kontrakt kontrakt, Model model) {
        //Kontrakt nyKontrakt = new Kontrakt();
        //nyKontrakt.setKu_id(id);
        model.addAttribute("nyKontrakt", kontrakt);
        return "kontrakter/kontraktDato";
    }

    @GetMapping("/kontraktAuto")
    public String kontraktAuto(@ModelAttribute Kontrakt kontrakt, @ModelAttribute Autocamper autocamper, Model model, Model modelList) {
        model.addAttribute("nyKontrakt", kontrakt);
        List<Autocamper> autocamperliste = autocamperService.listFrieAutocampere(kontrakt.getStart_dato(), kontrakt.getSlut_dato());
        modelList.addAttribute("autocamperliste", autocamperliste);
        return "kontrakter/kontraktAuto";
    }

    @GetMapping("/kontraktData")
    public String kontraktData(@ModelAttribute Kontrakt kontrakt, Model model) {
        //kontrakt.setA_id(a_id);
        model.addAttribute("nyKontrakt", kontrakt);

        return "kontrakter/kontraktData";
    }

    @GetMapping("/acceptKontrakt")
    public String acceptKontrakt(@ModelAttribute Kontrakt kontrakt, Model model, SessionStatus status) {

        //String[] checked = request.getParameterValues("ko_tilbehor[]");
        //Kontrakt test = new Kontrakt(100, tempData.getKu_id(), tempData.getA_id(), tempData.getStart_dato(), tempData.getSlut_dato(), data.getAflevering(), data.getAfhentning(), Arrays.toString(checked), "", 0.0);
        kontrakt.setKo_pris(autocamperService.findAutocamperMedId(kontrakt.getA_id()).getA_pris()*kontrakt.daysBetween() + kontrakt.udregnPris());
        model.addAttribute("nyKontrakt", kontrakt);
        status.isComplete();
        //System.out.println(temp);
        return "kontrakter/acceptKontrakt";
    }

    @PostMapping("/acceptKontrakt")
    public String acceptKontrakt(@ModelAttribute Kontrakt kontrakt) {

        if (kontraktService.tilfojKontrakt(kontrakt)) {
            return "redirect:/kontrakter";
        } else {
            return "home/opretFejl";
        }
    }
}
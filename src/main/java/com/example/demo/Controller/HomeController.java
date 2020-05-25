package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.AutocamperService;
import com.example.demo.Service.KontraktService;
import com.example.demo.Service.KundeService;
import com.example.demo.Service.TilbehorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    //Håndtering af kunder-sider
    @GetMapping("/kunder")
    public String kunde() {
        return "home/kunder";
    }

    @GetMapping("/kundeliste")
    public String kundeliste(Model model) {
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
            return "redirect:/kunder";
        } else {
            return "/kunder";
        }
    }

    //Håndtering af autocampere-sider
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
            return "redirect:/autocampere";
        } else {
            return "home/opretFejl";
        }
    }

    //Håndtering af tilbehør-sider
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

    //Håndtering af kontrakter-sider
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
        model.addAttribute("nyKontrakt", kontrakt);
        return "kontrakter/kontraktDato";
    }

    @GetMapping("/kontraktAuto")
    public String kontraktAuto(@ModelAttribute Kontrakt kontrakt, Model model) {
        model.addAttribute("nyKontrakt", kontrakt);
        List<Autocamper> autocamperliste = autocamperService.listFrieAutocampere(kontrakt.getStart_dato(), kontrakt.getSlut_dato());
        model.addAttribute("autocamperliste", autocamperliste);
        return "kontrakter/kontraktAuto";
    }

    @GetMapping("/kontraktData")
    public String kontraktData(@ModelAttribute Kontrakt kontrakt, Model model) {
        model.addAttribute("nyKontrakt", kontrakt);
        return "kontrakter/kontraktData";
    }

    @GetMapping("/acceptKontrakt")
    public String acceptKontrakt(@ModelAttribute Kontrakt kontrakt, Model model) {
        kontrakt.setKo_pris(autocamperService.findAutocamperMedId(kontrakt.getA_id()).getA_pris() *
                            kontrakt.daysBetween() + kontrakt.udregnPrisForTilbehor());
        model.addAttribute("nyKontrakt", kontrakt);
        model.addAttribute("valgtKunde", kundeService.findKundeMedId(kontrakt.getKu_id()));
        model.addAttribute("valgtAutocamper", autocamperService.findAutocamperMedId(kontrakt.getA_id()));
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
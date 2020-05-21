package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.AutocamperService;
import com.example.demo.Service.KontraktService;
import com.example.demo.Service.KundeService;
import com.example.demo.Service.TilbehorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
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
            return "home/opretFejl";
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

    public static String[] temp = new String[4];
    @GetMapping("/kontraktDato/{ku_id}")
    public String kontraktDato(@PathVariable("ku_id") int id) {
        temp[0] = Integer.toString(id);
        return "kontrakter/kontraktDato";
    }

    @GetMapping("/kontraktAuto")
    public String kontraktAuto(@ModelAttribute Dato dato, @ModelAttribute Autocamper autocamper, Model model) {
        temp[1] = dato.getStart_dato();
        temp[2] = dato.getSlut_dato();
        List<Autocamper> autocamperliste = autocamperService.listFrieAutocampere(temp[1], temp[2]);
        model.addAttribute("autocamperliste", autocamperliste);
        return "kontrakter/kontraktAuto";
    }

    @GetMapping("/kontraktData/{a_id}")
    public String kontraktData(@PathVariable("a_id") int a_id, @ModelAttribute Tilbehor tilbehor, Model model) {
        temp[3] = Integer.toString(a_id);
        List<Tilbehor> tilbehorliste = tilbehorService.listTilbehor();
        model.addAttribute("tilbehorListe", tilbehorliste);
        return "kontrakter/kontraktData";
    }
}
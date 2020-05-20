package com.example.demo.Controller;

import com.example.demo.Model.Autocamper;
import com.example.demo.Model.Kunde;
import com.example.demo.Model.Tilbehor;
import com.example.demo.Service.AutocamperService;
import com.example.demo.Service.KundeService;
import com.example.demo.Service.TilbehorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String Autocamper(){
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
    public String tilbehor() {
        return "home/tilbehor";
    }

    @GetMapping("opretTilbehor")
    public String opretTilbehor(@ModelAttribute Tilbehor tilbehor) {
        if (tilbehorService.tilfojTilbehor(tilbehor)) {
            return "redirect:/";
        } else {
            return "home/opretFejl";
        }
    }
}

package com.example.demo.Controller;

import com.example.demo.Model.Kunde;
import com.example.demo.Service.KundeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    KundeService kundeService;

    @GetMapping("/")
    public String index(){
        return "home/index";
    }

    @GetMapping("/kunder")
    public String kunde() {
        return "home/kunder";
    }

    @GetMapping("/opretKunde")
    public String opretKunde() {
        return "home/opretKunde";
    }

    @PostMapping("/opretKunde")
    public String opretKunde(Model model, @ModelAttribute Kunde kunde) {
        model.addAttribute("nyKunde", kunde);
        return "home/acceptKunde";
    }

    

    @PostMapping("/acceptKunde")
    public String acceptKunde(@ModelAttribute Kunde kunde) {
        kundeService.tilfojKunde(kunde);
        return "redirect:/";
    }
}

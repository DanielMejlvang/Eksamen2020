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
    //Her Autowires alle vores service klasser
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

    //Her vises en liste af kunder i DB
    @GetMapping("/kundeliste")
    public String kundeliste(Model model) {
        List<Kunde> kundeliste = kundeService.listKunder();
        model.addAttribute("kundeliste", kundeliste);
        return "kunder/kundeliste";
    }

    //Her kan bruger udfylde data til en ny kunde
    @GetMapping("/opretKunde")
    public String opretKunde() {
        return "kunder/opretKunde";
    }

    //Her oprettes Kunde objektet med de indtastede data
    @PostMapping("/opretKunde")
    public String opretKunde(Model model, @ModelAttribute Kunde kunde) {
        model.addAttribute("nyKunde", kunde);
        return "kunder/acceptKunde";
    }

    //Her skal brugeren acceptere den indtastede data, og kunden bliver gemt i DB
    @PostMapping("/acceptKunde")
    public String acceptKunde(@ModelAttribute Kunde kunde) {
        if (kundeService.tilfojKunde(kunde)) {
            return "redirect:/kunder";
        } else {
            return "home/opretFejl";
        }
    }

    //Viser en detaljeret visning af en specifik kunde
    @GetMapping("/kundeDetaljer/{ku_id}")
    public String kundeDetaljer(@PathVariable ("ku_id") int ku_id, Model model){
        model.addAttribute("valgtKunde", kundeService.findKundeMedId(ku_id));
        return "kunder/kundeDetaljer";
    }

    /*Her checkes om den kunde der skal slettes er tilknyttet en kontrakt
    Hvis de er bliver man dirigeret videre til en ny side, ellers bliver
    kunden bare slettet og man bliver redirected tilbage til kundelisten*/
    @GetMapping("/sletKunde/{ku_id}")
    public String sletKunde(@PathVariable ("ku_id") int ku_id, Model model, Kunde kunde){
        model.addAttribute("kunde", kunde);

        if(kundeService.erIKontrakt(ku_id)){
            List<Kontrakt> ko_liste = kontraktService.findKontrakterMedKundeId(ku_id);
            model.addAttribute("kontraktListe", ko_liste);
            return "kunder/kundeSletFejl";
        } else if (kundeService.sletKunde(ku_id)){
            return "redirect:/kundeliste";
        } else {
            return "home/generelFejl";
        }
    }

    //Her slettes alle kontrakter der er tilknyttet en kunde, derefter slettes kunden
    @GetMapping("sletKundeKontrakt/{ku_id}")
    public String sletKundeKontrakt(@PathVariable ("ku_id") int ku_id, Kunde kunde, Model model){
        List<Kontrakt> liste = kontraktService.findKontrakterMedKundeId(ku_id);

        for (Kontrakt k: liste){
            kontraktService.sletKontrakt(k.getKo_id());
        }

        if (kundeService.sletKunde(ku_id)){
            return "redirect:/kundeliste";
        } else {
            return "home/generelFejl";
        }
    }

    //Håndtering af autocampere-sider
    @GetMapping("/autocampere")
    public String Autocamper(@ModelAttribute Autocamper autocamper, Model model){
        List<Autocamper> autocamperliste = autocamperService.listAutocampere();
        model.addAttribute("autocamperliste", autocamperliste);
        return "home/autocampere";
    }

    //Her udfylder bruger data til en ny autocamper
    @GetMapping("/opretAutocamper")
    public String opretAutocamper(){
        return "autocampere/opretAutocamper";
    }

    //Her oprettes et nyt Autocamper objekt med de indtastede data
    @PostMapping("/opretAutocamper")
    public String opretAutocamper(Model model, @ModelAttribute Autocamper autocamper){
        model.addAttribute("nyAutocamper", autocamper);
        return "autocampere/acceptAutocamper";
    }

    //Her vises en opsumering af de indtastede data, som skal accepteres af bruger før autocamperen bliver oprettet i DB
    @PostMapping("/acceptAutocamper")
    public String acceptAutocamper(@ModelAttribute Autocamper autocamper){
        if(autocamperService.tilfojAutocamper(autocamper)) {
            return "redirect:/autocampere";
        } else {
            return "home/opretFejl";
        }
    }

    //Samme fremgangsmåde som at slette kunder. Forklaret på linje 78
    @GetMapping("sletAutocamper/{a_id}")
    public String sletAutocamper(@PathVariable ("a_id") int a_id, Model model, Autocamper autocamper){
        model.addAttribute("autocamper", autocamper);

        if (autocamperService.erIKontrakt(a_id)){
            List<Kontrakt> ko_liste = kontraktService.findKontrakterMedAutocamperId(a_id);
            model.addAttribute("kontraktListe", ko_liste);
            return "home/autocamperSletFejl";
        } else if (autocamperService.sletAutocamper(a_id)){
            return "redirect:/autocampere";
        } else {
            return "home/generelFejl";
        }
    }

    //Samme fremgangsmåde som at slette kunder. Forklaret på linje 96
    @GetMapping("sletAutocamperKontrakt/{a_id}")
    public String sletAutocamperKontrakt(@PathVariable ("a_id") int a_id, Autocamper autocamper, Model model){
        List<Kontrakt> liste = kontraktService.findKontrakterMedAutocamperId(a_id);

        for (Kontrakt k: liste){
            kontraktService.sletKontrakt(k.getKo_id());
        }

        if(autocamperService.sletAutocamper(a_id)){
            return "redirect:/autocampere";
        } else {
            return "home/generelFejl";
        }
    }

    //Håndtering af tilbehør-sider
    @GetMapping("/tilbehor")
    public String tilbehor(@ModelAttribute Tilbehor tilbehor, Model model) {
        List<Tilbehor> tilbehorliste = tilbehorService.listTilbehor();
        model.addAttribute("tilbehorliste", tilbehorliste);
        return "home/tilbehor";
    }

    //Her oprettes nyt tilbehør i DB
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

    //Her starter brugeren med at oprette en ny kontrakt
    @GetMapping("/opretKontrakt")
    public String opretKontakt(@ModelAttribute Kunde kunde, Model model) {
        List<Kunde> kundeliste = kundeService.listKunder();
        model.addAttribute("kundeliste", kundeliste);
        return "kontrakter/opretKontrakt";
    }

    //Hvis brugeren ønsker at oprette en ny kunde til en kontrakt ledes de hertil
    @GetMapping("/opretKontraktKunde")
    public String opretKontraktKunde() {
        return "kontrakter/opretKontraktKunde";
    }

    //Her oprettes en ny kunde i DB der bindes til den kontrakt der er ved at blive oprettet
    @PostMapping("/opretKontraktKunde")
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

    //Her vælges start og slut dato for udlejningsperioden
    @GetMapping("/kontraktDato")
    public String kontraktDato(@ModelAttribute Kontrakt kontrakt, Model model) {
        model.addAttribute("nyKontrakt", kontrakt);
        return "kontrakter/kontraktDato";
    }

    //Her vælges en autocamper der skal tilknyttes kontrakten. Kun autocampere der er ledige i den valgte periode
    @GetMapping("/kontraktAuto")
    public String kontraktAuto(@ModelAttribute Kontrakt kontrakt, Model model) {
        model.addAttribute("nyKontrakt", kontrakt);
        List<Autocamper> autocamperliste = autocamperService.listFrieAutocampere(kontrakt.getStart_dato(), kontrakt.getSlut_dato());
        model.addAttribute("autocamperliste", autocamperliste);
        return "kontrakter/kontraktAuto";
    }

    //Side til at indtaste de sidste data i en kontrakt, tilbehør, kommentarer, etc
    @GetMapping("/kontraktData")
    public String kontraktData(@ModelAttribute Kontrakt kontrakt, Model model) {
        model.addAttribute("nyKontrakt", kontrakt);
        return "kontrakter/kontraktData";
    }

    //Viser en detaljeret visning af den valgte kontrakt, kunde og autocamper som skal accepteres af bruger
    @GetMapping("/acceptKontrakt")
    public String acceptKontrakt(@ModelAttribute Kontrakt kontrakt, Model model) {
        kontrakt.udregnTotal(autocamperService.findAutocamperMedId(kontrakt.getA_id()).getA_pris());
        model.addAttribute("nyKontrakt", kontrakt);
        model.addAttribute("valgtKunde", kundeService.findKundeMedId(kontrakt.getKu_id()));
        model.addAttribute("valgtAutocamper", autocamperService.findAutocamperMedId(kontrakt.getA_id()));
        return "kontrakter/acceptKontrakt";
    }

    //Opretter den kontrakt der er vist i DB
    @PostMapping("/acceptKontrakt")
    public String acceptKontrakt(@ModelAttribute Kontrakt kontrakt) {
        if (kontraktService.tilfojKontrakt(kontrakt)) {
            return "redirect:/kontrakter";
        } else {
            return "home/opretFejl";
        }
    }

    //Viser en liste over kontrakter i DB
    @GetMapping("/kontraktListe")
    public String kontraktListe(Model model){
        List<Kontrakt> kontraktliste = kontraktService.listKontrakter();
        for(Kontrakt k : kontraktliste) {
            k.setKo_kommentar(kundeService.findKundeMedId(k.getKo_id()).getEfternavn());
        }
        model.addAttribute("kontraktliste", kontraktliste);
        return "kontrakter/kontraktListe";
    }

    //Laver en detaljeret visning af en kontrakt, kunde og autocamper
    @GetMapping("/kontraktDetaljer/{ko_id}")
    public String kontraktDetaljer(@PathVariable ("ko_id") int ko_id, Model model) {
        model.addAttribute("nyKontrakt", kontraktService.findKontraktMedId(ko_id));
        model.addAttribute("valgtKunde", kundeService.findKundeMedId(kontraktService.findKontraktMedId(ko_id).getKu_id()));
        model.addAttribute("valgtAutocamper", autocamperService.findAutocamperMedId(kontraktService.findKontraktMedId(ko_id).getA_id()));
        return "kontrakter/kontraktDetaljer";
    }

    //Sletter en kontrakt fra DB
    @GetMapping("/sletKontrakt/{ko_id}")
    public String sletKontrakt(@PathVariable ("ko_id") int ko_id){
        if(kontraktService.sletKontrakt(ko_id)){
            return "redirect:/kontraktListe";
        } else{
            return "home/generelFejl";
        }
    }

    //Fejl sider
    @GetMapping("/opretFejl")
    public String opretFejl() {
        return "home/opretFejl";
    }

    @GetMapping("/generelFejl")
    public String generelFejl() {
        return "home/generelFejl";
    }
}
//Ansvarlig - ENA

package com.example.demo.Service;

import com.example.demo.Model.Autocamper;
import com.example.demo.Repository.AutocamperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutocamperService {
    @Autowired
    AutocamperRepository ar;

    public boolean erIKontrakt(int id){
        return ar.erIKontrakt(id);
    }

    public List<Autocamper> listAutocampere(){
        return ar.listAutocampere();
    }

    public Autocamper findAutocamperMedId(int id){
        return ar.findAutocamperMedId(id);
    }

    public Boolean tilfojAutocamper(Autocamper autocamper){
        return ar.tilfojAutocamper(autocamper);
    }

    public Boolean sletAutocamper(int id){
        return ar.sletAutocamper(id);
    }

    public List<Autocamper> listFrieAutocampere(String start, String slut) {
        return ar.listFrieAutocampere(start, slut);
    }
}

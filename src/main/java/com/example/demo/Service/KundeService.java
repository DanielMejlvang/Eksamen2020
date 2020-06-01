//Ansvarlig - DMR

package com.example.demo.Service;

import com.example.demo.Model.Kunde;
import com.example.demo.Repository.KundeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KundeService {
    @Autowired
    KundeRepository kr;

    //Metode til at undersøge om en kunde er tilknyttet en eller flere kontrakter
    public boolean erIKontrakt(int id){
        return kr.erIKontrakt(id);
    }

    //metode til at hente data fra database
    public List<Kunde> listKunder() {
        return kr.listKunder();
    }

    //metode til at finde specifik kunde efter id
    public Kunde findKundeMedId(int id) {
        return kr.findKundeMedId(id);
    }

    //metode til tilføje kunde til databasen
    public Boolean tilfojKunde(Kunde kunde) {
        return kr.tilfojKunde(kunde);
    }

    //metode til at slette en kunde fra databasen
    public Boolean sletKunde(int id) {
        return kr.sletKunde(id);
    }

    public int nyesteId() {
        return kr.nyesteId();
    }
}

package com.example.demo.Service;

import com.example.demo.Model.Kontrakt;
import com.example.demo.Repository.KontraktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KontraktService {
    @Autowired
    KontraktRepository ks;

    public List<Kontrakt> listKontrakter(){
        return ks.listKontrakter();
    }

    public Kontrakt findKontraktMedId(int id){
        return ks.findKontraktMedId(id);
    }

    public Boolean tilfojKontrakt(Kontrakt k){
        return ks.tilfojKontrakt(k);
    }

    public Boolean sletKontrakt(int id){
        return ks.sletKontrakt(id);
    }
}

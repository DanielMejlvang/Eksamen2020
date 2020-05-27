package com.example.demo.Service;

import com.example.demo.Model.Kontrakt;
import com.example.demo.Repository.KontraktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KontraktService {
    @Autowired
    KontraktRepository kr;

    public List<Kontrakt> listKontrakter(){
        return kr.listKontrakter();
    }

    public Kontrakt findKontraktMedId(int id){
        return kr.findKontraktMedId(id);
    }

    public Boolean tilfojKontrakt(Kontrakt k){
        return kr.tilfojKontrakt(k);
    }

    public Boolean sletKontrakt(int id){
        return kr.sletKontrakt(id);
    }

    public List<Kontrakt> findKontrakterMedKundeId(int id){
        return kr.findKontraktMedKundeId(id);
    }
}

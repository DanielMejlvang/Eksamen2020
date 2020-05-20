package com.example.demo.Service;

import com.example.demo.Model.Kontrakt;
import com.example.demo.Repository.KontraktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KontraktService {
    @Autowired
    KontraktRepository ks;

    public Boolean tilfojKontrakt(Kontrakt k){
        return ks.tilfojKontrakt(k);
    }
}

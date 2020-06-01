//Ansvarlig - ENA

package com.example.demo.Service;

import com.example.demo.Model.Tilbehor;
import com.example.demo.Repository.TilbehorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TilbehorService {
    @Autowired
    TilbehorRepository tr;

    public List<Tilbehor> listTilbehor() {
        return tr.listTilbehor();
    }

    public Tilbehor findTilbehorMedNavn(String navn) {
        return tr.findTilbehorMedNavn(navn);
    }

    public Boolean tilfojTilbehor(Tilbehor tilbehor) {
        return tr.tilfojTilbehor(tilbehor);
    }
    public Boolean sletTilbehor(String navn) {
        return tr.sletTilbehor(navn);
    }
}

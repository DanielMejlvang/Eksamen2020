package com.example.demo.Repository;

import com.example.demo.Model.Kontrakt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KontraktRepository {
    @Autowired
    JdbcTemplate template;

    public Boolean tilfojKontrakt(Kontrakt k){
        String sql = "INSERT INTO kontrakter (ku_id, a_id, start_dato, slut_dato, aflevering, afhentning, ko_kommentar, ko_pris, cykelstativ, barnesaede, sengetoj, picnicbord) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return template.update(sql, k.getKu_id(), k.getA_id(), k.getStart_dato(), k.getSlut_dato(), k.getAflevering(), k.getAfhentning(), k.getKo_kommentar(), k.getKo_pris(), k.isCykelstativ(), k.isBarnesaede(), k.isSengetoj(), k.isPicnicbord()) > 0;
    }
}

package com.example.demo.Repository;

import com.example.demo.Model.Kontrakt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KontraktRepository {
    @Autowired
    JdbcTemplate template;

    public List<Kontrakt> listKontrakter(){
        String sql = "SELECT * FROM kontrakter";
        RowMapper<Kontrakt> rm = new BeanPropertyRowMapper<>(Kontrakt.class);
        return template.query(sql, rm);
    }

    public Kontrakt findKontraktMedId(int id){
        String sql = "SELECT * FROM kontrakter WHERE ko_id = ?";
        RowMapper<Kontrakt> rm = new BeanPropertyRowMapper<>(Kontrakt.class);
        return template.queryForObject(sql, rm, id);
    }

    public List<Kontrakt> findKontrakterMedKundeId(int id){
        String sql = "SELECT * FROM kontrakter WHERE ku_id = ?";
        RowMapper<Kontrakt> rm = new BeanPropertyRowMapper<>(Kontrakt.class);
        return template.query(sql, rm, id);
    }

    public Boolean tilfojKontrakt(Kontrakt k){
        String sql = "INSERT INTO kontrakter (ku_id, a_id, start_dato, slut_dato, aflevering, afhentning, ko_kommentar, ko_pris, cykelstativ, barnesaede, sengetoj, picnicbord) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return template.update(sql, k.getKu_id(), k.getA_id(), k.getStart_dato(), k.getSlut_dato(), k.getAflevering(), k.getAfhentning(), k.getKo_kommentar(), k.getKo_pris(), k.isCykelstativ(), k.isBarnesaede(), k.isSengetoj(), k.isPicnicbord()) > 0;
    }

    public Boolean sletKontrakt(int id){
        String sql = "DELETE FROM kontrakter WHERE ko_id = ?";
        return template.update(sql, id) > 0;
    }
}

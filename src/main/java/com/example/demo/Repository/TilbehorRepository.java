package com.example.demo.Repository;

import com.example.demo.Model.Tilbehor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TilbehorRepository {
    @Autowired
    JdbcTemplate template;

    //opret List med Tilbehor objekter
    public List<Tilbehor> listTilbehor() {
        String sql = "SELECT * FROM tilbehor ORDER BY navn";
        RowMapper<Tilbehor> rm = new BeanPropertyRowMapper<>(Tilbehor.class);
        return template.query(sql, rm);
    }

    //find enkelt Tilbehor-objekt ud fra et navn
    public Tilbehor findTilbehorMedNavn(String navn) {
        //anvender prepared statement for at undgå SQL injection
        String sql = "SELECT * FROM tilbehor WHERE navn = ?";
        RowMapper<Tilbehor> rm = new BeanPropertyRowMapper<>(Tilbehor.class);
        return template.queryForObject(sql, rm, navn);
    }

    //indsæt et Tilbehor-objekts værdier ind i databasen
    public Boolean tilfojTilbehor(Tilbehor tilbehor) {
        String sql = "INSERT INTO tilbehor VALUES (?, ?)";
        return template.update(sql, tilbehor.getNavn(), tilbehor.getPris()) > 0;
    }

    //fjern en række fra tilbehor-tabellen
    public Boolean sletTilbehor(String navn) {
        String sql = "DELETE FROM tilbehor WHERE navn = ?";
        return template.update(sql, navn) > 0;
    }
}

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

    public List<Tilbehor> listTilbehor() {
        String sql = "SELECT * FROM tilbehor";
        RowMapper<Tilbehor> rm = new BeanPropertyRowMapper<>(Tilbehor.class);
        return template.query(sql, rm);
    }

    public Tilbehor findTilbehorMedNavn(String navn) {
        String sql = "SELECT * FROM tilbehor WHERE navn = ?";
        RowMapper<Tilbehor> rm = new BeanPropertyRowMapper<>(Tilbehor.class);
        return template.queryForObject(sql, rm, navn);
    }

    public Boolean tilfojTilbehor(Tilbehor tilbehor) {
        String sql = "INSERT INTO tilbehor VALUES (?, ?)";
        return template.update(sql, tilbehor.getNavn(), tilbehor.getPris()) > 0;
    }
    public Boolean sletTilbehor(String navn) {
        String sql = "DELETE FROM tilbehor WHERE navn = ?";
        return template.update(sql, navn) > 0;
    }
}

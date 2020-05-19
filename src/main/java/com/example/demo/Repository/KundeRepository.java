package com.example.demo.Repository;

import com.example.demo.Model.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository {
    @Autowired
    JdbcTemplate template;

    //metode til at hente data fra database
    public List<Kunde> listKunder() {
        String sql = "SELECT * FROM kunder";
        //rowmapper putter data direkte ind i et objekt af Kunde.class
        RowMapper<Kunde> rm = new BeanPropertyRowMapper<>(Kunde.class);
        return template.query(sql, rm);
    }

    //metode til at finde specifik kunde efter id
    public Kunde findKundeMedId(int id) {
        String sql = "SELECT * FROM kunder WHERE ku_id = ?";
        RowMapper<Kunde> rm = new BeanPropertyRowMapper<>(Kunde.class);
        return template.queryForObject(sql, rm, id);
    }

    //metode til tilføje kunde til databasen
    public Boolean tilfojKunde(Kunde kunde) {
        //tilføj data til postnumre, ignorer hvis allerede eksistere
        String sql = "INSERT IGNORE INTO postnumre VALUES (?, ?, ?)";
        template.update(sql, kunde.getPostnummer(), kunde.getBynavn(), kunde.getLand());

        //tilføj data i kunder-tabellen
        sql = "INSERT INTO kunder (fornavn, efternavn, telefon, email, korekort, gade, postnummer)" +
              "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return template.update(sql, kunde.getFornavn(), kunde.getEfternavn(), kunde.getTelefon(), kunde.getEmail(),
                        kunde.getKorekort(), kunde.getGade(), kunde.getPostnummer()) > 0;
    }

    //metode til at slette en kunde fra databasen
    public Boolean sletKunde(int id) {
        String sql = "DELETE FROM kunder WHERE ku_id = ?";
        //return boolean om sletning gik til
        return template.update(sql, id) > 0;
    }
}
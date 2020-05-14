package com.example.demo.Repository;

import com.example.demo.Model.Autocamper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AutocamperRepository {
    @Autowired
    JdbcTemplate template;

    //Metode der henter data om autocampere fra databasen
    public List<Autocamper> listAutocampere(){
        String sql = "SELECT * FROM autocampere";
        RowMapper<Autocamper> rm = new BeanPropertyRowMapper<>(Autocamper.class);
        return template.query(sql, rm);
    }

    //Finder en specifik autocamper ud fra dens ID
    public Autocamper findAutocamperMedId(int id){
        String sql = "SELECT * FROM autocampere WHERE a_id = ?";
        RowMapper<Autocamper> rm = new BeanPropertyRowMapper<>(Autocamper.class);
        return template.queryForObject(sql, rm, id);
    }

    //Tilføjer en autocamper til autocamper tabellen i databasen
    public Autocamper tilfojAutocamper(Autocamper a){
        String sql = "INSERT INTO autocampere (a_type, a_maerke, a_model, a_odometer, a_senge, a_nummerplade, a_pris) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, a.getA_type(),a.getA_maerke(),a.getA_model(),a.getA_odometer(), a.getA_senge(), a.getA_nummerplade(),a.getA_pris());
        return null;
    }

    //Sletter en autocamper fra databasen
    public Boolean sletAutocamper(int id){
        String sql = "DELETE FROM autocampere WHERE a_id = ?";
        //Returnerer sandt hvis sletningen gik igennem
        return template.update(sql, id) < 0;
    }
}

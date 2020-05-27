package com.example.demo.Repository;

import com.example.demo.Model.Autocamper;
import com.example.demo.Model.Kontrakt;
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

    public boolean erIKontrakt(int a_id){
        String sql = "SELECT * FROM kontrakter WHERE a_id = ?";
        RowMapper<Kontrakt> rm = new BeanPropertyRowMapper<>(Kontrakt.class);
        List<Kontrakt> liste = template.query(sql, rm, a_id);
        if(liste.size()==0){
            return false;
        } else {
            return true;
        }
    }

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
    public Boolean tilfojAutocamper(Autocamper a){
        String sql = "INSERT INTO autocampere (a_type, a_maerke, a_model, a_odometer, a_senge, a_nummerplade, a_pris) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return template.update(sql, a.getA_type(),a.getA_maerke(),a.getA_model(),a.getA_odometer(), a.getA_senge(), a.getA_nummerplade(),a.getA_pris()) > 0;
    }

    //Sletter en autocamper fra databasen
    public Boolean sletAutocamper(int id){
        String sql = "DELETE FROM autocampere WHERE a_id = ?";
        //Returnerer sandt hvis sletningen gik igennem
        return template.update(sql, id) > 0;
    }

    //find ledige autocampere
    public List<Autocamper> listFrieAutocampere(String startDato, String slutDato) {
        String sql = "SELECT * FROM autocampere WHERE a_id NOT IN" +
                     "(SELECT a_id FROM kontrakter LEFT JOIN autocampere USING (a_id)" +
                     "WHERE (? <= start_dato AND ? >= start_dato) OR" +
                     "(? <= slut_dato AND ? >= slut_dato) OR " +
                     "(? >= start_dato AND ? <= slut_dato))";
        RowMapper<Autocamper> rm = new BeanPropertyRowMapper<>(Autocamper.class);
        return template.query(sql, rm, startDato, slutDato, startDato, slutDato, startDato, slutDato);
    }
}

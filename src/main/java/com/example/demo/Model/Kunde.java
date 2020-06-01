//Ansvarlig - MT, DMR & ENA

package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kunde {
    @Id
    private int ku_id;
    private String fornavn;
    private String efternavn;
    private String telefon;
    private String email;
    private String korekort;
    private String gade;
    private String postnummer;
    private String bynavn;
    private String land;

    public Kunde() {
    }

    public Kunde(int ku_id, String fornavn, String efternavn, String telefon, String email, String korekort, String gade, String postnummer, String bynavn, String land) {
        this.ku_id = ku_id;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.telefon = telefon;
        this.email = email;
        this.korekort = korekort;
        this.gade = gade;
        this.postnummer = postnummer;
        this.bynavn = bynavn;
        this.land = land;
    }

    public int getKu_id() {
        return ku_id;
    }

    public void setKu_id(int ku_id) {
        this.ku_id = ku_id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKorekort() {
        return korekort;
    }

    public void setKorekort(String kørekort) {
        this.korekort = kørekort;
    }

    public String getGade() {
        return gade;
    }

    public void setGade(String gade) {
        this.gade = gade;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getBynavn() {
        return bynavn;
    }

    public void setBynavn(String bynavn) {
        this.bynavn = bynavn;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
}

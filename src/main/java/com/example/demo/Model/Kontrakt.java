package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kontrakt {
    @Id
    private int ko_id;
    private int ku_id;
    private int a_id;
    private String start_dato;
    private String slut_dato;
    private String aflevering;
    private String afhentning;
    private String ko_tilbehor;
    private String ko_kommentar;
    private double ko_pris;

    public Kontrakt() {
    }

    public Kontrakt(int ku_id) {
        this.ku_id = ku_id;
    }

    public Kontrakt(int ku_id, String start_dato, String slut_dato) {
        this.ku_id = ku_id;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
    }

    public Kontrakt(int ko_id, int ku_id, int a_id, String start_dato, String slut_dato, String aflevering, String afhentning, String ko_tilbehor, String ko_kommentar, double ko_pris) {
        this.ko_id = ko_id;
        this.ku_id = ku_id;
        this.a_id = a_id;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.aflevering = aflevering;
        this.afhentning = afhentning;
        this.ko_tilbehor = ko_tilbehor;
        this.ko_kommentar = ko_kommentar;
        this.ko_pris = ko_pris;
    }

    public int getKo_id() {
        return ko_id;
    }

    public void setKo_id(int ko_id) {
        this.ko_id = ko_id;
    }

    public int getKu_id() {
        return ku_id;
    }

    public void setKu_id(int ku_id) {
        this.ku_id = ku_id;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getStart_dato() {
        return start_dato;
    }

    public void setStart_dato(String start_dato) {
        this.start_dato = start_dato;
    }

    public String getSlut_dato() {
        return slut_dato;
    }

    public void setSlut_dato(String slut_dato) {
        this.slut_dato = slut_dato;
    }

    public String getAflevering() {
        return aflevering;
    }

    public void setAflevering(String aflevering) {
        this.aflevering = aflevering;
    }

    public String getAfhentning() {
        return afhentning;
    }

    public void setAfhentning(String afhentning) {
        this.afhentning = afhentning;
    }

    public String getKo_tilbehor() {
        return ko_tilbehor;
    }

    public void setKo_tilbehor(String ko_tilbehor) {
        this.ko_tilbehor = ko_tilbehor;
    }

    public String getKo_kommentar() {
        return ko_kommentar;
    }

    public void setKo_kommentar(String ko_kommentar) {
        this.ko_kommentar = ko_kommentar;
    }

    public double getKo_pris() {
        return ko_pris;
    }

    public void setKo_pris(double ko_pris) {
        this.ko_pris = ko_pris;
    }
}

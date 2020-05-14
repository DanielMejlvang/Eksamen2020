package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Autocamper {
    @Id
    private int a_id;
    private String a_type;
    private String a_maerke;
    private String a_model;
    private int a_odometer;
    private int a_senge;
    private String a_nummerplade;
    private int a_pris;

    public Autocamper() {
    }

    public Autocamper(int a_id, String a_type, String a_maerke, String model, int odometer, int senge, String nummerplade, int pris) {
        this.a_id = a_id;
        this.a_type = a_type;
        this.a_maerke = a_maerke;
        this.a_model = model;
        this.a_odometer = odometer;
        this.a_senge = senge;
        this.a_nummerplade = nummerplade;
        this.a_pris = pris;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_type() {
        return a_type;
    }

    public void setA_type(String type) {
        this.a_type = type;
    }

    public String getA_maerke() {
        return a_maerke;
    }

    public void setA_maerke(String maerke) {
        this.a_maerke = maerke;
    }

    public String getA_model() {
        return a_model;
    }

    public void setA_model(String model) {
        this.a_model = model;
    }

    public int getA_odometer() {
        return a_odometer;
    }

    public void setA_odometer(int odometer) {
        this.a_odometer = odometer;
    }

    public int getA_senge() {
        return a_senge;
    }

    public void setA_senge(int senge) {
        this.a_senge = senge;
    }

    public String getA_nummerplade() {
        return a_nummerplade;
    }

    public void setA_nummerplade(String nummerplade) {
        this.a_nummerplade = nummerplade;
    }

    public int getA_pris() {
        return a_pris;
    }

    public void setA_pris(int pris) {
        this.a_pris = pris;
    }
}

package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Autocamper {
    @Id
    private int a_id;
    private String type;
    private String maerke;
    private String model;
    private int odometer;
    private int senge;
    private String nummerplade;
    private int pris;

    public Autocamper() {
    }

    public Autocamper(int a_id, String type, String maerke, String model, int odometer, int senge, String nummerplade, int pris) {
        this.a_id = a_id;
        this.type = type;
        this.maerke = maerke;
        this.model = model;
        this.odometer = odometer;
        this.senge = senge;
        this.nummerplade = nummerplade;
        this.pris = pris;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaerke() {
        return maerke;
    }

    public void setMaerke(String maerke) {
        this.maerke = maerke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getSenge() {
        return senge;
    }

    public void setSenge(int senge) {
        this.senge = senge;
    }

    public String getNummerplade() {
        return nummerplade;
    }

    public void setNummerplade(String nummerplade) {
        this.nummerplade = nummerplade;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }
}

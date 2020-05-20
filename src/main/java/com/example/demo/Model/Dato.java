package com.example.demo.Model;

import javax.persistence.Entity;

//@Entity
public class Dato {
    private String start_dato;
    private String slut_dato;

    public Dato() {
    }

    public Dato(String start_dato, String slut_dato) {
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
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
}

package com.example.demo.Model;

import java.util.ArrayList;

public class Data {
    private int ku_id;
    private String start_dato;
    private String slut_dato;
    private int a_id;

    public Data() {
    }

    public Data(int ku_id, String start_dato, String slut_dato, int a_id) {
        this.ku_id = ku_id;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.a_id = a_id;
    }

    public int getKu_id() {
        return ku_id;
    }

    public void setKu_id(int ku_id) {
        this.ku_id = ku_id;
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

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }
}

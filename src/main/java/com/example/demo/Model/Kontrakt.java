package com.example.demo.Model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    private String ko_kommentar;
    private double ko_pris;
    private boolean cykelstativ;
    private boolean barnesaede;
    private boolean sengetoj;
    private boolean picnicbord;

    public Kontrakt() {
    }

    public Kontrakt(int ko_id, int ku_id, int a_id, String start_dato, String slut_dato, String aflevering, String afhentning, String ko_kommentar, double ko_pris, boolean cykelstativ, boolean barnesaede, boolean sengetoj, boolean picnicbord) {
        this.ko_id = ko_id;
        this.ku_id = ku_id;
        this.a_id = a_id;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.aflevering = aflevering;
        this.afhentning = afhentning;
        this.ko_kommentar = ko_kommentar;
        this.ko_pris = ko_pris;
        this.cykelstativ = cykelstativ;
        this.barnesaede = barnesaede;
        this.sengetoj = sengetoj;
        this.picnicbord = picnicbord;
    }

    public void udregnTotal(double a_pris) {
        ko_pris *= 0.7;
        ko_pris += udregnPrisForTilbehor();
        ko_pris += autocamperPris(a_pris);
    }

    public double udregnPrisForTilbehor(){
        double sum = 0;

        if (cykelstativ){
            sum += 9.99;
        }
        if (barnesaede){
            sum += 2.9;
        }
        if(sengetoj){
            sum += 5;
        }
        if(picnicbord){
            sum += 8.99;
        }

        return (sum * this.daysBetween());
    }

    public double autocamperPris(double a_pris) {
        return a_pris * daysBetween();
    }

    public double daysBetween(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long diff = 0;
        try{
            Date startDato = sdf.parse(start_dato);
            Date slutDato = sdf.parse(slut_dato);
            long diffInMillies = Math.abs(slutDato.getTime() - startDato.getTime());
            diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        } catch (ParseException e){
            System.out.println("Couldn't parse");
        }
        return (double) diff;
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

    public boolean isCykelstativ() {
        return cykelstativ;
    }

    public void setCykelstativ(boolean cykelstativ) {
        this.cykelstativ = cykelstativ;
    }

    public boolean isBarnesaede() {
        return barnesaede;
    }

    public void setBarnesaede(boolean barnesaede) {
        this.barnesaede = barnesaede;
    }

    public boolean isSengetoj() {
        return sengetoj;
    }

    public void setSengetoj(boolean sengetoj) {
        this.sengetoj = sengetoj;
    }

    public boolean isPicnicbord() {
        return picnicbord;
    }

    public void setPicnicbord(boolean picnicbord) {
        this.picnicbord = picnicbord;
    }
}

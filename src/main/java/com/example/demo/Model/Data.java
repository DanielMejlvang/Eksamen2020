package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
@Entity
public class Data {
    @Id
    private String ku_id;

    public Data() {
    }

    public Data(String ku_id) {
        this.ku_id = ku_id;
    }

    public String getKu_id() {
        return ku_id;
    }

    public void setKu_id(String ku_id) {
        this.ku_id = ku_id;
    }
}

package com.example.kyrsovaia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Zakaz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String number;
    private String email;
    private String data_postavki_zakaza;

    public Zakaz() {
    }

    public Zakaz(String name, String number, String email, String data_postavki_zakaza) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.data_postavki_zakaza = data_postavki_zakaza;
    }

    public String getData_postavki_zakaza() {
        return data_postavki_zakaza;
    }

    public void setData_postavki_zakaza(String data_postavki_zakaza) {
        this.data_postavki_zakaza = data_postavki_zakaza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

}

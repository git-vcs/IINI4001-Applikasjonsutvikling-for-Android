package com.assignment03.ver2;

public class Person {
    private String navn;
    private String dato;

    Person(String navn, String dato){
        this.dato=dato;
        this.navn=navn;
    }


    public String getNavn() {
        return navn;
    }

    public String getDato() {
        return dato;
    }

    @Override
    public String toString() {
        return navn + " " +dato;
    }
}


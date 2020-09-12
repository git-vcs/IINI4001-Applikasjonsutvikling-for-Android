package com.example.assignment03;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Person  {
    private String navn;
    private String fødesledato;
    Person(String navn, String fødesledato){
        this.navn=navn;
        this.fødesledato=fødesledato;
    }

    public String getFødesledato() {
        return fødesledato;
    }

    public String getNavn() {
        return navn;
    }


    private List<Person> personList;

    private void dummyData(){
        personList=new ArrayList<>();
        personList.add(new Person("Person nr 1","01.01.01"));
        personList.add(new Person("Person nr 2","02.02.02"));

    }

}


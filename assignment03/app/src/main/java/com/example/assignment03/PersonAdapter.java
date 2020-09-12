package com.example.assignment03;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonView> {

    List<Person> personList;
    PersonAdapter(List<Person> personList){
        this.personList=personList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView rView){
        super.onAttachedToRecyclerView(rView);
    }



    //todo
    // overide onCreateViewHolder fra personView hvis nødvendig


    @Override
    public void onBindViewGolder(PersonView pV,int i){
    }


    public static class PersonView extends RecyclerView.ViewHolder{
        TextView navn;
        TextView datao;

        PersonView(View v){
            super(v);

            //todo
            //henter data for navn og dato fra xml

        }
    }


    public void onCreateViewGolder(View v, int i){
        View view= LayoutInflater.from(v.getContext()).inflate();
        PersonView pv=new PersonView(v);

    }



}

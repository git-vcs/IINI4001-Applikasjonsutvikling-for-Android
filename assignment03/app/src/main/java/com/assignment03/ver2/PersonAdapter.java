package com.assignment03.ver2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class PersonAdapter extends ArrayAdapter<Person> {
    private Context context;
    private TextView textViewName;
    private TextView textViewDate;
    private int resource;

    public PersonAdapter(@NonNull Context context, int resource, ArrayList<Person> personer) {
        super(context, resource, personer);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //henter View og legger de til i listView
        String oneName=getItem(position).getNavn();
        String oneDato=getItem(position).getDato();
        Person onePerson=new Person(oneName,oneDato);
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);
        textViewName=(TextView)convertView.findViewById(R.id.navn);
        textViewDate=(TextView)convertView.findViewById(R.id.dato);
        textViewName.setText(oneName);
        textViewDate.setText(oneDato);
        convertView.setTag(this);
        return convertView;
    }
}

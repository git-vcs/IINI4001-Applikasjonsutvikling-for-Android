package com.assignment0402;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//et fragment som levere en kanpp tilbake
public class ButtonFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        Button b=new Button(getActivity());
        b.setText("fragmentTEstButtom");
        return b;
    }

}


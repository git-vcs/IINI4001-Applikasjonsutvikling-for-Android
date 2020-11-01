package com.assignment08_sudoku;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;

import androidx.annotation.Nullable;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
     
}

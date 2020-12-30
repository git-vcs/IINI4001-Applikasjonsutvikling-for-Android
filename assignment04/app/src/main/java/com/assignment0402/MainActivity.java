package com.assignment0402;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import java.util.Objects;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(this.getClass().getSimpleName(), "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }





}

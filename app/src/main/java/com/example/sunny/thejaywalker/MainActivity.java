package com.example.sunny.thejaywalker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Layout inflater  
        LayoutInflater l=getLayoutInflater();
        View v=l.inflate(R.layout.activity_main, null);
    }
}

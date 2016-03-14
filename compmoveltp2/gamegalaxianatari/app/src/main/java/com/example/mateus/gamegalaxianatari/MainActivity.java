package com.example.mateus.gamegalaxianatari;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //TouchScreenView view = new TouchScreenView(this);
        //setContentView(view);
        GameGalaxianView gpv = new GameGalaxianView(this);
        setContentView(gpv);

    }




}

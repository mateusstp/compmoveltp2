package com.example.mateus.gamegalaxianatari;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton start;
    ImageButton exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setContentView(R.layout.activity_main);
        //TouchScreenView view = new TouchScreenView(this);
        //setContentView(view);
        Intent it = getIntent();
        if (it != null) {
            String msg = it.getStringExtra("msg");
            if (msg != null) {
               // Log.i("TESTE", "Mensagem : " + msg);
            }
        }
        start = (ImageButton)findViewById(R.id.imageButtonStart);
        exit = (ImageButton)findViewById(R.id.imageButtonExit);
        start.setOnClickListener(this);
        exit.setOnClickListener(this);
        //GameGalaxianView gpv = new GameGalaxianView(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageButtonStart){
            Intent intent = new Intent(this, MainActivity.class);

            String message = "teste";
            intent.putExtra("dado",message);
            startActivity(intent);
        }else if(v.getId() == R.id.imageButtonExit){
            finish();
        }
    }
}

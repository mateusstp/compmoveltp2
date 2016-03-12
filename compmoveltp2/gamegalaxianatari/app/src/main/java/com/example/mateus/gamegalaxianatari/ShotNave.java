package com.example.mateus.gamegalaxianatari;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by mateus on 10/02/16.
 */

public class ShotNave extends View{
    private static final String CATEGORIA = "AppNum53";
    private int xShotNave;
    private int yShotNave;



    private Drawable shotNave;
    private int shotNaveWidth_x;
    private int shotNaveHeight_y;



    public ShotNave(Context context, int sx, int sy) {
        super(context, null);
        xShotNave =sx;
        yShotNave =sy;
        setShotNaveDrawable(context.getResources().getDrawable(R.drawable.shotnave));

        //Recupera dimensoes da imagem
        setShotNaveWidth_x(getShotNaveDrawable().getIntrinsicWidth());
        setShotNaveHeight_y(getShotNaveDrawable().getIntrinsicHeight());

    }

    public static String getCATEGORIA() {
        return CATEGORIA;
    }

    public void drawShotNave(Canvas canvas){
        getShotNaveDrawable().setBounds(getxShotNave(), getyShotNave(), getxShotNave() + getShotNaveWidth_x(), getyShotNave() + getShotNaveHeight_y());
        getShotNaveDrawable().draw(canvas);
    }

    public void moveShot(){
        decrementYShotNave();
    }

      public void decrementYShotNave(){
        this.yShotNave--;
    }


    public void rectangleToString(){
        Log.i(getCATEGORIA(), "drawBall xRec : yRec " + getxShotNave() + " : " + getyShotNave());
    }

    public int getxShotNave() {
        return xShotNave;
    }

    public void setxShotNave(int xShotNave) {
        this.xShotNave = xShotNave;
    }

    public int getyShotNave() {
        return yShotNave;
    }

    public void setyShotNave(int yShotNave) {
        this.yShotNave = yShotNave;
    }

    public Drawable getShotNaveDrawable() {
        return shotNave;
    }

    public void setShotNaveDrawable(Drawable shotNave) {
        this.shotNave = shotNave;
    }

    public int getShotNaveWidth_x() {
        return shotNaveWidth_x;
    }

    public void setShotNaveWidth_x(int shotNaveWidth_x) {
        this.shotNaveWidth_x = shotNaveWidth_x;
    }

    public int getShotNaveHeight_y() {
        return shotNaveHeight_y;
    }

    public void setShotNaveHeight_y(int shotNaveHeight_y) {
        this.shotNaveHeight_y = shotNaveHeight_y;
    }
}



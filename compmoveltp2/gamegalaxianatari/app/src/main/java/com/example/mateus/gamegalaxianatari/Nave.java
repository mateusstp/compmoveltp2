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

public class Nave extends View {
    private static final String CATEGORIA = "AppNum53";

    private int xNave;
    private int yNave;
    private Drawable nave;
    private int naveWidth_x;
    private int naveHeight_y;
    private ArrayList<ShotNave> arrayShotsNave;
    /**-1 -> left
     * 0  -> stoped
     * 1  -> right **/
    private int direction;



    public Nave(Context context) {
        super(context, null);

        setNaveDrawable(context.getResources().getDrawable(R.drawable.nave));
        setArrayShotsNave(new ArrayList<ShotNave>());
        //Recupera dimensoes da imagem
        setNaveWidth_x(getNaveDrawable().getIntrinsicWidth()-10);
        setNaveHeight_y(getNaveDrawable().getIntrinsicHeight()-10);
        setdirection(1);
    }

    public static String getCATEGORIA() {
        return CATEGORIA;
    }

    public void drawNave(Canvas canvas){
        getNaveDrawable().setBounds(getxNave(), getyNave(), getxNave() + getNaveWidth_x(), getyNave() + getNaveHeight_y());
        getNaveDrawable().draw(canvas);

    }

    public void rectangleToString(){
        Log.i(getCATEGORIA(), "drawBall xRec : yRec " + getxNave() + " : " + getyNave());
    }




    /**-1 -> left
     * 0  -> stoped
     * 1  -> right **/
    public void setdirection(int d ) {
        if(d != 1 && d != -1 ){
            this.direction=0;
         }
        this.direction=d;
    }

    public int getdirection() {return  this.direction; }


    public int getxNave() {
        return xNave;
    }

    public void setxNave(int xNave) {
        this.xNave = xNave;
    }

    public int getyNave() {
        return yNave;
    }

    public void setyNave(int yNave) {
        this.yNave = yNave;
    }

    public Drawable getNaveDrawable() {
        return nave;
    }

    public void setNaveDrawable(Drawable nave) {
        this.nave = nave;
    }

    public int getNaveWidth_x() {
        return naveWidth_x;
    }

    public void setNaveWidth_x(int naveWidth_x) {
        this.naveWidth_x = naveWidth_x;
    }

    public int getNaveHeight_y() {
        return naveHeight_y;
    }

    public void setNaveHeight_y(int naveHeight_y) {
        this.naveHeight_y = naveHeight_y;
    }

    public ArrayList<ShotNave> getArrayShotsNave() {
        return arrayShotsNave;
    }

    public void setArrayShotsNave(ArrayList<ShotNave> arryshotsnava) {
        this.arrayShotsNave = arryshotsnava;
    }

    public void moveShots(){
        if(arrayShotsNave != null){
            for(int i =0; i < arrayShotsNave.size();i++){
                arrayShotsNave.get(i).moveShot();
            }
        }

    }

    public void drawShots(Canvas canvas){
        if(arrayShotsNave != null){
            for(int i =0; i < arrayShotsNave.size();i++){
                arrayShotsNave.get(i).drawShotNave(canvas);
            }
        }

    }


}



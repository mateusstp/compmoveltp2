package com.example.mateus.gamegalaxianatari;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

/**
 * Created by mateus on 10/02/16.
 */

public class ShotEnemy extends View{
    private static final String CATEGORIA = "AppNum53";
    private int xShotEnemy;
    private int yShotEnemy;



    private Drawable shotEnemy;
    private int shotEnemyWidth_x;
    private int shotEnemyHeight_y;



    public ShotEnemy(Context context, int sx, int sy) {
        super(context, null);
        xShotEnemy =sx;
        yShotEnemy =sy;
        setShotEnemyDrawable(context.getResources().getDrawable(R.drawable.shotalien));

        //Recupera dimensoes da imagem
        setShotEnemyWidth_x(getShotEnemyDrawable().getIntrinsicWidth());
        setShotEnemyHeight_y(getShotEnemyDrawable().getIntrinsicHeight());

    }

    public static String getCATEGORIA() {
        return CATEGORIA;
    }

    public void drawShotEnemy(Canvas canvas){
        getShotEnemyDrawable().setBounds(getxShotEnemy(), getyShotEnemy(), getxShotEnemy() + getShotEnemyWidth_x(), getyShotEnemy() + getShotEnemyHeight_y());
        getShotEnemyDrawable().draw(canvas);
    }

    public void moveShot(){
        incrementYShotEnemy();
    }

      public void incrementYShotEnemy(){
        this.yShotEnemy++;
    }


    public void rectangleToString(){
        Log.i(getCATEGORIA(), "drawBall xRec : yRec " + getxShotEnemy() + " : " + getyShotEnemy());
    }

    public int getxShotEnemy() {
        return xShotEnemy;
    }

    public void setxShotEnemy(int xShotEnemy) {
        this.xShotEnemy = xShotEnemy;
    }

    public int getyShotEnemy() {
        return yShotEnemy;
    }

    public void setyShotEnemy(int yShotEnemy) {
        this.yShotEnemy = yShotEnemy;
    }

    public Drawable getShotEnemyDrawable() {
        return shotEnemy;
    }

    public void setShotEnemyDrawable(Drawable shotEnemy) {
        this.shotEnemy = shotEnemy;
    }

    public int getShotEnemyWidth_x() {
        return shotEnemyWidth_x;
    }

    public void setShotEnemyWidth_x(int shotEnemyWidth_x) {
        this.shotEnemyWidth_x = shotEnemyWidth_x;
    }

    public int getShotEnemyHeight_y() {
        return shotEnemyHeight_y;
    }

    public void setShotEnemyHeight_y(int shotEnemyHeight_y) {
        this.shotEnemyHeight_y = shotEnemyHeight_y;
    }
}



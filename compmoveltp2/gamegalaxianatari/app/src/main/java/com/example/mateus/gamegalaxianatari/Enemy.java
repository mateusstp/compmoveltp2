package com.example.mateus.gamegalaxianatari;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;


/**
 * Created by mateus on 10/02/16.
 */

public class Enemy extends View{

    private static final String CATEGORIA = "AppNum53";
   // private Drawable enemy[] = {(Drawable)findViewById(R.drawable.aliens1),(Drawable)findViewById(R.drawable.aliens1)};
    private int startPointX;
    private int startPointY;
    private int widthScreen;
    private int heightScreen;
    private int x;
    private int y;
    private Drawable ball;
    private int Width_x;
    private int Height_y;
    /**{1,1}   -> +x,+y
     * {-1,-1} -> -x,-y
     * {-1,1}  -> -x,+y
     * {1,-1}  -> +x,-y**/
    private int[] direction;



    public Enemy(Context context) {

        super(context, null);
        setBall(context.getResources().getDrawable(R.drawable.bola));

        //Recupera dimensoes da imagem
        setBallWidth_x(getBall().getIntrinsicWidth());
        setBallHeight_y(getBall().getIntrinsicHeight());
        direction=new int[2];
        direction[0] =  1;
        direction[1] = -1;
    }

    public void drawBall(Canvas canvas){
        getBall().setBounds(x, y, x + Width_x, y + Height_y);
        getBall().draw(canvas);
    }

    public void ballToString(){
        Log.i(CATEGORIA,"drawBall xBall : yBall "+x+ " : "+ y);
    }


    public void incrementX(){
        this.x++;
    }

    public void decrementX(){
        this.x--;
    }

    public void incrementY(){
        this.y++;
    }

    public void decrementY(){
        this.y--;
    }
    public int getxBall() {
        return x;
    }

    public void setxBall(int xBall) {
        this.x = xBall;
    }

    public int getyBall() {
        return y;
    }

    public void setyBall(int yBall) {
        this.y = yBall;
    }

    public int getBallWidth_x() {
        return Width_x;
    }

    public void setBallWidth_x(int ballWidth_x) {
        this.Width_x = ballWidth_x;
    }

    public int getBallHeight_y() {
        return Height_y;
    }

    public void setBallHeight_y(int ballHeight_y) {
        this.Height_y = ballHeight_y;
    }

    public int getdirectionX() {
        return direction[0];
    }
    public int getdirectionY() {
        return direction[1];
    }
    /**{1,1}   -> +x,+y
     * {-1,-1} -> -x,-y
     *
     * {-1,1}  -> -x,+y
     * {1,-1}  -> +x,-y**/
    public void setdirection(int xUnitario,int yUnitario ) {
        if(xUnitario != 1 && xUnitario != -1 || yUnitario != 1 && yUnitario != -1 ){
            return;
        }
        this.direction[0] = xUnitario;
        this.direction[1] = yUnitario;
    }

    public void move(){
        //incrementX();
        // incrementY();
        // incrementX();

        //xBall= 250+(int) (300 * Math.cos((Math.PI/4)*yBall));
        if(direction[0]==1 && direction[1]==1){
            incrementX();
            incrementY();
        }else if(direction[0]==-1 && direction[1]==-1){
            decrementX();
            decrementY();
        }else if(direction[0]==-1 && direction[1]==1){
            decrementX();
            incrementY();
        }else if(direction[0]==1 && direction[1]==-1){
            incrementX();
            decrementY();
        }
    }



    public Drawable getBall() {
        return ball;
    }

    public void setBall(Drawable ball) {
        this.ball = ball;
    }
}



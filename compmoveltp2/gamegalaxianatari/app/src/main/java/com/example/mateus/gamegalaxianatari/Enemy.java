package com.example.mateus.gamegalaxianatari;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;


/**
 * Created by mateus on 10/02/16.
 */

public class Enemy extends View{
    private static final String CATEGORIA = "AppNum53";
    private int widthScreen;
    private int heightScreen;
    private int x;
    private int y;
    private Drawable enemy;
    private int Width_x;
    private int Height_y;
    private int id;
    private int tipo;
    /**{1,1}   -> +x,+y
     * {-1,-1} -> -x,-y
     * {-1,1}  -> -x,+y
     * {1,-1}  -> +x,-y**/
    private int[] direction;



    public Enemy(Context context, int id ) {
        super(context, null);
        Drawable enemy[] = {context.getResources().getDrawable(R.drawable.aliens1),context.getResources().getDrawable(R.drawable.aliens2),context.getResources().getDrawable(R.drawable.aliens3),context.getResources().getDrawable(R.drawable.aliens4)};
        setEnemy(enemy[id/5]);
        tipo=id/5;
        this.id=id;
        //Recupera dimensoes da imagem
        setEnemyWidth_x(getEnemy().getIntrinsicWidth());
        setEnemyHeight_y(getEnemy().getIntrinsicHeight());
        direction=new int[2];
        direction[0] =  1;
        direction[1] = -1;
    }

    public void drawEnemy(Canvas canvas){
        getEnemy().setBounds(x, y, x + Width_x, y + Height_y);
        getEnemy().draw(canvas);
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
    public int getxEnemy() {
        return x;
    }

    public void setxEnemy(int xEnemy) {
        this.x = xEnemy/8+(xEnemy*((id%5))/6);
    }

    public int getyEnemy() {
        return y;
    }

    public void setyEnemy(int yEnemy) {
        this.y = (yEnemy*(id/5)/10);
    }

    public int getEnemyWidth_x() {
        return Width_x;
    }

    public void setEnemyWidth_x(int EnemyWidth_x) {
        this.Width_x = EnemyWidth_x;
    }

    public int getEnemyHeight_y() {
        return Height_y;
    }

    public void setEnemyHeight_y(int EnemyHeight_y) {
        this.Height_y = EnemyHeight_y;
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



    public Drawable getEnemy() {
        return enemy;
    }

    public void setEnemy(Drawable en) {
        this.enemy = en;
    }
}



package com.example.mateus.gamegalaxianatari;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by mateus on 10/02/16.
 */

public class Enemy extends View{
    private static final String CATEGORIA = "AppNum53";
    private int widthScreen;
    private int heightScreen;
    private int x;
    private boolean position=true;
    private int y;
    private Drawable enemy;
    private int Width_x;
    private int Height_y;
    private int id;
    private int tipo;
    private boolean alive=true;



    public boolean isAlive() {
        return alive;
    }

    public void dead() {
        this.alive = false;
        x=-1;
        y=-1;
        Width_x=0;
        Height_y=0;
    }

    /**{1,1}   -> +x,+y
     * {-1,-1} -> -x,-y
     * {-1,1}  -> -x,+y

     * {1,-1}  -> +x,-y**/
    private int direction;
private ArrayList<ShotEnemy> arrayShotsEnemy;

    public ArrayList<ShotEnemy> getArrayShotsEnemy() {
        return arrayShotsEnemy;
    }

    public void setArrayShotsEnemy(ArrayList<ShotEnemy> arrayShotsEnemy) {
        this.arrayShotsEnemy = arrayShotsEnemy;
    }




    public Enemy(Context context, int id ) {
        super(context, null);
        Drawable enemy[] = {context.getResources().getDrawable(R.drawable.aliens1),context.getResources().getDrawable(R.drawable.aliens2),context.getResources().getDrawable(R.drawable.aliens3),context.getResources().getDrawable(R.drawable.aliens4)};
        setEnemy(enemy[id/5]);
        tipo=id/5;
        this.id=id;
        //Recupera dimensoes da imagem
        setEnemyWidth_x(getEnemy().getIntrinsicWidth());
        setEnemyHeight_y(getEnemy().getIntrinsicHeight());
        Random rand = new Random();
        direction = rand.nextInt()%2;
        arrayShotsEnemy=new ArrayList<>();
    }

    public void drawEnemy(Canvas canvas){
        getEnemy().setBounds(x, y, x + Width_x, y + Height_y);
        getEnemy().draw(canvas);
    }

  public void moveNormallyRight()
  {
      incrementX();
  }
    public void moveNormallyLeft()
    {
        decrementX();
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
        widthScreen=xEnemy;
    }

    public int getyEnemy() {
        return y;
    }

    public void setyEnemy(int yEnemy) {
        this.y = (yEnemy*(id/5)/10);
        heightScreen=yEnemy;
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

    public int getdirection() {
        return direction;
    }
    /**-----------{1,1}   -> +x,+y
     * {-1,-1} -> -x,-y
     *
     *-------- {-1,1}  -> -x,+y
     * {1,-1}  -> +x,-y**/

   /* public void setdirection(int xUnitario,int yUnitario ) {
        if(xUnitario != 1 && xUnitario != -1 || yUnitario != 1 && yUnitario != -1 ){
            return;
        }
        this.direction[0] = xUnitario;
        this.direction[1] = yUnitario;
    }*/

    public void move(){
       // Log.d("testes", "antes"+x+";"+y);

        //if(x>=widthScreen && direction==0)
        if(x > widthScreen && y > heightScreen)
        {
            x=0;
            y=0;
        }

        if(x < 0 && y > heightScreen)
        {
            x=widthScreen;
            y=0;
        }
       /* if(x<=0 && direction==1)
        {
            x=widthScreen;
        }



        if(y>=heightScreen)
        {
            y=0;
        }*/

        if(direction==0)
        {
            incrementX();
            incrementY();
        }
        else
        {
            decrementX();
            incrementY();
        }
      //  Log.d("testes", "depois"+x+";"+y);

    }

    public void setAtPosition()
    {
       this.position=false;
    }

    public boolean atPosition()
    {
        return position;
    }

    public Drawable getEnemy() {
        return enemy;
    }

    public void setEnemy(Drawable en) {
        this.enemy = en;
    }

    public void moveShots(){
        if(arrayShotsEnemy != null){
            for(int i =0; i < arrayShotsEnemy.size();i++){
                arrayShotsEnemy.get(i).moveShot();
            }
        }

    }

    public void drawShots(Canvas canvas){
        if(arrayShotsEnemy != null){
            for(int i =0; i < arrayShotsEnemy.size();i++){
                arrayShotsEnemy.get(i).drawShotEnemy(canvas);
            }
        }

    }

    public int nextX(){
        if(direction==0)
        {

                return x+1;

        }
        else
        {
                return x-1;

        }
    }

    public int nextY(){

                return y+1;
    }
}



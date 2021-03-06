package com.example.mateus.gamegalaxianatari;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.Random;

//import java.util.logging.Handler;


/**
 * Created by mateus on 10/02/16.
 */

public class ControlGameGalaxian extends Thread {

    private static final String CATEGORIA = "AppNum53";

    Nave nave;
    ArrayList<Enemy> enemies;
    private int screenWidth_x, screenHeight_y;
    private boolean enemyActivated=false;
    private int boundtop;
    private int bounddown;
    private int boundleft;
    private int boundright;
    private int atual=-1;
    private Context ctx;
    private int ntiro=0;
    int gameover=1;
    private Handler handler;

    public ControlGameGalaxian(Handler h, int screenWidth_x, int screenHeight_y, Nave r, ArrayList<Enemy> en, Context ct) {
        ctx=ct;
        this.handler = h;

        this.screenWidth_x = screenWidth_x;
        this.screenHeight_y = screenHeight_y;


        this.nave = r;

        this.enemies=en;

        boundtop = 0;
        bounddown = screenHeight_y;
        boundleft = 0;
        boundright = screenWidth_x;
    }

    boolean right=true;
    public void run() {
        for (;;) {
            try {
                Message message = new Message();
                //defino um codigo para controle.
              //  message.what = 1;
                //delay
                Thread.sleep(6);
              //  collisionShotEnemyNave();
                //defino um codigo para controle.
                message.what = gameover;
                collisionShotNaveEnemy();
                nave.moveShots();
                if(atual==-1)
                {
                    int next;
                    Random rand = new Random();
                    next = (rand.nextInt()*1000)%21;
                    if( enemies.get(next)!=null) {
                        enemies.get(next).setAtPosition();
                        enemies.get(next).move();
                        atual=next;
                    }

                }
                else
                {
                    if( enemies.get(atual)!=null) {
                        if (ntiro<=0) {
                            ShotEnemy ns = new ShotEnemy(ctx, enemies.get(atual).nextX() + enemies.get(atual).getEnemyWidth_x() / 2 + 10, enemies.get(atual).nextY() + enemies.get(atual).getEnemyHeight_y() / 2 + 15);
                            enemies.get(atual).getArrayShotsEnemy().add(ns);
                            Random rand = new Random();
                            ntiro = (rand.nextInt()*10000)%300;
                        }
                        enemies.get(atual).setAtPosition();
                        enemies.get(atual).move();
                        enemies.get(atual).moveShots();
                        ntiro--;
                    }
                    else
                        atual=-1;
                }

                for(int i=0; i<enemies.size();i++) {
                    if((enemies.get(i).getxEnemy()+enemies.get(i).getEnemyWidth_x())==screenWidth_x)
                    {
                        right=false;
                        break;
                    }

                    if (enemies.get(i).getxEnemy() == 0) {
                        right = true;
                        break;
                    }
                }
                for(int x=0; x<enemies.size();x++)
                {
                    if(enemies.get(x)!=null && enemies.get(x).atPosition()==true) {
                        if (right)
                            enemies.get(x).moveNormallyRight();
                        else
                            enemies.get(x).moveNormallyLeft();
                    }
                }
                handler.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void collisionShotNaveEnemy() {

        for (int ishot = 0; ishot < nave.getArrayShotsNave().size(); ishot++) {
            for (int ien = 0; ien < enemies.size(); ien++) {
                boolean collision = enemies.get(ien).getEnemy().copyBounds().contains(nave.getArrayShotsNave().get(ishot).getxShotNave(), nave.getArrayShotsNave().get(ishot).getyShotNave());
                if (collision) {
                    enemies.remove(ien);
                    nave.getArrayShotsNave().remove(ishot);
                }

            }
        }
    }

    /*private void collisionShotEnemyNave() {
        for (int ien = 0; ien < enemies.size(); ien++) {
            for (int ishoten = 0; ishoten < enemies.get(ien).getArrayShotsEnemy().size(); ishoten++) {
                boolean collision = nave.getNaveDrawable().copyBounds().contains(enemies.get(ien).getArrayShotsEnemy().get(ishoten).getxShotEnemy(), enemies.get(ien).getArrayShotsEnemy().get(ishoten).getyShotEnemy());
                if (collision) {
                   // gameover = 0;
                }
            }
        }
    }*/
}



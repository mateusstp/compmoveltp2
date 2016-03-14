package com.example.mateus.gamegalaxianatari;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

//import java.util.logging.Handler;


/**
 * Created by mateus on 10/02/16.
 */

public class ControlNaveGameGalaxian extends Thread {

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

    public ControlNaveGameGalaxian(Handler h, int screenWidth_x, int screenHeight_y, Nave r, ArrayList<Enemy> en, Context ct) {
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
                gameover=1;
                Message message = new Message();
                Thread.sleep(2);
                collisionShotEnemyNave();
                //defino um codigo para controle.
                collisionShotNaveEnemy();
                collisionEnemyNave();
                nave.moveShots();
                message.what = gameover;
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
                if (collision && enemies.get(ien).isAlive()) {
                   // enemies.remove(ien);
                   enemies.get(ien).dead();
                    nave.getArrayShotsNave().remove(ishot);
                }else if(nave.getArrayShotsNave().get(ishot).getyShotNave()<=0){
                    nave.getArrayShotsNave().remove(ishot);
                }


            }
        }

        boolean alive=false;

        for(int x=0; x<enemies.size();x++)
        {

            if(enemies.get(x).isAlive())
            {
                alive=true;
            }
        }
        if (!alive)
            gameover=2;


    }

    private void collisionShotEnemyNave() {
        for (int ien = 0; ien < enemies.size(); ien++) {
            for (int ishoten = 0; ishoten < enemies.get(ien).getArrayShotsEnemy().size(); ishoten++) {
                boolean collision = nave.getNaveDrawable().copyBounds().contains(enemies.get(ien).getArrayShotsEnemy().get(ishoten).getxShotEnemy(), enemies.get(ien).getArrayShotsEnemy().get(ishoten).getyShotEnemy());
                if (collision) {
                    gameover = 0;

                }
            }
        }
    }

    private void collisionEnemyNave() {
        for (int ien = 0; ien < enemies.size(); ien++) {
            if(!enemies.get(ien).atPosition()){
                boolean collision = nave.getNaveDrawable().copyBounds().contains(enemies.get(ien).getxEnemy(), enemies.get(ien).getyEnemy());
                if (collision) {
                    gameover = 0;
                }
            }

        }
    }
}



package com.example.mateus.gamegalaxianatari;

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

    Aliens ball;
    Nave nave;
    ArrayList<Enemy> enemies;
    private int screenWidth_x, screenHeight_y;
    private boolean enemyActivated=false;
    private int boundtop;
    private int bounddown;
    private int boundleft;
    private int boundright;
    private int atual=-1;

    private Handler handler;

    public ControlGameGalaxian(Handler h, int screenWidth_x, int screenHeight_y, Nave r, ArrayList<Enemy> en) {

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
                message.what = 1;
                //delay
                Thread.sleep(5);
              //  collisionBallnave();
               // collisionWall();

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
                        enemies.get(atual).setAtPosition();
                        enemies.get(atual).move();
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

    private void collisionWall() {
        /**calc bounds x and y **/
        int boundX;
        int boundY;
        if (ball.getdirectionX() == 1) {
            boundX = ball.getxBall() + ball.getBallWidth_x() - 14;
        } else {
            boundX = ball.getxBall() + 14;
        }

        if (ball.getdirectionY() == 1) {
            boundY = ball.getyBall() + ball.getBallHeight_y() - 14;
        } else {
            boundY = ball.getyBall() + 14;
        }


        /**verify collision wall**/
        if (boundX >= boundright) {

            if (ball.getdirectionX() == 1 && ball.getdirectionY() == -1) {
                ball.setdirection(-1, -1);
            } else {
                ball.setdirection(-1, 1);
            }
        } else if (boundY <= boundtop) {
            if (ball.getdirectionX() == 1 && ball.getdirectionY() == -1) {
                ball.setdirection(1, 1);
            } else {
                ball.setdirection(-1, 1);
            }
        } else if (boundX <= boundleft) {
            if (ball.getdirectionX() == -1 && ball.getdirectionY() == -1) {
                ball.setdirection(1, -1);
            } else {
                ball.setdirection(1, 1);
            }
        } else if (boundY >= bounddown) {
            if (ball.getdirectionX() == -1 && ball.getdirectionY() == 1) {
                ball.setdirection(1, -1);
            } else {
                ball.setdirection(-1, -1);
            }

        }


    }


    private void collisionBallnave() {

        //boolean  colisao= rec.getnave().copyBounds().contains((int) x, (int) y);
        boolean boundCollisionY = ball.getyBall() - 14 == (int) (screenHeight_y - nave.getNaveHeight_y() * 1.8);

        if (boundCollisionY) {

            boolean boundHigherCollisionX = ball.getxBall() + ball.getBallWidth_x() - 14 <= nave.getxNave() + nave.getNaveWidth_x() * 1.2;
            boolean boundBelowCollisionX = ball.getxBall() - ball.getBallWidth_x() + 14 >= nave.getxNave() - nave.getNaveWidth_x() * 0.7;

            if (boundHigherCollisionX && boundBelowCollisionX) {
                if (nave.getdirection() == 0) {
                    ball.setdirection(ball.getdirectionX(), -1 * ball.getdirectionY());
                } else if (nave.getdirection() == 1) {
                    ball.setdirection(1, -1);
                } else if (nave.getdirection() == -1) {
                    ball.setdirection(-1, -1);
                }
            }
        }
    }

}



package com.example.mateus.gamegalaxianatari;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

//import java.util.logging.Handler;

/**
 * Created by mateus on 10/02/16.
 */
public class GameGalaxianView extends View {

    private static final String CATEGORIA = "AppNum53";
    private int xN;
    private boolean down;
    private int larguraTela, alturaTela;

    private Handler handlerNave,handlerEnemy,handlerShotsEnemy ;
    private ControlEnemyGameGalaxian controlEnemyGalaxian;
    private ControlNaveGameGalaxian controlNaveGalaxian;
    private ControlShotsEnemyGameGalaxian controlShotEnemyGalaxian;
    private Nave nave;
    private ArrayList<Enemy> enemies;
    private ArrayList<ShotEnemy> shotsEnemies;
    private boolean move=false;
    int statusjogo=1;
    public GameGalaxianView(Context context) {
        super(context, null);

        nave = new Nave(context);
        shotsEnemies = new ArrayList<ShotEnemy>();
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(context, 0));
        enemies.add(new Enemy(context, 1));
        enemies.add(new Enemy(context, 2));
        enemies.add(new Enemy(context, 3));
        enemies.add(new Enemy(context, 4));
        enemies.add(new Enemy(context, 5));
        enemies.add(new Enemy(context, 6));
        enemies.add(new Enemy(context, 7));
        enemies.add(new Enemy(context, 8));
        enemies.add(new Enemy(context, 9));
        enemies.add(new Enemy(context, 10));
        enemies.add(new Enemy(context, 11));
        enemies.add(new Enemy(context, 12));
        enemies.add(new Enemy(context, 13));
        enemies.add(new Enemy(context, 14));
        enemies.add(new Enemy(context, 15));
        enemies.add(new Enemy(context, 16));
        enemies.add(new Enemy(context, 17));
        enemies.add(new Enemy(context, 18));
        enemies.add(new Enemy(context,19));
        move=false;
        setFocusable(true);

    }

    @Override
//Callback para quando a tela é iniciada ou redimensionada
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        this.setBackgroundColor(Color.BLACK);
        super.onSizeChanged(width, height, oldw, oldh);
        this.alturaTela = height;
        this.larguraTela = width;

        handlerNave     = new Handler() {
            @Override
            public void handleMessage(Message msg) { //chamo um método para melhor organização.
                updateUI(msg);
            }
        };

        handlerEnemy     = new Handler() {
            @Override
            public void handleMessage(Message msg) { //chamo um método para melhor organização.
                updateUI(msg);
            }
        };


        handlerShotsEnemy = new Handler() {
            @Override
            public void handleMessage(Message msg) { //chamo um método para melhor organização.
                updateUI(msg);
            }
        };

        nave.setyNave(height - nave.getNaveHeight_y() - 30);
        nave.setxNave(width / 2 - (nave.getNaveWidth_x() / 2));
        xN=nave.getxNave();

        for(int x=0; x<enemies.size();x++)
        {
            if(enemies.get(x).isAlive()) {
                enemies.get(x).setEnemyHeight_y(height / 12);
                enemies.get(x).setEnemyWidth_x(width / 18);
                enemies.get(x).setxEnemy(width);
                enemies.get(x).setyEnemy(height);
            }
        }

        controlEnemyGalaxian= new ControlEnemyGameGalaxian(handlerEnemy,larguraTela,alturaTela,nave,enemies,getContext());
        controlEnemyGalaxian.start();
        controlNaveGalaxian = new ControlNaveGameGalaxian(handlerNave,larguraTela,alturaTela,nave,enemies,getContext());
        controlNaveGalaxian.start();
        //controlShotEnemyGalaxian = new ControlShotsEnemyGameGalaxian(handlerShotsEnemy,nave,alturaTela,shotsEnemies,getContext());
        //controlNaveGalaxian.start();

    }

    /**
     * desenha objetos nas posiçoes inicais
     **/
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        nave.drawNave(canvas);
        nave.drawShots(canvas);
        for(int x=0; x<enemies.size();x++)
        {
            if(enemies.get(x).isAlive()) {
                enemies.get(x).drawEnemy(canvas);
            }
            enemies.get(x).drawShots(canvas);

        }
    }


    @Override
    //Move a imagem

    public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();

    switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            Log.d(CATEGORIA, "ACTION_DOWN");
            //Inicia movimento se pressionou a imagem
            down = nave.getNaveDrawable().copyBounds().contains((int) x, (int) y);
            move=false;
            break;
        case MotionEvent.ACTION_MOVE:
            Log.d(CATEGORIA,"ACTION_MOVE");
            //Arrasta o boneco

            if (down) {
                move=true;
                if (x - nave.getNaveWidth_x()/ 2 > 0 & x + nave.getNaveWidth_x()/ 2 < larguraTela) {
                    //  this.xN = (int) x - (nave.getnaveWidth_x() / 2);

                    if((xN-nave.getxNave())>0){
                        nave.setdirection(1);
                    }else if((xN-nave.getxNave())<0){
                        nave.setdirection(-1);
                    }else{
                        nave.setdirection(0);
                    }

                    xN=nave.getxNave();
                    nave.setxNave(this.xN = (int) x - (nave.getNaveWidth_x() / 2));

                }



            }
            break;
        case MotionEvent.ACTION_UP:
            Log.d(CATEGORIA, "ACTION_UP");
            //Finaliza movimento
            //boolean up = down = nave.getNaveDrawable().copyBounds().contains((int) x, (int) y);
            if(down && !move){
                move=false;
                Log.d(CATEGORIA, "Move true selecio true altura: " + alturaTela);
                ShotNave ns = new ShotNave(getContext(),nave.getxNave()+nave.getNaveWidth_x()/2-10, nave.getyNave()-nave.getNaveHeight_y()/2+15);
                nave.getArrayShotsNave().add(ns);
                MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.lazermp3);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        mp.release();
                    }

                });
                mp.start();


                Log.d(CATEGORIA, "Move true selecio true");
            }

            break;
    }
    nave.moveShots();
    invalidate();
    return true;
}

//Move a imagem
    /*public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Inicia movimento se pressionou a imagem
                selecionou = nave.getNaveDrawable().copyBounds().contains((int) x, (int) y);
                break;
            case MotionEvent.ACTION_MOVE:
                //Arrasta o boneco
                if (selecionou) {
                    //
                    if (x - nave.getNaveWidth_x()/ 2 > 0 & x + nave.getNaveWidth_x()/ 2 < larguraTela) {
                      //  this.xN = (int) x - (nave.getnaveWidth_x() / 2);

                        if((xN-nave.getxNave())>0){
                            nave.setdirection(1);
                        }else if((xN-nave.getxNave())<0){
                            nave.setdirection(-1);
                        }else{
                            nave.setdirection(0);
                        }

                        xN=nave.getxNave();
                        nave.setxNave(this.xN = (int) x - (nave.getNaveWidth_x() / 2));

                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                //Finaliza movimento

                selecionou = false;
                break;
        }
        invalidate();
        return true;
    }
*/

    /**
     * Método responsável pelo controle de Message do Handler * * @param msg Message
     */
    private void updateUI(Message msg) {
        if (msg.what == 1) { //Converto o object para string (pois foi o que eu passei)
           // String texto = (String) msg.obj;
            //defino no meu TextView o texto.
            invalidate();
        }else if (msg.what == 0) { //Converto o object para string (pois foi o que eu passei)
            // String texto = (String) msg.obj;
            //defino no meu TextView o texto.
            CharSequence text = "PERDEEEEEEEEUUUUU!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();
            controlNaveGalaxian.interrupt();
            controlEnemyGalaxian.interrupt();
            ((Activity)getContext()).finish();

        }
        else if(msg.what == 2)
        {
            CharSequence text = "GANHOUU!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();
            controlNaveGalaxian.interrupt();
            controlEnemyGalaxian.interrupt();
            ((Activity)getContext()).finish();



        }

    }


}



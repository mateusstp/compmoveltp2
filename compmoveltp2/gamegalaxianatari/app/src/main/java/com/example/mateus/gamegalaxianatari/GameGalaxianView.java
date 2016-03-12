package com.example.mateus.gamegalaxianatari;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

//import java.util.logging.Handler;

/**
 * Created by mateus on 10/02/16.
 */
public class GameGalaxianView extends View {

    private static final String CATEGORIA = "AppNum53";
    int xN;
    private boolean selecionou;
    private int larguraTela, alturaTela;

    private Handler handler;
    private ControlGameGalaxian controlBall;
    private Nave nave;
    private ArrayList<Enemy> enemies;

    public GameGalaxianView(Context context) {
        super(context, null);

        nave = new Nave(context);
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

        setFocusable(true);

    }

    @Override
//Callback para quando a tela é iniciada ou redimensionada
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        this.alturaTela = height;
        this.larguraTela = width;

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) { //chamo um método para melhor organização.
                updateUI(msg);
            }
        };


        nave.setyNave(height - nave.getNaveHeight_y());
        nave.setxNave(width / 2 - (nave.getNaveWidth_x() / 2));
        xN=nave.getxNave();

        for(int x=0; x<enemies.size();x++)
        {
            if(enemies.get(x)!=null) {
                enemies.get(x).setEnemyHeight_y(height / 12);
                enemies.get(x).setEnemyWidth_x(width / 18);
                enemies.get(x).setxEnemy(width);
                enemies.get(x).setyEnemy(height);
            }
        }

        controlBall = new ControlGameGalaxian(handler,larguraTela,alturaTela,nave,enemies,getContext());
        controlBall.start();

    }

    /**
     * desenha objetos nas posiçoes inicais
     **/
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        nave.drawNave(canvas);

        for(int x=0; x<enemies.size();x++)
        {
            if(enemies.get(x)!=null) {
                enemies.get(x).drawEnemy(canvas);
                enemies.get(x).drawShots(canvas);
            }
        }
    }


    @Override
//Move a imagem
    public boolean onTouchEvent(MotionEvent event) {
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


    /**
     * Método responsável pelo controle de Message do Handler * * @param msg Message
     */
    private void updateUI(Message msg) {
        if (msg.what == 1) { //Converto o object para string (pois foi o que eu passei)
           // String texto = (String) msg.obj;
            //defino no meu TextView o texto.
            invalidate();
        }
    }



}



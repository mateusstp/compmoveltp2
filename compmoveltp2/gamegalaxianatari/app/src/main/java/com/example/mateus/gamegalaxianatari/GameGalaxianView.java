package com.example.mateus.gamegalaxianatari;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

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
    private Aliens ball;
    private Nave nave;

    public GameGalaxianView(Context context) {
        super(context, null);

        ball = new Aliens(context);
        nave = new Nave(context);
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

        ball.setxBall(width / 2 - (ball.getBallWidth_x() / 2));
        ball.setyBall(height / 2 - (ball.getBallHeight_y() / 2));

        controlBall = new ControlGameGalaxian(handler,larguraTela,alturaTela,ball,nave);
        controlBall.start();

    }

    /**
     * desenha objetos nas posiçoes inicais
     **/
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        nave.drawNave(canvas);
        ball.drawBall(canvas);
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



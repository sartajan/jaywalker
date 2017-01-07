package com.example.sunny.thejaywalker;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import static java.lang.Math.tan;
import static java.lang.StrictMath.abs;

/**
 * Created by sunny on 2016-11-24.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public Game game;
    public GameRunner runner;
    float x;
    float y;
   // private float speedY;
  //  private final float  speedMax = 0.5f;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
    //    int eventAction = event.getAction();

        //to get x/y location

     /*   x =  event.getX();
        y =  event.getY();

     //   speedY = (float)(speedMax*(tan(abs(y-0)/(abs(x-0)))));

    //    Log.d("ST","SpeedY is "+speedY);

   //     Log.d("ST", "The X touch co-ordinate is " + x);
  //      Log.d("ST", "The Y touch co-ordinate is " + y);

        //code to handle event
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        //view to redraw canvas
        invalidate();*/

        //view that event was handled
        game.onTouchEvent(event);
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Log.d("ST","Surface Created");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        Log.d("ST","Surface Changed");

        game = new Game(getWidth(), getHeight(), holder, getResources());
        runner = new GameRunner(game);
        runner.start();


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        Log.d("ST","Surface Destroyed");
        if (runner!=null){

            runner.shutdown();
            while (runner!=null){
                try {
                    runner.join();
                    runner = null;
                } catch (Exception e) {
                }
            }
        }

    }
}

package com.example.sunny.thejaywalker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by sunny on 2016-11-25.
 */

public class Game {

    private enum State{
        PAUSED,WON,LOST,RUNNING
    }
    private State state = State.PAUSED;

    private int screenWidth;
    private int screenHeight;
    private SurfaceHolder holder;
    private Car car;
    private JayWalker jaywalker;
    private Resources resources;
    //public Road road;
    private float touchX;
    private float touchY;
    MotionEvent event;
    Random random = new Random();
    int randomInt;
    private int roadUnitNumber = 24;
    Road[] roadArray = new Road[roadUnitNumber];
    Paint textPaint;



    public Game(int width, int height, SurfaceHolder holder, Resources resources){
        this.holder = holder;
        screenHeight = height;
        screenWidth = width;
        jaywalker = new JayWalker(width, height);
        for (int m = 0; m<roadArray.length; m++){
            roadArray[m]=new Road(width, height);
            Log.d("ST","got "+m+" roady arrays ready");
        }
        car = new Car(width, height);
        this.resources = resources;
        randomInt = random.nextInt(3);
        Log.d("ST","Done the Game constructor");

        textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(50);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public void init(){

        Bitmap roadImage = BitmapFactory.decodeResource(resources, R.drawable.road_unit);
        Log.d("ST", "saved RoadImage");
        for (int n = 0; n<roadArray.length; n++) {
            roadArray[n].init(roadImage);
            Log.d("ST", "roadArray initialized");
        }

        initPosition(roadArray);

        Bitmap jaywalkerImage = BitmapFactory.decodeResource(resources, R.drawable.jaywalker);
        jaywalker.init(jaywalkerImage);

        car.passReferences(roadArray, jaywalker);

            if (randomInt == 0) {
                Bitmap carImage = BitmapFactory.decodeResource(resources, R.drawable.car);
                car.init(carImage);
            } else if (randomInt == 1) {
                Bitmap carImage = BitmapFactory.decodeResource(resources, R.drawable.car3);
                car.init(carImage);
            } else if (randomInt == 2) {
                Bitmap carImage = BitmapFactory.decodeResource(resources, R.drawable.car4);
                car.init(carImage);
            } else {
                Bitmap carImage = BitmapFactory.decodeResource(resources, R.drawable.car);
                car.init(carImage);
            }
        Log.d("ST","roadArray size is "+roadArray.length);
        jaywalker.passReferences(roadArray, car);

        Log.d("ST","Done with initializing");

    }

    public void update(long elapsed){
        if(jaywalker.getScreenRect().contains(car.getScreenRect().left,jaywalker.getScreenRect().centerY())& jaywalker.getScreenRect().contains(car.getScreenRect().bottom,jaywalker.getScreenRect().centerX())||jaywalker.getScreenRect().contains(car.getScreenRect().right,jaywalker.getScreenRect().centerY())){
            state=State.LOST;
        } else{state = State.RUNNING;}

            jaywalker.update(elapsed, touchX,touchY);
     //       car.update(elapsed);
           // car.update(elapsed);
        if(state==State.LOST){
            drawText();
        }

    }

    public void draw(long elapsed){
        Canvas canvas = holder.lockCanvas();
        if(canvas !=null) {
            canvas.drawColor(Color.WHITE);
            for (int j =0; j<roadArray.length;j++) {
                roadArray[j].draw(canvas, System.currentTimeMillis());
            }
            car.draw(canvas, System.currentTimeMillis());
            jaywalker.draw(canvas, System.currentTimeMillis());
            holder.unlockCanvasAndPost(canvas);

        }


    }

    public void drawText(){
        Canvas canvas = holder.lockCanvas();
        if (canvas != null){
            canvas.drawColor(Color.WHITE);
            canvas.drawText("You Lose", canvas.getWidth()/2, canvas.getHeight()/2,textPaint);
        }
        holder.unlockCanvasAndPost(canvas);
    }

    public void onTouchEvent(MotionEvent event){
        this.event = event;
        if( state==State.RUNNING) {
            this.touchX = event.getX();
            this.touchY = event.getY();
        }
        else {
            state=State.RUNNING;
        }

    }


    public float getTouchX() {
        return touchX=event.getX();
    }

    public float getTouchY() {
        return touchY=event.getY();
    }

    public void initPosition(Road[] roadArray){

        for(int n =0; n<roadArray.length; n++){

            if (n<6) {
                roadArray[n].setX(screenWidth);
                roadArray[n].setY(screenHeight - ((n + 1) * (roadArray[n].getRect().height()) + 125));
                //  Log.d("ST","In the first Loop");
            }
            if (n>=6 & n< 12){
                roadArray[n].setX(screenWidth - (roadArray[n].getRect().width()));
                roadArray[n].setY(screenHeight - ((n-5)*(roadArray[n].getRect().height()) + 125));
                //  Log.d("ST","In the second Loop");
            }

            if (n>=12 & n< 18){
                roadArray[n].setX(screenWidth - 2*(roadArray[n].getRect().width()));
                roadArray[n].setY(screenHeight - ((n-11)*(roadArray[n].getRect().height()) + 125));
            }

            if (n>=18 & n < 24){
                roadArray[n].setX(screenWidth - 3*(roadArray[n].getRect().width()));
                roadArray[n].setY(screenHeight - ((n-17)*(roadArray[n].getRect().height()) + 125));

            }

        }

    }
}

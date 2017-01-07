package com.example.sunny.thejaywalker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.atan;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;
import static java.lang.StrictMath.tan;
import static java.lang.StrictMath.tanh;
import static java.lang.StrictMath.toDegrees;
import static java.lang.StrictMath.toRadians;

/**
 * Created by sunny on 2016-11-25.
 */

public class JayWalker extends Sprite {

    private final int frames = 2;
    private long frameticker =1;
    private long frameperiod = 500;
    private Bitmap flipped;
   // private Road road;
    private Car car;
    private float touchX;
    private float touchY;
    private float speedX;
    private float speedY;
    private final float speedMax = 0.1f;
    private float x;
    private float y;
    private int dirX = 1 ;
    private int dirY = 1;
    private double angle;
    private double previousangle;
    private Road[] roadArray;
    private int quadrant =1;

    public JayWalker(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    // initialize jaywalker with initPosition
    public void init(Bitmap image) {
        super.init(image);
        initPosition();

    }

    //receive reference to car and road objects
    public void passReferences(Road road[], Car car){
          roadArray= new Road[road.length];
        for (int j =0; j<roadArray.length; j++) {
           roadArray[j]=road[j];
        }
        this.car = car;
    }

    //update jaywalker object
    public void update(long elapsed,float touchX, float touchY){

        x = getX();
        y = getY();

        //get screen touch coordinates
        this.touchX = touchX;
        this.touchY = touchY;

        //getting image rectangle on screen
        Rect imageRectOnScreen = getScreenRect();

        //calculate angle for speed & image turn
        angle = (atan(abs(touchY-imageRectOnScreen.centerY())/(abs(touchX-imageRectOnScreen.centerX()))));

        if (touchY>= imageRectOnScreen.centerY()){
        //  angle = (atan(abs(touchY-imageRectOnScreen.centerY())/(abs(touchX-imageRectOnScreen.centerX()))));
            speedY = 0;
            speedX = (float)(speedMax*(cos(angle)));

            if (touchX>imageRectOnScreen.centerX()){
                quadrant = 4;} else { quadrant = 3;}

        } else{
        //  angle = (atan(abs(touchY-imageRectOnScreen.centerY())/(abs(touchX-imageRectOnScreen.centerX()))));
            speedY = (float)(speedMax*(sin(angle)));
            speedX = (float)(speedMax*(cos(angle)));
            angle = toDegrees(angle);
            if (touchX>imageRectOnScreen.centerX()){
                quadrant = 1; } else { quadrant = 2; }
        }

        if (touchX < imageRectOnScreen.centerX()){
            for (int k = 0; k<roadArray.length;k++) {
                roadArray[k].moveRight(speedX, elapsed);
            }
            car.IncreaseRight(speedX, elapsed);

        } else if(touchX>imageRectOnScreen.centerX()){

            for (int k = 0; k<roadArray.length;k++) {
                roadArray[k].moveLeft(speedX, elapsed);
            }
            car.IncreaseLeft(speedX, elapsed);

        } else{

        }

        if(touchY<imageRectOnScreen.centerY()){
            moveUp(imageRectOnScreen.centerY(), elapsed, y);
        }

        car.update(elapsed);

      //  setX(x);
      //  setY(y);
    }

    public void moveUp(int imageRectOnScreenY,long elapsed, float y){
        if(imageRectOnScreenY>(getScreenHeight()/2)){

            dirY = 1;
            Log.d("ST","amount to be subtracted: "+elapsed*speedY*dirY);
            y -=elapsed*speedY*dirY;
            Log.d("ST","Image Y on screen JayWalker: "+imageRectOnScreenY);
            Log.d("ST","Speed Y "+speedY);
        //    Log.d("ST","Y value being set is "+y);
            setY(y);
        } else {
            for (int k = 0; k<roadArray.length;k++) {
                roadArray[k].moveDown(speedY, elapsed);
            }
            car.moveDown(speedY, elapsed);
            dirY=0;
            y +=elapsed*speedY*dirY;
            setY(y);
        }
    }

    @Override
    public void draw(Canvas canvas, long systemCurrentTime) {
        if (systemCurrentTime >= frameticker + frameperiod) {
            frameticker = systemCurrentTime;
           // Log.d("ST", "Angle " + angle);
            flipped = flip();
            //    flipped = rotate((float)angle, quadrant);
            super.init(flipped);
            super.draw(canvas, systemCurrentTime);
            //  Log.d("ST","SysCurrTime is Greater!");
            // previousangle = angle;
        } else {
            // if (angle!=0 || angle!=previousangle) {
            //           flipped = rotate((float) angle);
            //  }
            super.draw(canvas, systemCurrentTime);
            // previousangle = angle;
            //  }
        }
    }

    public void initPosition(){
        setX(getScreenWidth()/2-getRect().centerX());
        setY(getScreenHeight()-getRect().height());
    }
}

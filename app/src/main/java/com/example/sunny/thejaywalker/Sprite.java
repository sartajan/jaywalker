package com.example.sunny.thejaywalker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import java.lang.Math;

import static java.lang.StrictMath.toRadians;

/**
 * Created by sunny on 2016-11-25.
 */

public abstract class Sprite {

    private float x = 30;
    private float y = 30;
    private int screenWidth;
    private int screenHeight;
    private Bitmap image;
    private Bitmap flipped;
    private Rect bounds;
    Matrix mflip = new Matrix();
    Matrix mrotate =new Matrix();
    private float degreesOld =45;



    public Sprite(int screenWidth, int screenHeight){

        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void init(Bitmap image){
        this.image = image;
        bounds = new Rect(0,0, image.getWidth(), image.getHeight());
    }

    public Rect getRect(){
        return bounds;
    }

    public Rect getScreenRect(){

        return new Rect((int)this.x, (int)this.y, (int)this.x + getRect().width(), (int)this.y+getRect().height());
    }

    public void draw(Canvas canvas, long systemCurrentTime){

       // if (stack)
        canvas.drawBitmap(image, x, y, null);

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public Bitmap flip()
    {
        mflip.preScale(-1, 1);
        Bitmap flipped = Bitmap.createBitmap(this.image, 0, 0, this.image.getWidth(), this.image.getHeight(), mflip, false);
        flipped.setDensity(DisplayMetrics.DENSITY_DEFAULT);
      //  Log.d("ST","Done the flip loop");
        return flipped;
    }

    public Bitmap rotate(float degrees, int quadrant){
        if (quadrant ==1){
            degrees = 90-degrees;
            if(degreesOld>(degrees+2) || degreesOld<(degrees-2) ) {
                //degrees
                mrotate.setRotate(degreesOld, getRect().width() / 2, getRect().height() / 2);
                degreesOld = degrees;
               // mrotate.setRotate(0);
            } else{}
        }else {
            degreesOld = 0;//else if(quadrant == 2){
            mrotate.setRotate(degreesOld);
        }
     //       degrees=90;
      //      mrotate.setRotate(degrees,getRect().width()/2,getRect().height()/2);            //mrotate.setRotate(degrees);
     //   }else if (quadrant ==3){
       //     degrees=(270);
       //     mrotate.setRotate(degrees,getRect().width()/2,getRect().height()/2);            //mrotate.setRotate(degrees);
      //  }else if (quadrant ==4){
         //  degrees= 270;
        //    mrotate.postRotate(degrees,this.image.getWidth()/2,this.image.getHeight()/2);
          //  mrotate.postTranslate(getX(),getY());
          //  Log.d("ST","imagecenter X is "+getX()+getRect().width()/2);
            //mrotate.setRotate(degrees);
       // }
       // Log.d("ST","X position of rotation is "+getScreenRect().centerX());
        //Log.d("ST","Y position of rotation is "+getScreenRect().centerY());
        Bitmap flipped = Bitmap.createBitmap(this.image, 0, 0, this.image.getWidth(), this.image.getHeight(), mrotate, false);
        flipped.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        mrotate.setRotate(0);

        return flipped;
    }




}
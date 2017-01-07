package com.example.sunny.thejaywalker;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by sunny on 2016-11-26.
 */

public class Road extends Sprite {

 //   private float speedX;
 //   private float speedY;
    private float dirX = 1;
    private float currentX;
    private float currentY;
    private float dirY = 1;
    private long elapsed;
    private int initCounter = 0;
    private int roadUnitNumber = 3;

    public Road(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);

    }

    @Override
    public void init(Bitmap image) {
        super.init(image);
      //  initPosition(initCounter);
    }

    public void moveLeft(float speedX, long elapsed){

        currentX = getX();
        if (currentX<(getScreenWidth() - (3*(getRect().width())))) {
            Log.d("ST","The unit's LEFT edge's position is "+currentX);
            updatePosition();
            Log.d("ST","The unit's new LEFT edge's position is "+currentX);
        }
        dirX = -1;
        currentX += dirX*speedX*elapsed;
        setX(currentX);

    }

    public void moveRight(float speedX , float elapsed ){
        currentX = getX();
        if (currentX>(getScreenWidth())) {
            Log.d("ST","The unit's RIGHT edge's position is "+currentX);
            updatePosition();
            Log.d("ST","The unit's new RIGHT edge's position is "+currentX);
        }
        dirX = 1;
        currentX += dirX*speedX*elapsed;
        setX(currentX);

    }

    public void moveDown(float speedY, float elapsed){
        currentY = getY();
        if (currentY>getScreenHeight()) {
            Log.d("ST","The unit's top edge's position is "+getScreenRect().top);
            updatePosition();
            Log.d("ST","The unit's new top edge's position is "+currentY);
        }
        currentY += dirY * speedY * elapsed;
        setY(currentY);
    }

    public void initPosition(int initCounter){

        setX(getScreenWidth() - getRect().width());
        setY(getScreenHeight() - (getRect().height() + 125));

    }

    public void updatePosition(){
        if (currentY>getScreenHeight()) {
            currentY = (currentY - ((6) * (getRect().height())));
        } else if (currentX>(getScreenWidth())){
            currentX = (currentX - ((3)* (getRect().width())));
        } else if (currentX<(getScreenWidth() - (3*(getRect().width())))){
            currentX = (currentX + ( 3*(getRect().width())));
        }
    }


}

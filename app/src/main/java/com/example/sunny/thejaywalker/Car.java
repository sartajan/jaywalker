package com.example.sunny.thejaywalker;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Random;

/**
 * Created by sunny on 2016-11-25.
 */

public class Car extends Sprite {

    private float currentX;
    private float currentY = 0f;
    private final float carSpeed = 0.3f;
    private float dirX = -1;
    private float dirY = 1;
    //private float speedY;
    private Random random = new Random();
    private int randomInt = 0;
    private Bitmap flipped;
    private JayWalker jaywalker;
    private int updateCounter = 0;
    private float deltaY = 0f;
    private float originalY = 0f;
    private Road[] roadArray;


    public Car(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    @Override
    public void init(Bitmap image) {

        super.init(image);
        randomInt = random.nextInt(6);
        randomInt = 2;

        if (randomInt == 0) {
            initPosition();
        } else if (randomInt == 1) {
            initPosition2();
        } else if (randomInt == 2) {
            initPosition3();
        } else if (randomInt == 3) {
            flipped = flip();
            super.init(flipped);
            initPosition4();
        } else if (randomInt == 4) {
            flipped = flip();
            super.init(flipped);
            initPosition5();
        } else if (randomInt == 5) {
            flipped = flip();
            super.init(flipped);
            initPosition6();
        }
        if (randomInt > 2) {
            dirX = 1;
        } else {
            dirX = -1;
        }
    }

    public void passReferences(Road[] road, JayWalker jaywalker) {
        roadArray = new Road[road.length];
        for (int i = 0; i < road.length; i++) {
            roadArray[i] = road[i];
        }
        this.jaywalker = jaywalker;
    }

    public void update(long elapsed) {

        currentX = getX();
        currentY = getY();
        if (dirX == 1 & getScreenRect().left >= getScreenWidth() || dirX == -1 & getScreenRect().right <= 0) {
            updatePosition();
        }else{
            currentX += carSpeed * dirX * elapsed;
            setX(currentX);
        }

    }

    public void moveDown(float speedY, long elapsed) {

        currentY = getY();
        currentY += elapsed * speedY * dirY;
        setY(currentY);
    }

    public void IncreaseLeft(float speedX, long elapsed) {
        currentX = getX();
        currentX += (speedX * (-1) * elapsed);
        setX(currentX);
        //update(elapsed);

    }

    public void IncreaseRight(float speedX, long elapsed) {
        currentX = getX();
        currentX += speedX * (1) * elapsed;
        setX(currentX);
        //   update(elapsed);
    }

    public void initPosition() {
        if (updateCounter < 1) {
            originalY = getScreenHeight() - (125 + roadArray[0].getRect().height() - Math.abs(roadArray[0].getRect().height() / 2 - getRect().height() / 2));
            setY(originalY);
            updateCounter++;

        } else {
            originalY = getScreenHeight() + deltaY - (125 + roadArray[0].getRect().height() - Math.abs(roadArray[0].getRect().height() / 2 - getRect().height() / 2));
            setY(originalY);
        }
        setX(getScreenWidth() - getRect().width());

    }

    public void initPosition2() {
        if (updateCounter < 1) {
            originalY = getScreenHeight() - (125 + 2 * roadArray[0].getRect().height() - Math.abs(roadArray[0].getRect().height() / 2 - getRect().height() / 2));
            setY(originalY);
            updateCounter++;

        } else {
            originalY +=deltaY;
            setY(originalY);
        }
        setX(getScreenWidth() - getRect().width());

    }

    public void initPosition3() {

        if (updateCounter < 1) {
            originalY = getScreenHeight() - (125 + 3 * roadArray[0].getRect().height() - Math.abs(roadArray[0].getRect().height() / 2 - getRect().height() / 2));
            setY(originalY);
            updateCounter++;

        } else {
            originalY +=deltaY;
            setY(originalY);
        }
        setX(getScreenWidth() - getRect().width());
    }

    public void initPosition4() {
        if (updateCounter < 1) {
            originalY = getScreenHeight() - (125 + roadArray[0].getRect().height() - Math.abs(roadArray[0].getRect().height() / 2 - getRect().height() / 2));
            setY(originalY);
            updateCounter++;

        } else {
            originalY = getScreenHeight() - (125 + roadArray[0].getRect().height() - Math.abs(roadArray[0].getRect().height() / 2 - getRect().height() / 2));
            setY(originalY);
        }
        setX(0);
    }

    public void initPosition5() {

        if (updateCounter < 1) {
            originalY = getScreenHeight() - (125 + 2 * roadArray[0].getRect().height() - Math.abs(roadArray[0].getRect().height() / 2 - getRect().height() / 2));
            setY(originalY);
            updateCounter++;

        } else {
            originalY = getScreenHeight() - (125 + 2 * roadArray[0].getRect().height() - Math.abs(roadArray[0].getRect().height() / 2 - getRect().height() / 2));
            setY(originalY);

        }
        setX(0);
    }

    public void initPosition6() {

        if (updateCounter < 1) {
            originalY = getScreenHeight() - (125 + 3 * roadArray[0].getRect().height() - Math.abs(roadArray[0].getRect().height() / 2 - getRect().height() / 2));
            setY(originalY);
            updateCounter++;

        } else {
            setY(originalY);
        }
        setX(0);
    }

    public void updatePosition(){
        deltaY = currentY - originalY;
        randomInt = random.nextInt(3)+1;
        randomInt = 1;
        originalY +=(deltaY-randomInt*roadArray[0].getRect().height());
        setY(originalY);

        if(dirX==1){
            setX(0);
        }
        else{
            setX(getScreenWidth()-getRect().width());
        }
    }
}



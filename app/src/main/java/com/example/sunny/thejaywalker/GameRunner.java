package com.example.sunny.thejaywalker;

import android.provider.Telephony;
import android.util.Log;

/**
 * Created by sunny on 2016-11-25.
 */

public class GameRunner extends Thread {

    private volatile boolean running = true;
    private Game game;

    public GameRunner(Game game){
        this.game = game;
    }


    @Override
    public void run(){

        game.init();
        long lastTime = System.currentTimeMillis();

        while (running){

            long now = System.currentTimeMillis();
            long elapsed = now - lastTime;

            if (elapsed<=100000){
                game.update(elapsed);
                game.draw(elapsed);
           }
            lastTime = now ;

        }


    }

    public void shutdown() {
        running = false;
    }
}

package com.sindyoke;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    SurfaceHolder surfaceHolder;
    boolean isRunning;
    long startTime, loopTime;
    long DELAY = 33; // between screen refreshes

    public GameThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
    }

    @Override
    public void run() {
        // it starts running when we call start on GameThread because it's true in constructor
        // then we freeze the canvas using surfaceHolder, draw everything and update the view
        // then we unfreeze the canvas
        // if drawing took less than loopTime, we wait a bit
        while(isRunning){
            startTime = SystemClock.uptimeMillis();
            // locking the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if(canvas!=null){
                synchronized (surfaceHolder){
                    AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
                    AppConstants.getGameEngine().updateAndDrawBird(canvas);
                    AppConstants.getGameEngine().updateAndDrawTubes(canvas);
                    // unlocking the canvas
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            loopTime = SystemClock.uptimeMillis() - startTime;
            // pausing to make sure we update the right amount per second
            if(loopTime<DELAY){
                try {
                    Thread.sleep(DELAY-loopTime);
                } catch (InterruptedException e){
                    Log.e("Interrupted", "Interrupted while sleeping");
                }
            }
        }

    }

    public void setIsRunning(boolean state) {
        this.isRunning = state;
    }

    public boolean isRunning(){
        return isRunning;
    }
}

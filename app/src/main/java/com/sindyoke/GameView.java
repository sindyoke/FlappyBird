package com.sindyoke;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread; // this refreshes the screen

    public GameView(Context context) {
        super(context);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(!gameThread.isRunning()){
            gameThread = new GameThread(holder);
            gameThread.start();
        } else {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(gameThread.isRunning()){
            gameThread.setIsRunning(false);
            boolean retry = true;
            while (retry){
                try {
                    gameThread.join();
                    retry = false;
                } catch (InterruptedException e){
                    Log.e("GameView", "error while destroying surface", e);
                }
            }
        }
    }

    void initView() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
        // with this holder object we will lock the canvas, draw and then unlock the canvas
    }

    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        // tap is detected
        if(action==MotionEvent.ACTION_DOWN){
            if(AppConstants.getGameEngine().gameState==0){
                AppConstants.getGameEngine().gameState = 1;
                AppConstants.getSoundBank().playSwoosh();
            } else {
                AppConstants.getSoundBank().playWing();
            }
            AppConstants.getGameEngine().bird.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED);
        }
        return true;
    }
}

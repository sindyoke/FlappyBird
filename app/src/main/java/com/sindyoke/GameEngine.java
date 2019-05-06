package com.sindyoke;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    BackgroundImage backgroundImage;
    Bird bird;
    static int gameState;
    ArrayList<Tube> tubes;
    Random random;

    public GameEngine() {
        backgroundImage = new BackgroundImage();
        bird = new Bird();
        // 0 = not started
        // 1 = playing
        // 2 = game over
        gameState = 0;

        tubes = new ArrayList<>();
        random = new Random();
        for(int i = 0; i < AppConstants.numberOfTubes; i++){
            int tubeX = AppConstants.SCREEN_WIDTH + i * AppConstants.distanceBetweenTubes;
            int topTubeOffsetY = AppConstants.minTubeOffsetY + random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
            Tube tube = new Tube(tubeX, topTubeOffsetY);
            tubes.add(tube);
        }

        Log.i("GameEngine", "AppConstants.SCREEN_HEIGHT is " + AppConstants.SCREEN_HEIGHT);
        Log.i("GameEngine", "BirdHeight is " + AppConstants.getBitmapBank().getBirdHeight());
        Log.i("GameEngine", "there are " + tubes.size() + " tubes created");
    }

    public void updateAndDrawTubes(Canvas canvas){
        if(gameState==1){
            for(int i=0; i < AppConstants.numberOfTubes; i++){
                if(tubes.get(i).getTubeX() < -AppConstants.getBitmapBank().getTubeWidth()){
                    tubes.get(i).setTubeX(tubes.get(i).getTubeX() + AppConstants.numberOfTubes * AppConstants.distanceBetweenTubes);
                    int topTubeOffsetY = AppConstants.minTubeOffsetY + random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
                    tubes.get(i).setTopTubeOffsetY(topTubeOffsetY);
                }
                tubes.get(i).setTubeX(tubes.get(i).getTubeX() - AppConstants.tubeVelocity);
                canvas.drawBitmap(AppConstants.getBitmapBank().getTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                canvas.drawBitmap(AppConstants.getBitmapBank().getTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
            }
        }
    }

    public void updateAndDrawBackgroundImage(Canvas canvas){
        backgroundImage.setX(backgroundImage.getX()-backgroundImage.getVelocity());
        if(backgroundImage.getX()<-AppConstants.getBitmapBank().getBackgroundWidth()){
            backgroundImage.setX(0);
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
        if(backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth()-AppConstants.SCREEN_WIDTH)){
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX()+AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
        }
    }

    public void updateAndDrawBird(Canvas canvas){
        if(gameState==1) {
            if (bird.getY() < AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight() || bird.getVelocity() < 0) {
                bird.setVelocity(bird.getVelocity() + AppConstants.gravity);
                bird.setY(bird.getY() + bird.getVelocity());

                Log.i("GameEngine", "bird velocity ions " + bird.getVelocity() + ", birdY is " + bird.getY());
            }
        }
        int currentFrame = bird.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(currentFrame), bird.getX(), bird.getY(), null);
        currentFrame++;
        if(currentFrame > bird.maxFrame){
            currentFrame = 0;
        }
        bird.setCurrentFrame(currentFrame);
    }
}

package com.sindyoke;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

class AppConstants {

    private static BitmapBank bitmapBank;
    private static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static int gapBetweenTopAndBottomTubes;
    static int numberOfTubes;
    static int tubeVelocity;
    static int minTubeOffsetY; // min Y value of the bottom edge of the top tube
    static int maxTubeOffsetY; // max Y value of the bottom edge of the top tube
    static int distanceBetweenTubes;
    static SoundBank soundBank;
    static Context gameActivityContext;

    static void initialization(Context context){
        setScreenSize(context);
        setGameConstants();
        bitmapBank = new BitmapBank(context.getResources());
        gameEngine = new GameEngine();
        soundBank = new SoundBank(context);
    }

    public static SoundBank getSoundBank(){
        return soundBank;
    }

    private static void setGameConstants(){
        AppConstants.gravity = 3;
        AppConstants.VELOCITY_WHEN_JUMPED = -40;
        AppConstants.gapBetweenTopAndBottomTubes = 600;
        AppConstants.numberOfTubes = 2;
        AppConstants.tubeVelocity = 12;
        AppConstants.minTubeOffsetY = (int) (AppConstants.gapBetweenTopAndBottomTubes / 2.0);
        AppConstants.maxTubeOffsetY = AppConstants.SCREEN_HEIGHT - AppConstants.minTubeOffsetY - AppConstants.gapBetweenTopAndBottomTubes;
        AppConstants.distanceBetweenTubes = SCREEN_WIDTH * 3/4;
    }

    static BitmapBank getBitmapBank() {
        return bitmapBank;
    }

    static GameEngine getGameEngine() {
        return gameEngine;
    }

    private static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }
}

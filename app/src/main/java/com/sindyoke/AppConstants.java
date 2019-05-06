package com.sindyoke;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {

    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static int gapBetweenTopAndBottomTubes;
    static int numberOfTubes;
    static int tubeVelocity;
    static int minTubeOffsetY; // min Y value of the bottom edge of the top tube
    static int maxTubeOffsetY; // max Y value of the bottom edge of the top tube
    static int distanceBetweenTubes;

    public static void initialization(Context context){
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources());
        gameEngine = new GameEngine();
        setGameConstants();
    }

    public static void setGameConstants(){
        AppConstants.gravity = 3;
        AppConstants.VELOCITY_WHEN_JUMPED = -40;
        gapBetweenTopAndBottomTubes = 600;
        numberOfTubes = 2;
        tubeVelocity = 12;
        minTubeOffsetY = (int) (AppConstants.gapBetweenTopAndBottomTubes / 2.0);
        maxTubeOffsetY = AppConstants.SCREEN_HEIGHT - AppConstants.minTubeOffsetY - AppConstants.gapBetweenTopAndBottomTubes;
        distanceBetweenTubes = SCREEN_WIDTH * 3/4;
    }

    public static BitmapBank getBitmapBank() {
        return bitmapBank;
    }

    public static GameEngine getGameEngine() {
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

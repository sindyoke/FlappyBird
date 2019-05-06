package com.sindyoke;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {
    Bitmap background;
    Bitmap[] bird;
    Bitmap tubeTop, tubeBottom;

    public BitmapBank(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.game_background_landscape);
        background = scaleImage(background);
        bird = new Bitmap[10];
        bird[0] = BitmapFactory.decodeResource(res, R.drawable.bird_frame_1);
        bird[1] = BitmapFactory.decodeResource(res, R.drawable.bird_frame_2);
        bird[2] = BitmapFactory.decodeResource(res, R.drawable.bird_frame_3);
        bird[3] = BitmapFactory.decodeResource(res, R.drawable.bird_frame_4);
        bird[4] = BitmapFactory.decodeResource(res, R.drawable.bird_frame_5);
        bird[5] = BitmapFactory.decodeResource(res, R.drawable.bird_frame_6);
        bird[6] = BitmapFactory.decodeResource(res, R.drawable.bird_frame_7);
        bird[7] = BitmapFactory.decodeResource(res, R.drawable.bird_frame_8);
        bird[8] = BitmapFactory.decodeResource(res, R.drawable.bird_frame_9);
        bird[9] = BitmapFactory.decodeResource(res, R.drawable.bird_frame_10);
        tubeBottom = BitmapFactory.decodeResource(res, R.drawable.pipe_bottom_90x554);
        tubeTop = BitmapFactory.decodeResource(res, R.drawable.pipe_top_90x554);
    }

    public Bitmap getTubeTop() {
        return tubeTop;
    }

    public Bitmap getTubeBottom() {
        return tubeBottom;
    }

    public int getTubeWidth(){
        return tubeTop.getWidth();
    }

    public int getTubeHeight(){
        return tubeTop.getHeight();
    }

    public Bitmap getBird(int frame){
        return bird[frame];
    }

    public int getBirdWidth(){
        return bird[0].getWidth();
    }

    public int getBirdHeight(){
        return bird[0].getHeight();
    }

    public Bitmap getBackground() {
        return background;
    }

    public int getBackgroundWidth(){
        return background.getWidth();
    }

    public int getBackgroundHeight(){
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();
        /*
        we'll multiply ratio with height to get scaled width
        then call createScaledBitmap to create a new bitmap
         */
        int backgroundScaledWidth = (int) (widthHeightRatio * AppConstants.SCREEN_HEIGHT);
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }

}

package com.sindyoke;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundBank {
    private Context context;
    private MediaPlayer swoosh, point, hit, wing, end;

    public SoundBank(Context context) {
        this.context = context;
        swoosh = MediaPlayer.create(context, R.raw.swoosh);
        point = MediaPlayer.create(context, R.raw.point);
        hit = MediaPlayer.create(context, R.raw.hit);
        wing = MediaPlayer.create(context, R.raw.wing);
        end = MediaPlayer.create(context, R.raw.end);
    }

    public void playSwoosh(){
        if(swoosh!=null){
            swoosh.start();
        }
    }

    public void playPoint(){
        if(point!=null){
            point.start();
        }
    }

    public void playHit(){
        if(hit!=null){
            hit.start();
        }
    }

    public void playWing(){
        if(wing!=null){
            wing.start();
        }
    }

    public void playEnd(){
        if(end!=null){
            end.start();
        }
    }
}

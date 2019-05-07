package com.sindyoke;

import java.util.Random;

public class Tube {

    // tubeX = Tube X-coordinate,
    // topTubeOffsetY = top tube bottom edge coordinate
    private int tubeX, topTubeOffsetY;
    private Random random;
    private int tubeColor;

    public Tube(int tubeX, int topTubeOffsetY) {
        this.tubeX = tubeX;
        this.topTubeOffsetY = topTubeOffsetY;
        random = new Random();
    }

    public void setTubeColor(){
        tubeColor = random.nextInt(3);
    }

    public int getTubeColor(){
        return tubeColor;
    }

    public int getTubeX() {
        return tubeX;
    }

    public void setTubeX(int tubeX) {
        this.tubeX = tubeX;
    }

    public int getTopTubeOffsetY() {
        return topTubeOffsetY;
    }

    public void setTopTubeOffsetY(int topTubeOffsetY) {
        this.topTubeOffsetY = topTubeOffsetY;
    }

    public int getTopTubeY(){
        return topTubeOffsetY - AppConstants.getBitmapBank().getTubeHeight();
    }

    public int getBottomTubeY(){
        return topTubeOffsetY + AppConstants.gapBetweenTopAndBottomTubes;
    }
}

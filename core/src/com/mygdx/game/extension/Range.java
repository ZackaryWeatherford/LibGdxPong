package com.mygdx.game.extension;

public class Range {

    public int min;
    public int max;

    public Range(int min, int max){
        this.min = min;
        this.max = max;
    }

    /** starting and ending numbers included in range*/
    public boolean inRangeInclusive(int num){
        return min <= num && num <= max;
    }

    public boolean inRangeExclusive(int num){
        return min < num && num < max;
    }



}

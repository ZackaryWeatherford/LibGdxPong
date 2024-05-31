package com.mygdx.game.extension;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class Timer {

    /** */
    public float timeLeft;

    /** */
    public float timerTime;

    /** */
    public static ArrayList<Timer> timers = new ArrayList<Timer>();

    /** */
    public boolean paused;

    /** */
    public boolean repeat;

    /** */
    public boolean repeatForever;

    /** */
    public int numberOfRepeats = 0;

    /** */
    public int currentRepeat = 0;

    /** */
    boolean timerOver = false;


    /** */
    public Timer(float timerCountdown){
        timeLeft = timerCountdown;
        timerTime = timerCountdown;
        timers = new ArrayList<Timer>();
        paused = false;
        repeat = false;
        repeatForever = false;
        numberOfRepeats = 0;
        timers.add(this);
    }

    /** */
    public Timer(float timerCountdown, int numberOfRepeats){
        timeLeft = timerCountdown;
        timerTime = timerCountdown;
        timers = new ArrayList<Timer>();
        paused = false;
        repeat = false;
        repeatForever = false;
        this.numberOfRepeats = numberOfRepeats;
        timers.add(this);
    }

    /** */
    public Timer(float timerCountdown, boolean repeatForever){
        timeLeft = timerCountdown;
        timerTime = timerCountdown;
        timers = new ArrayList<Timer>();
        paused = false;
        repeat = false;
        this.repeatForever = repeatForever;
        timers.add(this);
    }

    /**
     *
     *  */
    public static void incrementTimers(){

        for(Timer timer : timers)
            if(!timer.paused)
                timer.updateTime();
    }

    /** */
    public void updateTime(){

        if(timerOver && (currentRepeat > 0)){
            timeLeft = timerTime;
            currentRepeat--;
            timerOver = false;
        }

        if(timerOver && repeatForever){
            timeLeft = timerTime;
            timerOver = false;
            currentRepeat++;
        }

        if(!paused && timeLeft > 0) {
            timeLeft -= Gdx.graphics.getDeltaTime();
            timerOver = timeLeft <= 0;
        }

    }

    /** */
    public void reset(){
        timeLeft = 0;
        if(numberOfRepeats != 0)
            currentRepeat = numberOfRepeats;
    }

    /** */
    public void delete(){
        timers.remove(this);
    }

}
package com.pinkmountaines;

import javax.swing.*;

public class Timer implements Runnable {

    private Thread runThread;
    private boolean running = false;
    private boolean paused = false;
    private TimeFrame timeFrame;
    private long summedTime = 0;

    public Timer(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public static void main(String[] args) {
        TimeFrame t = new TimeFrame();
        JFrame f = new JFrame("Timer");
        f.setSize(300, 200);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.getContentPane().add(t);
        f.setVisible(true);
    }

    public void startTimer() {
        running = true;
        paused = false;
        runThread = new Thread(this);
        runThread.start();
    }

    public void pauseTimer() {
        paused = true;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (running && !paused) {
            timeFrame.update(summedTime + (System.currentTimeMillis() - startTime));
        }
        if (paused) {
            summedTime += System.currentTimeMillis() - startTime;
        } else {
            summedTime = 0;
        }
    }
}
package com.pinkmountaines;

import javax.swing.*;
import java.io.*;
import java.util.logging.Logger;

public class Timer implements Runnable {
    private static final Logger LOG = Logger.getLogger(Timer.class.getName());
    private static final String LAST_SAVED_TIME_FILE_NAME = "res/lastUpdateTime";

    private static final int TIMER_WIDTH = 300;
    private static final int TIMER_HEIGHT = 200;

    private boolean running = false;
    private boolean paused = false;
    private TimeFrame timeFrame;
    private long summedTime = 0;

    public Timer(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
        getInitTime();
    }

    private void getInitTime() {
        try (FileReader reader = new FileReader(LAST_SAVED_TIME_FILE_NAME)) {
            BufferedReader bf = new BufferedReader(reader);
            long savedCurrentTime = Long.parseLong(bf.readLine());
            long lastTimerValue = Long.parseLong(bf.readLine());
            this.summedTime = System.currentTimeMillis() - savedCurrentTime + lastTimerValue;
        } catch (IOException | NumberFormatException e) {
            this.summedTime = 0;
            LOG.severe("Couldn't read file " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TimeFrame t = new TimeFrame();
        JFrame f = new JFrame("Timer");
        f.setSize(TIMER_WIDTH, TIMER_HEIGHT);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.getContentPane().add(t);
        f.setVisible(true);
    }

    public void startTimer() {
        running = true;
        paused = false;
        Thread runThread = new Thread(this);
        runThread.start();
    }

    public void pauseTimer() {
        paused = true;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (running && !paused) {
            long timeToDisplay = summedTime + (System.currentTimeMillis() - startTime);
            timeFrame.update(timeToDisplay);

            try (PrintWriter writer = new PrintWriter(LAST_SAVED_TIME_FILE_NAME)) {
                writer.println(System.currentTimeMillis());
                writer.println(timeToDisplay);
            } catch (IOException e) {
                LOG.severe("Couldn't save time" + e.getMessage());
            }

        }
        if (paused) {
            summedTime += System.currentTimeMillis() - startTime;
        } else {
            summedTime = 0;
        }
    }
}
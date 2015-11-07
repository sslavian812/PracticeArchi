package com.pinkmountaines;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFrame extends JPanel {
    private final Timer timer;
    private final JLabel time;
    private final JButton pause;
    private final JButton start;
    private final JButton stop;


    private static final DateFormat format = new SimpleDateFormat("HH:mm:ss");



    public TimeFrame() {
        this.timer = new Timer(this);
        this.time = new JLabel("Timer", JLabel.CENTER);
        this.start = new JButton("Start");
        this.pause = new JButton("Pause");
        this.stop = new JButton("Stop");
        this.pause.setEnabled(false);
        start.addActionListener(new StartListener());
        pause.addActionListener(new PauseListener());
        stop.addActionListener(new StopListener());

        add(time);
        add(start);
        add(pause);
        add(stop);
        update(0);
    }

    public void update(long ms) {
        time.setText(timeFormat(ms));
    }

    public class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            timer.startTimer();
            pause.setEnabled(true);
            start.setEnabled(false);
        }
    }

    public class PauseListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            timer.pauseTimer();
            pause.setEnabled(false);
            start.setEnabled(true);
        }
    }

    public class StopListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            timer.stopTimer();
            pause.setEnabled(false);
            start.setEnabled(true);
        }
    }


    private String timeFormat(long count) {
//        return format.format(new Date(count));
        return format.format(new Date(count));

//        long hours = count / SECONDS_PER_HOUR;
//        long minutes = (count - hours * SECONDS_PER_HOUR) / SECONDS_PER_HOUR;
//        long seconds = count - minutes * SECONDS_PER_HOUR;
//        return String.format("%02d : %02d : %02d", hours, minutes, seconds);
    }
}
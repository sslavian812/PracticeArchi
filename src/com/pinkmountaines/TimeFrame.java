package com.pinkmountaines;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimeFrame extends JPanel {
    private final Timer timer;
    private final JLabel time;
    private final JButton pause;
    private final JButton start;

    public TimeFrame() {
        this.timer = new Timer(this);
        this.time = new JLabel("Timer", JLabel.CENTER);
        this.start = new JButton("Start");
        this.pause = new JButton("Stop");
        this.pause.setEnabled(false);
        start.addActionListener(new StartListener());
        pause.addActionListener(new PauseListener());
        add(time);
        add(start);
        add(pause);
        update(0);
    }

    public void update(long ms) {
        time.setText(String.valueOf((ms / 60000) % 1000) + ":" +
                String.valueOf((ms / 1000) % 1000) + "." + String.valueOf((ms) % 1000));
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
}
package it.unibz.cspiess.event;

import it.unibz.cspiess.Scoreboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimerIncrementEvent extends AbstractAction {
    private JLabel label;
    private long startTime;
    private Scoreboard scoreboard;
    private boolean isGuest;

    public TimerIncrementEvent(JLabel Label, long StartTime, Scoreboard userscoreboard, boolean isGuest) {
        label = Label;
        startTime = StartTime;
        scoreboard = userscoreboard;
        this.isGuest = isGuest;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        label.setText(dateFormat.format(new Date(System.currentTimeMillis() - startTime)));
        if (!isGuest) scoreboard.incrementTimeSpent(1);
    }
}
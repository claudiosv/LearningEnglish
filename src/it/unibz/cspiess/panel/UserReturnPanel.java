package it.unibz.cspiess.panel;

import it.unibz.cspiess.MainFrame;
import it.unibz.cspiess.dialog.DifficultDialog;
import it.unibz.cspiess.dialog.StatisticsDialog;
import it.unibz.cspiess.event.BeginEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by claudio on 28/01/2017.
 */
public class UserReturnPanel extends JPanel {
    public UserReturnPanel(MainFrame frameInstance) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(new EmptyBorder(5, 10, 5, 5));

        JPanel scoreboardWithButtons = new JPanel();
        scoreboardWithButtons.setLayout(new GridLayout(2, 1));
        ScoreboardPanel scoreboardPanel = new ScoreboardPanel(frameInstance);

        JPanel buttons = new JPanel();

        JButton mostDifficultButton = new JButton("Most difficult");
        mostDifficultButton.addActionListener(eventMostDifficult -> new DifficultDialog(frameInstance));
        buttons.add(mostDifficultButton);

        JButton statisticsButton = new JButton("Statistics");
        statisticsButton.addActionListener(eventStatistics -> new StatisticsDialog(frameInstance));
        buttons.add(statisticsButton);

        JCheckBox onlyWrongCheckBox = new JCheckBox("Only wrongly answered");
        onlyWrongCheckBox.addActionListener(wrongEvent -> {
            frameInstance.setIncorrectOnly(onlyWrongCheckBox.isSelected());
        });
        buttons.add(onlyWrongCheckBox);

        JButton beginButton = new JButton("Begin");
        beginButton.addActionListener(new BeginEvent(frameInstance));
        buttons.add(beginButton);

        scoreboardWithButtons.add(scoreboardPanel);
        scoreboardWithButtons.add(buttons);

        JLabel cent = new JLabel("<html>Welcome back, " + frameInstance.getCurrentScoreboard().getNickname() + "</html>");
        cent.setAlignmentX(Component.CENTER_ALIGNMENT);
        cent.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(cent);
        this.add(scoreboardWithButtons);
    }
}
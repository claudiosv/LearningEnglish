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
public class InstructionsPanel extends JPanel {
    private boolean isGuest;

    public InstructionsPanel(MainFrame frameInstance, boolean isGuest) {
        this.isGuest = isGuest;
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(new EmptyBorder(5, 10, 5, 5));
        if (isGuest) {
            this.add(new JLabel("<html><i>Hello</i> guest. Welcome to Learning English</html>"));
            this.add(new JLabel("<html>"
                    + "<br>Learning English helps you learn alternative meanings of words by answering questions.<br><br>"
                    + "1. Click \"Begin\" and random questions will be presented from a set of 30. Questions may be repeated.<br><br>"
                    + "2. Select the correct answer and click on \"Correct\", or \"Skip\" the question. Clicking \"Correct\" will show the correct answer regardless of whether a selection was made or not.<br><br>"
                    + "3. A timer in the top right shows how much time you have spent analysing the question.<br><br>"
                    + "4. You can view statistics on your current session, and the best performers at any time by pressing \"Statistics\", or the most difficult questions for all users by clicking \"Most difficult\"<br><br>"
                    + "5. Start questions by clicking \"Begin\". Remember, as a guest your performance will not be saved. Good luck!"
                    + "</html>"));
        } else {
            this.add(new JLabel("<html>Welcome to Learning English, " + frameInstance.getCurrentScoreboard().getNickname() + "</html>"));
            this.add(new JLabel("<html>"
                    + "<br>Learning English helps you learn alternative meanings of words by answering questions.<br><br>"
                    + "1. Click \"Begin\" and random questions will be presented from a set of 30. Questions may be repeated.<br><br>"
                    + "2. Select the correct answer and click on \"Correct\", or \"Skip\" the question. Clicking \"Correct\" will show the correct answer regardless of whether a selection was made or not.<br><br>"
                    + "3. A timer in the top right shows how much time you have spent analysing the question. This time is added to your scoreboard.<br><br>"
                    + "4. You can view statistics on your performance at any time by pressing \"Statistics\", or the most difficult questions for all users by clicking \"Most difficult\"<br><br>"
                    + "5. Start questions by clicking \"Begin\". Good luck!"
                    + "</html>"));
        }

        JPanel buttons = new JPanel();
        JButton mostDifficultButton = new JButton("Most difficult");
        mostDifficultButton.addActionListener(e -> new DifficultDialog(frameInstance));
        buttons.add(mostDifficultButton);

        JButton statisticsButton = new JButton("Statistics");
        statisticsButton.addActionListener(e -> new StatisticsDialog(frameInstance));
        buttons.add(statisticsButton);

        if (!isGuest) buttons.add(new JCheckBox("Only wrongly answered"));
        JButton beginButton = new JButton("Begin");
        beginButton.addActionListener(new BeginEvent(frameInstance));
        buttons.add(beginButton);
        this.add(buttons);
    }
}

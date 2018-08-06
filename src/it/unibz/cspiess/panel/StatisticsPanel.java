package it.unibz.cspiess.panel;

import it.unibz.cspiess.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by claudio on 28/01/2017.
 */
public class StatisticsPanel extends JPanel {
    private MainFrame frameInstance;

    public StatisticsPanel(MainFrame frameInstance) {
        this.frameInstance = frameInstance;
        this.setLayout(new BorderLayout());
        JTabbedPane tabPane = new JTabbedPane();

        if (!frameInstance.isGuest()) tabPane.addTab("Your performance", new ScoreboardPanel(frameInstance));
        tabPane.addTab("Best performer", new ScoreboardPanel(frameInstance, frameInstance.getScoreboards().getBestScoreboard()));
        tabPane.addTab("Worst performer", new ScoreboardPanel(frameInstance, frameInstance.getScoreboards().getWorstScoreboard()));
        this.add(tabPane, BorderLayout.CENTER);
    }
}

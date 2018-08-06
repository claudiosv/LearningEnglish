package it.unibz.cspiess.dialog;

import it.unibz.cspiess.MainFrame;
import it.unibz.cspiess.panel.StatisticsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by claudio on 31/01/2017.
 */
public class StatisticsDialog extends JDialog implements ActionListener {
    public StatisticsDialog(MainFrame frameInstance) {//(JFrame parent, Question[] difficultQuestions) {
        super(frameInstance.getMainFrame(), "Statistics", true);

        getContentPane().add(new StatisticsPanel(frameInstance));

        JPanel buttonPanel = new JPanel();

        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);
        buttonPanel.add(okButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
    }
}
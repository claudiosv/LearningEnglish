package it.unibz.cspiess.dialog;

import it.unibz.cspiess.MainFrame;
import it.unibz.cspiess.panel.MostDifficultPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultDialog extends JDialog implements ActionListener {
    public DifficultDialog(MainFrame frameInstance) {//(JFrame parent, Question[] difficultQuestions) {
        super(frameInstance.getMainFrame(), "Most difficult questions", true);

        getContentPane().add(new MostDifficultPanel(frameInstance));

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

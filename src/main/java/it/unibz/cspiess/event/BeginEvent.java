package it.unibz.cspiess.event;

import it.unibz.cspiess.MainFrame;
import it.unibz.cspiess.Questions;
import it.unibz.cspiess.panel.QuestionPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BeginEvent extends AbstractAction {
    private MainFrame mainFrame;
    private Questions questions;

    public BeginEvent(MainFrame actualFrame) {
        mainFrame = actualFrame;
        questions = actualFrame.getQuestions();
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        QuestionPanel questionPanel;

        if (mainFrame.isIncorrectOnly() && !mainFrame.isGuest()) {
            questionPanel = new QuestionPanel(mainFrame, true);
        } else {
            questionPanel = new QuestionPanel(mainFrame, false);
        }
        mainFrame.getMainFrame().setContentPane(questionPanel);
        mainFrame.getMainFrame().validate();
        mainFrame.getMainFrame().getContentPane().validate();
        questionPanel.validate();
        if (questionPanel.getTimer() != null) questionPanel.getTimer().start();

    }
}
package it.unibz.cspiess.event;

import it.unibz.cspiess.MainFrame;
import it.unibz.cspiess.panel.QuestionPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by claudio on 31/01/2017.
 */
public class CorrectionEvent extends AbstractAction {
    private QuestionPanel parentPanel;
    private MainFrame frameInstance;

    public CorrectionEvent(MainFrame frameInstance, QuestionPanel parentPanel) {
        this.parentPanel = parentPanel;
        this.frameInstance = frameInstance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (JRadioButton button : parentPanel.getMeaningRadioButtons()) {
            if (button.isSelected() && !button.getText().contains(parentPanel.getQuestion().getCorrectMeaning())) {
                button.setText(button.getText().replace("<p>", "<p style='color:red; font-weight:bold;'>"));
                parentPanel.getResultLabel().setText("<html><p style='color:red;'>Incorrect! Better luck next time.</p></html>");
                parentPanel.getQuestion().incrementIncorrectCount();
                if (!frameInstance.isGuest())
                    this.frameInstance.getCurrentScoreboard().addWrongQuestionId(parentPanel.getQuestion().getIdentifier());
            }

            if (button.getText().contains(parentPanel.getQuestion().getCorrectMeaning())) {
                button.setText(button.getText().replace("<p>", "<p style='color:green; font-weight:bold;'>"));
                if (button.isSelected()) {
                    parentPanel.getResultLabel().setText("<html><p style='color:green;'>Correct! Keep it up, you're doing great!</p></html>");
                    parentPanel.getQuestion().incrementCorrectCount();
                    if (!frameInstance.isGuest()) this.frameInstance.getCurrentScoreboard().incrementQuestionsCorrect();
                }
            }
        }

        parentPanel.getProceedButton().setText("Next");
        parentPanel.getCorrectButton().setEnabled(false);
        parentPanel.getTimer().stop();
    }
}
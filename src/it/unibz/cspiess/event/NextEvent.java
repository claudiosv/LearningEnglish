package it.unibz.cspiess.event;

import it.unibz.cspiess.MainFrame;
import it.unibz.cspiess.panel.InstructionsPanel;
import it.unibz.cspiess.panel.UserReturnPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by claudio on 31/01/2017.
 */
public class NextEvent extends AbstractAction {
    private MainFrame frameInstance;
    private JTextField nicknameTextField;

    public NextEvent(MainFrame frameInstance, JTextField nicknameTextField) {
        this.frameInstance = frameInstance;
        this.nicknameTextField = nicknameTextField;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        boolean newUser = true;

        //e.getActionCommand()
        frameInstance.setGuest(e.getActionCommand().equals("guest"));
        InstructionsPanel instructionsPanel = new InstructionsPanel(frameInstance, true);
        if (frameInstance.isGuest()) {
            frameInstance.getMainFrame().setContentPane(instructionsPanel);
            frameInstance.getMainFrame().validate();
            frameInstance.getMainFrame().getContentPane().validate();
        } else {

            try {
                if (frameInstance.getScoreboards().scoreboardExists(nicknameTextField.getText())) {
                    frameInstance.setCurrentScoreboard(frameInstance.getScoreboards().getScoreboardByNickname(nicknameTextField.getText()));
                    newUser = false;
                } else {
                    frameInstance.setCurrentScoreboard(frameInstance.getScoreboards().addUser(nicknameTextField.getText()));
                    newUser = true;
                }
            } catch (Exception ex) {

            }

            if (newUser) {
                instructionsPanel = new InstructionsPanel(frameInstance, false);
                frameInstance.getMainFrame().setContentPane(instructionsPanel);
                frameInstance.getMainFrame().validate();
                frameInstance.getMainFrame().getContentPane().validate();
            } else {
                JPanel scoreboardWithButtons = new UserReturnPanel(frameInstance);
                frameInstance.getMainFrame().setContentPane(scoreboardWithButtons);
                frameInstance.getMainFrame().validate();
                frameInstance.getMainFrame().getContentPane().validate();
            }
        }
    }

}
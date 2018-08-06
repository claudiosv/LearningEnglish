package it.unibz.cspiess.panel;

import it.unibz.cspiess.MainFrame;
import it.unibz.cspiess.event.NextEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by claudio on 27/01/2017.
 */
public class StartPanel extends JPanel {
    private JTextField nicknameTextField;
    private JLabel guestWarningLabel;
    private JButton nextButton;

    public StartPanel(MainFrame frameInstance) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome to Learning English");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setBorder((new EmptyBorder(20, 0, 20, 0)));
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 25));
        this.add(welcomeLabel);

        JLabel getStartedLabel = new JLabel("Please select one of the two options. If you choose to play as a user, you must enter a nickname to get started.");
        getStartedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getStartedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        getStartedLabel.setBorder((new EmptyBorder(20, 0, 20, 0)));
        this.add(getStartedLabel);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));

        ButtonGroup radioButtons = new ButtonGroup();

        JRadioButton asUserRadioButton = new JRadioButton("Play as user");
        asUserRadioButton.setBorder(new EmptyBorder(0, 0, 20, 0));
        asUserRadioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        asUserRadioButton.addActionListener(new AsUserActionListener());

        radioPanel.add(asUserRadioButton);
        nicknameTextField = new JTextField();
        nicknameTextField.setEnabled(false);
        nicknameTextField.getDocument().addDocumentListener(new NicknameTextfieldChangedEvent());
        NextEvent nextEvent = new NextEvent(frameInstance, nicknameTextField);

        nicknameTextField.addActionListener(nextEvent);
        nicknameTextField.setMaximumSize(new Dimension(400, nicknameTextField.getPreferredSize().height));

        radioPanel.add(nicknameTextField);

        JRadioButton asGuestRadioButton = new JRadioButton("Play as guest");
        asGuestRadioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        asGuestRadioButton.setBorder(new EmptyBorder(20, 0, 20, 0));
        asGuestRadioButton.addActionListener(new AsGuestActionListener());
        radioPanel.add(asGuestRadioButton);
        guestWarningLabel = new JLabel("No scoreboard or saved questions");
        guestWarningLabel.setEnabled(false);
        guestWarningLabel.setBorder(new EmptyBorder(00, 0, 0, 0));
        radioPanel.add(guestWarningLabel);

        radioButtons.add(asUserRadioButton);
        radioButtons.add(asGuestRadioButton);

        radioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioPanel.setBorder((new EmptyBorder(20, 0, 20, 0)));
        this.add(radioPanel);

        nextButton = new JButton("Next");
        nextButton.addActionListener(nextEvent);
        nextButton.setEnabled(false);
        this.add(nextButton);
    }

    private class AsGuestActionListener extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            nicknameTextField.setEnabled(false);
            guestWarningLabel.setEnabled(true);
            nextButton.setEnabled(true);
            nextButton.setActionCommand("guest");
        }
    }

    private class AsUserActionListener extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            guestWarningLabel.setEnabled(false);
            nicknameTextField.setEnabled(true);
            nextButton.setEnabled(!nicknameTextField.getText().isEmpty());
            nextButton.setActionCommand("user");
        }
    }

    private class NicknameTextfieldChangedEvent implements DocumentListener {
        @Override
        public void changedUpdate(DocumentEvent e) {
            nextButton.setEnabled(!nicknameTextField.getText().isEmpty());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changedUpdate(e);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            changedUpdate(e);
        }
    }
}

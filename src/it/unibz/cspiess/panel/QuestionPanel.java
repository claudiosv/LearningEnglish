package it.unibz.cspiess.panel;

import it.unibz.cspiess.MainFrame;
import it.unibz.cspiess.Question;
import it.unibz.cspiess.dialog.DifficultDialog;
import it.unibz.cspiess.dialog.StatisticsDialog;
import it.unibz.cspiess.event.BeginEvent;
import it.unibz.cspiess.event.CorrectionEvent;
import it.unibz.cspiess.event.TimerIncrementEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by claudio on 28/01/2017.
 */
public class QuestionPanel extends JPanel {
    private ArrayList<JRadioButton> meaningRadioButtons;
    private Timer timer;
    private Question question;
    private JLabel resultLabel;
    private JButton ProceedButton;
    private JButton CorrectButton;

    public QuestionPanel(MainFrame frameInstance, boolean wrongAnsweredOnly) {
        meaningRadioButtons = new ArrayList<>();
        question = new Question();
        if (!frameInstance.isGuest()) frameInstance.getCurrentScoreboard().incrementQuestionsShown();
        try {
            if (wrongAnsweredOnly)
                question = frameInstance.getQuestions().getIncorrectQuestion(frameInstance.getCurrentScoreboard());
            else
                question = frameInstance.getQuestions().getRandomQuestion();

            this.setLayout(new BorderLayout(10, 10));

            JPanel panel2 = new JPanel();
            panel2.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
            JLabel questionLabel = new JLabel("<html><p>" + question.getQuestionSentence().replace(question.getQuestionWord(), "<u>" + question.getQuestionWord() + "</u>") + "</p></html>");
            questionLabel.setFont(new Font(null, Font.PLAIN, 22));
            panel2.add(questionLabel);

            JLabel lab = new JLabel("Possible meanings:");
            lab.setFont(new Font(null, Font.PLAIN, 18));
            lab.setAlignmentX(Component.LEFT_ALIGNMENT);
            lab.setHorizontalAlignment(SwingConstants.LEFT);
            panel2.add(lab);

            ButtonGroup answersGroup = new ButtonGroup();

            for (String wordMeaning : question.getWordMeanings()) {
                JRadioButton meaning = new JRadioButton("<html><p>" + wordMeaning + "</p></html>");
                meaning.setFont(new Font(null, Font.PLAIN, 18));
                meaningRadioButtons.add(meaning);
                answersGroup.add(meaning);
                panel2.add(meaning);
            }

            panel2.add(Box.createRigidArea(new Dimension(0, 10)));
            JLabel timerLabel = new JLabel("00:00");
            timerLabel.setFont(new Font(null, Font.PLAIN, 22));
            timer = new Timer(1000, new TimerIncrementEvent(timerLabel, System.currentTimeMillis(), frameInstance.getCurrentScoreboard(), frameInstance.isGuest()));
            panel2.add(timerLabel);
            resultLabel = new JLabel();
            panel2.add(resultLabel);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
            JButton statisticsButton = new JButton("Statistics");
            statisticsButton.addActionListener(e -> new StatisticsDialog(frameInstance));
            buttonPanel.add(statisticsButton);

            JButton mostDifficultButton = new JButton("Most difficult");
            mostDifficultButton.addActionListener(e -> new DifficultDialog(frameInstance));
            buttonPanel.add(mostDifficultButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            CorrectButton = new JButton("Correct");
            CorrectButton.addActionListener(new CorrectionEvent(frameInstance, this));
            buttonPanel.add(CorrectButton);

            ProceedButton = new JButton("Skip");
            ProceedButton.addActionListener(e -> timer.stop());
            ProceedButton.addActionListener(new BeginEvent(frameInstance));
            buttonPanel.add(ProceedButton);

            buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            this.add(panel2, BorderLayout.LINE_START);
            this.add(buttonPanel, BorderLayout.PAGE_END);
        } catch (Exception e) {
            this.add(new JLabel("<html>No incorrect questions are left.</html>"));
        }
    }

    public ArrayList<JRadioButton> getMeaningRadioButtons() {
        return meaningRadioButtons;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Timer getTimer() {
        return timer;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public JButton getProceedButton() {
        return ProceedButton;
    }

    public JButton getCorrectButton() {
        return CorrectButton;
    }
}

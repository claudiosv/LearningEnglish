package it.unibz.cspiess.panel;

import it.unibz.cspiess.MainFrame;
import it.unibz.cspiess.Question;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by claudio on 27/01/2017.
 */
public class MostDifficultPanel extends JPanel {
    public MostDifficultPanel(MainFrame frameInstance) {
        this.setLayout(new GridLayout(2, 1));
        this.setBorder(new EmptyBorder(5, 10, 5, 5));

        ArrayList<Question> difficultQuestions = frameInstance.getQuestions().getQuestions();
        Collections.sort(difficultQuestions,
                (o1, o2) -> Integer.compare(o2.getErrorPercentage(), o1.getErrorPercentage()));

        String message = "";
        int totalErrors = 0;
        int count = 0;
        int fiveOrLess = difficultQuestions.size() > 5 ? 5 : difficultQuestions.size();
        JPanel questionsPanels = new JPanel();

        for (count = 0; count < fiveOrLess; count++) {
            if (difficultQuestions.get(count).getIncorrectCount() > 0) {
                JPanel questionPanel = new JPanel(new GridBagLayout());//(new GridLayout(1,2, 10, 10));
                questionPanel.setBorder(new MatteBorder(1, 1, (count == (fiveOrLess - 1) ? 1 : 0), 1, Color.GRAY));
                JLabel label1 = new JLabel("<html>" + difficultQuestions.get(count).getQuestionSentence() + "</html>");
                GridBagConstraints c = new GridBagConstraints();
                c.weighty = 0.8;
                c.gridx = 0;
                JLabel label2 = new JLabel("<html>" + difficultQuestions.get(count).getErrorPercentage() + "% wrong</html>");

                GridBagConstraints c1 = new GridBagConstraints();
                c1.weighty = 0.2;
                c1.gridx = 1;
                c1.insets = new Insets(0, 50, 0, 0);
                questionPanel.add(label1, c);
                questionPanel.add(label2, c1);

                questionsPanels.add(questionPanel);
            }
            totalErrors += difficultQuestions.get(count).getIncorrectCount();
        }
        questionsPanels.setLayout(new GridLayout(fiveOrLess, 2));
        message = "<html>The " + count + " most difficult question" + (count > 1 ? "s are" : " is") + ":" + message;
        if (totalErrors == 0) {
            message = "No question has been answered incorrectly. Please, answer some questions incorrectly first.";
        }

        this.add(new JLabel(message));
        this.add(questionsPanels);
    }
}

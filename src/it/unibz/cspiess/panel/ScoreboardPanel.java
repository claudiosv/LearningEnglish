package it.unibz.cspiess.panel;

import it.unibz.cspiess.MainFrame;
import it.unibz.cspiess.Scoreboard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by claudio on 27/01/2017.
 */
public class ScoreboardPanel extends JPanel {
    private MainFrame frameInstance;
    private Scoreboard currentScoreboard;

    public ScoreboardPanel(MainFrame frameInstance, Scoreboard differentScoreboard) {
        this.frameInstance = frameInstance;
        this.currentScoreboard = differentScoreboard;
        Initialise();
    }

    public ScoreboardPanel(MainFrame frameInstance) {
        this.frameInstance = frameInstance;
        this.currentScoreboard = frameInstance.getCurrentScoreboard();
        Initialise();
    }

    public void Initialise() {
        this.frameInstance = frameInstance;
        this.setLayout(new GridLayout(6, 1));
        this.setBorder(new EmptyBorder(5, 10, 5, 5));

        this.add(new JLabel("The scoreboard of: " + currentScoreboard.getNickname()));


        JPanel questionPanel = new JPanel(new GridBagLayout());//(new GridLayout(1,2, 10, 10));
        questionPanel.setBorder(new MatteBorder(1, 1, 0, 1, Color.GRAY));
        JLabel questionTextLabel = new JLabel("<html>Questions shown</html>");
        GridBagConstraints questionLabelConstraints = new GridBagConstraints();
        questionLabelConstraints.weighty = 0.8;
        questionLabelConstraints.gridx = 0;
        JLabel countLabel = new JLabel("<html>" + currentScoreboard.getQuestionsShown() + " questions</html>");

        GridBagConstraints countLabelConstraints = new GridBagConstraints();
        countLabelConstraints.weighty = 0.2;
        countLabelConstraints.gridx = 1;
        countLabelConstraints.insets = new Insets(0, 50, 0, 0);
        questionPanel.add(questionTextLabel, questionLabelConstraints);
        questionPanel.add(countLabel, countLabelConstraints);

        this.add(questionPanel);

        questionPanel = new JPanel(new GridBagLayout());//(new GridLayout(1,2, 10, 10));
        questionPanel.setBorder(new MatteBorder(1, 1, 0, 1, Color.GRAY));
        questionTextLabel = new JLabel("<html>Questions correct</html>");
        questionLabelConstraints = new GridBagConstraints();
        questionLabelConstraints.weighty = 0.8;
        questionLabelConstraints.gridx = 0;
        countLabel = new JLabel("<html>" + currentScoreboard.getQuestionsCorrect() + " questions</html>");

        countLabelConstraints = new GridBagConstraints();
        countLabelConstraints.weighty = 0.2;
        countLabelConstraints.gridx = 1;
        countLabelConstraints.insets = new Insets(0, 50, 0, 0);
        questionPanel.add(questionTextLabel, questionLabelConstraints);
        questionPanel.add(countLabel, countLabelConstraints);

        this.add(questionPanel);

        questionPanel = new JPanel(new GridBagLayout());//(new GridLayout(1,2, 10, 10));
        questionPanel.setBorder(new MatteBorder(1, 1, 0, 1, Color.GRAY));
        questionTextLabel = new JLabel("<html>Questions wrong</html>");
        questionLabelConstraints = new GridBagConstraints();
        questionLabelConstraints.weighty = 0.8;
        questionLabelConstraints.gridx = 0;
        countLabel = new JLabel("<html>" + currentScoreboard.getQuestionsWrong() + " questions</html>");

        countLabelConstraints = new GridBagConstraints();
        countLabelConstraints.weighty = 0.2;
        countLabelConstraints.gridx = 1;
        countLabelConstraints.insets = new Insets(0, 50, 0, 0);
        questionPanel.add(questionTextLabel, questionLabelConstraints);
        questionPanel.add(countLabel, countLabelConstraints);

        this.add(questionPanel);

        questionPanel = new JPanel(new GridBagLayout());//(new GridLayout(1,2, 10, 10));
        questionPanel.setBorder(new MatteBorder(1, 1, 0, 1, Color.GRAY));
        questionTextLabel = new JLabel("<html>Avg. question time</html>");
        questionLabelConstraints = new GridBagConstraints();
        questionLabelConstraints.weighty = 0.8;
        questionLabelConstraints.gridx = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date(currentScoreboard.getAverageTime() * 1000);
        countLabel = new JLabel("<html>" + dateFormat.format(date) + "</html>");

        countLabelConstraints = new GridBagConstraints();
        countLabelConstraints.weighty = 0.2;
        countLabelConstraints.gridx = 1;
        countLabelConstraints.insets = new Insets(0, 50, 0, 0);
        questionPanel.add(questionTextLabel, questionLabelConstraints);
        questionPanel.add(countLabel, countLabelConstraints);

        this.add(questionPanel);

        questionPanel = new JPanel(new GridBagLayout());//(new GridLayout(1,2, 10, 10));
        questionPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
        questionTextLabel = new JLabel("<html>Success percentage</html>");
        questionLabelConstraints = new GridBagConstraints();
        questionLabelConstraints.weighty = 0.8;
        questionLabelConstraints.gridx = 0;
        countLabel = new JLabel("<html>" + currentScoreboard.getSuccessPercentage() + "%</html>");

        countLabelConstraints = new GridBagConstraints();
        countLabelConstraints.weighty = 0.2;
        countLabelConstraints.gridx = 1;
        countLabelConstraints.insets = new Insets(0, 50, 0, 0);
        questionPanel.add(questionTextLabel, questionLabelConstraints);
        questionPanel.add(countLabel, countLabelConstraints);

        this.add(questionPanel);
    }
}

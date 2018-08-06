package it.unibz.cspiess;

import it.unibz.cspiess.panel.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by claudio on 18/01/2017.
 */
public class MainFrame {
    private Questions questions;
    private Scoreboards scoreboards;
    private JFrame mainFrame;
    private Scoreboard currentScoreboard;
    private boolean isGuest;
    private boolean incorrectOnly;

    public MainFrame() {
        Initialise();
    }

    private void Initialise() {
        scoreboards = DatabaseManager.loadScoreboards();
        questions = DatabaseManager.loadQuestions();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("Error setting interface feel");
        }

        mainFrame = new JFrame("Learning English");
        JMenuBar mainMenuBar = new JMenuBar();
        JMenu exitMenu = new JMenu("Menu");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(aboutMenuItem.getComponent(),
                "Learning English by Claudio Spiess\n\n"
                        + "Claudio studies Computer Science at the Free University of Bolzano since 2016. His favourite IDE is IntelliJ.\n"
                        + "He developed Learning English with the help of JavaDocs, especially for Swing's lovely layout managers.\n\n"
                        + "Number of classes: 26\n\nNumber of methods: 117\n\nTotal number of lines of code: 1574", "About Learning English", JOptionPane.INFORMATION_MESSAGE));

        exitMenu.add(aboutMenuItem);
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(e -> {
            DatabaseManager.saveQuestions(questions);
            DatabaseManager.saveScoreboards(scoreboards);
        });
        exitMenu.add(saveMenuItem);
        exitMenu.add(exitMenuItem);
        mainMenuBar.add(exitMenu);

        mainFrame.setJMenuBar(mainMenuBar);
        mainFrame.setPreferredSize(new Dimension(1000, 600));

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatabaseManager.saveQuestions(questions);
                DatabaseManager.saveScoreboards(scoreboards);
            }
        });
        mainFrame.setContentPane(new StartPanel(this));
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public Scoreboard getCurrentScoreboard() {
        return currentScoreboard;
    }

    public void setCurrentScoreboard(Scoreboard currentScoreboard) {
        this.currentScoreboard = currentScoreboard;
    }

    public Scoreboards getScoreboards() {
        return scoreboards;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public boolean isIncorrectOnly() {
        return incorrectOnly;
    }

    public void setIncorrectOnly(boolean incorrectOnly) {
        this.incorrectOnly = incorrectOnly;
    }
}

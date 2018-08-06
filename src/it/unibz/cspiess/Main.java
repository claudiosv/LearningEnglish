package it.unibz.cspiess;

import javax.swing.*;

/**
 * Created by claudio on 18/01/2017.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}

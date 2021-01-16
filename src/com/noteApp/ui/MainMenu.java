package com.noteApp.ui;

import com.noteApp.be.Note;
import com.noteApp.be.NotesHandler;
import javax.swing.*;
import java.awt.*;
import java.util.List;


class MainMenu extends JFrame {


    public MainMenu() {
        JFrame menuFrame = new JFrame("Welcome Aboard");
        menuFrame.setSize(500, 500);
        menuFrame.setLayout(new FlowLayout());
        JMenuBar menuBar = new JMenuBar();
//        menuFrame.setLayout(new GridLayout(2,1));
        JPanel upperButtons = new JPanel();
        upperButtons.getPreferredSize();
        JPanel lowerButtonsPanel = new JPanel();
        lowerButtonsPanel.getPreferredSize();
        JLabel recentNotes = new JLabel("Recent Notes: ");
        JButton recentNoteButton = new JButton();
        JButton newN = new JButton("New");
        newN.getPreferredSize();
        newN.setToolTipText("New Note");
        newN.addActionListener(a -> {
            if (a.getSource() == newN) {
                NewNote firstNote = new NewNote();
            }
        });
        JButton myNotes = new JButton("Get Notes");
        myNotes.getPreferredSize();
        myNotes.addActionListener(e -> {
            if (e.getSource() == myNotes) {
                List<Note> allNotes = NotesHandler.getAllNotes();

                for (Note n : allNotes) {
                    JTextPane textPane = new JTextPane();
                    textPane.setSize(300,300);
                    BoxLayout boxLayout = new BoxLayout(textPane,BoxLayout.Y_AXIS);
                    textPane.setLayout(boxLayout);
                    textPane.setEditable(false);
                    textPane.setText(n.getText());
                    menuFrame.setJMenuBar(menuBar);
                    menuFrame.add(textPane);
                    menuFrame.pack();
                    menuFrame.setVisible(true);
                }
            }
        });
        JButton sharedOnes = new JButton("Shared Ones"); /// main frame buttons
        menuBar.add(newN);
        menuBar.add(myNotes);
        menuBar.add(sharedOnes);
        menuFrame.setJMenuBar(menuBar);
        menuFrame.add(upperButtons, BorderLayout.PAGE_START);
//        menuFrame.add(lowerButtonsPanel, BorderLayout.SOUTH);
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null); // puts the program on the center of the screen


        List<Note> allNotes = NotesHandler.getAllNotes();
        for (Note n : allNotes) {
            JTextPane textPane = new JTextPane();
            textPane.setSize(300,300);
            BoxLayout boxLayout = new BoxLayout(textPane,BoxLayout.Y_AXIS);
            textPane.setLayout(boxLayout);
            textPane.setEditable(false);
            textPane.setText(n.getText());
            menuFrame.setJMenuBar(menuBar);
            menuFrame.add(textPane);
            menuFrame.pack();
            menuFrame.setVisible(true);
        }
    }
}





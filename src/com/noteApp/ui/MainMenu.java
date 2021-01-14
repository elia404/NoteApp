package com.noteApp.ui;

import com.noteApp.be.Note;
import com.noteApp.be.NotesHandler;
import javax.swing.*;
import java.awt.*;
import java.util.List;


class MainMenu extends JFrame {



    public MainMenu() {
        JFrame menuFrame = new JFrame("Welcome Aboard");
        menuFrame.setSize(550,550);
        menuFrame.setLayout(new GridLayout(6,3));
        JPanel upperButtons = new JPanel();
        upperButtons.setSize(250,200);
        JPanel lowerButtonsPanel = new JPanel();
        lowerButtonsPanel.setSize(200,70);
        JLabel recentNotes = new JLabel("Recent Notes: ");
        JButton recentNoteButton = new JButton();
        JButton newN = new JButton("New");
        newN.setToolTipText("New Note");
        newN.addActionListener(a -> {
            if(a.getSource()==newN) {
                NewNote firstNote = new NewNote();
            }
        });
        JButton myNotes = new JButton("Get Notes");
        myNotes.addActionListener(e -> {
            if (e.getSource() == myNotes) {
                List<Note> allNotes=NotesHandler.getAllNotes();
                for( Note n : allNotes ){
                    JTextArea textArea = new JTextArea();
                    JPanel textPanel = new JPanel();
                    textPanel.setSize(250,100);
                    GroupLayout groupLayout = new GroupLayout(textPanel);
                    textPanel.setLayout(groupLayout);
                    groupLayout.setAutoCreateContainerGaps(true);
                    groupLayout.setAutoCreateGaps(true);
                    groupLayout.setHorizontalGroup((groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(textArea))));
                    groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(textArea)));
                    textArea.setText(n.getText());
                    menuFrame.add(upperButtons,BorderLayout.NORTH);
                    menuFrame.add(textPanel,BorderLayout.CENTER);
                    menuFrame.add(lowerButtonsPanel,BorderLayout.SOUTH);
                    menuFrame.setVisible(true);
                    }
            }
        });
        JButton sharedOnes = new JButton("Shared Ones"); /// main frame buttons
        upperButtons.add(newN);
        upperButtons.add(myNotes);
        upperButtons.add(sharedOnes);
        menuFrame.add(upperButtons, BorderLayout.PAGE_START);
        menuFrame.add(lowerButtonsPanel,BorderLayout.SOUTH);
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null); // puts the program on the center of the screen


        List<Note> allNotes=NotesHandler.getAllNotes();
        for (Note n : allNotes){
            JTextArea textArea = new JTextArea();
            JPanel textPanel = new JPanel();
            textPanel.setSize(250,100);
            GroupLayout groupLayout = new GroupLayout(textPanel);
            textPanel.setLayout(groupLayout);
            groupLayout.setAutoCreateContainerGaps(true);
            groupLayout.setAutoCreateGaps(true);
            groupLayout.setHorizontalGroup((groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(textArea))));
            groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textArea)));
            textArea.setText(n.getText());
            menuFrame.add(upperButtons,BorderLayout.NORTH);
            menuFrame.add(textPanel,BorderLayout.CENTER);
            menuFrame.add(lowerButtonsPanel,BorderLayout.SOUTH);
            menuFrame.setVisible(true);

        }
    }
    }



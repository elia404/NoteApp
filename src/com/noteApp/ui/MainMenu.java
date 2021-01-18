package com.noteApp.ui;

import com.noteApp.be.Note;
import com.noteApp.be.NotesHandler;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
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

        JButton recentNoteButton = new JButton();
        JButton newN = new JButton("New Note");
        newN.getPreferredSize();
        newN.setToolTipText("New Note");
        newN.addActionListener(a -> {
            newNoteAction(newN, a);
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
                    textPane.setToolTipText(n.getId() + "");
                    textPane.setText(n.getText());
                    System.out.println(n.getId());
                    System.out.println(textPane.getToolTipText());
                    textPane.addMouseListener(new MouseListener() {

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            NotesHandler.getSingleNote(n.getId());
                            NewNote secondNote = new NewNote();
                            secondNote.noteTextArea.setText(n.getText());
                            secondNote.saveAs.addActionListener(e1 -> {
                                if(e1.getSource()== secondNote.saveAs){
                                    NotesHandler.updateNote(n.getId(),secondNote.noteTextArea.getText());
                                    textPane.setText(secondNote.noteTextArea.getText());


                                }
                            });
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
//                            NotesHandler.getSingleNote(n.getId());
//                            NewNote secondNote = new NewNote();
//                            secondNote.noteTextArea.setText(n.getText());
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });

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
//        menuFrame.add(upperButtons, BorderLayout.PAGE_START);
//        menuFrame.add(lowerButtonsPanel, BorderLayout.SOUTH);
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null); // puts the program on the center of the screen


//        List<Note> allNotes = NotesHandler.getAllNotes();
//        for (Note n : allNotes) {
//            JTextPane textPane = new JTextPane();
//            textPane.setSize(300,300);
//            BoxLayout boxLayout = new BoxLayout(textPane,BoxLayout.Y_AXIS);
//            textPane.setLayout(boxLayout);
//            textPane.setEditable(false);
//            textPane.setContentType(n.getId()+"");
//            textPane.setText(n.getText());
//            System.out.println(n.getId());
//            System.out.println(textPane.getContentType());
//            menuFrame.setJMenuBar(menuBar);
//            menuFrame.add(textPane);
//            menuFrame.pack();
//            menuFrame.setVisible(true);
//        }
    }

    private void newNoteAction(JButton newN, java.awt.event.ActionEvent a) {
        if (a.getSource() == newN) {
            NewNote firstNote = new NewNote();
        }
    }
}





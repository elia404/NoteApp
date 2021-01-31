package com.noteApp.ui;

import com.noteApp.be.Note;
import com.noteApp.be.NotesHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

class MainMenu extends JFrame {


    public MainMenu() {
        JFrame menuFrame = new JFrame("Welcome Aboard");
        menuFrame.setSize(500, 500);
        menuFrame.setLayout(new FlowLayout());
        JMenuBar menuBar = new JMenuBar();
        JPanel upperButtons = new JPanel();
        upperButtons.getPreferredSize();
        JPanel lowerButtonsPanel = new JPanel();
        lowerButtonsPanel.getPreferredSize();
        JButton newN = new JButton("New Note");
        newN.getPreferredSize();
        newN.setToolTipText("New Note");
        newN.addActionListener(a -> newNoteAction(newN, a));
        JButton myNotes = new JButton("Get Notes");
        myNotes.getPreferredSize();
        myNotes.addActionListener(e -> {
            try {
                textAction(menuFrame, menuBar);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        JButton sharedOnes = new JButton("Shared Ones");
        menuBar.add(newN);
        menuBar.add(myNotes);
        menuBar.add(sharedOnes);
        menuFrame.setJMenuBar(menuBar);
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null); // puts the program on the center of the screen

    }

    private void textAction(JFrame menuFrame, JMenuBar menuBar) throws SQLException, ClassNotFoundException {
        List<Note> allNotes = NotesHandler.getAllNotes();
        for (Note n: allNotes) {
            JTextPane textPane = new JTextPane();
            textPane.setSize(300, 300);
            BoxLayout boxLayout = new BoxLayout(textPane, BoxLayout.Y_AXIS);
            textPane.setLayout(boxLayout);
            textPane.setEditable(false);
            textPane.setToolTipText(n.getId() + "");
            textPane.setText(n.getText());
            System.out.println(n.getId());
            System.out.println(textPane.getToolTipText());
            textPane.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        NotesHandler.getSingleNote(n.getId());
                    } catch (SQLException | ClassNotFoundException t) {
                        t.printStackTrace();
                    }
                    NewNote secondNote = new NewNote();
                    JButton deleteButton = new JButton("Delete");
                    deleteButton.addActionListener(e12 -> {
                        if(e12.getSource()==deleteButton){
                            int a = JOptionPane.showConfirmDialog(secondNote.note,"Delete Note? ");
                            if (a == JOptionPane.YES_OPTION) {
                                try {
                                    NotesHandler.deleteNote(n.getId());
                                    secondNote.note.setVisible(false);
                                } catch (SQLException | ClassNotFoundException t) {
                                    t.printStackTrace();
                                }
                                secondNote.note.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                secondNote.note.setVisible(false);
                            }
                        }
                    });
                    secondNote.lowerButtonsPanel.add(deleteButton);
                    secondNote.noteTextArea.setText(n.getText());
                    secondNote.saveAs.addActionListener(e1 -> {
                        if (e1.getSource() == secondNote.saveAs) {
                            try {
                                NotesHandler.updateNote(n.getId(), secondNote.noteTextArea.getText());
                            } catch (SQLException | ClassNotFoundException t) {
                                t.printStackTrace();
                            }
                            textPane.setText(secondNote.noteTextArea.getText());
                        }
                    });
                }
                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent ev) {
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
    private void newNoteAction(JButton newN, java.awt.event.ActionEvent a) {
        if (a.getSource() == newN) {
            NewNote firstNote = new NewNote();
        }
    }
}
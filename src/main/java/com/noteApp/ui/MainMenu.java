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
        JPanel panelCont = new JPanel();
        menuFrame.add(panelCont,BorderLayout.CENTER);
        JMenuBar menuBar = new JMenuBar();
        JButton newN = new JButton("New Note");
        newN.getPreferredSize();
        newN.setToolTipText("New Note");
        newN.addActionListener(a ->
                newNoteAction(newN, a));

        JButton getNotes = new JButton("Get Notes");
        JButton deleteAll = new JButton("Delete All");
        menuBar.add(newN);
        menuBar.add(getNotes);
        menuBar.add(deleteAll);
        menuFrame.setJMenuBar(menuBar);
        menuFrame.add(panelCont,BorderLayout.CENTER);
        getNotes.getPreferredSize();

        getNotes.addActionListener(e -> {
            try {

                getNotes(menuFrame, menuBar,panelCont);

            } catch (Exception f) {
                System.err.println(f.getClass().getName() + ": " + f.getMessage());
            }
        });

        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelCont.setVisible(false);
                panelCont.removeAll();
                panelCont.revalidate();
                panelCont.repaint();
                panelCont.setVisible(true);

                panelCont.getUI();
            }
        });
        menuFrame.setJMenuBar(menuBar);
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);

    }

    public void getNotes(JFrame menuFrame, JMenuBar menuBar, JPanel panelCont) throws SQLException, ClassNotFoundException { removeAll();
        List<Note> allNotes = NotesHandler.getAllNotes();
        for (Note n: allNotes) {
            JTextPane textPane = new JTextPane();
            textPane.setSize(300, 300);
            BoxLayout boxLayout = new BoxLayout(textPane, BoxLayout.Y_AXIS);
            textPane.setLayout(boxLayout);
            textPane.setEditable(false);
            textPane.setToolTipText(n.getId() + "");
            textPane.setText(n.getText());
            panelCont.add(textPane);

            textPane.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    NotesHandler.getSingleNote(n.getId());
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
                                    System.err.println(t.getClass().getName() + ": " + t.getMessage());
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
                                NotesHandler.updateOrInsert(n.getId(), secondNote.noteTextArea.getText());

                            } catch (SQLException | ClassNotFoundException t) {
                                System.err.println(t.getClass().getName() + ": " + t.getMessage());
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

            menuFrame.add(panelCont,BorderLayout.CENTER);
            menuFrame.setJMenuBar(menuBar);
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
package com.noteApp.ui;


import com.noteApp.be.NotesHandler;



import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JColorChooser;


public class NewNote {

    JSpinner fontSizeSpinner;
    JFrame note;
    JPanel notePanel;
    JPanel lowerButtonsPanel;
    JPanel textPanel;
    JTextArea noteTextArea;
    JMenuBar noteMenuBar;
    JButton saveAs;
    JButton openFile;
    JFileChooser fileChooser;
    JButton cancel;
    JMenu textEditor;
    JMenu style;
    JMenuItem bColor;
    JMenuItem textColor;


    public NewNote() {

        note = new JFrame();
        note.setVisible(true);
        notePanel = new JPanel();
        textPanel = new JPanel();
        noteTextArea = new JTextArea();
        noteTextArea.setLineWrap(true);
        noteTextArea.setWrapStyleWord(true);
        noteTextArea.setSize(250, 350);
        noteTextArea.setFont(new Font("Ariel", Font.PLAIN, 20));
        noteTextArea.setVisible(true);
        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(30, 15));
        fontSizeSpinner.setValue(20);
        fontSizeSpinner.addChangeListener(e -> noteTextArea.setFont(new Font(noteTextArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue())));
        noteMenuBar = new JMenuBar();// main menu ---  new Note
        saveAs = new JButton("Save @ Close");
        saveAs.addActionListener(e -> {
            if (e.getSource() == saveAs) {
                NotesHandler.addNote(noteTextArea.getText());
                note.setVisible(false);
            }
        });

        cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
            if (e.getSource() == cancel) {
                System.exit(0);
            }
        });
        lowerButtonsPanel = new JPanel();
        lowerButtonsPanel.add(saveAs);
        lowerButtonsPanel.add(cancel);
        textEditor = new JMenu("Text Editor");
        bColor = new JMenuItem("Background Color"); ///items
        textColor = new JMenuItem("Text Color");
        textColor.addActionListener(e -> {
            Color colors = null;
            if (e.getSource() == textColor) {
                JColorChooser textColorChooser = new JColorChooser();
                colors = textColorChooser.showDialog(null, "Choose color", Color.BLACK);
            }
            noteTextArea.setForeground(colors);
        });
        bColor.addActionListener(e -> {
            Color co = JColorChooser.showDialog(noteMenuBar, "Choose", Color.CYAN);
            noteTextArea.setBackground(co);

        });

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        JComboBox fontBox = new JComboBox(fonts);
        fontBox.addActionListener(e -> {
            if (e.getSource() == fontBox) {
                noteTextArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, noteTextArea.getFont().getSize()));
            }
        });

        style = new JMenu("Style");
        textEditor.add(bColor);
        textEditor.add(textColor);
        noteMenuBar.add(style);
        noteMenuBar.add(textEditor);
        noteMenuBar.add(fontBox);
        noteMenuBar.add(fontSizeSpinner);
        note.setJMenuBar(noteMenuBar);


        note.add(noteMenuBar, BorderLayout.PAGE_START);
        note.add(noteTextArea, BorderLayout.CENTER);
        note.add(lowerButtonsPanel, BorderLayout.SOUTH);
        note.setSize(500, 500);
        note.setLocationRelativeTo(null);
        note.setVisible(true);



    }



}



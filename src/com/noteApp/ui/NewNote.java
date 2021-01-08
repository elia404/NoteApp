package com.noteApp.ui;


import java.io.IOException;
import java.io.File;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import javax.swing.JColorChooser;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewNote extends JFrame {
    JScrollPane scrollPane;
    JSpinner fontSizeSpinner;
    JComboBox fontBox;
    JFrame note;
    JPanel notePanel;
    JPanel lowerButtonsPanel;
    JPanel textPanel;
    JTextArea noteTextArea;
    JMenuBar noteMenuBar;
    JButton saveAs;
    JButton openFile;
    JFileChooser fileChooser;
    JButton cancelb;
    JMenu textEditor;
    JMenu style;
    JMenuItem bColor;
    JMenuItem textColor;


    public NewNote() {
        this.setLayout(new FlowLayout());
        note = new JFrame();
        note.setVisible(true);
        note.setSize(570, 570);
        notePanel = new JPanel();
        textPanel = new JPanel();
        noteTextArea = new JTextArea();

        noteTextArea.setSize(500, 500);
        noteTextArea.setLineWrap(true);
        noteTextArea.setWrapStyleWord(true);
        noteTextArea.setFont(new Font("Ariel", Font.PLAIN, 20));
        scrollPane = new JScrollPane(noteTextArea);
        scrollPane.setPreferredSize(new Dimension(450, 450));
        scrollPane.setVisible(true);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(30, 15));
        fontSizeSpinner.setValue(20);
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                noteTextArea.setFont(new Font(noteTextArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));
            }
        });
        noteMenuBar = new JMenuBar();// main menu ---  new Note
        saveAs = new JButton("Save As");
        saveAs.addActionListener(new ActionListener() {
                                     @Override
                                     public void actionPerformed(ActionEvent e) {
                                         if (e.getSource() == saveAs) {
                                             fileChooser = new JFileChooser();
                                             fileChooser.setDialogTitle("Save");
                                             fileChooser.setCurrentDirectory(new File("C:\\Users\\USER\\elia\\notes"));
                                             int a = fileChooser.showSaveDialog(NewNote.this);
                                             if (a == JFileChooser.APPROVE_OPTION) {
                                                 File file = fileChooser.getSelectedFile();
                                                 SaveFile(noteTextArea.getText(), file);
                                             }
                                             if (a == JFileChooser.CANCEL_OPTION) {
                                                 System.exit(0);
                                             }

                                         }
                                     }
                                 });

                    cancelb = new JButton("Cancel");
                    cancelb.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() == cancelb) {
                                System.exit(0);
                            }
                        }
                    });
                    lowerButtonsPanel = new JPanel();
                    lowerButtonsPanel.add(saveAs);
                    lowerButtonsPanel.add(cancelb);
                    openFile = new JButton("Open");
                    openFile.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==openFile){

                            }
                        }
                    });
                    textEditor = new JMenu("Text Editor");
                    bColor = new JMenuItem("Background Color"); ///items
                    textColor = new JMenuItem("Text Color");
                    textColor.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Color colors = null;
                            if (e.getSource() == textColor) {
                                JColorChooser textColorChooser = new JColorChooser();
                                colors = textColorChooser.showDialog(null, "Choose color", Color.BLACK);
                            }
                            noteTextArea.setForeground(colors);
                        }
                    });
                    bColor.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Color co = JColorChooser.showDialog(noteMenuBar, "Choose", Color.CYAN);
                            noteTextArea.setBackground(co);

                        }
                    });

                    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

                    fontBox = new JComboBox(fonts);
                    fontBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() == fontBox) {
                                noteTextArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, noteTextArea.getFont().getSize()));
                            }
                        }
                    });

                    style = new JMenu("Style");
                    textEditor.add(bColor);
                    textEditor.add(textColor);
                    noteMenuBar.add(style);
                    noteMenuBar.add(textEditor);
                    noteMenuBar.add(fontBox);
                    noteMenuBar.add(fontSizeSpinner);
                    notePanel.add(noteMenuBar);

                    note.add(notePanel, BorderLayout.PAGE_START);
                    note.add(textPanel, BorderLayout.CENTER);
                    note.add(lowerButtonsPanel, BorderLayout.PAGE_END);
                    note.add(scrollPane, BorderLayout.EAST);
                    note.setJMenuBar(noteMenuBar);
                    ///this.setLocationRelativeTo(null);

                    // end


                }

    public void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex){
            Logger.getLogger(NewNote.class.getName()).log(Level.SEVERE,null,ex);
        }
    }


    public void LoadFile(String content ,File file){
    }
}



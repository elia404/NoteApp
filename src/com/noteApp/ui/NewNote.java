package com.noteApp.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;


public class NewNote extends JFrame {
    JScrollPane scrollPane;
    JSpinner fontSizeSpinner;
    JComboBox fontBox;
    JFrame note;
    JTextArea noteTextArea;
    JMenuBar noteMenuBar;
    JMenu file;
    JMenu textEditor;
    JMenu style;
    JMenuItem saveAs;
    JMenuItem open;
    JMenuItem bColor;
    JMenuItem textColor;




    public NewNote() {
        this.setLayout(new FlowLayout());
        note = new JFrame();
    note.setVisible(true);
    note.setSize(550,550);
    noteTextArea = new JTextArea();
    noteTextArea.setSize(500,500);
    noteTextArea.setLineWrap(true);
    noteTextArea.setWrapStyleWord(true);
    noteTextArea.setFont(new Font("Ariel",Font.PLAIN,20));
    scrollPane = new JScrollPane(noteTextArea);
    scrollPane.setPreferredSize(new Dimension(450,450));
    scrollPane.setVisible(true);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    fontSizeSpinner = new JSpinner();
    fontSizeSpinner.setPreferredSize(new Dimension(30,15));
    fontSizeSpinner.setValue(20);
    fontSizeSpinner.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            noteTextArea.setFont(new Font(noteTextArea.getFont().getFamily(),Font.PLAIN,(int)fontSizeSpinner.getValue()));
        }
    });
    noteMenuBar = new JMenuBar();// main menu ---  new Note
        file = new JMenu("File");
    file.setToolTipText("Save as, import..");
    saveAs = new JMenuItem("Save As");
    open = new JMenuItem("Open");
    file.add(saveAs);
    file.add(open);
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
           noteTextArea.setForeground(colors);  /// ask esther!!!
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
        if(e.getSource()==fontBox){
            noteTextArea.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,noteTextArea.getFont().getSize()));
        }
       }
   });


   textEditor.add(bColor);
   textEditor.add(textColor);
   style = new JMenu("Style");
    noteMenuBar.add(file);
    noteMenuBar.add(style);
    noteMenuBar.add(textEditor);
    noteMenuBar.add(fontBox);
    noteMenuBar.add(fontSizeSpinner);
    note.add(noteMenuBar);
    note.setJMenuBar(noteMenuBar);
    note.add(scrollPane);
    this.setLocationRelativeTo(null);

    // end






    }
}

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
    JPanel notePanel;
    JPanel lowerButtonsPanel;
    JPanel textPanel;
    JTextArea noteTextArea;
    JMenuBar noteMenuBar;
    JButton saveAs;
    JButton cancel;
    JMenu textEditor;
    JMenu style;
    JMenuItem open;
    JMenuItem bColor;
    JMenuItem textColor;




    public NewNote() {
        this.setLayout(new FlowLayout());
        note = new JFrame();
    note.setVisible(true);
    note.setSize(570,570);
    notePanel = new JPanel();
    textPanel = new JPanel();
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
    saveAs = new JButton("Save As");
    cancel = new JButton("Cancel");
    cancel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()== cancel){
                System.exit(0);
            }
        }
    });
    lowerButtonsPanel = new JPanel();
    lowerButtonsPanel.add(saveAs,BorderLayout.PAGE_END);
    lowerButtonsPanel.add(cancel,BorderLayout.PAGE_END);
    open = new JMenuItem("Open");
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
        if(e.getSource()==fontBox){
            noteTextArea.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,noteTextArea.getFont().getSize()));
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

   note.add(notePanel,BorderLayout.PAGE_START);
   note.add(textPanel,BorderLayout.CENTER);
   note.add(lowerButtonsPanel,BorderLayout.PAGE_END);
   note.add(scrollPane,BorderLayout.EAST);
    note.setJMenuBar(noteMenuBar);
    ///this.setLocationRelativeTo(null);

    // end






    }
}

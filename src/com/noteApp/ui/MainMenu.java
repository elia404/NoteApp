package com.noteApp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame  {

    public MainMenu(){
        this.setLayout(new BorderLayout());
        JMenuBar mainMenu = new JMenuBar();
        mainMenu.setVisible(true);
        JMenu add = new JMenu("Add");
        add.setToolTipText("New Note");
        JMenu upload = new JMenu("Upload");
        JMenu myArea = new JMenu("My Area");
        JMenu sharedOnes = new JMenu("Shared Ones"); /// main menu buttons
        mainMenu.add(add);
        mainMenu.add(upload);
        mainMenu.add( myArea);
        mainMenu.add(sharedOnes);
        JMenuItem newNote = new JMenuItem("New Note"); /// inside menu buttons
        add.add(newNote);
        this.add(mainMenu, BorderLayout.NORTH);
        this.setLocationRelativeTo(null); // puts the program on the center of the screen
        newNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewNote firstNote = new NewNote();
                firstNote.pack();
            }
        });



    }

}

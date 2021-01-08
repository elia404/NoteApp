package com.noteApp.ui;

import javax.swing.*;
import java.awt.*;

public class MainActivity extends JFrame {

    public static void main(String args[]) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
        mainMenu.setSize(300,100);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}


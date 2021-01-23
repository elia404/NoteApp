package com.noteApp.be;

import java.sql.Statement;
import java.util.Random;

public class Note {
    private String text;
    public int id;

    public Note(String text) {
        this.text = text;


    }

    public String getText() {
        return text;
    }



    public void setText(String text) {
        this.text = text;
    }



    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}

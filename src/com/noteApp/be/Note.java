package com.noteApp.be;

import java.util.Random;

public class Note {
    private String text;
    private int id;

    public Note(String text) {
        this.text = text;
        Random rand = new Random();
        this.id =  rand.nextInt(1000);
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

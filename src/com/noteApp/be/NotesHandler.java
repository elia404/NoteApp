package com.noteApp.be;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class  NotesHandler  {
    static Map<Integer, Note> allNotes = new HashMap<Integer, Note>();


    public static List<Note> getAllNotes() {
        return new ArrayList<>(allNotes.values());

    }

    public static void getSingleNote(int id) {
        allNotes.get(id);
    }

    public static void addNote( String text) {
        Note newNote=new Note(text);
        allNotes.put(newNote.getId(), newNote);
    }

    public static void updateNote(int id, String newText) {
        Note currentNote=allNotes.get(id);
        currentNote.setText(newText);
    }



}
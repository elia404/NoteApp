package com.noteApp.be;

import java.io.File;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class  NotesHandler  {
    static Map<Integer, Note> allNotes = new HashMap<Integer, Note>();


    public static List<Note> getAllNotes() {
        return new ArrayList<>(allNotes.values());

    }

    public static Note getSingleNote(int id) {
        return allNotes.get(id);
    }

    public static void addNote( String text) {
        Note newNote=new Note(text);
        allNotes.put(newNote.getId(), newNote);
    }

    public static void updateNote(int id, String newText) {
        Note currentNote=allNotes.get(id);
        currentNote.setText(newText);
    }


//    File file;
//    String content;
//    String newContent;
//    String fileName;
//
//    public void setContent(String content, File file) {
//        try {
//            FileWriter fileWriter;
//            fileWriter = new FileWriter(file);
//            fileWriter.write(String.valueOf(content));
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void readContent(String content, JTextArea text) throws IOException {
//
//        FileInputStream fileInputStream = new FileInputStream(content);
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        content = bufferedReader.readLine();
//        text.append(content);
//
//    }
//
//    public void closeApp() {
//        System.exit(0);
//    }


}
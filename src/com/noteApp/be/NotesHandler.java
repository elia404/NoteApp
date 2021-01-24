package com.noteApp.be;
import java.util.ArrayList;
import java.util.List;



public class  NotesHandler  {
    static List<Note> allNotes = new ArrayList<>();



    public static  List<Note> getAllNotes() {
        DataBaseConnection db=new DataBaseConnection();
        return (List<Note>) db.select();



    }

    public static  Note getSingleNote(int id) {
        DataBaseConnection db = new DataBaseConnection();
        Note n = db.selectOne();
        n.setId(id);
      return n;
    }

    public static void addNote(String text) {
        DataBaseConnection db = new DataBaseConnection();
        db.insertNote(text);


    }

    public static void updateNote(int id, String newText) {
        DataBaseConnection db = new DataBaseConnection();
        db.update(id,newText);

    }


    public static void deleteNote(int id){
        DataBaseConnection db = new DataBaseConnection();
        db.deleteOne(id);

    }

}
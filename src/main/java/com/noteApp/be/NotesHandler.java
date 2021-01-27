package com.noteApp.be;
import java.util.List;

public class  NotesHandler  {
    public static DataBaseConnection db=new DataBaseConnection();

    public static  List<Note> getAllNotes() throws Exception {
        return (List<Note>) db.select();
    }

    public static void getSingleNote(int id) throws ClassNotFoundException {
        Note n = db.selectOne();
        n.setId(id);
    }

    public static void addNote(String text) throws ClassNotFoundException {
        db.insertNote(text);
    }

    public static void updateNote(int id, String newText) throws ClassNotFoundException {
        db.update(id,newText);
    }

    public static void deleteNote(int id) throws ClassNotFoundException {
        db.deleteOne(id);
    }

}
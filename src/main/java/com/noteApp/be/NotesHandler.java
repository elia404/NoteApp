package com.noteApp.be;
import java.util.List;

public class  NotesHandler  {
    public static DataBaseConnection db=new DataBaseConnection();

    public static  List<Note> getAllNotes(){
        return (List<Note>) db.select();
    }

    public static void getSingleNote(int id){
        Note n = db.selectOne();
        n.setId(id);
    }

    public static void addNote(String text){
        db.insertNote(text);
    }

    public static void updateNote(int id, String newText){
        db.update(id,newText);
    }

    public static void deleteNote(int id) throws ClassNotFoundException {
        db.deleteOne(id);
    }

}
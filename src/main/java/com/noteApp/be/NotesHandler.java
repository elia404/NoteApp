package com.noteApp.be;
import java.sql.SQLException;
import java.util.List;

public class  NotesHandler  {
    public static DataBaseConnection  db=  new DataBaseConnection();

    @SuppressWarnings("unchecked")
    public static  List<Note> getAllNotes(){
        return (List<Note>) db.select();
    }

    public static void getSingleNote(int id){
        Note n = db.selectOne();
        n.setId(id);
        n.setText(n.getText());

    }

    public static void addNote(String text) {
        db.insertNote(text);
    }

    public static void updateNote(int id, String newText) throws SQLException, ClassNotFoundException {
      db.update(id,newText);


    }

    public static void deleteNote(int id) throws SQLException, ClassNotFoundException {
        db.deleteOne(id);
    }

    public static void updateOrInsert(int id, String newText) throws SQLException, ClassNotFoundException {
        if (id==0){
           db.insertNote(newText);
        }else{
            db.update(id, newText);
        }
    }
}
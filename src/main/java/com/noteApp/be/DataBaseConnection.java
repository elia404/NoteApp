package com.noteApp.be;

import org.apache.commons.dbutils.QueryRunner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {
    String noteText;
    List<Note> allNotes = new ArrayList<>();
    Statement stmt = null;
    static Connection connection;

    static {
        try {
            connection = ConnectionPool.getConnectionPool();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public List select(){
        try {
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from public.notes;");
            while (resultSet.next()) {
               int noteId = resultSet.getInt(Constants.ID_COL);
                String  noteText = resultSet.getString(Constants.TEXT_COL);
                Note n = new Note(noteText);
                n.setId(noteId);
                allNotes.add(n);
                allNotes.toArray();
            }
            resultSet.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return allNotes;
    }

    public Note selectOne(){
        try {
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from notes where true;");
            while(resultSet.next()) {
               int noteId = resultSet.getInt(Constants.ID_COL);
                String  noteText = resultSet.getString(Constants.TEXT_COL);
                Note n = new Note(noteText);
                n.setId(noteId);
                return n;
            }
            resultSet.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return new Note(noteText);
    }

    public void insertNote(String text){
        try {
            stmt = connection.createStatement();
            String sql = "INSERT INTO public.notes(id, text) VALUES(default,'"+text+"')";
            int resultSet = stmt.executeUpdate(sql);
            System.out.println(resultSet);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }
    public void update(int id, String newText) {
        try {
            QueryRunner qr = new QueryRunner();
            String sql = "UPDATE public.notes SET text = '"+newText+"' WHERE id ='"+id+"'";
            int num = qr.update(connection,sql);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public void deleteOne(int id){
        try {
            QueryRunner qr = new QueryRunner();
            String sql = "DELETE FROM public.notes WHERE id= '"+id+"'";
            int num = qr.update(connection,sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

}

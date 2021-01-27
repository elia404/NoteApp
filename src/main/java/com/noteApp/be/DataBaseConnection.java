package com.noteApp.be;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {
    int noteId;
    String noteText;
    List<Note> allNotes = new ArrayList<Note>();
    Statement stmt = null;
    int resultSet;

    public List select() throws Exception {
        ConnectionPool.setUpPool();
        try {
            Connection connection = ConnectionPool.getConnectionPool();
            stmt = connection.createStatement();
            System.out.println("connected");
            ResultSet resultSet = stmt.executeQuery("select * from notes;");
            while (resultSet.next()) {
               int noteId = resultSet.getInt(Constants.ID_COL);
                String  noteText = resultSet.getString(Constants.TEXT_COL);
                System.out.printf("id = %s, text = %s", noteId, noteText);
                System.out.println();
                Note n = new Note(noteText);
                n.setId(noteId);
                allNotes.add(n);
            }
            resultSet.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return allNotes;
    }

    public Note selectOne() throws ClassNotFoundException {
        ConnectionPool.setUpPool();
        try {
            Connection connection = ConnectionPool.getConnectionPool();
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from notes where true;");
            while(resultSet.next()) {
                noteId = resultSet.getInt(Constants.ID_COL);
                String  noteText = resultSet.getString(Constants.TEXT_COL);
                Note n = new Note(noteText);
                n.setId(noteId);
                return n;
            }
            resultSet.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return new Note(noteText);
    }

    public void insertNote(String text) throws ClassNotFoundException {
        ConnectionPool.setUpPool();
        try {
            Connection connection = ConnectionPool.getConnectionPool();
            stmt = connection.createStatement();
            String sql = "INSERT INTO public.notes(id, text) VALUES(default,'"+text+"')";
            resultSet = stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }
    public void update(int id, String newText) throws ClassNotFoundException {
        ConnectionPool.setUpPool();
        try {
           Connection connection = ConnectionPool.getConnectionPool();
            stmt = connection.createStatement();
            String sql = "UPDATE public.notes SET text = '"+newText+"' WHERE id = '"+id+"'";
            resultSet = stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void deleteOne(int id) throws ClassNotFoundException {
        ConnectionPool.setUpPool();
        try {
          Connection connection = ConnectionPool.getConnectionPool();
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("DELETE FROM public.notes where id= '"+id+"' returning id");
            while (resultSet.next()) {
                Note n = new Note(noteText);
                n.getId();
            }
            resultSet.close();
            stmt.close();
            connection.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }
}
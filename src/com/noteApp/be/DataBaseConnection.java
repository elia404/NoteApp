package com.noteApp.be;

import com.noteApp.ui.NewNote;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {
    int noteId;
    Connection c = null;
    Statement stmt = null;
    String noteText;
    List<Note> allNotes = new ArrayList<>();

    public List select() {

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "noham");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from notes;");
            while (resultSet.next()) {
                noteId = resultSet.getInt("id");
                noteText = resultSet.getString("text");
                System.out.printf("id = %s, text = %s", noteId, noteText);
                System.out.println();
                Note n = new Note(noteText);
                n.setId(noteId);
                allNotes.add(n);


            }
            resultSet.close();
            stmt.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        return allNotes;

    }


    public Note selectOne() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "noham");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from notes where true;");
            while (resultSet.next()) {
                noteId = resultSet.getInt("id");
                noteText = resultSet.getString("text");
                Note n = new Note(noteText);
                n.setId(noteId);
                return n;
            }
            resultSet.close();
            stmt.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return new Note(noteText);
    }


    public void insertNote(String text) {
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "noham");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("INSERT INTO public.notes(id, text) VALUES(default,'"+text+"') returning id");

            while (resultSet.next()) {
                Note n = new Note(text);
                n.getId();
                n.getText();
                resultSet.getMetaData();

            }
//
            resultSet.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }



    }
    public void update(int id, String newText) {
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "noham");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("UPDATE public.notes SET text = '"+newText+"' WHERE id = '"+id+"' returning text" );

            while (resultSet.next()) {
                Note n = new Note(newText);
                n.setText(newText);
                n.getId();
                resultSet.getMetaData();

            }
            resultSet.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }



    }


    public void deleteOne(int id){
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "noham");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("DELETE FROM public.notes where id= '"+id+"' returning id");

            while (resultSet.next()) {
                Note n = new Note(noteText);
                n.getId();
            }

            resultSet.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }
}
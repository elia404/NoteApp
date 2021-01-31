package com.noteApp.be;

import net.proteanit.sql.DbUtils;
import org.apache.commons.dbutils.QueryRunner;


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {
    int noteId;
    String noteText;
    List<Note> allNotes = new ArrayList<>();
    Statement stmt = null;
    public static Connection connection;
    PreparedStatement preparedStmt = null;
    public JTable notes;

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
            ResultSet resultSet = stmt.executeQuery("select * from notes;");
            while (resultSet.next()) {
               int noteId = resultSet.getInt(Constants.ID_COL);
                String  noteText = resultSet.getString(Constants.TEXT_COL);
                Note n = new Note(noteText);
                n.setId(noteId);
                allNotes.add(n);

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
                noteId = resultSet.getInt(Constants.ID_COL);
                String  noteText = resultSet.getString(Constants.TEXT_COL);
                Note n = new Note(noteText);
                n.setId(noteId);
                return n;

            }
            resultSet.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            updateTable();
        }
        return new Note(noteText);
    }

    public void insertNote(String text){
        try {
            stmt = connection.createStatement();
            String sql = "INSERT INTO public.notes(id, text) VALUES(default,'"+text+"')";
            int resultSet = stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
//        updateTable();
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
//            stmt = connection.createStatement();
//            System.out.println("clala");
//            ResultSet resultSet = stmt.executeQuery("DELETE FROM public.notes WHERE id= '"+id+"'" );
//            while (resultSet.next()) {
//                Note n = new Note(noteText);
//                n.getId();
//
//            }
//            stmt.close();
//            resultSet.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

//    private void updateTable(){
//        try{
//
//            String sql = "select * from public.notes";
//            preparedStmt = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStmt.executeQuery();
//            notes.setModel(DbUtils.resultSetToTableModel(resultSet));
//
//        } catch (SQLException t) {
//            System.err.println(t.getClass().getName() + ": " + t.getMessage());
//        }
//    }
}

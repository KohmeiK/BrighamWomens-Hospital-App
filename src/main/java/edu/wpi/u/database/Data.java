package edu.wpi.u.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public abstract class Data {
    protected Connection conn = null;
    protected ResultSet rset;
    protected String url = "jdbc:derby:BWdb;bootPassword=bwdbpassword";
    protected static Database db;

    public Data(){

    }

    public void connect() {
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    public void readCSV(String filePath, String tableName){

        String tempPath = "temp.csv"; //TODO : Change path in jar file
        String str1 = "CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE ('APP', '" + tableName.toUpperCase() + "', '" + tempPath + "', ', ', null, null,1)";

        try {
            String content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
            String[] columns = content.split("\n", 2);
            //String[] attributes = content.split(","); TODO: Make table columns from header values
            columns[1] += "\n";
            File temp = new File(tempPath);
            if(temp.createNewFile()){
                System.out.println("File created");
            }
            FileWriter myWriter = new FileWriter(tempPath);
            myWriter.write(columns[1]);
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement p = conn.prepareStatement(str1);
            p.execute();
        }
        catch (Exception e){
            System.out.println("Path: " + filePath);
            //e.printStackTrace();
        }
    }

    public void saveCSV(String tableName, String filePath, String header){
        File f = new File(filePath);
        if(f.delete()){
            System.out.println("\n"); //TODO : Used to be "file deleted"
        }
        String str = "CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE ('APP','" + tableName.toUpperCase() + "','" + filePath + "',',',null,null)";
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Wants new file");
            e.printStackTrace();
        }

        try {
            String content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
            FileWriter fw = new FileWriter(filePath);
            fw.write(header);
            fw.write("\n");
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean updateField(String tableName, String idField, String id, String field, String val) {
        try {
            String str = "update " + tableName + " set" + field + "=? where " + idField + "=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, val);
            ps.setString(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update " + tableName + "'s " + field );
            return false;
        }
        return true;
    }

    public boolean updateField(String tableName, String idField, String id, String field, int val) {
        try {
            String str = "update " + tableName + " set" + field + "=? where " + idField + "=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setInt(1, val);
            ps.setString(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update " + tableName + "'s " + field );
            return false;
        }
        return true;
    }

/*    public void deleteTables() {
//        try {
//            String str = "drop table Nodes";
//            Statement s = conn.createStatement();
//            s.execute(str);
//            str = "drop table Edges";
//            s.execute(str);
//            str = "drop table Assignments";
//            s.execute(str);
//            str = "drop table Locations";
//            s.execute(str);
//            str = "drop table Requests";
//            s.execute(str);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }
    //Deprecated
    public void dropValues() {
//        try {
//            Statement ps = conn.createStatement();
//            String str = "alter table Locations drop constraint nodeID";
//            ps.execute(str);
//            str = "delete from Nodes";
//            ps.execute(str);
//            str = "delete from Edges";
//            ps.execute(str);
//            str = "alter table Locations drop constraint requestID";
//            ps.execute(str);
//            str = "alter table Assignments drop constraint requestID";
//            ps.execute(str);
//            str = "delete from Requests";
//            ps.execute(str);
//            str = "delete from Locations";
//            ps.execute(str);
//            str = "delete from Assigments";
//            ps.execute(str);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void stop() {
        dropValues();
        deleteTables();
        try{
            System.out.println("herro");
            //conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }*/
}

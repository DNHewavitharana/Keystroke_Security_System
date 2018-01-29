/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dilshan
 */
public class InsertData {
    
    public void insert(String name, double pressDelay, double changeDelay) {
        String sql = "INSERT INTO warehouses(name, pressDelay, changeDelay) VALUES(?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, pressDelay);
            pstmt.setDouble(3, changeDelay);
            pstmt.executeUpdate();
            System.out.println("Inserted data");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/Dilshan/Documents/NetBeansProjects/Security_System/SQLite/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    /**
     * select all rows in the warehouses table
     */
    public void selectAll(){
        String sql = "SELECT id, name, pressDelay, changeDelay FROM warehouses";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("pressDelay") + "\t" +
                                   rs.getDouble("changeDelay"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public ArrayList getUser(String name){
        
           ArrayList times = new ArrayList(); 
            String sql = "SELECT id, name, pressDelay, changeDelay "
                        + "FROM warehouses WHERE name = ? ";
            
                        Connection conn;
        try {
            conn = DriverManager.getConnection(SQLiteJDBCDriverConnection.getter());
            try (PreparedStatement pstm = conn.prepareStatement(sql)){
                pstm.setString(1, name);
                ResultSet rs = pstm.executeQuery();
            
            // loop through the result set
            System.out.println("This is the user");
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("pressDelay") + "\t" +
                                   rs.getDouble("changeDelay"));
                times.add(rs.getDouble("pressDelay"));
                times.add(rs.getDouble("changeDelay"));
            }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return times;      
    }
}

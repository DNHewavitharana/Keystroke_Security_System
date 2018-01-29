/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Dilshan
 */
public class SQLiteJDBCDriverConnection {
    static String url;
    
    public static void createNewDatabase(String fileName) {
    
        url = "jdbc:sqlite:C:/Users/Dilshan/Documents/NetBeansProjects/Security_System/SQLite/db/" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewTable() {
         String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	pressDelay double NOT NULL,\n"
                + "	changeDelay double NOT NULL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("create table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static String getter(){
        return url;
    }
    
    
}

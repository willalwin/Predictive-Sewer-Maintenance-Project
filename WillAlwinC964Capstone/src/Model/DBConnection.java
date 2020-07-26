/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author WillA
 */
public class DBConnection {
    private static final String databaseName = "DB name here";
    private static final String DB_URL = "DB URL here" + databaseName;
    private static final String username = "user";
    private static final String password = "pass";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static Emp loggedInUser;
    static Connection conn;

    public static void makeConnection() throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(DB_URL, username, password);
        System.out.println("logged into database");
    }
    
    public static void closeConnection() throws SQLException{
        conn.close();
    }
    

    public static Connection getConn() {
        return conn;
    }

    public static Emp getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Emp loggedInUser) {
        DBConnection.loggedInUser = loggedInUser;
    }

    
}

package university.management.system;

import java.sql.*;

public class Conn {
    
    Connection c;
    Statement s;

    // Constructor to initialize connection and statement
    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///universitymanagementsystem", "root", "12345");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get the Statement object for use in other classes
    public Statement getStatement() {
        return s;
    }

    // Method to close connection and statement
    public void close() {
        try {
            s.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

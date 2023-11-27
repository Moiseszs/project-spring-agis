package com.agis.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connector {
    
    private static String URL = "jdbc:jtds:sqlserver://localhost;DatabaseName=DbAgis";
    private static String driver = "net.sourceforge.jtds.jdbc.Driver";

    public static Connection connect() throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(URL, "dbuser", "pass");
        return connection;
    }
}

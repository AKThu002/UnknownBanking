package com.mycompany.unknownbanking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class databaseConnection {
   Connection connection = null;
   String url = "jdbc:postgresql://localhost/UnknownBanking";
   String user = "postgres";
   String password = "postgre";
   public Connection databaseConn() {

       try {
           connection = DriverManager.getConnection(url, user, password);
       } catch (SQLException ex) {
           Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
       }
        return connection;
   }
   
}
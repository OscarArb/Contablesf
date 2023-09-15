/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author OscarArb
 */
public class dbConnection {

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
      private final String user = "root";
      private final String pass = "";
      private final String url = "jdbc:mysql://localhost:3306/contablesf";
    
    //private final String user = "Oscar";
    //private final String pass = "Vane222019";
    //private final String url = "jdbc:mysql://cutu.mysql.database.azure.com:3306/contablesf";
   
}

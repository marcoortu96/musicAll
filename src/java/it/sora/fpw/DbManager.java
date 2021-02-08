/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sora.fpw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcoortu
 */

public class DbManager {
    private static DbManager instance;
    
    private DbManager(){
        // Se si dovesse arrivare qui senza eccezioni, è possibile connettersi al DB
        try {
            // si carica a run-time la classe del Driver // tramite il nome del driver stesso
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
        
        // viene sollevata questa eccezione nel caso
        // non si riesca a caricare la classe specificata. // Il DB in questo caso non sarà utilizzabile,
        // potrebbe essere il caso di terminare l’applicazione 
        Logger.getLogger(DbManager.class.getName())
            .log(Level.SEVERE, null, ex);
        }    
    }
    
    //Connessione al db
    public Connection getDbConnection() {
        String db = "jdbc:mysql://ec2-52-47-198-123.eu-west-3.compute.amazonaws.com:443/fpw18_ortumarco?zeroDateTimeBehavior=convertToNull";
        
        try {
   
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db 
            Connection conn = DriverManager.getConnection(db, "fpw18_ortumarco", "marcoortu96");
            return conn;
            
        } catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            Logger.getLogger(DbManager.class.getName())
            .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //Creo singleton
    public static DbManager getInstance() {
       if (instance == null)
           instance = new DbManager();
       
       return instance;
    }
    
}

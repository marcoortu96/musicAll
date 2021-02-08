/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sora.fpw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcoortu
 */
public class UserFactory {
    /*
     *Singleton 
     */
    private static UserFactory instance;
    private ArrayList<User> userList = new ArrayList<>();
    
    private UserFactory() {
        
    }
    
    /**Metodo per istanziare user
     * @return instance**/
    public static UserFactory getInstance(){
        if(instance == null)
            instance = new UserFactory();
        
        return instance;
    }
    
    //Restituisce la lista di tutti gli utenti
    public ArrayList<User> getUsers() {
        ArrayList<User> usersFromDB = new ArrayList<>();
        
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            Statement stmt = conn.createStatement();
            // definisco la query
            String sql = "select * from utente";
            ResultSet set = stmt.executeQuery(sql);     

            while (set.next()) {
                User userToAdd = new User();
                userToAdd.setId(set.getInt("id_utente"));
                userToAdd.setName(set.getString("nome"));
                userToAdd.setSurname(set.getString("cognome"));
                userToAdd.setEmail(set.getString("email"));
                userToAdd.setUsername(set.getString("username"));
                userToAdd.setPassword(set.getString("password"));
                userToAdd.setUrlProfImg(set.getString("url_prof_img"));
                
                usersFromDB.add(userToAdd);
            }             
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usersFromDB;
    }
    
    //Restituisce una lista di soli autori
    public ArrayList<User> getAuthors() {
        ArrayList<User> usersFromDB = new ArrayList<>();
        
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            Statement stmt = conn.createStatement();
            // definisco la query
            String sql = "select * from utente where tipo_utente = 'AUTORE'";
            ResultSet set = stmt.executeQuery(sql);     

            while (set.next()) {
                User userToAdd = new User();
                userToAdd.setId(set.getInt("id_utente"));
                userToAdd.setName(set.getString("nome"));
                userToAdd.setSurname(set.getString("cognome"));
                userToAdd.setEmail(set.getString("email"));
                userToAdd.setUsername(set.getString("username"));
                userToAdd.setPassword(set.getString("password"));
                userToAdd.setUrlProfImg(set.getString("url_prof_img"));
                
                usersFromDB.add(userToAdd);
            }             
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usersFromDB;
    }
    
    //Restituisce un utente in base all'id
    public User getUserById(int id) {
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from utente where id_utente = ?";
            User userToReturn = new User();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            ResultSet set = stmt.executeQuery();
            
            //Se ho trovato l'utente
            if (set.next()) {
                userToReturn.setId(set.getInt("id_utente"));
                userToReturn.setName(set.getString("nome"));
                userToReturn.setSurname(set.getString("cognome"));
                userToReturn.setEmail(set.getString("email"));
                userToReturn.setUsername(set.getString("username"));
                userToReturn.setPassword(set.getString("password"));
                userToReturn.setUrlProfImg(set.getString("url_prof_img"));
                userToReturn.setUserType(UserType.valueOf(set.getString("tipo_utente")));
            }
            
            stmt.close();
            conn.close();
            
            return userToReturn;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    //Controlla se le credenziali corrisondono a un utente registrato
    public boolean login(String username, String password) {
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from utente where username = ? and password = ?";
            boolean loggedIn = false;
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet set = stmt.executeQuery();
            
            loggedIn = set.next();
            
            stmt.close();
            conn.close();
            
            return loggedIn;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    //Restituisce un utente in base alla sua mail
    public User getUserByEmail(String email) {
        for (User user: userList)
            if (user.getEmail().equals(email))
                return user;
        
        return null;
    }
    
    //Restituisce un utente in base al suo username
    public User getUserByUsername(String username) {
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from utente where username = ?";
            User userToReturn = new User();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, username);
            
            ResultSet set = stmt.executeQuery();
            
            //Se ho trovato l'utente
            if (set.next()) {
                userToReturn.setId(set.getInt("id_utente"));
                userToReturn.setName(set.getString("nome"));
                userToReturn.setSurname(set.getString("cognome"));
                userToReturn.setEmail(set.getString("email"));
                userToReturn.setUsername(set.getString("username"));
                userToReturn.setPassword(set.getString("password"));
                userToReturn.setUrlProfImg(set.getString("url_prof_img"));
                userToReturn.setUserType(UserType.valueOf(set.getString("tipo_utente")));
            }
            
            stmt.close();
            conn.close();
            
            return userToReturn;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    //Metodo utilizzato per eliminare un utente e tutti i suoi relativi dati
    public void deleteUser(User user) {
        Connection conn = DbManager.getInstance().getDbConnection();
        
        try {
            
            conn.setAutoCommit(false);
            //Query utlizzata per cancellare i commenti delle notizie dell'utente da eliminare
            String sql1 = "delete commenti.* from commenti join notizia on commenti.id_notizia = notizia.id_notizia where notizia.id_utente = ?";
            //Query utlizzata per eliminare i commenti scritti da un utente
            String sql = "delete from commenti where id_utente = ?";
            //Query utilizzata per eliminare le notizie scritte da un untente
            String sql2 = "delete from notizia where id_utente = ?";
            //Query utlizzata per eliminare utente
            String sql3 = "delete from utente where id_utente = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setInt(1, user.getId());
            
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setInt(1, user.getId());
            
            PreparedStatement stmt3 = conn.prepareStatement(sql3);
            stmt3.setInt(1, user.getId());
            
            stmt1.executeUpdate();
            stmt.executeUpdate(); 
            stmt2.executeUpdate();
            int rowsQuery3 = stmt3.executeUpdate();
            
            //Controllo se l'utente è stato eliminato correttamente
            if (rowsQuery3 != 1) {
               conn.rollback();
            }
            
            conn.commit();
            stmt.close();
            stmt1.close();
            stmt2.close();
            stmt3.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("porcodio");
            
            if(conn != null) {
                try {
                    conn.rollback();
                }catch(SQLException e) {
                    Logger.getLogger(UserFactory.class.getName()).
                    log(Level.SEVERE, null, e);
                }
            }
        }

    }
    
    //Restituisce una lista di autori in base all'input dell'utente
    public ArrayList<User> getUserByInput(String input) {
         ArrayList<User> userFromDB = new ArrayList<>();
        
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            // definisco la query
            String sql = "select * from utente where (nome like ? or cognome like ?) and tipo_utente = 'AUTORE'";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%"+input+"%");
            stmt.setString(2, "%"+input+"%");
            
            ResultSet set = stmt.executeQuery(); 
            
            while (set.next()) {
                User user = new User();
                
                user.setId(set.getInt("id_utente"));
                user.setName(set.getString("nome"));
                user.setSurname(set.getString("cognome"));
                user.setUrlProfImg(set.getString("url_prof_img"));
                userFromDB.add(user);
            }             
            stmt.close();
            conn.close();
            
            return userFromDB;
            
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}

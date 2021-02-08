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
public class NewsFactory {
    /*
     *Singleton 
     */
    private static NewsFactory instance;
    private ArrayList<News> listNews = new ArrayList<>();
    
    private NewsFactory() {
        
    }
    
    public static NewsFactory getInstance() {
        if (instance == null)
            instance = new NewsFactory();
        
        return instance;
    }
    
    //Restituisce la lista delle notizie
    public ArrayList<News> getNews() {
        ArrayList<News> newsFromDB = new ArrayList<>();
        
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            Statement stmt = conn.createStatement();
            // definisco la query
            String sql = "select * from notizia order by data_notizia desc";
            ResultSet set = stmt.executeQuery(sql);     

            while (set.next()) {
                News newsToAdd = new News();
                newsToAdd.setId(set.getInt("id_notizia"));
                newsToAdd.setTitle(set.getString("titolo"));
                newsToAdd.setContent(set.getString("contenuto"));
                newsToAdd.setDate(set.getString("data_notizia"));
                newsToAdd.setDescrImg(set.getString("descr_img"));
                newsToAdd.setImg(set.getString("url_img"));
                newsToAdd.setAltImg(set.getString("alt_img")); 
                newsToAdd.setCategoryByString(set.getString("categoria"));
                //aggiungo autore
                UserFactory userFactory = UserFactory.getInstance();
                newsToAdd.setAuthor(userFactory.getUserById(set.getInt("id_utente")));
                
                newsFromDB.add(newsToAdd);
            }             
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newsFromDB;
    }
    
    //Restituisce una notizia in base all'id
    public News getNewsById(int id) {
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from notizia where id_notizia = ?";
            News newsToReturn = new News();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            ResultSet set = stmt.executeQuery();
            
            if (set.next()) {
               newsToReturn.setId(set.getInt("id_notizia"));
               newsToReturn.setTitle(set.getString("titolo"));
               newsToReturn.setContent(set.getString("contenuto"));
               newsToReturn.setDate(set.getString("data_notizia"));
               newsToReturn.setDescrImg(set.getString("descr_img"));
               newsToReturn.setImg(set.getString("url_img"));
               newsToReturn.setAltImg(set.getString("alt_img")); 
               newsToReturn.setCategoryByString(set.getString("categoria"));
               //aggiungo autore
               UserFactory userFactory = UserFactory.getInstance();
               newsToReturn.setAuthor(userFactory.getUserById(set.getInt("id_utente"))); 
            }
            
            stmt.close();
            conn.close();
            
            return newsToReturn;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    //Salva in un array tutte le notizie di determinato autore
    public ArrayList<News> getNewsByAuthor(int id) {
        ArrayList<News> newsFromDB = new ArrayList<>();
        
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            // definisco la query
            String sql = "select * from notizia where id_utente = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            ResultSet set = stmt.executeQuery();     

            while (set.next()) {
                
                News newsToReturn = new News();
                //newsToAdd = getNewsById(set.getInt("id_notizia"));
               newsToReturn.setId(set.getInt("id_notizia"));
               newsToReturn.setTitle(set.getString("titolo"));
               newsToReturn.setContent(set.getString("contenuto"));
               newsToReturn.setDate(set.getString("data_notizia"));
               newsToReturn.setDescrImg(set.getString("descr_img"));
               newsToReturn.setImg(set.getString("url_img"));
               newsToReturn.setAltImg(set.getString("alt_img")); 
               newsToReturn.setCategoryByString(set.getString("categoria"));
               //aggiungo autore
               UserFactory userFactory = UserFactory.getInstance();
               newsToReturn.setAuthor(userFactory.getUserById(set.getInt("id_utente"))); 
                
                newsFromDB.add(newsToReturn);
            }             
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newsFromDB;
    }
    
    //Salva su un array tutte le notizie di una determinata categoria
    public ArrayList<News> getNewsByCategory(String category) {
        ArrayList<News> newsFromDB = new ArrayList<>();
        
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            // definisco la query
            String sql = "select * from notizia where find_in_set(?,categoria) > 0";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, category);
            
            ResultSet set = stmt.executeQuery(); 
            
            while (set.next()) {
                News newsToAdd = new News();
                newsToAdd = getNewsById(set.getInt("id_notizia"));
                
                newsFromDB.add(newsToAdd);
            }             
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newsFromDB;
    } 
    
    //Metodo utilizzato per aggiungere una nuova notizia nel db
    public void addNews(News news) {
        
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "insert into notizia(titolo, contenuto, data_notizia, descr_img, url_img, alt_img, categoria, id_utente) "
                       + "values(?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, news.getTitle());
            stmt.setString(2, news.getContent());
            stmt.setString(3, news.getDate());
            stmt.setString(4, news.getDescrImg());
            stmt.setString(5, news.getImg());
            stmt.setString(6, news.getAltImg());
            stmt.setString(7, news.getCategoryByArray());
            stmt.setInt(8, news.getAuthor().getId());
           
            
            int rows = stmt.executeUpdate();
            
            //Controllo se l'operazione è andata a buon fine
            if (rows == 1) {
                System.out.println("Insert ok!");
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //Metodo utilizzato per modificare una nuova notizia nel db
    public void modifyNews(News news) {
        
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "update notizia "
                       + "set titolo = ?, contenuto = ?, data_notizia = ?, descr_img = ?, url_img = ?, alt_img = ?, categoria = ?, id_utente = ? "
                       + "where id_notizia = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, news.getTitle());
            stmt.setString(2, news.getContent());
            stmt.setString(3, news.getDate());
            stmt.setString(4, news.getDescrImg());
            stmt.setString(5, news.getImg());
            stmt.setString(6, news.getAltImg());
            stmt.setString(7, news.getCategoryByArray());
            stmt.setInt(8, news.getAuthor().getId());
            stmt.setInt(9, news.getId());
            
            int rows = stmt.executeUpdate();
            
            //Controllo se l'operazione è andata a buon fine
            if (rows == 1) {
                System.out.println("Insert ok!");
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getCategoroies() {
       ArrayList<String> newsFromDB = new ArrayList<>();
        
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            // definisco la query
            String sql = "select * from categoria";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet set = stmt.executeQuery(); 
            
            while (set.next()) {
                newsFromDB.add(set.getString("nome_categoria"));
            }             
            stmt.close();
            conn.close();
            
            return newsFromDB;
            
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newsFromDB;
    }
    //Restituisce una lista di categorie in base all'input dell'utente
    
    public ArrayList<String> getCategoryByInput(String input) {
         ArrayList<String> newsFromDB = new ArrayList<>();
        
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            // definisco la query
            String sql = "select * from categoria where nome_categoria like ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%"+input+"%");
            
            ResultSet set = stmt.executeQuery(); 
            
            while (set.next()) {
                newsFromDB.add(set.getString("nome_categoria"));
            }             
            stmt.close();
            conn.close();
            
            return newsFromDB;
            
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newsFromDB;
    }

}

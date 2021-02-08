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
public class CommentFactory {
    /*
     *Singleton
     */
    private static CommentFactory instance;
    private ArrayList<Comment> commentList = new ArrayList<>();
    
    private CommentFactory() {
        
    }
    
    public static CommentFactory getInstance() {
        if (instance == null)
            instance = new CommentFactory();
        
        return instance;
    }
    
    public ArrayList<Comment> getComments() {
        ArrayList<Comment> commentsFromDB = new ArrayList<>();
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            Statement stmt = conn.createStatement();
            // definisco la query
            String sql = "select * from commenti";
            ResultSet set = stmt.executeQuery(sql); 
            
            while (set.next()) {
                Comment commentToReturn = new Comment();
                commentToReturn.setId(set.getInt("id_commento"));
                commentToReturn.setContent(set.getString("contenuto"));
                //aggiungo id notizia
                NewsFactory newsFactory = NewsFactory.getInstance();
                commentToReturn.setNewsId(newsFactory.getNewsById(set.getInt("id_notizia")));
                //aggiungo autore
                UserFactory userFactory = UserFactory.getInstance();
                commentToReturn.setAuthor(userFactory.getUserById(set.getInt("id_utente")));
                
                commentsFromDB.add(commentToReturn);
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return commentsFromDB;
    }
    
    public Comment getCommentById(int id) {
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from commenti where id_commento = ?";
            Comment commentToReturn = new Comment();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            ResultSet set = stmt.executeQuery();
            
            if (set.next()) {
                commentToReturn.setId(set.getInt("id_commento"));
                commentToReturn.setContent(set.getString("contenuto"));
                //aggiungo id notizia
                NewsFactory newsFactory = NewsFactory.getInstance();
                commentToReturn.setNewsId(newsFactory.getNewsById(set.getInt("id_notizia")));
                //aggiungo autore
                UserFactory userFactory = UserFactory.getInstance();
                commentToReturn.setAuthor(userFactory.getUserById(set.getInt("id_utente")));
            }
            
            stmt.close();
            conn.close();
            
            return commentToReturn;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ArrayList<Comment> getCommentByNid(int nid) {
        ArrayList<Comment> commentsFromDB = new ArrayList<>();
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from commenti where id_notizia = ?";
           
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, nid);
            
            ResultSet set = stmt.executeQuery();
            
            while (set.next()) {
                Comment commentToReturn = new Comment();
                commentToReturn.setId(set.getInt("id_commento"));
                commentToReturn.setContent(set.getString("contenuto"));
                //aggiungo id notizia
                NewsFactory newsFactory = NewsFactory.getInstance();
                commentToReturn.setNewsId(newsFactory.getNewsById(set.getInt("id_notizia")));
                //aggiungo autore
                UserFactory userFactory = UserFactory.getInstance();
                commentToReturn.setAuthor(userFactory.getUserById(set.getInt("id_utente")));
                
                commentsFromDB.add(commentToReturn);
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return commentsFromDB;
    }
    
    public ArrayList<Comment> getCommentByAuthor(User author) {
        ArrayList<Comment> commentsFromDB = new ArrayList<>();
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from commenti where id_utente = ?";
           
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, author.getId());
            
            ResultSet set = stmt.executeQuery();
            
            while (set.next()) {
                Comment commentToReturn = new Comment();
                commentToReturn.setId(set.getInt("id_commento"));
                commentToReturn.setContent(set.getString("contenuto"));
                //aggiungo id notizia
                NewsFactory newsFactory = NewsFactory.getInstance();
                commentToReturn.setNewsId(newsFactory.getNewsById(set.getInt("id_notizia")));
                //aggiungo autore
                UserFactory userFactory = UserFactory.getInstance();
                commentToReturn.setAuthor(userFactory.getUserById(set.getInt("id_utente")));
                
                commentsFromDB.add(commentToReturn);
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return commentsFromDB;
    }
    
}

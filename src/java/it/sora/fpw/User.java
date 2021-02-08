/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sora.fpw;
import static it.sora.fpw.UserType.*;

/**
 *
 * @author marcoortu
 */
public class User {
    
    private int id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String urlProfImg;
    private UserType userType;
    
    public User() {
      this.id = 0;
      this.name = "Mario";
      this.surname = "Rossi";
      this.email = "example@blog.it";
      this.username = "mariorossi";
      this.password = "password";
      this.urlProfImg = "";
      this.userType = AUTORE;
    }
    
     /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
     /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the urlProfImg
     */
    public String getUrlProfImg() {
        return urlProfImg;
    }

    /**
     * @param urlProfImg the urlProfImg to set
     */
    public void setUrlProfImg(String urlProfImg) {
        this.urlProfImg = urlProfImg;
    }
    
    /**
     * @return the userType
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
    public boolean equals(User otherUser) {
        return this.id == otherUser.getId();
    } 
}


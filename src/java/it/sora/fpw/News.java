/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sora.fpw;



import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author marcoortu
 */
public class News implements Comparable {

    private int id;
    private String title;
    private String content;
    private String date;
    private String descrImg;
    private String img;
    private String altImg;
    private ArrayList<String> category;
    private User author;
    
    public News() {
        this.id = 0;
        this.title = "Titoli di esempio";
        this.content = "Testo di esempio";
        this.date = "1996/01/01";
        this.descrImg = "Descrizione immagine";
        this.img = "";
        this.altImg = "immagine";
        this.category = new ArrayList<>();
        this.author = new User();
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the descrImg
     */
    public String getDescrImg() {
        return descrImg;
    }

    /**
     * @param descrImg the descrImg to set
     */
    public void setDescrImg(String descrImg) {
        this.descrImg = descrImg;
    }

    /**
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }
    
     /**
     * @return the altImg
     */
    public String getAltImg() {
        return altImg;
    }

    /**
     * @param altImg the altImg to set
     */
    public void setAltImg(String altImg) {
        this.altImg = altImg;
    }
    
     /**
     * @return the category
     */
    public ArrayList<String> getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    /**
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(User author) {
        this.author = author;
    } 
    
    //Converte un arrayList in una stringa
    public String getCategoryByArray() {
        String categoria = "";
        
        for(String s : this.category) {
            categoria += s + ",";
        }
        return categoria.substring(0, categoria.length()-1);
    }
    
    //Trasforma una stringa in un arrayList di stringhe
    public void setCategoryByString(String categories) {
        this.category = new ArrayList<>(Arrays.asList(categories.split(",")));
    }

    @Override
    public int compareTo(Object o) {
        News news = (News) o;
        return this.date.compareTo(news.date);
    }
}

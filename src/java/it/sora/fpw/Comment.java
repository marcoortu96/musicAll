/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sora.fpw;

/**
 *
 * @author marcoortu
 */
public class Comment {
 
    private int id;
    private User author;
    private String content;
    private News newsId;
    
    public Comment() {
        this.id = 0;
        this.author = new User();
        this.content = "Commento di esempio";
        this.newsId = new News();
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
     * @return the newsId
     */
    public News getNewsId() {
        return newsId;
    }

    /**
     * @param newsId the newsId to set
     */
    public void setNewsId(News newsId) {
        this.newsId = newsId;
    }

}

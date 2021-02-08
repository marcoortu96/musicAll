/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sora.fpw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcoortu
 */
public class Notizie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            
            NewsFactory factory = NewsFactory.getInstance();
            ArrayList<News> newsList = factory.getNews();
            
            request.setAttribute("newsList", newsList);
            
            //Setto categorie dinamiche
            ArrayList<String> categoryList = factory.getCategoroies();
            session.setAttribute("categoryList", categoryList);
            
            request.setAttribute("newsList", newsList);
            
            //factory utilizzata per stampare gli autori
            UserFactory factoryUser = UserFactory.getInstance();
            ArrayList<User> userList = factoryUser.getAuthors();
           
            session.setAttribute("userList", userList);
            
            
            //parametro utilizzato per mostrare tutte le notizie
            request.setAttribute("nid", -1);
            
            //Se category è diverso da null newsList prende tutte le notizie per categorie
            String category = request.getParameter("category");
            if (category != null) {
                newsList = factory.getNewsByCategory(category);
                request.setAttribute("newsList", newsList);
            }
            
            //Mostra tutte le notizie di un determinato autore
            if (request.getParameter("author") != null) {
                String author = request.getParameter("author");
                int idAuthor = Integer.parseInt(author);
                newsList = factory.getNewsByAuthor(idAuthor);
                request.setAttribute("newsList", newsList);
            }
            
  
            request.getRequestDispatcher("M1/notizia.jsp").forward(request, response);
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

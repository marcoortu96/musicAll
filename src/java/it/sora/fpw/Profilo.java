/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sora.fpw;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcoortu
 */
public class Profilo extends HttpServlet {

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
            
            //Prendo l'id dell'utente solo se ha loggato
            int userId = 0;
            
            if(session.getAttribute("userId") != null)
                userId = Integer.parseInt(session.getAttribute("userId").toString());
            
            UserFactory factory = UserFactory.getInstance();
            User user = factory.getUserById(userId);
            
            //Se utente è un autore mostra il form con i dettagli del profilo da modificare
             if (session.getAttribute("loggedIn") != null &&
                session.getAttribute("loggedIn").equals(true)) {          
                 
               request.setAttribute("user", user);
               
               //Utente preme il bottone salva modifiche
               if(request.getParameter("saveProfile") != null) {
                   
                   String name = request.getParameter("name");
                   String surname = request.getParameter("surname");
                   String username = request.getParameter("username");
                   String password = request.getParameter("password");
                   String email = request.getParameter("email");
                   String urlImmagine = request.getParameter("urlImmagine");
                   
                   User userMod = new User();
                   
                   userMod.setName(name);
                   userMod.setSurname(surname);
                   userMod.setUsername(username);
                   userMod.setPassword(password);
                   userMod.setEmail(email);
                   userMod.setUrlProfImg(urlImmagine);
                   
                   request.setAttribute("profileToShow", userMod);
                   request.setAttribute("profiloSalvato", true);
                   
               }
               
               if(request.getParameter("deleteProfile") != null) {
                   factory.deleteUser(user);
                   
                   session.setAttribute("loggedIn", false);
                   session.invalidate();
                  
                   request.getRequestDispatcher("M1/login.jsp").forward(request, response);
                   return;
               }
               
               request.getRequestDispatcher("M1/profilo.jsp").forward(request, response);   
            } 
            //Se utente è un lettore mostro il messaggio di accesso negato
            else{
                request.setAttribute("error", true);
                request.getRequestDispatcher("M1/profilo.jsp").forward(request, response);
            }
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

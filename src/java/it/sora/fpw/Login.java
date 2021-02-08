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

public class Login extends HttpServlet {

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
            
            /**Se l'utente esegue il logout chiudo la sessione
               e reindirizzo l'utente alla pagina login**/
            if (request.getParameter("logout") != null) {
                session.invalidate();
                
                request.getRequestDispatcher("M1/login.jsp").forward(request, response);
                return;
            }
            /**Utente loggato**/
            if (session.getAttribute("loggedIn") != null &&
                session.getAttribute("loggedIn").equals(true)) {
                
                request.getRequestDispatcher("notizia.jsp").forward(request, response);
                return;
            }
            /**Utente non loggato, loggedIn == false **/
            else {
                String username = request.getParameter("nome");
                String password = request.getParameter("pswd");
                
                UserFactory factory = UserFactory.getInstance();
                              
                if(username != null && password != null && factory.login(username, password)) {
                    // email e password sono validi
                    int userId = factory.getUserByUsername(username).getId();
                    session.setAttribute("userId", userId);
                    session.setAttribute("loggedIn", true);
                    
                    User utente = factory.getUserById(userId);
                    session.setAttribute("utente", utente);
                          
                    request.getRequestDispatcher("notizie.html").forward(request, response);
                    return;
                }
                // Username e password sbagliati
                else if (username != null && password != null && (factory.login(username, password) == false)) {
                    request.setAttribute("invalidData", true);
                    request.getRequestDispatcher("M1/login.jsp").forward(request, response);
                    return;
                }              
            }
            
            request.getRequestDispatcher("M1/login.jsp").forward(request, response);
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

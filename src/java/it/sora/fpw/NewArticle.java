/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sora.fpw;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
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
public class NewArticle extends HttpServlet {

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
            if(session.getAttribute("userId") != null){
                int userId = Integer.parseInt(session.getAttribute("userId").toString());
            
                NewsFactory nfactory = NewsFactory.getInstance();
                UserFactory factory = UserFactory.getInstance();
                User user = factory.getUserById(userId);
                
                //Se utente è un autore mostra il form della pagina scriviArticolo   
                if(user.getUserType() == UserType.AUTORE) { 
                    
                    //Utente preme il bottone salva per "salvare" un nuovo articolo compilato
                    if(request.getParameter("saveNewArt") != null){
                        String title = request.getParameter("title");
                        String data = request.getParameter("data");
                        String urlImage = request.getParameter("urlImage");
                        String imgCaption = request.getParameter("didascalia");
                        String text = request.getParameter("lungo");
                        String[] categories = request.getParameterValues("categoria"); 
                        
                        ArrayList<String> checkedCategories = new ArrayList<>();
                        
                        News articleNew = new News();
                        
                        articleNew.setId(nfactory.getNews().size()+1);
                        articleNew.setTitle(title);
                        articleNew.setDate(data);
                        articleNew.setImg(urlImage);
                        articleNew.setDescrImg(imgCaption);
                        articleNew.setContent(text);
                        articleNew.setAuthor(user);
                        
                        if(categories != null){
                            for(String s : categories){ 
                                checkedCategories.add(s);
                            }
                        } 
                        articleNew.setCategory(checkedCategories);
                        String[] arrayCategories = {"", "", "", "", "", "", "", ""};

                        for(String str : checkedCategories) {
                            switch(str) {
                                case "Trap":
                                    arrayCategories[0] = "checked";
                                    break;
                                case "Rap":
                                    arrayCategories[1] = "checked";
                                    break;
                                case "Rock":
                                    arrayCategories[2] = "checked";
                                    break;
                                case "Pop":
                                    arrayCategories[3] = "checked";
                                    break;
                                case "Electronic":
                                    arrayCategories[4] = "checked";
                                    break;
                                case "Indie":
                                    arrayCategories[5] = "checked";
                                    break;
                                case "Jazz":
                                    arrayCategories[6] = "checked";
                                    break;
                                case "Classica":
                                    arrayCategories[7] = "checked";
                                    break;     
                            }
                        }
                         
                        request.setAttribute("arrayCategories", arrayCategories);
                        nfactory.addNews(articleNew);
                        request.setAttribute("articleToShow", articleNew);
                        request.setAttribute("articoloSalvato", true);
                        request.getRequestDispatcher("M1/scriviArticolo.jsp").forward(request, response);
                    }
                    
                    //Utente preme il bottone salva per "salvare" un articolo modificato già esistente
                    if(request.getParameter("saveArticle") != null) {
                        String title = request.getParameter("title");
                        String data = request.getParameter("data");
                        String urlImage = request.getParameter("urlImage");
                        String imgCaption = request.getParameter("didascalia");
                        String text = request.getParameter("lungo");
                        String[] categories = request.getParameterValues("categoria");


                        ArrayList<String> checkedCategories = new ArrayList<>();
                        News articleMod = new News();                       
                        
                        articleMod.setId(Integer.parseInt(request.getParameter("saveArticle")));
                        articleMod.setTitle(title);
                        articleMod.setDate(data);
                        articleMod.setImg(urlImage);
                        articleMod.setDescrImg(imgCaption);
                        articleMod.setContent(text);
                        articleMod.setAuthor(user);

                        if(categories != null){
                            for(String s : categories){ 
                                checkedCategories.add(s);
                            }
                        } 
                        articleMod.setCategory(checkedCategories);

                        //prendo le categorie della notizia per mostrare il check quando modifico l'articolo
                        String[] arrayCategories = {"", "", "", "", "", "", "", ""};

                        for(String str : checkedCategories) {
                            switch(str) {
                                case "Trap":
                                    arrayCategories[0] = "checked";
                                    break;
                                case "Rap":
                                    arrayCategories[1] = "checked";
                                    break;
                                case "Rock":
                                    arrayCategories[2] = "checked";
                                    break;
                                case "Pop":
                                    arrayCategories[3] = "checked";
                                    break;
                                case "Electronic":
                                    arrayCategories[4] = "checked";
                                    break;
                                case "Indie":
                                    arrayCategories[5] = "checked";
                                    break;
                                case "Jazz":
                                    arrayCategories[6] = "checked";
                                    break;
                                case "Classica":
                                    arrayCategories[7] = "checked";
                                    break;     
                            }
                        }

                        request.setAttribute("arrayCategories", arrayCategories);
                        nfactory.modifyNews(articleMod);
                        request.setAttribute("articleToShow", articleMod);
                        request.setAttribute("articoloSalvato", true);
                        request.setAttribute("flag", true); //utilizzato per stampare id quando si salva un art. modificato
                        request.getRequestDispatcher("M1/scriviArticolo.jsp").forward(request, response);
                    }
                    //Se utente preme su modifica
                    else if(request.getParameter("nid") != null) { 
                        //Prendo l'attributo nid
                        String nid = request.getParameter("nid");

                        //prendo le notizie dal nid                   
                        News article = nfactory.getNewsById(parseInt(nid));
                        
                        //viene mostrato accesso negato se un autore tenta di modificare un articolo di un altro autore
                        if(article.getAuthor().getId() != userId){
                            request.setAttribute("error", true);
                        }

                        //prendo le categorie della notizia per mostrare il check quando modifico l'articolo
                        String[] arrayCategories = {"", "", "", "", "", "", "", ""};

                        for(String str : article.getCategory()) {
                            switch(str) {
                                case "Trap":
                                    arrayCategories[0] = "checked";
                                    break;
                                case "Rap":
                                    arrayCategories[1] = "checked";
                                    break;
                                case "Rock":
                                    arrayCategories[2] = "checked";
                                    break;
                                case "Pop":
                                    arrayCategories[3] = "checked";
                                    break;
                                case "Electronic":
                                    arrayCategories[4] = "checked";
                                    break;
                                case "Indie":
                                    arrayCategories[5] = "checked";
                                    break;
                                case "Jazz":
                                    arrayCategories[6] = "checked";
                                    break;
                                case "Classica":
                                    arrayCategories[7] = "checked";
                                    break;     
                            }
                        }

                        request.setAttribute("arrayCategories", arrayCategories);
                        request.setAttribute("articleToShow", article); 
                        request.setAttribute("articoloModificato", true); 
                        request.getRequestDispatcher("M1/scriviArticolo.jsp").forward(request, response);
                    } 
                    //Se utente preme "aggiungi articolo" mostra il form vuoto
                    if(request.getParameter("nuovoArticolo") != null) {
                        request.setAttribute("newArt", true);
                        request.getRequestDispatcher("M1/scriviArticolo.jsp").forward(request, response); 
                    }
                }
                //Se utente è un lettore mostro il messaggio di accesso negato
                else{
                    request.setAttribute("error", true);
                    request.getRequestDispatcher("M1/scriviArticolo.jsp").forward(request, response);
                }
            }
            //Se utente prova ad accedere da url gli viene negato l'accesso
            else{
                request.setAttribute("error", true);
                request.getRequestDispatcher("M1/scriviArticolo.jsp").forward(request, response);
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

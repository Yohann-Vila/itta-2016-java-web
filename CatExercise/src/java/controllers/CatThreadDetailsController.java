/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import business.CatThread;
import business.Comment;
import dao.DAOFactory;
import dao.ICatThreadDAO;
import dao.ICommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Personnal
 */
@WebServlet(name = "CatThreadDetailsController", urlPatterns = {"/threaddetails"})
public class CatThreadDetailsController extends HttpServlet {

    ICatThreadDAO catThreadDAO;
    ICommentDAO commentDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        catThreadDAO = DAOFactory.getInstanceOfCatThread();
        commentDAO = DAOFactory.getInstanceOfComment();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
      //basicTests(resp);
       
    }

    /* private methods */
    private void basicTests(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        
        out.println("ok");
        
        int id = 1;
        basicDetailsPrintThread(id, out);
        out.println("---------------------------------------------");
        int newcommentid = addComment(id, "bob", "Ceci est un commentaire crée récemment.");
        basicDetailsPrintThread(id, out);        
        out.println("---------------------------------------------");
        markCommentDeleted(newcommentid);
        basicDetailsPrintThread(id, out);        
        out.println("---------------------------------------------");         
    }
    
    private void basicDetailsPrintThread(int id, PrintWriter out) {
        CatThread thread = getThreadByID(id, true);
        if ( thread == null) {
            out.println("Aucun thread trouvé avec ID " + id);
            return;
        }
        
        out.println("Titre : " + thread.getTitre());
        out.println("Thread id : " + thread.getCatThreadId());
        out.println("Crée par : " + thread.getLogin());        
        out.println("le : " + thread.getCreationDate());  
        out.println("url : " + thread.getUriPhoto());  
        out.println("commentaires :\n");
        Collection<Comment> comments = thread.getComments();
        for (Comment comment : comments) {
            out.println("---");            
            out.println(" N°" + comment.getCommentID() + " crée par " + comment.getLogin());
            out.println(" crée le " + comment.getCreationDate() );
            out.println(comment.getContent());
        }        
    }
    
    private CatThread getThreadByID(int id, boolean actif) {
        CatThread catThread = catThreadDAO.findByID(id);
        
        if (catThread == null) {
            return null;
        }

        loadThreadComments(catThread, actif);
        
        return catThread;
    }
    
    private int addComment(int threadID, String user, String content) {
        Comment comment = new Comment(threadID, user, content);
        boolean created = false;
        try {
            created = commentDAO.insert(comment);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        if(created) {
            return comment.getCommentID();
        } else {
            return -1;
        }
    }
    
    private void markCommentDeleted(int commentid) {
        Comment comment = commentDAO.findByID(commentid);
        markCommentDeleted(comment);
    }
    
    private void markCommentDeleted(Comment comment) {
        comment.setDeleted(true);
    }
    
    private void loadThreadComments(CatThread thread, boolean actif) {
        try {
            thread.setComments(commentDAO.getPostsFromThread(thread.getCatThreadId(), actif));
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import business.CatThread;
import business.Comment;
import business.DAOFactory;
import business.ICatThreadDAO;
import business.ICommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    /* private methods */
    
    private CatThread getThreadByID(int id, boolean actif) {
        CatThread catThread = catThreadDAO.findByID(id);
        
        if (catThread == null) {
            return null;
        }

        loadThreadComments(catThread, actif);
        
        return catThread;
    }
    
    private boolean addComment(int threadID, String user, String content) {
        Comment comment = new Comment(threadID, user, content);
        boolean result = false;
        try {
            result = commentDAO.create(comment);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return result;
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

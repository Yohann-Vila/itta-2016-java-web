/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import business.*;
import dao.DAOFactory;
import dao.ICatThreadDAO;
import dao.ICommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
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

        User user = (User) req.getSession().getAttribute("user");
        
        // idthread
        String idThreadString = req.getParameter("idthread");
        if (idThreadString == null) {
            basicTests(resp);
            return;
        }
        
        Integer idThread = null;
        try {
             idThread = Integer.parseInt(idThreadString);            
        } catch (NumberFormatException ex) {
            System.out.println("thread id " + idThreadString + " not found in CatThreadDetails controller.");
            throw new ServletException("thread id " + idThreadString + " not found in CatThreadDetails controller.");            
        }

        boolean onlyShowActive = user==null?true:!user.isAdmin();
        CatThread currentCatThread = getThreadByID(idThread, onlyShowActive);
        if (currentCatThread == null) {
            System.out.println("thread id " + idThreadString + " not found in CatThreadDetails controller.");
            throw new ServletException("thread id " + idThreadString + " not found in CatThreadDetails controller.");               
        }
        
        req.getSession().setAttribute("currentCatThread", currentCatThread);
        
        // if commentId parameter is there, it's for admin and ability to mark / unmark comments
        int commentid;
        try {
            commentid = Integer.parseInt(req.getParameter("commentid"));
        } catch (NumberFormatException | NullPointerException ex ){
            commentid = -1;
        }
        
        Comment comment = commentDAO.findByID(commentid);
        if (comment != null && user != null) {
            //check admin
            if (user.isAdmin()) {
                //change state of comment
                comment.setDeleted(! comment.isDeleted());
            }

            
        }
        
        
        RequestDispatcher rd = req.getRequestDispatcher("/threaddetail.jsp");
        rd.forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int threadId = -1;
        User user;  
        Object userObj;
        String username = null;
        String commentContent = null;
        
        // check if parameters are there
        
        // 1 - threadid
        try {
            threadId = Integer.parseInt(req.getParameter("idthread"));            
        } catch(NumberFormatException ex) {
            invalidPost(threadId, username, commentContent);
        }
      
        // 2 - user
        userObj = req.getSession().getAttribute("user");
        if (userObj == null) {
            invalidPost(threadId, username, commentContent);
            return;
        }
        
        try {
            user = (User) userObj;
        } catch (ClassCastException ex) {
            invalidPost(threadId, username, commentContent);
            return;
        }
        
        username = user.getLogin();
        
        // 3 - comment
        commentContent = (String) req.getParameter("commentcontent");
        if (commentContent == null) {
            invalidPost(threadId, username, commentContent);
            return;
        }
        
        // add the comment
        addComment(threadId, username, commentContent);
        // load the thread detail page 
        doGet(req, resp);
    }
    
    private void invalidPost(int threadId, String username, String commentContent) throws ServletException{
        StringBuilder message = new StringBuilder();
        message.append("Invalid comment post attempt.");
        message.append("\nthreadid : ").append(threadId);
        message.append("\nusername : ").append(username);
        message.append("\ncommentcontent : ").append(commentContent);
        
        throw new ServletException(message.toString());        
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
            out.println(" N°" + comment.getCommentId() + " crée par " + comment.getLogin());
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
            return comment.getCommentId();
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

package controllers;

import business.DAOFactory;
import business.IUserDAO;
import business.User;
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
 * @author Administrator
 */
@WebServlet(name = "SecurityController", urlPatterns = {"/SecurityController"})
public class SecurityController extends HttpServlet {
    private IUserDAO userDAO=DAOFactory.getInstanceOfUser();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("OK");
        //createThread(title, uriPhoto, user)
    } 
       
    private User getLogin(String login){
        User user=null;
        try
        {
            user=userDAO.find(login);  
        }
         catch(Exception e)
         {
             System.out.println(e.getMessage());
         }
        
        return user;        

    }
}

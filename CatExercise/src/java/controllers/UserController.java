package controllers;

import business.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UserController", urlPatterns = {"/usercontroller"})
public class UserController extends HttpServlet {
    private IUserDAO userDAO=DAOFactory.getInstanceOfUser();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("OK");
        //createThread(title, uriPhoto, user)
    }  
    
    private Collection<User> getAll(int seclevel){
        Collection<User> cuser=null;
        try
        {
            cuser = userDAO.getAll();   
        }
         catch(Exception e)
         {
             System.out.println(e.getMessage());
         }
        
        return cuser;
    }
    User banUser(String login,int seclevel){
        User banuser = null;
        try
        {
            banuser = userDAO.find(login);
        }
        catch(Exception e)
        {
             System.out.println(e.getMessage());
        }
        return banuser;
    }

    
}

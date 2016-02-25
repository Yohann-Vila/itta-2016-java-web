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

@WebServlet(name = "SecurityController", urlPatterns = {"/SecurityController"})
public class SecurityController extends HttpServlet {

    private IUserDAO userDAO;
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        userDAO = DAOFactory.getInstanceOfUser();
    }   
            
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("Wel done !");
        User utilisateur = getLogin("admin");
        out.print(utilisateur.getLogin()+" - "+ utilisateur.getPassword() + " - " + utilisateur.getSeclevel()+"\n");
               
        utilisateur = getLogin("titi");
        
        out.print(utilisateur.getLogin()+" - "+ utilisateur.getPassword() + " - " + utilisateur.getSeclevel()+"\n");
   //     utilisateur = getLogin("ghjfs");  
   //     out.print(utilisateur.getLogin()+" - "+ utilisateur.getPassword() + " - " + utilisateur.getSeclevel()+"\n");        
        out.println("Fin du doGet SecurityControler");
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

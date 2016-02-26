package controllers;

import business.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UserController", urlPatterns = {"/usercontroller"})
public class UserController extends HttpServlet {
    
    private IUserDAO userDAO;
   
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        userDAO = DAOFactory.getInstanceOfUser();
    }  
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("Début Usercontroller");
        Collection<User> xx = getAll(0);
        for (User utilisateur : xx) {
            out.println("user : \t"+utilisateur.getLogin()+" - \t"+ utilisateur.getIsban()+" - \t"+ utilisateur.getSeclevel());
        }
        
        out.println("banish user : titi ");
        
        if(banUser("titi",100)!= null)
        {
           out.println("nouveau status utilisateur : \n");
           User user_titi = userDAO.find("titi");
           out.println(user_titi.getLogin()+" - "+ user_titi.getIsban());
        }
        else 
        {
            out.println("utilisateur non trouvé");
        }
        out.println("Fin : Début Usercontroller");
    }  
    
    private Collection<User> getAll(int seclevel){
        Collection<User> cuser=null;
        try
        {
            cuser = userDAO.getAll();  
            out.println("prise utilisateur");
        }
         catch(Exception e)
         {
             System.out.println(e.getMessage());
         }
        
        return cuser;
    }
    User banUser(String login,int seclevel){
        User banuser = null;
        if(seclevel==100)// mettre une  => enum user.security.CONSTANTE
        {
            try
            {
            
                banuser = userDAO.find(login);
                banuser.setIsban(true);
                userDAO.modify(banuser);
            
           
            }
            catch(Exception e)
            {
                 System.out.println(e.getMessage());
            }
        }
        return banuser;
    }

    
}

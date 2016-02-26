package controllers;

import business.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Collection;
import java.util.Date;
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



        out.println("Création de lulu ");

        out.println(" appel : \n" + createUser("Lulu", "motdepasse", "pseudolulu"));

        out.println("Création de toto ");

        out.println(" appel : \n" + createUser("toto", "motdepasse", "pseudototo"));

        out.println("Fin : Usercontroller");
    }
 
    boolean createUser(String login, String password, String pseudo) {
        
        User newuser = new User(login,password);
        boolean retour = false;

        newuser.setIsban(false);
        newuser.setSeclevel(0);
        if (pseudo != null) {
            newuser.setPseudo(pseudo);
        } else {
            newuser.setPseudo(login);
        }
        newuser.setCreationdate(new Date());

        try {
            if (userDAO.create(newuser)) {
                User chercheuser = userDAO.find(newuser.getLogin());
                out.println("L'utilisateur " + chercheuser.getLogin() + " - " + chercheuser.getPassword() + " - " + chercheuser.getPseudo() + " - "
                        + chercheuser.getCreationdate().toString() + " - " + chercheuser.getSeclevel() + " - " + chercheuser.getIsban());
                retour = true;
            } else {
                out.println(" Utilisateur existe déjà ");
            }
        } catch (Exception e) {
            out.println(" Error " + e);

        }
        return retour;
    }

 

}

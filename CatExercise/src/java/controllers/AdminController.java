package controllers;

import dao.DAOFactory;
import dao.IUserDAO;
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

@WebServlet(name = "AdminController", urlPatterns = {"/admincontroller"})
public class AdminController extends HttpServlet {

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
            out.println("user : \t" + utilisateur.getLogin() + " - \t" + utilisateur.getBanish() + " - \t" + utilisateur.getSeclevel());
        }

        out.println("banishe user : titi ");

        if (banishUser("titi", User.ADMINISTRATEUR) != null) {
            out.println("nouveau status utilisateur : \n");
            User user_titi = userDAO.find("titi");
            out.println(user_titi.getLogin() + " - " + user_titi.getBanish());
        } else {
            out.println("utilisateur non trouvé");
        }

        out.println("Création de lulu ");

        out.println(" appel : \n" + AdcreateUser("Lulu", "motdepasse", "pseudolulu"));

        out.println("Création de toto ");

        out.println(" appel : \n" + AdcreateUser("toto", "motdepasse", "pseudototo"));

        out.println("Fin : Usercontroller");
    }

    private Collection<User> getAll(int seclevel) {
        Collection<User> cuser = null;
        try {
            cuser = userDAO.getAll();
            out.println("prise utilisateur");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cuser;
    }

    boolean AdcreateUser(String login, String password, String pseudo) {
        
        User newuser = new User(login,password);
        boolean retour = false;

        newuser.setBanish(false);
        newuser.setSeclevel(User.UTILISATEUR);
        if (pseudo != null) {
            newuser.setPseudo(pseudo);
        } else {
            newuser.setPseudo(login);
        }
        newuser.setCreationdate(new Date());

        try {
            if (userDAO.insert(newuser)) {
                User chercheuser = userDAO.find(newuser.getLogin());
                out.println("L'utilisateur " + chercheuser.getLogin() + " - " + chercheuser.getPassword() + " - " + chercheuser.getPseudo() + " - "
                        + chercheuser.getCreationdate().toString() + " - " + chercheuser.getSeclevel() + " - " + chercheuser.getBanish());
                retour = true;
            } else {
                out.println(" Utilisateur existe déjà ");
            }
        } catch (Exception e) {
            out.println(" Error " + e);

        }
        return retour;
    }

    User banishUser(String login, int seclevel) {
        User banuser = null;
        if (seclevel == User.ADMINISTRATEUR)// mettre une  => enum user.security.CONSTANTE
        {
            try {

                banuser = userDAO.find(login);
                banuser.setBanish(true);
                userDAO.update(banuser);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return banuser;
    }

}

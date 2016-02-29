package controllers;

import dao.DAOFactory;
import dao.IUserDAO;
import business.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import javax.servlet.RequestDispatcher;
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
        super.init();
        userDAO = DAOFactory.getInstanceOfUser();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DisplayUser(req, resp);

    }

    private void DisplayUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String select_user = req.getParameter("login");
        String add_user = req.getParameter("creation");

        if (select_user != null) {
            if (select_user.isEmpty() == false) {
                User userBasish = userDAO.find(select_user);
                banishUser(select_user, User.ADMINISTRATEUR, userBasish.getBanish() == true ? false : true);
            }
        }

        if (add_user != null) {
            switch (add_user.toString()) {
                case "create":
                    RequestDispatcher reqdsp = req.getRequestDispatcher("/admin/admininput.jsp");
                    reqdsp.forward(req, resp);
                    break;
                case "modification":
                    break;
                default:
                    break;
            }
        }

        req.getSession().setAttribute("Listusers", getAll());

        RequestDispatcher reqdsp = req.getRequestDispatcher("/admin/adminpage.jsp");
        reqdsp.forward(req, resp);
    }

    private Collection<User> getAll() {
        LinkedHashSet<User> cuser = new LinkedHashSet<>();
        try {
            cuser.addAll(userDAO.getAll());
            // out.println("prise utilisateur");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cuser;
    }

    boolean AdcreateUser(String login, String password, String pseudo) {

        User newuser = new User(login, password);
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
                retour = true;
            } else {
                // out.println(" Utilisateur existe déjà ");
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return retour;
    }

    User banishUser(String login, int seclevel, boolean banish) {
        User banuser = null;
        if (seclevel == User.ADMINISTRATEUR) {
            try {

                banuser = userDAO.find(login);
                banuser.setBanish(banish);
                userDAO.update(banuser);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return banuser;
    }

}

package controllers;

import dao.DAOFactory;
import dao.IUserDAO;
import business.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SecurityController", urlPatterns = {"/login"})
public class SecurityController extends HttpServlet {

    private IUserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        userDAO = DAOFactory.getInstanceOfUser();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String redirect = req.getParameter("redirect");

        User u = getLogin(login.trim(), password.trim());
        if (u != null) {
            req.getSession().setAttribute("user", u);
            if (redirect.trim().isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/find.jsp?login=1");
            } else {
                resp.sendRedirect(req.getContextPath() + redirect + "?login=1");
            }
            resp.getWriter().println("Vous êtes authentifié avec " + u.getLogin());
        } else {
            
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=1");
        }
    }

    private void test(HttpServletResponse resp) throws IOException {
//        PrintWriter out = resp.getWriter();
//        out.println("Wel done !");
//        User utilisateur = getLogin("admin");
//        out.print(utilisateur.getLogin() + " - " + utilisateur.getPassword() + " - " + utilisateur.getSeclevel() + "\n");
//
//        utilisateur = getLogin("titi");
//
//        out.print(utilisateur.getLogin() + " - " + utilisateur.getPassword() + " - " + utilisateur.getSeclevel() + "\n");
//        //     utilisateur = getLogin("ghjfs");
//        //     out.print(utilisateur.getLogin()+" - "+ utilisateur.getPassword() + " - " + utilisateur.getSeclevel()+"\n");        
//        out.println("Fin du doGet SecurityControler");
    }

    private User getLogin(String login, String password) {
        User user = null;
        try {
            user = userDAO.find(login);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

}

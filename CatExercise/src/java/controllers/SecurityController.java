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
import javax.servlet.http.HttpSession;

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
        String logout = req.getParameter("logout");
        if (logout != null && !logout.isEmpty() && logout.equals("1")) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }   
        }
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
        return;
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String redirect = req.getParameter("redirect");
        String add = req.getParameter("add");

        if (add != null && !add.isEmpty() && add.equals("1")) {
            if (cannotAddUser(login, password, repassword, resp, req)) {
                return;
            }
        }
        tryToLogTheUser(login, password, req, redirect, resp);
        return;
    }

    private void tryToLogTheUser(String login, String password, HttpServletRequest req, String redirect, HttpServletResponse resp) throws IOException {
        User u = getLogin(login.trim(), password.trim());
        if (u != null) {
            req.getSession().setAttribute("user", u);
            if (redirect == null || redirect.trim().isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/find.jsp?login=1");
                return;
            } else {
                resp.sendRedirect(req.getContextPath() + redirect + "?login=1");
                return;
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=1");
            return;
        }
    }

    private boolean cannotAddUser(String login, String password, String repassword, HttpServletResponse resp, HttpServletRequest req) throws IOException {
        if (login != null && !login.isEmpty()
                && password != null && !password.isEmpty()
                && repassword != null && !repassword.isEmpty()) {
            if (password.equals(repassword)) {
                if (userDAO.find(login) == null) {
                    userDAO.insert(new User(login, password));
                } else {
                    resp.sendRedirect(req.getContextPath() + "/sign-up.jsp?error=exist");
                    return true;
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/sign-up.jsp?error=password");
                return true;
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/sign-up.jsp?error=field");
            return true;
        }
        return false;
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

package controllers;

import business.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


@WebServlet(name = "CatThreadController", urlPatterns = {"/thread"})
public class CatThreadController extends HttpServlet {

    private final ICatThreadDAO catThreadDAO = DAOFactory.getInstanceOfCatThread();
    private final IUserDAO userDAO = DAOFactory.getInstanceOfUser();



    /*Overide*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("OK");
        out.println(createThread("Test", "/photo/chat1.png", userDAO.find("toto")));
        out.println("Fin");

    }

    /*Method Private*/
    private boolean createThread(String title, String uriPhoto, User user) {
        CatThread c = new CatThread(user.getLogin(), title, uriPhoto);
        boolean b = false;
        try {
            b = catThreadDAO.create(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return b;
    }
    
}

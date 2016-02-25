package controllers;

import business.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "CatThreadController", urlPatterns = {"/thread"})
public class CatThreadController extends HttpServlet {

    private final ICatThreadDAO catThreadDAO = DAOFactory.getInstanceOfCatThread();
    private final IUserDAO userDAO = DAOFactory.getInstanceOfUser();
    private int nbByPage = 10;


    /*Overide*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("OK");
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        out.println(createThread("Test", "/photo/chat1.png", userDAO.find("toto")));
        out.println("Test PAGINATION : "+currentPage);
        
        for (CatThread catThread : getThreadByPages(currentPage)) {
            out.println(catThread.getTitre());
        }
        out.print("Fin Test PAGINATION");
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

    private LinkedHashSet<CatThread> getThreadByPages(int currentPage) {
        LinkedHashSet<CatThread> outSet = new LinkedHashSet<>();
        int maxValue = currentPage * nbByPage;
        int stratValue = (currentPage - 1) * nbByPage;
        int i = 0;
        Iterator<CatThread> it = catThreadDAO.getAll().iterator();
        while (it.hasNext() && stratValue < maxValue) {
            if (i >= stratValue) {
                outSet.add(it.next());
                stratValue++;
            }else{
                it.next();
            }
            i++;
        }
        return outSet;
    }

    private void setThreadPages(int nbByPage) {
        this.nbByPage = nbByPage;
    }
}

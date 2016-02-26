package controllers;

import dao.DAOFactory;
import dao.ICatThreadDAO;
import dao.IUserDAO;
import business.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "CatThreadController", urlPatterns = {"/thread"})
public class CatThreadController extends HttpServlet {

    private ICatThreadDAO catThreadDAO;
    private IUserDAO userDAO;
    private boolean filterDeletedThread;

    /*Overide*/
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        catThreadDAO = DAOFactory.getInstanceOfCatThread();
        userDAO = DAOFactory.getInstanceOfUser();
        filterDeletedThread = true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String what = req.getParameter("what");
        
        int nbByPage = initOrDefaultNbByPage(req);
        int currentPage = initOrDefaultCurrentPage(req);
        
        Collection<CatThread> filteredResult = (Collection<CatThread>)req.getSession().getAttribute("filteredResult");
        
        if (req.getSession().getAttribute("what") == null || !req.getSession().getAttribute("what").equals(what) || filteredResult == null) {
            filteredResult = finCatThread(what);
            req.getSession().setAttribute("what", what);
            req.getSession().setAttribute("filteredResult", filteredResult);
            req.getSession().setAttribute("pageList", getPageList(filteredResult, nbByPage));
            System.out.println("RELOAD");
        }
        req.getSession().setAttribute("catThreadList", getThreadByPages(filteredResult, nbByPage, currentPage));
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("what", what);
        
        RequestDispatcher rd = req.getRequestDispatcher("/thread-list.jsp");
        rd.forward(req, resp);

        //resp.sendRedirect(req.getContextPath() + "/thread-list.jsp?currentPage="+currentPage+"&what="+what);
//        PrintWriter out = resp.getWriter();
//        out.println("OK");
//
//        String what = req.getParameter("what");
//        System.out.println(what);
//        filteredResult = finCatThread(what);
//        out.println(filteredResult.size());
//        CatThread c = createThread("TEST_CREATE", "test", userDAO.find("toto"));
//        int currentPage = 1;
//        try {
//            currentPage = Integer.parseInt(req.getParameter("page"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        out.println(createThread("Test", "/photo/chat1.png", userDAO.find("toto")));
//        out.println("Test PAGINATION : " + currentPage);
//
//        for (CatThread catThread : getThreadByPages(currentPage)) {
//            out.println(catThread.getCatThreadId() + " :" + catThread.getTitre());
//        }
//        out.println("Fin Test PAGINATION");
//        out.println(c.getCatThreadId());
//        removeThread(c);
//        out.println(filteredResult.size());
//
//        out.println("Fin");
    }

    private int initOrDefaultNbByPage(HttpServletRequest req) {
        int nb = 10;
        try {
            nb =Integer.parseInt(req.getParameter("nbByPage"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nb;
    }
    private int initOrDefaultCurrentPage(HttpServletRequest req) {
        int nb = 1;
        try {
            nb =Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nb;
    }
    
    private TreeSet<Integer> getPageList(Collection<CatThread> result, int rsByPage) {
        TreeSet<Integer> pageList = new TreeSet<>();
        for (int i = 1; i <= (result.size() / rsByPage) + 1; i++) {
            pageList.add(i);
        }
        return pageList;
    }

    /*Method Private*/
    private CatThread createThread(String title, String uriPhoto, User user) {
        CatThread c = new CatThread(user.getLogin(), title, uriPhoto);
        boolean b = false;
        try {
            b = catThreadDAO.insert(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return b ? c : null;
    }

    private LinkedHashSet<CatThread> getThreadByPages(Collection<CatThread> result, int rsByPage, int currentPage) {
        LinkedHashSet<CatThread> outSet = new LinkedHashSet<>();
        int maxValue = currentPage * rsByPage;
        int stratValue = (currentPage - 1) * rsByPage;
        int i = 0;
        Iterator<CatThread> it = result.iterator();
        while (it.hasNext() && stratValue < maxValue) {
            if (i >= stratValue) {
                outSet.add(it.next());
                stratValue++;
            } else {
                it.next();
            }
            i++;
        }
        return outSet;
    }

    private Collection<CatThread> finCatThread(String what) {
        if (what == null || what.equals("*") || what.isEmpty()) {
            return catThreadDAO.getAll(filterDeletedThread);
        } else {
            Collection<CatThread> c = new LinkedHashSet<>();
            c.addAll(catThreadDAO.findByTitle(what, filterDeletedThread));
            User find = userDAO.find(what);
            if (find != null) {
                String login = find.getLogin();
                if (login != null) {
                    c.addAll(catThreadDAO.findByLogin(login, filterDeletedThread));
                }
            }
            return c;
        }
    }

    private boolean removeThread(CatThread c) {
        c.deleteThread();
        boolean status = false;
        try {
            status = catThreadDAO.update(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

}

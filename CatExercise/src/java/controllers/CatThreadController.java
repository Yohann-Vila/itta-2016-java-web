package controllers;

import dao.*;
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
    private boolean hideDeletedThread;

    /*Overide*/
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        catThreadDAO = DAOFactory.getInstanceOfCatThread();
        userDAO = DAOFactory.getInstanceOfUser();
        hideDeletedThread = true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String what = req.getParameter("what") ==null || req.getParameter("what").isEmpty() ? "*" : req.getParameter("what");
        if(req.getSession().getAttribute("user") !=null && ((User)req.getSession().getAttribute("user")).isAdmin()){
            hideDeletedThread = false;
        }
        int del = req.getParameter("del") ==null || req.getParameter("del").isEmpty() ? -1 : Integer.parseInt(req.getParameter("del"));
        int undel = req.getParameter("undel") ==null || req.getParameter("undel").isEmpty() ? -1 : Integer.parseInt(req.getParameter("undel"));
        needToDeleteOrHide(undel,req);
        needToDeleteOrHide(del,req);


        if (isNeedToAddThread(req, resp)) return;

        
        int nbByPage = initOrDefaultNbByPage(req);
        int currentPage = initOrDefaultCurrentPage(req);

        Collection<CatThread> filteredResult = (Collection<CatThread>) req.getSession().getAttribute("filteredResult");

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

    }

    private boolean isNeedToAddThread(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean addthread =  req.getParameter("addthread") ==null || req.getParameter("addthread").isEmpty() ? false : true;
        if (addthread) {
            Object o = req.getSession().getAttribute("user");
            if (o != null && o instanceof User) {
                String urlphoto = req.getParameter("title");
                String title = req.getParameter("urlphoto");
                CatThread ct = createThread(urlphoto, title, (User)o);
                resp.sendRedirect(req.getContextPath() + "/threaddetails?idthread="+ct.getCatThreadId());
                return true;
            } else {
                resp.sendRedirect(req.getContextPath() + "/login.jsp?redirect=/add-thread.jsp");
                return true;
            }
        }
        return false;
    }

    private void needToDeleteOrHide(int param,HttpServletRequest req) throws NumberFormatException {
        if(param > 0 && req.getSession().getAttribute("user") !=null && ((User)req.getSession().getAttribute("user")).isAdmin()){
            CatThread t = catThreadDAO.findByID(param);
            t.changeDeletedState();
            boolean update = catThreadDAO.update(t);
            req.getSession().setAttribute("filteredResult",null);
        }
    }

    /*Method Private*/
    private int initOrDefaultNbByPage(HttpServletRequest req) {
        int nb = 10;
        try {
            nb = Integer.parseInt(req.getParameter("nbByPage"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nb;
    }

    private int initOrDefaultCurrentPage(HttpServletRequest req) {
        int nb = 1;
        try {
            nb = Integer.parseInt(req.getParameter("page"));
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
            return catThreadDAO.getAll(hideDeletedThread);
        } else {
            Collection<CatThread> c = new LinkedHashSet<>();
            c.addAll(catThreadDAO.findByTitle(what, hideDeletedThread));
            User find = userDAO.find(what);
            if (find != null) {
                String login = find.getLogin();
                if (login != null) {
                    c.addAll(catThreadDAO.findByLogin(login, hideDeletedThread));
                }
            }
            return c;
        }
    }

}

package controllers;

import business.*;

public class CatThreadController {

    private ICatThreadDAO catThreadDAO = DAOFactory.getInstanceOfCatThread();

    boolean createThread(String title, String uriPhoto, User user) {
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

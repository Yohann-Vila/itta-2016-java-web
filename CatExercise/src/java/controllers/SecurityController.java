package controllers;
import business.*;

public class SecurityController {
    private IUserDAO userDAO;
    User getLogin(String login){
       return userDAO.find(login);
    }
}

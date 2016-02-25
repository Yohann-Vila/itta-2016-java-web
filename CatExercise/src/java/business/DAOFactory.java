/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Administrator
 */
public class DAOFactory {

    private static final String SOURCE = "RAM";

    public static IUserDAO getInstanceOfUser() {
        switch (SOURCE) {
            case "RAM":
                return new UserDAOTest();
            default:
                return new UserDAOTest();
        }
    }
    public static ICatThreadDAO getInstanceOfCatThread() {
        switch (SOURCE) {
            case "RAM":
                return new CatThreadDAOTest();
            default:
                return new CatThreadDAOTest();
        }
    }
    public static ICommentDAO getInstanceOfComment() {
        switch (SOURCE) {
            case "RAM":
                return new CommentDAOTest();
            default:
                return new CommentDAOTest();
        }
    }
}

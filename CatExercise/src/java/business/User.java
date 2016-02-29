package business;

import java.util.Date;

public class User {
    private String login;
    private String password;
    private String pseudo;
    private boolean banish;
    private int seclevel;
    private Date creationdate;

    public static final int ADMINISTRATEUR=100;
    public static final int UTILISATEUR=0;
    
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

//    public boolean getIsban() {
//        return banish;
//    }

    public void setBanish(boolean banish) {
        this.banish = banish;
    }
    public int getSeclevel() {
        return seclevel;
    }
    public boolean getBanish() {
        return banish;
    }

    public void setSeclevel(int seclevel) {
        this.seclevel = seclevel;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }
}

package business;

import java.util.Date;

public class User {

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    private String login;
    private String password;
    private String pseudo;
    private boolean isban;
    private int seclevel;
    private Date creationdate;
    
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

    public boolean isBan() {
        return isban;
    }

    public void setBan(boolean isban) {
        this.isban = isban;
    }

    public int getSeclevel() {
        return seclevel;
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

package business;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Yohann
 */
public class CatThread {
    static int count = 0;
    
    private final int catThreadId = getUniqueId();
    private final String login;
    private final String titre;
    private final String uriPhoto;
    private boolean deleted = false;
    private final Date creationDate = new Date();
    Collection<Comment> comments;
    
    public int getCatThreadId() {
        return catThreadId;
    }

    public String getLogin() {
        return login;
    }

    public String getTitre() {
        return titre;
    }

    public String getUriPhoto() {
        return uriPhoto;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Collection<Comment> getComments() {
        return comments;
    }
    

    
    public CatThread(String login, String titre, String uriPhoto) {
        this.login = login;
        this.titre = titre;
        this.uriPhoto = uriPhoto;
    }

    public void deleteThread() {
        this.deleted = true;
    }

    private int getUniqueId() {
        return ++count;
    }
}

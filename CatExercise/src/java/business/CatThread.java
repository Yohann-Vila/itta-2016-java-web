package business;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Yohann
 */
public class CatThread {

    private int catThreadId;
    private final String login;
    private final String titre;
    private final String uriPhoto;
    private boolean deleted = false;
    private final Date creationDate = new Date();
    Collection<Comment> comments;
    
    public void setCatThreadId(int catThreadId) {
        this.catThreadId = catThreadId;
    }
    
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
    
    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public CatThread(String login, String titre, String uriPhoto) {
        this.login = login;
        this.titre = titre;
        this.uriPhoto = uriPhoto;
    }

    public void deleteThread() {
        this.deleted = true;
    }

    @Override
    public int hashCode() {
        return catThreadId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CatThread) {
            return ((CatThread)obj).getCatThreadId() == this.catThreadId;
        }
        return false;
    }

    @Override
    public String toString() {
        return getCatThreadId() + ": "+getTitre();
    }
    
}

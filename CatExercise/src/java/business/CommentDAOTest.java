package business;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 *
 * @author Yohann
 */
public class CommentDAOTest implements ICommentDAO {

    static Collection<Comment> comments = new LinkedHashSet<>();
    
    @Override
    public Collection<Comment> getPostsFromThread(int idThread, boolean actif) {
        Collection<Comment> results = new LinkedHashSet<>();
        
        for (Comment comment : comments) {
            if(comment.getThreadID() == idThread) {
                // if "actif" switch is false, get all comments, 
                // if "actif" switch is true, only get comments that are not deleted
                if(!(actif && comment.isDeleted())) {
                    results.add(comment);
                }
            }
        }
        
        return Collections.unmodifiableCollection(results);
    }

    @Override
    public boolean create(Comment comment) {
        if (comment == null) {
            return false;
        }
        
        return comments.add(comment);
    }

    @Override
    public boolean modify(Comment comment) {
        if (comment == null) {
            return false;
        }
        
        int id = comment.getCommentID();
        Comment oldComment = findByID(id);
        
        if (oldComment == null) {
            return false;
        }
        
        comments.remove(oldComment);
        return comments.add(comment);
    }

    private Comment findByID(int id) {
        for (Comment comment : comments) {
            if (comment.getCommentID() == id) {
                return comment;
            }
        }
        return null;
    }

}

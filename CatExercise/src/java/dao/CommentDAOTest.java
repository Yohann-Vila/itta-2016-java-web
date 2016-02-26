package dao;

import business.Comment;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Yohann
 */
public class CommentDAOTest implements ICommentDAO {

    static AtomicInteger count = new AtomicInteger(0); // for id autogeneration
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
        comment.setCommentId(count.incrementAndGet());
        
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

    @Override
    public Comment findByID(int id) {
        for (Comment comment : comments) {
            if (comment.getCommentID() == id) {
                return comment;
            }
        }
        return null;
    }

}

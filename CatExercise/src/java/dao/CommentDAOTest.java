package dao;

import business.Comment;
import java.util.ArrayList;
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
    static ArrayList<Comment> comments = new ArrayList<>();
    
    @Override
    public Collection<Comment> getPostsFromThread(int idThread, boolean actif) {
        Collection<Comment> results = new LinkedHashSet<>();
        
        for (Comment comment : comments) {
            if(comment.getThreadId() == idThread) {
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
    public boolean insert(Comment comment) {
        if (comment == null) {
            return false;
        }
        comment.setCommentId(count.incrementAndGet());
        
        return comments.add(comment);
    }

    @Override
    public boolean update(Comment comment) {
        if (comment == null) {
            return false;
        }
        
        int id = comment.getCommentId();
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
            if (comment.getCommentId() == id) {
                return comment;
            }
        }
        return null;
    }

}

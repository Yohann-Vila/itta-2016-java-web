
package dao;

import business.Comment;
import java.util.Collection;

public interface ICommentDAO {
    Collection<Comment> getPostsFromThread(int IdThread,boolean actif);
    boolean insert(Comment c);
    boolean update(Comment c);
    Comment findByID(int commentID);
}

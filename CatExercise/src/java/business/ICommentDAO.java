
package business;

import java.util.Collection;

public interface ICommentDAO {
    Collection<Comment> getPostsFromThread(int IdThread,boolean actif);
    boolean create(Comment c);
    boolean modify(Comment c);
    Comment findByID(int commentID);
}

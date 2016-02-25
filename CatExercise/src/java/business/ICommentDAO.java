
package business;

public interface ICommentDAO {
    Comment getPostFromThread(int IdThread,boolean actif);
    boolean create(Comment c);
    boolean modify(Comment c);
}

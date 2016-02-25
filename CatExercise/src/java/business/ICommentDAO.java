
package business;

public interface ICommentDAO {
    Comment getPostFromFil(int IdThread,boolean actif);
    boolean create(Comment c);
    boolean modify(Comment c);
}

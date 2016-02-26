package business;

import java.util.Date;

public class Comment {
    private int commentId;


    private final int threadId;
    private String login;
    private final Date creationDate;
    private boolean deleted;
    private String content;
    
    public Comment(int threadId, String login, String content) {
        this.threadId = threadId;
        this.login = login;
        this.content = content;
        this.creationDate = new Date();
    }
    
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    
    public int getCommentID() {
        return commentId;
    }

    public int getThreadID() {
        return threadId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

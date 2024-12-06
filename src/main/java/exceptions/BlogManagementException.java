package exceptions;

public class BlogManagementException extends RuntimeException {

    public BlogManagementException() {
        super();
    }

    public BlogManagementException(String message) {
        super(message);
    }
}

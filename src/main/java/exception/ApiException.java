package exception;

public class ApiException  extends RuntimeException{
    private final int statusCode;
    public ApiException (int statusCode, String msg){
        super(msg);/*reference parent class*/
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }
}

package questionnaires.exception;

/**
 * Exception by authorization
 */
public class AuthorizationException extends Exception {
    public AuthorizationException(){
    }

    public AuthorizationException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }

}

package mk.ukim.finki.wp.lab.model.eception;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String username){
        super(String.format("Username with %s already exists!" , username));
    }
}

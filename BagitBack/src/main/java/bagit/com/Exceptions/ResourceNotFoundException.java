package bagit.com.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String name) {
        super(name);
    }

}

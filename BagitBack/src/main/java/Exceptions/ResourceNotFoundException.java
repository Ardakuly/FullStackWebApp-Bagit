package Exceptions;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String name) {
        super(name);
    }

}

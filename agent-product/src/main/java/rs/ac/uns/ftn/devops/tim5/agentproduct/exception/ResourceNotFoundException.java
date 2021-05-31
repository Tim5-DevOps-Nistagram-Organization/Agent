package rs.ac.uns.ftn.devops.tim5.agentproduct.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String resource) {
        super(String.format("%s not found.", resource));
    }
}

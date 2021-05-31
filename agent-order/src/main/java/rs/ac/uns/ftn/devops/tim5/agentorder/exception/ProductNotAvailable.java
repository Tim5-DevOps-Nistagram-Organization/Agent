package rs.ac.uns.ftn.devops.tim5.agentorder.exception;

public class ProductNotAvailable extends Throwable {
    public ProductNotAvailable(String name) {
        super(String.format("Product %s is not available.", name));
    }
}

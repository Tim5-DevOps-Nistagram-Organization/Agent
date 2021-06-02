package rs.ac.uns.ftn.devops.tim5.agentorder.service;

import rs.ac.uns.ftn.devops.tim5.agentorder.exception.ProductNotAvailable;
import rs.ac.uns.ftn.devops.tim5.agentorder.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.CustomerOrder;

public interface CustomerOrderService {
    CustomerOrder create(CustomerOrder order) throws ResourceNotFoundException, ProductNotAvailable;
}

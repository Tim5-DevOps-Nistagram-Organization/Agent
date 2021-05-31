package rs.ac.uns.ftn.devops.tim5.agentorder.service;

import rs.ac.uns.ftn.devops.tim5.agentorder.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.Product;

public interface ProductService {
    Product getProduct(Long id) throws ResourceNotFoundException;
}

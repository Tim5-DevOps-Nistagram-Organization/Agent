package rs.ac.uns.ftn.devops.tim5.agentproduct.service;

import rs.ac.uns.ftn.devops.tim5.agentproduct.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Product;

import java.util.Collection;

public interface ProductService {
    Product getProduct(Long id) throws ResourceNotFoundException;

    Collection<Product> getAllProducts();

    Product saveProduct(Product product) throws ResourceNotFoundException;

    Product updateProduct(Product productToUpdate) throws ResourceNotFoundException;

    void deleteProduct(Long id) throws ResourceNotFoundException;
}

package rs.ac.uns.ftn.devops.tim5.agentorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.devops.tim5.agentorder.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.Product;
import rs.ac.uns.ftn.devops.tim5.agentorder.repository.ProductRepository;
import rs.ac.uns.ftn.devops.tim5.agentorder.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product getProduct(Long id) throws ResourceNotFoundException {
        return repository.findOneByIdAndDeleted(id, false).orElseThrow(() -> new ResourceNotFoundException("Product"));
    }

}

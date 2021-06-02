package rs.ac.uns.ftn.devops.tim5.agentreport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.devops.tim5.agentreport.model.Product;
import rs.ac.uns.ftn.devops.tim5.agentreport.repository.ProductRepository;
import rs.ac.uns.ftn.devops.tim5.agentreport.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }
}

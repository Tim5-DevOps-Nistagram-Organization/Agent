package rs.ac.uns.ftn.devops.tim5.agentproduct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.devops.tim5.agentproduct.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Product;
import rs.ac.uns.ftn.devops.tim5.agentproduct.repository.ProductRepository;
import rs.ac.uns.ftn.devops.tim5.agentproduct.service.ImageService;
import rs.ac.uns.ftn.devops.tim5.agentproduct.service.ProductService;

import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ImageService imageService;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ImageService imageService) {
        this.repository = repository;
        this.imageService = imageService;
    }

    @Override
    public Product getProduct(Long id) throws ResourceNotFoundException {
        return repository.findOneByIdAndDeleted(id, false).orElseThrow(() -> new ResourceNotFoundException("Product"));
    }

    @Override
    public Collection<Product> getAllProducts() {
        return repository.findAllByDeleted(false);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) throws ResourceNotFoundException {
        product.setDeleted(false);
        product.setImage(product.getImage() != null ? imageService.getImage(product.getImage().getId()) : null);
        return repository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product productToUpdate) throws ResourceNotFoundException {
        Product product = getProduct(productToUpdate.getId());
        product.setName(productToUpdate.getName());
        product.setOnStock(productToUpdate.getOnStock());
        product.setPrice(productToUpdate.getPrice());
        product.setImage(
                productToUpdate.getImage() != null ? imageService.getImage(productToUpdate.getImage().getId()) : null);

        return repository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) throws ResourceNotFoundException {
        Product product = getProduct(id);
        repository.delete(product);
    }
}

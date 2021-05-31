package rs.ac.uns.ftn.devops.tim5.agentorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.devops.tim5.agentorder.exception.ProductNotAvailable;
import rs.ac.uns.ftn.devops.tim5.agentorder.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.CustomerOrder;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.Item;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.Product;
import rs.ac.uns.ftn.devops.tim5.agentorder.repository.CustomerOrderRepository;
import rs.ac.uns.ftn.devops.tim5.agentorder.service.CustomerOrderService;
import rs.ac.uns.ftn.devops.tim5.agentorder.service.ProductService;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepository repository;
    private final ProductService productService;

    @Autowired
    public CustomerOrderServiceImpl(CustomerOrderRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public CustomerOrder create(CustomerOrder order) throws ResourceNotFoundException, ProductNotAvailable {
        double sum = 0;
        for (Item item : order.getItems()) {
            Product product = productService.getProduct(item.getProduct().getId());
            if (product.getOnStock() >= item.getQuantity()) {
                item.setPrice(product.getPrice());
                item.setProduct(product);
                product.setOnStock(product.getOnStock() - item.getQuantity());
                sum += item.getQuantity() * item.getPrice();
            } else {
                throw new ProductNotAvailable(product.getName());
            }
        }
        order.setTotalPrice(sum);

        return repository.save(order);
    }
}

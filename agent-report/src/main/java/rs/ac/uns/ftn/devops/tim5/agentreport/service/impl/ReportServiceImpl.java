package rs.ac.uns.ftn.devops.tim5.agentreport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.devops.tim5.agentreport.model.Product;
import rs.ac.uns.ftn.devops.tim5.agentreport.service.ItemService;
import rs.ac.uns.ftn.devops.tim5.agentreport.service.ProductService;
import rs.ac.uns.ftn.devops.tim5.agentreport.service.ReportService;

import java.util.Collection;
import java.util.HashMap;

@Service
public class ReportServiceImpl implements ReportService {

    private final ItemService itemService;
    private final ProductService productService;

    @Autowired
    public ReportServiceImpl(ItemService itemService, ProductService productService) {
        this.itemService = itemService;
        this.productService = productService;
    }

    @Override
    public HashMap<Product, Integer> reportMostSold() {
        HashMap<Product, Integer> report = new HashMap<>();
        Collection<Product> products = productService.getProducts();
        for (Product product : products) {
            report.put(product, itemService.numOfProductSold(product.getId()));
        }
        return report;
    }

    @Override
    public HashMap<Product, Double> reportMostEarned() {
        HashMap<Product, Double> report = new HashMap<>();
        Collection<Product> products = productService.getProducts();
        for (Product product : products) {
            report.put(product, itemService.productEarning(product.getId()));
        }
        return report;
    }
}

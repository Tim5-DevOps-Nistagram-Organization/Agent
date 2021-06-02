package rs.ac.uns.ftn.devops.tim5.agentreport.service;

import rs.ac.uns.ftn.devops.tim5.agentreport.model.Product;

import java.util.HashMap;

public interface ReportService {
    HashMap<Product, Integer> reportMostSold();

    HashMap<Product, Double> reportMostEarned();
}

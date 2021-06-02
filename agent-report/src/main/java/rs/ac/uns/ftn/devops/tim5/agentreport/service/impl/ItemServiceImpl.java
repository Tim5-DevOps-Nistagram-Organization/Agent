package rs.ac.uns.ftn.devops.tim5.agentreport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.devops.tim5.agentreport.repository.ItemRepository;
import rs.ac.uns.ftn.devops.tim5.agentreport.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Integer numOfProductSold(Long id) {
        return repository.numOfProductSold(id).orElse(0);
    }

    @Override
    public Double productEarning(Long id) {
        return repository.productEarning(id).orElse(0.0);
    }
}

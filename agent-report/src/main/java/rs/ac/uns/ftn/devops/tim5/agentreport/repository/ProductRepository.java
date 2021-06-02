package rs.ac.uns.ftn.devops.tim5.agentreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.devops.tim5.agentreport.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

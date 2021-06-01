package rs.ac.uns.ftn.devops.tim5.agentreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.devops.tim5.agentreport.model.Item;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT SUM(i.quantity) FROM Item i WHERE i.product.id = ?1")
    Optional<Integer> numOfProductSold(Long productId);

    @Query(value = "SELECT SUM(i.price) FROM Item i WHERE i.product.id = ?1")
    Optional<Double> productEarning(Long productId);
}

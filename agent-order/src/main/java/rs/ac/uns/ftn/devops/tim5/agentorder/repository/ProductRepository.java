package rs.ac.uns.ftn.devops.tim5.agentorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findOneByIdAndDeleted(Long id, Boolean deleted);
}

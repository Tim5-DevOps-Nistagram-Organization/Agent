package rs.ac.uns.ftn.devops.tim5.agentproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}

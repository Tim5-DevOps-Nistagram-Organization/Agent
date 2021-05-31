package rs.ac.uns.ftn.devops.tim5.agentproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Image;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private Integer onStock;
    private Long imageId;
}

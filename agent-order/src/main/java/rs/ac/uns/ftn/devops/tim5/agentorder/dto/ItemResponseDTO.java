package rs.ac.uns.ftn.devops.tim5.agentorder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {

    private String productName;
    private Double productPrice;
    private Integer quantity;
}

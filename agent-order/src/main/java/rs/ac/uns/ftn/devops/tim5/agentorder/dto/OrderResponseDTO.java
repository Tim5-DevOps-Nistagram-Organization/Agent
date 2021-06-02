package rs.ac.uns.ftn.devops.tim5.agentorder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private String customerName;
    private String customerSurname;
    private String customerAddress;
    private Double totalPrice;
    private Set<ItemResponseDTO> items;

}

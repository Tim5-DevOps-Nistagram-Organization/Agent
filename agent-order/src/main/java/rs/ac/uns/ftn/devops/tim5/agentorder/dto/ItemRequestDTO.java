package rs.ac.uns.ftn.devops.tim5.agentorder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {

    @NotNull(message = "Product can not be null")
    private Long productId;

    @NotNull(message = "Quantity can not be null")
    @Min(value = 1, message = "Quantity min value is 1")
    private Integer quantity;
}

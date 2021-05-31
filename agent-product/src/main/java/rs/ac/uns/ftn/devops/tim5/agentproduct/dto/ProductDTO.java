package rs.ac.uns.ftn.devops.tim5.agentproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotNull(message = "Price can not be null")
    @DecimalMin(value = "0", message = "Price min value is 0")
    private Double price;


    @NotNull(message = "On stock can not be null")
    @Min(value = 0, message = "On stock min value is 0")
    private Integer onStock;


    @NotNull(message = "Image can not be null")
    private Long imageId;
}

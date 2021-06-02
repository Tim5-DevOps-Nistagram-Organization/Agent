package rs.ac.uns.ftn.devops.tim5.agentorder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
    private String customerName;

    @NotNull(message = "Surname can not be null")
    @NotBlank(message = "Surname can not be blank")
    private String customerSurname;

    @NotNull(message = "Address can not be null")
    @NotBlank(message = "Address can not be blank")
    private String customerAddress;

    @NotEmpty(message = "Items can not be empty")
    private Set<@Valid ItemRequestDTO> items;
}

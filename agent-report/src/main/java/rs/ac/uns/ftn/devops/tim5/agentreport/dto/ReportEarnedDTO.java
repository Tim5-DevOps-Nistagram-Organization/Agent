package rs.ac.uns.ftn.devops.tim5.agentreport.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportEarnedDTO {

    private Long productId;
    private String productName;
    private Double earned;

}

package rs.ac.uns.ftn.devops.tim5.agentreport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String customerSurname;

    private String customerAddress;

    private Double totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Item> items;
}

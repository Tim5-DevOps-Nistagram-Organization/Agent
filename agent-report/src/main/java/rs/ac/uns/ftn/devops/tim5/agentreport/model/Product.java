package rs.ac.uns.ftn.devops.tim5.agentreport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer onStock;

    private Boolean deleted;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Image image;
}

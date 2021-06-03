package rs.ac.uns.ftn.devops.tim5.agentorder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
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

    public CustomerOrder(String customerName, String customerSurname, String customerAddress, Set<Item> items) {
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerAddress = customerAddress;
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder order = (CustomerOrder) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(customerName, order.customerName) &&
                Objects.equals(customerSurname, order.customerSurname) &&
                Objects.equals(customerAddress, order.customerAddress) &&
                Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, customerSurname, customerAddress, totalPrice, items);
    }
}

package rs.ac.uns.ftn.devops.tim5.agentproduct.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(onStock, product.onStock) &&
                Objects.equals(deleted, product.deleted) &&
                Objects.equals(image == null ? null : image.getId(),
                        product.getImage() == null ? null : product.getImage().getId());

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, onStock, deleted, image);
    }
}

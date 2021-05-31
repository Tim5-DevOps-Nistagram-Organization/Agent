package rs.ac.uns.ftn.devops.tim5.agentproduct.mapper;

import rs.ac.uns.ftn.devops.tim5.agentproduct.dto.ProductDTO;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Image;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getOnStock(),
                product.getImage() != null ? product.getImage().getId() : 0L);
    }

    public static Product toEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.getId(), productDTO.getName(), productDTO.getPrice(), productDTO.getOnStock(), false,
                productDTO.getImageId() != 0L ? new Image(productDTO.getImageId(), new byte[0]) : null);
    }
}

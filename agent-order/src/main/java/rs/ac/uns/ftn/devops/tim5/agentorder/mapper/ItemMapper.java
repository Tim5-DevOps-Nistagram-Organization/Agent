package rs.ac.uns.ftn.devops.tim5.agentorder.mapper;

import rs.ac.uns.ftn.devops.tim5.agentorder.dto.ItemRequestDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.dto.ItemResponseDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.Item;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.Product;

public class ItemMapper {
    public static Item toEntity(ItemRequestDTO dto) {
        return new Item(dto.getQuantity(), new Product(dto.getProductId()));
    }

    public static ItemResponseDTO toDTO(Item item) {
        return new ItemResponseDTO(item.getProduct().getName(), item.getPrice(), item.getQuantity());
    }
}

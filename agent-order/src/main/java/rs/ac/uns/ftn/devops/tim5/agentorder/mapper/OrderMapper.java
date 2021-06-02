package rs.ac.uns.ftn.devops.tim5.agentorder.mapper;

import rs.ac.uns.ftn.devops.tim5.agentorder.dto.OrderRequestDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.dto.OrderResponseDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.model.CustomerOrder;

import java.util.stream.Collectors;

public class OrderMapper {

    public static CustomerOrder toEntity(OrderRequestDTO dto) {
        return new CustomerOrder(dto.getCustomerName(), dto.getCustomerSurname(), dto.getCustomerAddress(),
                dto.getItems().stream().map(ItemMapper::toEntity).collect(Collectors.toSet()));
    }

    public static OrderResponseDTO toDTO(CustomerOrder order) {
        return new OrderResponseDTO(order.getCustomerName(), order.getCustomerSurname(), order.getCustomerAddress(),
                order.getTotalPrice(), order.getItems().stream().map(ItemMapper::toDTO).collect(Collectors.toSet()));
    }
}

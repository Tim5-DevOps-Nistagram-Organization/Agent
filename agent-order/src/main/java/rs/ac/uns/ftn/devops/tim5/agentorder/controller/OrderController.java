package rs.ac.uns.ftn.devops.tim5.agentorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.devops.tim5.agentorder.dto.OrderRequestDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.dto.OrderResponseDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.exception.ProductNotAvailable;
import rs.ac.uns.ftn.devops.tim5.agentorder.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentorder.mapper.OrderMapper;
import rs.ac.uns.ftn.devops.tim5.agentorder.service.CustomerOrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final CustomerOrderService service;

    @Autowired
    public OrderController(CustomerOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> order(@Valid @RequestBody OrderRequestDTO orderDTO) throws ResourceNotFoundException, ProductNotAvailable {
        return new ResponseEntity<>(OrderMapper.toDTO(service.create(OrderMapper.toEntity(orderDTO))), HttpStatus.OK);
    }
}

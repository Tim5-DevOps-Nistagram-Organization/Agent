package rs.ac.uns.ftn.devops.tim5.agentproduct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.devops.tim5.agentproduct.dto.ProductDTO;
import rs.ac.uns.ftn.devops.tim5.agentproduct.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentproduct.mapper.ProductMapper;
import rs.ac.uns.ftn.devops.tim5.agentproduct.service.ProductService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Collection<ProductDTO>> getAll() {
        return new ResponseEntity<>(
                productService.getAllProducts().stream().map(ProductMapper::toDTO).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(ProductMapper.toDTO(productService.getProduct(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(
                ProductMapper.toDTO(productService.saveProduct(ProductMapper.toEntity(productDTO))), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO productDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(
                ProductMapper.toDTO(productService.updateProduct(ProductMapper.toEntity(productDTO))), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}

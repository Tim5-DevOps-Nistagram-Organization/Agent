package rs.ac.uns.ftn.devops.tim5.agentorder.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.devops.tim5.agentorder.constants.OrderConstants;
import rs.ac.uns.ftn.devops.tim5.agentorder.dto.ErrorMessageDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.dto.OrderRequestDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.dto.OrderResponseDTO;
import rs.ac.uns.ftn.devops.tim5.agentorder.repository.CustomerOrderRepository;
import rs.ac.uns.ftn.devops.tim5.agentorder.repository.ProductRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CustomerOrderRepository orderRepository;

    @MockBean
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        when(productRepository.findOneByIdAndDeleted(OrderConstants.PRODUCT1.getId(), false))
                .thenReturn(Optional.of(OrderConstants.PRODUCT1));
        when(productRepository.findOneByIdAndDeleted(OrderConstants.PRODUCT2.getId(), false))
                .thenReturn(Optional.of(OrderConstants.PRODUCT2));
        when(productRepository.findOneByIdAndDeleted(OrderConstants.PRODUCT3.getId(), false))
                .thenReturn(Optional.of(OrderConstants.PRODUCT3));
        when(orderRepository.save(eq(OrderConstants.ORDER_REQUEST_NEW)))
                .thenReturn(OrderConstants.ORDER_REQUEST_NEW_CREATED);
    }

    @Test
    public void testCreateOrder_Success() throws URISyntaxException {
        URI uri = new URI(OrderConstants.BASE_URL);
        HttpEntity<OrderRequestDTO> req = new HttpEntity<>(OrderConstants.ORDER_REQUEST_DTO_NEW, new HttpHeaders());

        ResponseEntity<OrderResponseDTO> res = restTemplate.exchange(uri, HttpMethod.POST, req, OrderResponseDTO.class);

        assertEquals(HttpStatus.OK, res.getStatusCode());

        OrderResponseDTO responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals(OrderConstants.ORDER_REQUEST_NEW_CREATED.getCustomerName(), responseBody.getCustomerName());
        assertEquals(OrderConstants.ORDER_REQUEST_NEW_CREATED.getCustomerSurname(), responseBody.getCustomerSurname());
        assertEquals(OrderConstants.ORDER_REQUEST_NEW_CREATED.getCustomerAddress(), responseBody.getCustomerAddress());
        assertEquals(OrderConstants.ORDER_REQUEST_NEW_CREATED.getTotalPrice(), responseBody.getTotalPrice());
        assertEquals(OrderConstants.ORDER_REQUEST_NEW_CREATED.getItems().size(), responseBody.getItems().size());
    }

    @Test
    public void testCreateOrder_Exception_ProductNotAvailable() throws URISyntaxException {
        URI uri = new URI(OrderConstants.BASE_URL);
        HttpEntity<OrderRequestDTO> req = new HttpEntity<>(OrderConstants.ORDER_REQUEST_DTO_NEW_NOT_AVAILABLE,
                new HttpHeaders());

        ResponseEntity<ErrorMessageDTO> res = restTemplate.exchange(uri, HttpMethod.POST, req, ErrorMessageDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());

        ErrorMessageDTO responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals("Product Product 1 is not available.", responseBody.getMessage());
    }
}

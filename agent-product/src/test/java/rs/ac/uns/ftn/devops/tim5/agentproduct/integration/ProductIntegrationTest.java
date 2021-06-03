package rs.ac.uns.ftn.devops.tim5.agentproduct.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.devops.tim5.agentproduct.constants.ProductConstants;
import rs.ac.uns.ftn.devops.tim5.agentproduct.dto.ErrorMessageDTO;
import rs.ac.uns.ftn.devops.tim5.agentproduct.dto.ProductDTO;
import rs.ac.uns.ftn.devops.tim5.agentproduct.repository.ImageRepository;
import rs.ac.uns.ftn.devops.tim5.agentproduct.repository.ProductRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ImageRepository imageRepository;

    @Before
    public void setUp() {
        when(productRepository.findAllByDeleted(false)).thenReturn(ProductConstants.PRODUCTS);

        when(productRepository.save(eq(ProductConstants.PRODUCT_NEW)))
                .thenReturn(ProductConstants.PRODUCT_NEW_CREATED);
        when(imageRepository.findById(ProductConstants.PRODUCT_NEW_IMAGE_DONT_EXIST.getImage().getId()))
                .thenReturn(Optional.empty());

        when(productRepository.findOneByIdAndDeleted(ProductConstants.PRODUCT_UPDATE.getId(), false))
                .thenReturn(Optional.of(ProductConstants.PRODUCT1));
        when(productRepository.save(eq(ProductConstants.PRODUCT_UPDATE)))
                .thenReturn(ProductConstants.PRODUCT_UPDATE);

        when(productRepository.findOneByIdAndDeleted(ProductConstants.PRODUCT_DELETE.getId(), false))
                .thenReturn(Optional.of(ProductConstants.PRODUCT_DELETE));
        doNothing().when(productRepository).delete(eq(ProductConstants.PRODUCT1));

        when(productRepository.findOneByIdAndDeleted(ProductConstants.PRODUCT_DELETE_ID_DOESNT_EXISTS, false))
                .thenReturn(Optional.empty());
    }

    @Test
    public void testGetAllProducts_Success() throws URISyntaxException {
        URI uri = new URI(ProductConstants.BASE_URL);
        HttpEntity<Object> req = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<Collection<ProductDTO>> res = restTemplate.exchange(uri, HttpMethod.GET, req,
                new ParameterizedTypeReference<>() {
                });

        assertEquals(HttpStatus.OK, res.getStatusCode());

        Collection<ProductDTO> responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals(ProductConstants.PRODUCTS.size(), responseBody.size());
        for (ProductDTO p : responseBody) {
            assertNotNull(p.getImageId());
        }
    }


    @Test
    public void testCreateNewProduct_Success() throws URISyntaxException {
        URI uri = new URI(ProductConstants.BASE_URL);
        HttpEntity<ProductDTO> req = new HttpEntity<>(ProductConstants.PRODUCT_DTO_NEW, new HttpHeaders());

        ResponseEntity<ProductDTO> res = restTemplate.exchange(uri, HttpMethod.POST, req, ProductDTO.class);

        assertEquals(HttpStatus.OK, res.getStatusCode());

        ProductDTO responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals(ProductConstants.PRODUCT_NEW_CREATED.getId(), responseBody.getId());
        assertEquals(ProductConstants.PRODUCT_NEW_CREATED.getName(), responseBody.getName());
        assertEquals(ProductConstants.PRODUCT_NEW_CREATED.getPrice(), responseBody.getPrice());
        assertEquals(ProductConstants.PRODUCT_NEW_CREATED.getOnStock(), responseBody.getOnStock());
    }

    @Test
    public void testCreateNewProduct_Exception_ImageNotFound() throws URISyntaxException {
        URI uri = new URI(ProductConstants.BASE_URL);
        HttpEntity<ProductDTO> req = new HttpEntity<>(ProductConstants.PRODUCT_DTO_NEW_IMAGE_DONT_EXIST,
                new HttpHeaders());

        ResponseEntity<ErrorMessageDTO> res = restTemplate.exchange(uri, HttpMethod.POST, req, ErrorMessageDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());

        ErrorMessageDTO responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.NOT_FOUND, responseBody.getHttpStatus());
        assertEquals("Image not found.", responseBody.getMessage());
    }

    @Test
    public void testUpdateProduct_Success() throws URISyntaxException {
        URI uri = new URI(ProductConstants.BASE_URL);
        HttpEntity<ProductDTO> req = new HttpEntity<>(ProductConstants.PRODUCT_DTO_UPDATE, new HttpHeaders());

        ResponseEntity<ProductDTO> res = restTemplate.exchange(uri, HttpMethod.PUT, req, ProductDTO.class);

        assertEquals(HttpStatus.OK, res.getStatusCode());

        ProductDTO responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals(ProductConstants.PRODUCT_UPDATE.getId(), responseBody.getId());
        assertEquals(ProductConstants.PRODUCT_UPDATE.getName(), responseBody.getName());
        assertEquals(ProductConstants.PRODUCT_UPDATE.getPrice(), responseBody.getPrice());
        assertEquals(ProductConstants.PRODUCT_UPDATE.getOnStock(), responseBody.getOnStock());
    }

    @Test
    public void testUpdateProduct_Exception_PriceMinIs0() throws URISyntaxException {
        URI uri = new URI(ProductConstants.BASE_URL);
        HttpEntity<ProductDTO> req = new HttpEntity<>(ProductConstants.PRODUCT_DTO_UPDATE_PRICE_MIN_IS_0,
                new HttpHeaders());

        ResponseEntity<ErrorMessageDTO> res = restTemplate.exchange(uri, HttpMethod.PUT, req, ErrorMessageDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());

        ErrorMessageDTO responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.BAD_REQUEST, responseBody.getHttpStatus());
        assertEquals("Price min value is 0", responseBody.getMessage());
    }

    @Test
    public void testDeleteProduct_Success() throws URISyntaxException {
        URI uri = new URI(ProductConstants.BASE_URL + "/" + ProductConstants.PRODUCT_DELETE.getId());
        HttpEntity<Object> req = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.DELETE, req, String.class);

        assertEquals(HttpStatus.OK, res.getStatusCode());

        String responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals("Successfully deleted!", responseBody);
    }

    @Test
    public void testDeleteProduct_Exception_ProductDoesntExists() throws URISyntaxException {
        URI uri = new URI(ProductConstants.BASE_URL + "/" + ProductConstants.PRODUCT_DELETE_ID_DOESNT_EXISTS);
        HttpEntity<Object> req = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<ErrorMessageDTO> res = restTemplate.exchange(uri, HttpMethod.DELETE, req, ErrorMessageDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());

        ErrorMessageDTO responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.NOT_FOUND, responseBody.getHttpStatus());
        assertEquals("Product not found.", responseBody.getMessage());
    }
}

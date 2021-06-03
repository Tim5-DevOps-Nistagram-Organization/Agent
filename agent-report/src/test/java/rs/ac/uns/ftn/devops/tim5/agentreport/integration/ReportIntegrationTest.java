package rs.ac.uns.ftn.devops.tim5.agentreport.integration;

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
import rs.ac.uns.ftn.devops.tim5.agentreport.constants.ReportConstants;
import rs.ac.uns.ftn.devops.tim5.agentreport.dto.ReportEarnedDTO;
import rs.ac.uns.ftn.devops.tim5.agentreport.dto.ReportSoldDTO;
import rs.ac.uns.ftn.devops.tim5.agentreport.repository.ItemRepository;
import rs.ac.uns.ftn.devops.tim5.agentreport.repository.ProductRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReportIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;


    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ItemRepository itemRepository;

    @Before
    public void setUp() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>() {{
            this.add(ReportConstants.PRODUCT);
        }});

        when(itemRepository.numOfProductSold(ReportConstants.PRODUCT.getId()))
                .thenReturn(Optional.of(ReportConstants.PRODUCT_SOLD));

        when(itemRepository.productEarning(ReportConstants.PRODUCT.getId()))
                .thenReturn(Optional.of(ReportConstants.PRODUCT_EARNED));
    }

    @Test
    public void testSoldReport_Success() throws URISyntaxException {
        URI uri = new URI(ReportConstants.BASE_URL + "/sold");
        HttpEntity<Object> req = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<Collection<ReportSoldDTO>> res = restTemplate.exchange(uri, HttpMethod.GET, req,
                new ParameterizedTypeReference<>() {
                });

        assertEquals(HttpStatus.OK, res.getStatusCode());

        Collection<ReportSoldDTO> responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals(1, responseBody.size());
        ReportSoldDTO report = ((ArrayList<ReportSoldDTO>) responseBody).get(0);
        assertEquals(ReportConstants.PRODUCT.getId(), report.getProductId());
        assertEquals(ReportConstants.PRODUCT.getName(), report.getProductName());
        assertEquals(ReportConstants.PRODUCT_SOLD, report.getSold());
    }

    @Test
    public void testEarnedReport_Success() throws URISyntaxException {
        URI uri = new URI(ReportConstants.BASE_URL + "/earned");
        HttpEntity<Object> req = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<Collection<ReportEarnedDTO>> res = restTemplate.exchange(uri, HttpMethod.GET, req,
                new ParameterizedTypeReference<>() {
                });

        assertEquals(HttpStatus.OK, res.getStatusCode());

        Collection<ReportEarnedDTO> responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals(1, responseBody.size());
        ReportEarnedDTO report = ((ArrayList<ReportEarnedDTO>) responseBody).get(0);
        assertEquals(ReportConstants.PRODUCT.getId(), report.getProductId());
        assertEquals(ReportConstants.PRODUCT.getName(), report.getProductName());
        assertEquals(ReportConstants.PRODUCT_EARNED, report.getEarned());
    }

}

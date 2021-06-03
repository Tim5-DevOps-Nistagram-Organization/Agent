package rs.ac.uns.ftn.devops.tim5.agentproduct.integration;

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
import rs.ac.uns.ftn.devops.tim5.agentproduct.constants.ImageConstants;
import rs.ac.uns.ftn.devops.tim5.agentproduct.dto.ErrorMessageDTO;
import rs.ac.uns.ftn.devops.tim5.agentproduct.repository.ImageRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ImageIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ImageRepository imageRepository;

    @Before
    public void setUp() {

        when(imageRepository.findById(ImageConstants.IMAGE_ID_DOESNT_EXIST)).thenReturn(Optional.empty());
    }

    @Test
    public void testGetById_Exception_ImageDoesntExist() throws URISyntaxException {
        URI uri = new URI(ImageConstants.BASE_URL + "/" + ImageConstants.IMAGE_ID_DOESNT_EXIST);
        HttpEntity<Object> req = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<ErrorMessageDTO> res = restTemplate.exchange(uri, HttpMethod.GET, req, ErrorMessageDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());

        ErrorMessageDTO responseBody = res.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.NOT_FOUND, responseBody.getHttpStatus());
        assertEquals("Image not found.", responseBody.getMessage());
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation.candidate.restapi2.api;

import evaluation.candidate.restapi2.Application;
import evaluation.candidate.restapi2.data.dao.ClientDao;
import evaluation.candidate.restapi2.data.persistance.ClientRepository;
import evaluation.candidate.restapi2.model.Client;
import evaluation.candidate.restapi2.service.ClientService;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author Raymond
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//@DataJpaTest
public class ClientApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Mock
    private static ClientRepository repository;

    @Mock
    private static ClientService service;

    @Mock
    private static ClientDao client1;

    public ClientApiControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of createClient method, of class ClientApiController.
     */
    @Test
    public void testCreateClient() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/Assessment/client";
        URI uri = new URI(baseUrl);
        Client client = new Client("Raymond", "Ralph","0824449813", "7605235084083", null);
        HttpEntity<Client> request = new HttpEntity<>(client);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
    }
    
}

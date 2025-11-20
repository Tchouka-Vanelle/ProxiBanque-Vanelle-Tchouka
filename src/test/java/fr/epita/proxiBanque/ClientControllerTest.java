package fr.epita.proxiBanque;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epita.proxiBanque.controller.ClientController;
import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // Spring Boot 3.4+
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // pour post(), get()
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; // pour status(), jsonPath()



@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    // TEST CREATION CLIENT (POST)
    @Test
    void testCreerClient() throws Exception {
        Client client = new Client();
        client.setNom("Dupont");
        client.setPrenom("Jean");
        client.setAdresse("10 rue des Lilas");
        client.setCodePostal("75000");
        client.setVille("Paris");
        client.setTelephone("0123456789");
        client.setId(1L);

        // simulation du service
        when(clientService.creerClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Dupont"))
                .andExpect(jsonPath("$.prenom").value("Jean"))
                .andExpect(jsonPath("$.adresse").value("10 rue des Lilas"))
                .andExpect(jsonPath("$.codePostal").value("75000"))
                .andExpect(jsonPath("$.ville").value("Paris"))
                .andExpect(jsonPath("$.telephone").value("0123456789"));
    }

    // TEST LIRE CLIENT (GET)
    @Test
    void testLireClient() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setNom("Dupont");
        client.setPrenom("Jean");

        when(clientService.lireClient(1L)).thenReturn(client);

        mockMvc.perform(get("/api/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Dupont"))
                .andExpect(jsonPath("$.prenom").value("Jean"));
    }
}

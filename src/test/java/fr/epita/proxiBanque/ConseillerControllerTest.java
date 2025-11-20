package fr.epita.proxiBanque;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epita.proxiBanque.controller.ConseillerController;
import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.service.ConseillerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConseillerController.class)
public class ConseillerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConseillerService conseillerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testVirement() throws Exception {
        doNothing().when(conseillerService).effectuerVirement(1L, 2L, 100.0);

        mockMvc.perform(post("/api/conseiller/virement")
                        .param("source", "1")
                        .param("destination", "2")
                        .param("montant", "100.0"))
                .andExpect(status().isOk());
    }

    @Test
    void testSimulationCreditConso() throws Exception {
        when(conseillerService.simulerCreditConso(any(Client.class), any(Double.class), any(Integer.class), any(Double.class)))
                .thenReturn(1050.0);

        mockMvc.perform(get("/api/conseiller/simulation/creditConso")
                        .param("clientId", "1")
                        .param("montant", "1000")
                        .param("duree", "12")
                        .param("taux", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("1050.0"));
    }

    @Test
    void testSimulationCreditImmo() throws Exception {
        when(conseillerService.simulerCreditImmo(any(Client.class), any(Double.class), any(Integer.class), any(Double.class)))
                .thenReturn(1200.0);

        mockMvc.perform(get("/api/conseiller/simulation/creditImmo")
                        .param("clientId", "1")
                        .param("montant", "1000")
                        .param("duree", "12")
                        .param("taux", "20"))
                .andExpect(status().isOk())
                .andExpect(content().string("1200.0"));
    }
}

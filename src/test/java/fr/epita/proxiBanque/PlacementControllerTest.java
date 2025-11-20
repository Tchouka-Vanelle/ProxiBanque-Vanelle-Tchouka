package fr.epita.proxiBanque;

import fr.epita.proxiBanque.controller.PlacementController;
import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.entity.Compte;
import fr.epita.proxiBanque.entity.Placement;
import fr.epita.proxiBanque.service.PlacementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlacementController.class)
public class PlacementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PlacementService placementService;

    @Test
    void testGetPlacements() throws Exception {
        List<Placement> placements = List.of(
                new Placement(1L, "Bourse Paris", 0.05),
                new Placement(2L, "Bourse New-York", 0.04),
                new Placement(3L, "Bourse Tokyo", 0.045)
        );

        when(placementService.proposerPlacements(any(Client.class))).thenReturn(placements);

        mockMvc.perform(get("/api/conseiller/placements")
                        .param("clientId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bourse").value("Bourse Paris"))
                .andExpect(jsonPath("$[1].bourse").value("Bourse New-York"))
                .andExpect(jsonPath("$[2].bourse").value("Bourse Tokyo"));
    }
}

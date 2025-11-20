package fr.epita.proxiBanque.controller;


import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.entity.Placement;
import fr.epita.proxiBanque.service.PlacementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/conseiller")
public class PlacementController {

    private final PlacementService placementService;

    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @GetMapping("/placements")
    public ResponseEntity<List<Placement>> getPlacements(@RequestParam Long clientId) {
        Client client = new Client();
        client.setId(clientId);

        List<Placement> placements = placementService.proposerPlacements(client);
        return ResponseEntity.ok(placements);
    }
}

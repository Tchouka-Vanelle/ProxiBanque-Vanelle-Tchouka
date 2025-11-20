package fr.epita.proxiBanque.controller;


import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.service.GestionSoldeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gestionSolde")
public class GestionSoldeController {

    private final GestionSoldeService gestionSoldeService;

    public GestionSoldeController(GestionSoldeService gestionSoldeService) {
        this.gestionSoldeService = gestionSoldeService;
    }

    @PostMapping("/virement")
    public ResponseEntity<Void> virement(@RequestParam Long source,
                                         @RequestParam Long destination,
                                         @RequestParam double montant) {
        gestionSoldeService.effectuerVirement(source, destination, montant);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/simulation/creditConso")
    public ResponseEntity<Double> simulationCreditConso(@RequestParam Long clientId,
                                                        @RequestParam double montant,
                                                        @RequestParam int duree,
                                                        @RequestParam double taux) {
        Client client = new Client();
        client.setId(clientId);
        double mensualite = gestionSoldeService.simulerCreditConso(client, montant, duree, taux);
        return ResponseEntity.ok(mensualite);
    }


    @GetMapping("/simulation/creditImmo")
    public ResponseEntity<Double> simulationCreditImmo(@RequestParam Long clientId,
                                                       @RequestParam double montant,
                                                       @RequestParam int duree,
                                                       @RequestParam double taux) {
        Client client = new Client();
        client.setId(clientId);
        double mensualite = gestionSoldeService.simulerCreditImmo(client, montant, duree, taux);
        return ResponseEntity.ok(mensualite);
    }
}

package fr.epita.proxiBanque.controller;


import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.service.ConseillerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conseiller")
public class ConseillerController {

    private final ConseillerService conseillerService;

    public ConseillerController(ConseillerService conseillerService) {
        this.conseillerService = conseillerService;
    }

    @PostMapping("/virement")
    public ResponseEntity<Void> virement(@RequestParam Long source,
                                         @RequestParam Long destination,
                                         @RequestParam double montant) {
        conseillerService.effectuerVirement(source, destination, montant);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/simulation/creditConso")
    public ResponseEntity<Double> simulationCreditConso(@RequestParam Long clientId,
                                                        @RequestParam double montant,
                                                        @RequestParam int duree,
                                                        @RequestParam double taux) {
        Client client = new Client();
        client.setId(clientId);
        double mensualite = conseillerService.simulerCreditConso(client, montant, duree, taux);
        return ResponseEntity.ok(mensualite);
    }


    @GetMapping("/simulation/creditImmo")
    public ResponseEntity<Double> simulationCreditImmo(@RequestParam Long clientId,
                                                       @RequestParam double montant,
                                                       @RequestParam int duree,
                                                       @RequestParam double taux) {
        Client client = new Client();
        client.setId(clientId);
        double mensualite = conseillerService.simulerCreditImmo(client, montant, duree, taux);
        return ResponseEntity.ok(mensualite);
    }
}

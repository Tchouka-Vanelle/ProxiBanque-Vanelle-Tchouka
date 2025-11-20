package fr.epita.proxiBanque.controller;


import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> creerClient(@RequestBody Client client) {
        Client created = clientService.creerClient(client);
        return ResponseEntity
                .created(URI.create("/api/clients/" + created.getId()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> lireClient(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clientService.lireClient(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Client>> lireClients() {
        return ResponseEntity.ok(clientService.lireClients());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Client> modifierClient(
            @PathVariable Long id,
            @RequestBody Client clientModifie) {

        try {
            Client updated = clientService.modifierClient(id, clientModifie);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerClient(@PathVariable Long id) {
        try {
            clientService.supprimerClient(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

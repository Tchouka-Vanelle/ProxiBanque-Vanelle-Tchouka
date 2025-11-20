package fr.epita.proxiBanque.service;

import fr.epita.proxiBanque.entity.CarteBancaire;
import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.entity.Compte;
import fr.epita.proxiBanque.repository.CarteBancaireRepository;
import fr.epita.proxiBanque.repository.ClientRepository;
import fr.epita.proxiBanque.repository.CompteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;
    private final CarteBancaireRepository CarteBancaireRepository;

    public ClientServiceImpl(ClientRepository clientRepository, CompteRepository compteRepository, CarteBancaireRepository CarteBancaireRepository) {
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
        this.CarteBancaireRepository = CarteBancaireRepository;
    }

    @Override
    public Client creerClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client lireClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));
    }

    @Override
    public List<Client> lireClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client modifierClient(Long id, Client nouveauClient) {
        Client existant = lireClient(id);

        existant.setNom(nouveauClient.getNom());
        existant.setPrenom(nouveauClient.getPrenom());
        existant.setAdresse(nouveauClient.getAdresse());
        existant.setCodePostal(nouveauClient.getCodePostal());
        existant.setVille(nouveauClient.getVille());
        existant.setTelephone(nouveauClient.getTelephone());

        return clientRepository.save(existant);
    }


    @Transactional
    public void supprimerClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        for (Compte compte : client.getComptes()) {
            compteRepository.delete(compte);
        }

        for (CarteBancaire carte : client.getCartes()) {
            carte.setActive(false); // ou delete
            CarteBancaireRepository.save(carte);
        }

        clientRepository.delete(client);
    }

}

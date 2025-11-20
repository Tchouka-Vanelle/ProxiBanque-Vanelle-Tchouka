package fr.epita.proxiBanque.service;

import fr.epita.proxiBanque.entity.Client;

import java.util.List;

public interface ClientService {

    Client creerClient(Client client);

    Client lireClient(Long id);

    List<Client> lireClients();

    Client modifierClient(Long id, Client nouveauClient);

    void supprimerClient(Long id);
}


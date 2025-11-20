package fr.epita.proxiBanque.service;


import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.entity.Placement;

import java.util.List;

public interface PlacementService {

    // Pour clients fortunés
    List<Placement> proposerPlacements(Client clientFortune);
}

package fr.epita.proxiBanque.service;

import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.entity.Placement;
import fr.epita.proxiBanque.repository.PlacementRepository;
import fr.epita.proxiBanque.service.PlacementService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlacementServiceImpl implements PlacementService {

    private final PlacementRepository placementRepository;

    public PlacementServiceImpl(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    @Override
    public List<Placement> proposerPlacements(Client clientFortune) {
        if (clientFortune.getFortune() <= 500_000) {
            throw new RuntimeException("Client non éligible à la gestion de patrimoine");
        }

        List<Placement> placements = new ArrayList<>();
        placements.add(new Placement(null, "Bourse Paris", 0.05));
        placements.add(new Placement(null, "Bourse New-York", 0.04));
        placements.add(new Placement(null, "Bourse Tokyo", 0.045));

        return placementRepository.saveAll(placements);
    }
}

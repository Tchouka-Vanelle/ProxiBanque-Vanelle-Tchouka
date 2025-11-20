package fr.epita.proxiBanque.repository;

import fr.epita.proxiBanque.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacementRepository extends JpaRepository<Placement, Long> {
}


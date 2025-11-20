package fr.epita.proxiBanque.repository;

import fr.epita.proxiBanque.entity.Conseiller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConseillerRepository extends JpaRepository<Conseiller, Long> {
}


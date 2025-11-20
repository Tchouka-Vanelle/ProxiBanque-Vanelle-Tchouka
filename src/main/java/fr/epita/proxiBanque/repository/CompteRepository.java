package fr.epita.proxiBanque.repository;

import fr.epita.proxiBanque.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}


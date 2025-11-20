package fr.epita.proxiBanque.repository;

import fr.epita.proxiBanque.entity.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenceRepository extends JpaRepository<Agence, String> {
}

package fr.epita.proxiBanque.repository;

import fr.epita.proxiBanque.entity.CarteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteBancaireRepository extends JpaRepository<CarteBancaire, Long> {
}


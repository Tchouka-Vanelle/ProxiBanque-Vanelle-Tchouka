package fr.epita.proxiBanque.repository;


import fr.epita.proxiBanque.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}


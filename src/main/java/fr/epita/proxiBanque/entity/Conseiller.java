package fr.epita.proxiBanque.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Conseiller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String nbrClient;

    @OneToMany(mappedBy = "conseiller", cascade = CascadeType.PERSIST)
    private List<Client> clients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;

    // On ajoute en verifiant la limite de 10 clients
    public void ajouterClient(Client client) {
        if (clients.size() >= 10) {
            throw new IllegalStateException("Un conseiller ne peut pas etre responsable de plus de 10 clients.");
        }
        clients.add(client);
        client.setConseiller(this);
    }
}

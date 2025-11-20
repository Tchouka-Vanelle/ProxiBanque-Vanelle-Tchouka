package fr.epita.proxiBanque.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Compte> comptes = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private List<CarteBancaire> cartes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "conseiller_id")
    private Conseiller conseiller;

    @Transient // ne sera pas persistant dans la base
    public double getFortune() {
        return comptes.stream()
                .mapToDouble(Compte::getSolde)
                .sum();
    }
}

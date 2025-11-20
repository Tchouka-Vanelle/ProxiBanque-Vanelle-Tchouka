package fr.epita.proxiBanque.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Agence {

    @Id
    @Pattern(regexp = "^[A-Za-z0-9]{5}$")
    private String id; // 5 caractères alphanumériques
    private LocalDate dateCreation;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gerant_id")
    private Gerant gerant;*/

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private List<Conseiller> conseillers = new ArrayList<>();
}

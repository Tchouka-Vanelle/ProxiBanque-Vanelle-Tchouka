package fr.epita.proxiBanque.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CompteCourant extends Compte {

    private double decouvertAutorise = 1000;
}


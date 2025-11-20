package fr.epita.proxiBanque.service;

import fr.epita.proxiBanque.entity.Client;

public interface ConseillerService {

    void effectuerVirement(Long idCompteSource, Long idCompteDestination, double montant);

    double simulerCreditConso(Client client, double montant, int dureeMois, double tauxAnnuel);

    double simulerCreditImmo(Client client, double montant, int dureeMois, double tauxAnnuel);
}

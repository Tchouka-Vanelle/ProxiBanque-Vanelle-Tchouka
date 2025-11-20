package fr.epita.proxiBanque.service;


import fr.epita.proxiBanque.entity.Client;
import fr.epita.proxiBanque.entity.Compte;
import fr.epita.proxiBanque.repository.CompteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ConseillerServiceImpl implements ConseillerService {

    private final CompteRepository compteRepository;

    public ConseillerServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Transactional
    @Override
    public void effectuerVirement(Long idCompteSource, Long idCompteDestination, double montant) {
        Compte source = compteRepository.findById(idCompteSource)
                .orElseThrow(() -> new RuntimeException("Compte source introuvable"));
        Compte destination = compteRepository.findById(idCompteDestination)
                .orElseThrow(() -> new RuntimeException("Compte destination introuvable"));
        if(montant < 0)
         new RuntimeException("Impossibe deffectuer un virement d'un montant negatif");

        double disponible = source.getSolde();
        if (source instanceof fr.epita.proxiBanque.entity.CompteCourant) {
            disponible += ((fr.epita.proxiBanque.entity.CompteCourant) source).getDecouvertAutorise();
        }

        if (disponible < montant) {
            throw new RuntimeException("Solde insuffisant pour le virement");
        }

        source.setSolde(source.getSolde() - montant);
        destination.setSolde(destination.getSolde() + montant);

        compteRepository.save(source);
        compteRepository.save(destination);
    }

    @Override
    public double simulerCreditConso(Client client, double montant, int dureeMois, double tauxAnnuel) {
        double interets = montant * tauxAnnuel / 100;
        return (montant + interets) / dureeMois;
    }

    @Override
    public double simulerCreditImmo(Client client, double montant, int dureeMois, double tauxAnnuel) {
        double interets = montant * tauxAnnuel / 100;
        return (montant + interets) / dureeMois;
    }
}

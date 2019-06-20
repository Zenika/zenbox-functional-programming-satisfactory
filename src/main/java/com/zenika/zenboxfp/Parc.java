package com.zenika.zenboxfp;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parc {
    public static final long INTERVAL_TIC_TAC_MS = 1000;

    private EtatParc etat;

    public Parc(List<Usine> usines, int productionElectrique) {
        this.etat = new EtatParc(usines, productionElectrique, null);
    }

    public List<Usine> getUsines() {
        return etat.usines;
    }

    public int getProductionElectrique() {
        return etat.productionElectrique;
    }

    public void setProductionElectrique(int productionElectrique) {
        etat = etat.avecProductionElectrique(productionElectrique);
    }

    public Usine getUsine(int id) {
        return etat.usines.get(id);
    }

    public void setCadence(int id, double cadence) {
        Usine usineRecadencée = etat.usines.get(id).avecCadence(cadence);
        etat = etat.avecUsine(id, usineRecadencée);
    }

    public int livrer(int id, int quantité) {
        ResultatTransfertStock resultatTransfertStock = etat.usines.get(id).stocker(quantité);
        etat = etat.avecUsine(id, resultatTransfertStock.usineAprès);
        return resultatTransfertStock.quantitéRendueAuJoueur;
    }

    public int stocker(int id, int quantité) {
        ResultatTransfertStock resultatTransfertStock = etat.usines.get(id).livrer(quantité);
        etat = etat.avecUsine(id, resultatTransfertStock.usineAprès);
        return resultatTransfertStock.quantitéRendueAuJoueur;
    }

    @Scheduled(fixedRate = INTERVAL_TIC_TAC_MS)
    public void tictac() {
        if (estDisjoncté()) {
            return;
        }
        List<Usine> usinesMisesAJour = new ArrayList<>(etat.usines.size());
        for (Usine usine : etat.usines) {
            usinesMisesAJour.add(usine.tictac(INTERVAL_TIC_TAC_MS));
        }
        etat = etat.avecUsines(usinesMisesAJour);
    }

    private boolean estDisjoncté() {
        double consommationTotale = 0;
        for (Usine usine : etat.usines) {
            consommationTotale += usine.getConsommation();
        }
        return consommationTotale > etat.productionElectrique;
    }

    public List<EtatParc> historique(int depuis) {
        List<EtatParc> historique = new LinkedList<>();
        EtatParc current = etat;
        while (current != null && historique.size() < depuis) {
            historique.add(current);
            current = current.etatPrécédent;
        }
        return historique;
    }
}

package com.zenika.zenboxfp;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
        return appliquerTransfertStock(id, usine -> usine.livrer(quantité));
    }

    public int stocker(int id, int quantité) {
        return appliquerTransfertStock(id, usine -> usine.stocker(quantité));
    }

    private int appliquerTransfertStock(int id, Function<Usine, ResultatTransfertStock> opérationStock) {
        ResultatTransfertStock resultatTransfertStock = opérationStock.apply(etat.usines.get(id));
        etat = etat.avecUsine(id, resultatTransfertStock.usineAprès);
        return resultatTransfertStock.quantitéRendueAuJoueur;
    }

    @Scheduled(fixedRate = INTERVAL_TIC_TAC_MS)
    public void tictac() {
        etat = etat.tictac(INTERVAL_TIC_TAC_MS);
    }

    public List<EtatParc> historique(int depuis) {
        return Stream.iterate(etat, etat -> etat.etatPrécédent)
            .takeWhile(Objects::nonNull)
            .limit(depuis)
            .collect(toList());
    }

    public List<EtatParc> simuler(int étapes) {
        return Stream.iterate(etat, etat -> etat.tictac(1000))
            .limit(étapes)
            .collect(toList());
    }
}

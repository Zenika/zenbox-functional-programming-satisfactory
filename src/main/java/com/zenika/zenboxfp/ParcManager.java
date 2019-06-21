package com.zenika.zenboxfp;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ParcManager {
    private final AtomicReference<Parc> parc;

    public ParcManager(List<Usine> usines, int productionElectrique) {
        this.parc = new AtomicReference<>(new Parc(usines, productionElectrique, null));
    }

    public List<Usine> getUsines() {
        return parc.get().usines;
    }

    public int getProductionElectrique() {
        return parc.get().productionElectrique;
    }

    public void setProductionElectrique(int productionElectrique) {
        parc.updateAndGet(parc -> parc.avecProductionElectrique(productionElectrique));
    }

    public Usine getUsine(int id) {
        return parc.get().usines.get(id);
    }

    public void setCadence(int id, double cadence) {
        Usine usineRecadencée = parc.get().usines.get(id).avecCadence(cadence);
        parc.updateAndGet(parc -> parc.avecUsine(id, usineRecadencée));
    }

    public int livrer(int id, int quantité) {
        return appliquerTransfertStock(id, usine -> usine.livrer(quantité));
    }

    public int stocker(int id, int quantité) {
        return appliquerTransfertStock(id, usine -> usine.stocker(quantité));
    }

    private int appliquerTransfertStock(int id, Function<Usine, ResultatTransfertStock> opérationStock) {
        ResultatTransfertStock resultatTransfertStock = opérationStock.apply(parc.get().usines.get(id));
        parc.updateAndGet(parc -> parc.avecUsine(id, resultatTransfertStock.usineAprès));
        return resultatTransfertStock.quantitéRendueAuJoueur;
    }

    @Scheduled(fixedRate = 1000)
    public void tictac() {
        parc.updateAndGet(parc -> parc.tictac(1000));
    }

    public List<Parc> historique(int depuis) {
        return Stream.iterate(parc.get(), parc -> parc.etatPrécédent)
            .takeWhile(Objects::nonNull)
            .limit(depuis)
            .collect(toList());
    }

    public List<Parc> simuler(int étapes) {
        return Stream.iterate(parc.get(), parc -> parc.tictac(1000))
            .limit(étapes)
            .collect(toList());
    }
}

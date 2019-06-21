package com.zenika.zenboxfp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Parc {
    public final List<Usine> usines;
    public final int productionElectrique;
    @JsonIgnore
    public final Parc etatPrécédent;

    public Parc(List<Usine> usines, int productionElectrique, Parc etatPrécédent) {
        this.usines = Collections.unmodifiableList(usines);
        this.productionElectrique = productionElectrique;
        this.etatPrécédent = etatPrécédent;
    }

    public Parc avecProductionElectrique(int productionElectrique) {
        return new Parc(usines, productionElectrique, this);
    }

    public Parc avecUsines(List<Usine> usines) {
        return new Parc(Collections.unmodifiableList(usines), productionElectrique, this);
    }

    public Parc avecUsine(int id, Usine nouvelleUsine) {
        List<Usine> usinesMisesAJour = usines.stream()
            .map(usine -> usine == usines.get(id) ? nouvelleUsine : usine)
            .collect(toList());
        return avecUsines(usinesMisesAJour);
    }

    public Parc tictac(long temps) {
        if (estDisjoncté()) {
            return this;
        }
        List<Usine> usinesMisesAJour = usines.stream()
            .map(usine -> usine.tictac(temps))
                .collect(toList());
        return avecUsines(usinesMisesAJour);
    }

    private boolean estDisjoncté() {
        double consommationTotale = usines.stream()
            .mapToDouble(Usine::getConsommation)
            .sum();
        return consommationTotale > productionElectrique;
    }
}

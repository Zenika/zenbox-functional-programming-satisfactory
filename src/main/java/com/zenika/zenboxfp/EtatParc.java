package com.zenika.zenboxfp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class EtatParc {
    public final List<Usine> usines;
    public final int productionElectrique;
    @JsonIgnore
    public final EtatParc etatPrécédent;

    public EtatParc(List<Usine> usines, int productionElectrique, EtatParc etatPrécédent) {
        this.usines = Collections.unmodifiableList(usines);
        this.productionElectrique = productionElectrique;
        this.etatPrécédent = etatPrécédent;
    }

    public EtatParc avecProductionElectrique(int productionElectrique) {
        return new EtatParc(usines, productionElectrique, this);
    }

    public EtatParc avecUsines(List<Usine> usines) {
        return new EtatParc(Collections.unmodifiableList(usines), productionElectrique, this);
    }

    public EtatParc avecUsine(int id, Usine nouvelleUsine) {
        List<Usine> usinesMisesAJour = usines.stream()
            .map(usine -> usine == usines.get(id) ? nouvelleUsine : usine)
            .collect(toList());
        return avecUsines(usinesMisesAJour);
    }

    public EtatParc tictac(long temps) {
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

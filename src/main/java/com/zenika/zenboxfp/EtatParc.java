package com.zenika.zenboxfp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public EtatParc avecUsine(int id, Usine usine) {
        List<Usine> usinesMisesAJour = new ArrayList<>(usines);
        usinesMisesAJour.set(id, usine);
        return avecUsines(usinesMisesAJour);
    }
}

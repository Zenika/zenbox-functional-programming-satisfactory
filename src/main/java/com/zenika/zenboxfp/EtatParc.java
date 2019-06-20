package com.zenika.zenboxfp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EtatParc {
    public final List<Usine> usines;
    public final int productionElectrique;

    public EtatParc(List<Usine> usines, int productionElectrique) {
        this.usines = Collections.unmodifiableList(usines);
        this.productionElectrique = productionElectrique;
    }

    public EtatParc avecProductionElectrique(int productionElectrique) {
        return new EtatParc(usines, productionElectrique);
    }

    public EtatParc avecUsines(List<Usine> usines) {
        return new EtatParc(Collections.unmodifiableList(usines), productionElectrique);
    }

    public EtatParc avecUsine(int id, Usine usine) {
        List<Usine> usinesMisesAJour = new ArrayList<>(usines);
        usinesMisesAJour.set(id, usine);
        return avecUsines(usinesMisesAJour);
    }
}

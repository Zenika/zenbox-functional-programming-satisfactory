package com.zenika.zenboxfp;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

public class Parc {
    public static final long INTERVAL_TIC_TAC_MS = 1000;

    private final Set<Usine> usines;
    private final int productionElectrique;

    public Parc(Set<Usine> usines, int productionElectrique) {
        this.usines = usines;
        this.productionElectrique = productionElectrique;
    }

    @Scheduled(fixedRate = INTERVAL_TIC_TAC_MS)
    public void tictac() {
        if (estDisjoncté()) {
            return;
        }
        for (Usine usine : usines) {
            usine.tictac(INTERVAL_TIC_TAC_MS);
        }
    }

    private boolean estDisjoncté() {
        double consommationTotale = 0;
        for (Usine usine : usines) {
            consommationTotale += usine.getConsommation();
        }
        return consommationTotale > productionElectrique;
    }
}

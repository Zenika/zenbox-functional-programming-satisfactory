package com.zenika.zenboxfp;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class Parc {
    public static final long INTERVAL_TIC_TAC_MS = 1000;

    private final List<Usine> usines;
    private int productionElectrique;

    public Parc(List<Usine> usines, int productionElectrique) {
        this.usines = usines;
        this.productionElectrique = productionElectrique;
    }

    public List<Usine> getUsines() {
        return usines;
    }

    public int getProductionElectrique() {
        return productionElectrique;
    }

    public void setProductionElectrique(int productionElectrique) {
        this.productionElectrique = productionElectrique;
    }

    public Usine getUsine(int id) {
        return usines.get(id);
    }

    public void setCadence(int id, double cadence) {
        usines.get(id).setCadence(cadence);
    }

    public int livrer(int id, int quantité) {
        return usines.get(id).livrer(quantité);
    }

    public int stocker(int id, int quantité) {
        return usines.get(id).stocker(quantité);
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

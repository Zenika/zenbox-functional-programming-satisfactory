package com.zenika.zenboxfp;

public class Usine {
    private final int capacitéStock;

    private EtatUsine etat;

    public Usine(int capacitéStock) {
        this.capacitéStock = capacitéStock;
    }

    public int getCapacitéStock() {
        return capacitéStock;
    }

    public double getCadence() {
        return etat.cadence;
    }

    public int getStockEntrée() {
        return etat.stockEntrée;
    }

    public int getStockSortie() {
        return etat.stockSortie;
    }

    public double getConsommation() {
        return etat.cadence;
    }

    public void setCadence(double cadence) {
        etat = this.etat.avecCadence(cadence);
    }

    public int livrer(int quantité) {
        int àLivrer = quantité <= etat.stockSortie ? quantité : etat.stockSortie;
        etat = etat.avecStockSortie(etat.stockSortie - àLivrer);
        return àLivrer;
    }

    public int stocker(int quantité) {
        int placeRestante = capacitéStock - etat.stockEntrée;
        int àStocker = quantité <= placeRestante ? quantité : placeRestante;
        etat = etat.avecStockEntrée(etat.stockEntrée + àStocker);
        return quantité - àStocker;
    }

    public void tictac(long durée) {
        // TODO?
    }

}

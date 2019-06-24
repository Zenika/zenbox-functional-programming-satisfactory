package com.zenika.zenboxfp;

public class Usine {
    private final int capacitéStock;

    public final EtatUsine etat;

    public Usine(int capacitéStock) {
        this.capacitéStock = capacitéStock;
        this.etat = new EtatUsine(0, 0, 0);
    }

    private Usine(int capacitéStock, EtatUsine etat) {
        this.capacitéStock = capacitéStock;
        this.etat = etat;
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

    public Usine avecCadence(double cadence) {
        return new Usine(capacitéStock, etat.avecCadence(cadence));
    }

    public ResultatTransfertStock livrer(int quantité) {
        int àLivrer = quantité <= etat.stockSortie ? quantité : etat.stockSortie;
        EtatUsine étatAprès = etat.avecStockSortie(etat.stockSortie - àLivrer);
        Usine usineAprès = new Usine(capacitéStock, étatAprès);
        return new ResultatTransfertStock(àLivrer, usineAprès);
    }

    public ResultatTransfertStock stocker(int quantité) {
        int placeRestante = capacitéStock - etat.stockEntrée;
        int àStocker = quantité <= placeRestante ? quantité : placeRestante;
        EtatUsine étatAprès = etat.avecStockEntrée(etat.stockEntrée + àStocker);
        Usine usineAprès = new Usine(capacitéStock, étatAprès);
        return new ResultatTransfertStock(quantité - àStocker, usineAprès);
    }

    public Usine tictac(long durée) {
        // TODO?
        return this;
    }

}

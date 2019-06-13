package com.zenika.zenboxfp;

public class Usine {
    private final int capacitéStock;

    private boolean problèmeDeStock = true;
    private double cadence;
    private int stockEntrée;
    private int stockSortie;
    private double complétionPièceCourante;
    private Parc parc;

    public Usine(int capacitéStock) {
        this.capacitéStock = capacitéStock;
    }

    public int getCapacitéStock() {
        return capacitéStock;
    }

    public boolean isProblèmeDeStock() {
        return problèmeDeStock;
    }

    public double getCadence() {
        return cadence;
    }

    public int getStockEntrée() {
        return stockEntrée;
    }

    public int getStockSortie() {
        return stockSortie;
    }

    public double getConsommation() {
        return cadence;
    }

    public void setCadence(double cadence) {
        this.cadence = cadence;
    }

    public int livrer(int quantité) {
        int àLivrer = quantité <= stockSortie ? quantité : stockSortie;
        stockSortie -= àLivrer;
        return àLivrer;
    }

    public int stocker(int quantité) {
        int placeRestante = capacitéStock - stockEntrée;
        int àStocker = quantité <= placeRestante ? quantité : placeRestante;
        stockEntrée += àStocker;
        return quantité - àStocker;
    }

    public void tic(long durée) {
        // TODO?
    }


}

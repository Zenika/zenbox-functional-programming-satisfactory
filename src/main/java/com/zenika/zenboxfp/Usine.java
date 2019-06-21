package com.zenika.zenboxfp;

public class Usine {
    public final int capacitéStock;
    public final double cadence;
    public final int stockEntrée;
    public final int stockSortie;

    public Usine(int capacitéStock) {
        this(capacitéStock, 0, 0, 0);
    }

    private Usine(int capacitéStock, double cadence, int stockEntrée, int stockSortie) {
        this.capacitéStock = capacitéStock;
        this.cadence = cadence;
        this.stockEntrée = stockEntrée;
        this.stockSortie = stockSortie;
    }

    public Usine avecCadence(double nouvelleCadence) {
        return new Usine(capacitéStock, nouvelleCadence, stockEntrée, stockSortie);
    }

    private Usine avecStockEntrée(int nouveauStockEntrée) {
        return new Usine(capacitéStock, cadence, nouveauStockEntrée, stockSortie);
    }

    private Usine avecStockSortie(int nouveauStockSortie) {
        return new Usine(capacitéStock, cadence, stockEntrée, nouveauStockSortie);
    }

    public double getConsommation() {
        return cadence;
    }

    public ResultatTransfertStock livrer(int quantité) {
        int àLivrer = quantité <= stockSortie ? quantité : stockSortie;
        Usine usineAprès = avecStockSortie(stockSortie - àLivrer);
        return new ResultatTransfertStock(àLivrer, usineAprès);
    }

    public ResultatTransfertStock stocker(int quantité) {
        int placeRestante = capacitéStock - stockEntrée;
        int àStocker = quantité <= placeRestante ? quantité : placeRestante;
        Usine usineAprès = avecStockEntrée(stockEntrée + àStocker);
        return new ResultatTransfertStock(quantité - àStocker, usineAprès);
    }

    public Usine tictac(long durée) {
        // TODO?
        return this;
    }
}

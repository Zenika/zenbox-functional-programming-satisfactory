package com.zenika.zenboxfp;

public class EtatUsine {
    public final double cadence;
    public final int stockEntrée;
    public final int stockSortie;

    public EtatUsine(double cadence, int stockEntrée, int stockSortie) {
        this.cadence = cadence;
        this.stockEntrée = stockEntrée;
        this.stockSortie = stockSortie;
    }

    public EtatUsine avecCadence(double nouvelleCadence) {
        return new EtatUsine(nouvelleCadence, stockEntrée, stockSortie);
    }

    public EtatUsine avecStockEntrée(int nouveauStockEntrée) {
        return new EtatUsine(cadence, nouveauStockEntrée, stockSortie);
    }

    public EtatUsine avecStockSortie(int nouveauStockSortie) {
        return new EtatUsine(cadence, stockEntrée, nouveauStockSortie);
    }
}

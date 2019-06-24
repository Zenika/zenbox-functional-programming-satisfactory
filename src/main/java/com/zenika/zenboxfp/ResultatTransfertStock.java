package com.zenika.zenboxfp;

public class ResultatTransfertStock {
    public final int quantitéRendueAuJoueur;
    public final Usine usineAprès;

    public ResultatTransfertStock(int quantitéRendueAuJoueur, Usine usineAprès) {
        this.quantitéRendueAuJoueur = quantitéRendueAuJoueur;
        this.usineAprès = usineAprès;
    }
}

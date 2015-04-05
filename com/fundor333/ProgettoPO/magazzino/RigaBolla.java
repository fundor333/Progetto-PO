/*
 * Copyright (c)
 * This code was created by f333. It's distributed as
 * part of the Progetto-PO.
 *
 * Progetto-PO is Open Source and distributed under the
 * GNU General Public License, version 2
 *
 * https://www.gnu.org/licenses/gpl-2.0.html
 *
 * File Updated 5/4/2015
 */

package com.fundor333.ProgettoPO.magazzino;

public class RigaBolla implements GenericoElemento {
    private Componenti componente;
    private Integer quantita;

    RigaBolla(Componenti componente, int quantita) {
        this.componente = componente;
        this.quantita = quantita;
    }

    public static String[] getNomeCampi() {
        String[] s = new String[]{"Nome", "Codice a Barre", "Caratteristiche", "Posizione", "Rimanenza", "Prezzo", "Tipo", "Numero Componenti"};
        return s;
    }

    public Componenti getComponente() {
        return componente;
    }

    public int getQuantita() {
        return quantita;
    }

    @Override
    public String[] getCampi() {
        String[] s = new String[8];
        for (int i = 0; i < 6; i++) {
            s[i] = componente.getCampi()[i];
        }
        s[7] = quantita.toString();
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RigaBolla rigaBolla = (RigaBolla) o;

        if (!componente.equals(rigaBolla.componente)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return componente.hashCode();
    }

    public void addQuantita(int quantita) {
        this.quantita = this.quantita + quantita;
    }
}

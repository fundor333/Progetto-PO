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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundor333.ProgettoPO.magazzino;

import com.fundor333.ProgettoPO.exception.TipoInvalido;

public class Tipo implements GenericoElemento {
    private final String nometipo;
    private final String annotazioni;
    private Tipo padre;

    public Tipo(String nometipo, String annotazioni, Tipo padre) throws TipoInvalido {
        if (nometipo == null) {
            this.nometipo = "Senza nome";
            this.annotazioni = "";
        } else {
            this.nometipo = nometipo;
            this.annotazioni = annotazioni;
        }
        this.padre = padre;
    }

    Tipo(String nometipo, String annotazioni) {
        if (nometipo == null) {
            this.nometipo = "Senza nome";
            this.annotazioni = "";
        } else {
            this.nometipo = nometipo;
            this.annotazioni = annotazioni;
        }
    }

    public static String[] getNomeCampi() {
        return new String[]{"Nome del Tipo", "Annotazioni", "Supertipo"};
    }

    public Tipo getPadre() {
        return padre;
    }

    public boolean equals(Object o) {
        if (o instanceof Tipo)
            return equals((Tipo) o);
        else
            return false;
    }

    private boolean equals(Tipo o) {
        if (super.equals(o) && o.padre == this.padre)
            return true;
        else
            return false;
    }

    public String getNometipo() {
        return nometipo;
    }

    public String[] getCampi() {
        try {
            return new String[]{this.nometipo, this.annotazioni, this.padre.nometipo};
        } catch (NullPointerException np) {
            return new String[]{this.nometipo, this.annotazioni, "NULL"};
        }
    }

    public void eliminaTipo() {

    }
}

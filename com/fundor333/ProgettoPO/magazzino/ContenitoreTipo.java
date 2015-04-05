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

import com.fundor333.ProgettoPO.exception.TipoGiaPresente;
import com.fundor333.ProgettoPO.exception.TipoInvalido;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ContenitoreTipo implements Serializable {
    private static final ContenitoreTipo ref = new ContenitoreTipo();
    private ArrayList<Tipo> lista = new ArrayList<Tipo>();
    private Tipo BASE = new Tipo("Generico", "Generico tipo di elemento");

    private ContenitoreTipo() {
        this.lista.add(0, BASE);
    }

    public static ContenitoreTipo getContenitoreTipo() {
        return ref;
    }

    public void add(String nome, String commenti, Tipo tipo) {
        Tipo t = null;
        try {
            t = new Tipo(nome, commenti, tipo);

            add(t);
            Magazzino.saveState();
        } catch (TipoInvalido ti) {
            JOptionPane.showMessageDialog(null, ti.getMessage());
        }
    }

    public void add(Tipo t) {
        try {
            if (this.lista.contains(t)) {
                throw new TipoGiaPresente();
            } else {
                this.lista.add(t);
            }
        } catch (TipoGiaPresente tipoGiaPresente) {
        }
    }

    public Tipo getWithName(String name) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNometipo() == name)
                return lista.get(i);
        }
        return null;
    }

    public Tipo Generico() {
        return BASE;
    }

    public ArrayList<Tipo> getList() {
        return lista;
    }

    void elimina(Tipo tipeWithName) {
        if (!tipeWithName.getCampi()[2].equals("NULL"))
            lista.remove(tipeWithName);
    }


}

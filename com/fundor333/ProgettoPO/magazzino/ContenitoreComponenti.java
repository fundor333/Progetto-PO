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

import com.fundor333.ProgettoPO.exception.ComponenteGiaPresente;
import com.fundor333.ProgettoPO.exception.ComponenteTerminato;
import com.fundor333.ProgettoPO.exception.ErroreMancanoComponenti;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;


public class ContenitoreComponenti implements Serializable {
    private static final ContenitoreComponenti ref = new ContenitoreComponenti();
    private ArrayList<Componenti> lista;

    private ContenitoreComponenti() {
        lista = new ArrayList<Componenti>();
    }

    public static ContenitoreComponenti getContenitoreComponenti() {
        return ref;
    }

    public boolean contains(Componenti c) {
        return lista.contains(c);
    }

    public void add(String nome, String posizione, long codiceaBarre, String caratteristiche, int quantita, double prezzo, Tipo t) {
        Componenti c = new Componenti(nome, posizione, codiceaBarre, caratteristiche, quantita, prezzo, t);
        if (this.lista.contains(c)) /*Se per caso l'ogetto creato è già esistente viene semplicemente aumentata la quantita*/ {
            try {
                /*Se la modifica della quantità del prodotto lo porta ad avere una quantità pari o inferiore a zero viene elminato il riferimento all'oggetto*/
                this.lista.get(this.lista.indexOf(c)).modificaQuantita(c.getQuantita());
                throw new ComponenteGiaPresente();
            } catch (ComponenteTerminato ex) {
                this.lista.remove(c);
            } catch (ErroreMancanoComponenti erroreMancanoComponenti) {
                JOptionPane.showMessageDialog(null, erroreMancanoComponenti.getMessage());
            } catch (ComponenteGiaPresente componenteGiaPresente) {
                JOptionPane.showMessageDialog(null, componenteGiaPresente.getMessage());
            }
        } else /*Aggiunge l'oggetto alla lista*/ {
            this.lista.add(c);
            JOptionPane.showMessageDialog(null, "Il componente è stato creato correttamente");
        }
        Magazzino.saveState();
    }

    public int size() {
        return lista.size();
    }

    public void remove(Componenti c) {
        lista.remove(c);
    }

    public Componenti getWithName(String s) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equals(s))
                return lista.get(i);
        }
        return null;
    }

    public ArrayList<Componenti> getList() {
        return lista;
    }

    void elimina(Componenti c) {
        lista.remove(c);
    }

    public void cambiaTipo(Tipo tipeWithName) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTipo().equals(tipeWithName))
                lista.get(i).setTipo(tipeWithName.getPadre());
        }
    }
}

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

package com.fundor333.ProgettoPO.gui;

import com.fundor333.ProgettoPO.magazzino.Componenti;
import com.fundor333.ProgettoPO.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestoreComponenti extends JDialog {
    private Tabella tabella;
    private JPanel setPulsanti = new JPanel();
    private JButton ok = new JButton("Salva");
    private JButton aggiungiElemento = new JButton("Aggiungi elemento");
    private Magazzino magazzino = Magazzino.getMagazzino();

    public GestoreComponenti(JFrame mainFrame) {
        super(mainFrame, "Magazzino");
        this.tabella = new Tabella<Componenti>(magazzino.getComponenti(), Componenti.getNomeCampi(),500,200);
        pulsanti();
        refreshTable();
        add(setPulsanti, BorderLayout.SOUTH);
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void pulsanti() {
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.saveState();
                setVisible(false);
            }
        });
        aggiungiElemento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AggiungiComponente ag = new AggiungiComponente(GestoreComponenti.this);
                ag.setVisible(true);
                refreshTable();
            }
        });
        refreshTable();
        setPulsanti.add(aggiungiElemento);
        setPulsanti.add(ok);
        setPulsanti.setLayout(new GridLayout(1, 3));
    }

    public void refreshTable() {
        tabella.aggiorna(magazzino.getComponenti());
        remove(tabella);
        add(tabella, BorderLayout.CENTER);
        setSize(601, 300);
        setSize(600, 300);//Non togliere perchè altrimenti non si aggiorna la finestra
    }
}
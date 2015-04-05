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

import com.fundor333.ProgettoPO.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminaBolla extends JDialog {

    private Magazzino magazzino = Magazzino.getMagazzino();
    private JButton ok = new JButton("OK");
    private Magazzino M = Magazzino.getMagazzino();

    private ArchivioBolle parent;

    private JPanel labels = new JPanel();
    private String[] nomiTipi;


    // Le varie enuple testo e campo di inserimento, una enupla per ogni attributo del componente
    private JLabel supertipoL = new JLabel("Bolla");
    private JComboBox supertipoT;
    private JPanel textField = new JPanel();

    public EliminaBolla(ArchivioBolle parent) {
        super(parent, "Nuovo Componente");
        this.parent = parent;
        inizializzaElementi();
        add(labels, BorderLayout.WEST);
        //Uso center in quanto si espande dinamicamente
        add(textField, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);
        setSize(300, 90);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void setLabels() {
        labels.add(supertipoL);
        labels.setLayout(new GridLayout(1, 1));
    }

    private void setTextField() {
        textField.add(supertipoT);
        textField.setLayout(new GridLayout(1, 1));
    }

    private void inizializzaElementi() {
        setTipoT();
        setLabels();
        setTextField();

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.getMagazzino().eliminaBolla(magazzino.getBollaWithName((String) (supertipoT.getSelectedItem())));
                parent.refreshTable();
                setVisible(false);
            }
        });
    }

    private void setTipoT() {
        nomiTipi = new String[magazzino.getBolla().size()];
        for (int i = 0; i < magazzino.getBolla().size(); i++) {
            this.nomiTipi[i] = magazzino.getBolla().get(i).getNome();
        }
        supertipoT = new JComboBox(nomiTipi);
        setSize(500, 300);
    }

}
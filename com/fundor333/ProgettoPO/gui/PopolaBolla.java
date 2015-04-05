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

import com.fundor333.ProgettoPO.exception.ErroreMancanoComponenti;
import com.fundor333.ProgettoPO.magazzino.BollaConsegna;
import com.fundor333.ProgettoPO.magazzino.Componenti;
import com.fundor333.ProgettoPO.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopolaBolla extends JDialog {

    private Magazzino magazzino = Magazzino.getMagazzino();
    private JButton ok = new JButton("OK");
    private Magazzino M = Magazzino.getMagazzino();

    private GestoreNuovaBolla parent;

    private JPanel labels = new JPanel();
    private String[] nomeBolle;


    // Le varie enuple testo e campo di inserimento, una enupla per ogni attributo del componente
    private JLabel componenteL = new JLabel("Componente");
    private JComboBox componenteB;
    private JLabel quantitaL = new JLabel("Quantità");
    private JTextField quantitaT = new JTextField();
    private JPanel textField = new JPanel();

    private BollaConsegna bolla;

    public PopolaBolla(GestoreNuovaBolla parent,BollaConsegna bolla) {
        super(parent, "Nuovo Componente");
        this.bolla=bolla;
        this.parent = parent;
        inizializzaElementi();
        add(labels, BorderLayout.WEST);
        //Uso center in quanto si espande dinamicamente
        add(textField, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void setLabels() {
        labels.add(componenteL);
        labels.add(quantitaL);
        labels.setLayout(new GridLayout(7, 1));
    }

    private void setTextField() {
        textField.add(componenteB);
        textField.add(quantitaT);
        textField.setLayout(new GridLayout(7, 1));
    }

    private void inizializzaElementi() {
        setTipoT();
        setLabels();
        setTextField();

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int quantita = Integer.parseInt(quantitaT.getText());
                    Componenti c = (M.getComponentiWithName((String) componenteB.getSelectedItem()));
                    bolla.addComponente(c,quantita);
                    setVisible(false);
                } catch (ErroreMancanoComponenti erroreMancanoComponenti) {
                    JOptionPane.showMessageDialog(null,"Non ci sono componenti a sufficienza");
                }
                parent.refreshTable();
            }
        });
    }

    private void setTipoT() {
        nomeBolle = new String[magazzino.getComponenti().size()];
        for (int i = 0; i < magazzino.getComponenti().size(); i++) {
            this.nomeBolle[i] = magazzino.getComponenti().get(i).getNome();
        }
        componenteB = new JComboBox(nomeBolle);
        setSize(500, 300);
    }

}
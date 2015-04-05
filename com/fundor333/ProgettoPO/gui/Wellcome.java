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

import com.fundor333.ProgettoPO.magazzino.BollaConsegna;
import com.fundor333.ProgettoPO.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wellcome extends JFrame {

    /*Set delle dimensioni della finestra*/
    public final static int LARGHEZZA = 200;
    public final static int ALTEZZA = 200;
    private final static Magazzino magazzino = Magazzino.getMagazzino();

    public static void main(String[] args) {
        /*Set della posizione relativa della finestra*/

        final JPanel nord = new JPanel();

        final int ALTEZZA_REL = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2.5);
        final int LARGHEZZA_REL = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3);

        /*Dichiarazione della finestra da visualizzare*/
        final JFrame mainFrame = new JFrame("Benvenuto");
        /*Dichiarazione degli altri elementi presenti*/
        final JButton gestoreComponenti = new JButton("Magazzino");
        final JButton gestoreTipi = new JButton("Tipi di componenti");
        final JButton gestorePacchi = new JButton("Gestore Bolle");
        final JButton nuovoPacco = new JButton("Nuova Bolla di Consegna");
        final JButton salvaStato = new JButton(("Chiudi"));


        /*Comandi di Listener*/
        gestoreComponenti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog gm = new GestoreComponenti(mainFrame);
                gm.setVisible(true);
            }
        });

        gestoreTipi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog gm = new GestoreTipi(mainFrame);
                gm.setVisible(true);
            }
        });

        salvaStato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.saveState();
                System.exit(0);
            }
        });

        gestorePacchi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog gm = new ArchivioBolle(mainFrame);
                gm.setVisible(true);
            }
        });

        nuovoPacco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog gm = new GestoreNuovaBolla(mainFrame,new BollaConsegna(System.currentTimeMillis()));
                gm.setVisible(true);
            }
        });

        /*Comandi di visualizzazione elementi*/
        nord.add(gestoreComponenti);
        nord.add(gestoreTipi);
        nord.add(gestorePacchi);
        nord.add(nuovoPacco);
        nord.add(salvaStato);
        nord.setLayout(new GridLayout(5, 1));

        mainFrame.add(nord);

        /*Comandi di impostazione di visualizzazione*/
        mainFrame.setLayout(new GridLayout(1, 1));
        mainFrame.setSize(LARGHEZZA, ALTEZZA);
        mainFrame.setLocation(ALTEZZA_REL, LARGHEZZA_REL);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
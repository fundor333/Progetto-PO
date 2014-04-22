package mscarpa.gui;

import mscarpa.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by matteoscarpa on 18/04/14.
 */
public class GestoreComponenti extends JDialog {
    private final Tabella tabella;
    private JPanel setPulsanti = new JPanel();
    private JButton ok = new JButton("Salva");
    private JButton aggiungiElemento = new JButton("Aggiungi elemento");

    public GestoreComponenti(JFrame mainFrame, Tabella tabella) {
        super(mainFrame, "Magazzino");
        this.tabella = tabella;
        pulsanti();
        refreshTable();
        add(setPulsanti, BorderLayout.SOUTH);
        setSize(700, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void pulsanti() {
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.saveState();
            }
        });
        aggiungiElemento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AggiungiComponente ag = new AggiungiComponente(GestoreComponenti.this);
                ag.setVisible(true);
            }
        });
        setPulsanti.add(aggiungiElemento);
        setPulsanti.add(ok);
        setPulsanti.setLayout(new GridLayout(2, 1));
    }

    public void refreshTable() {
        tabella.aggiorna(Magazzino.getMagazzino().getComponenti());
        remove(tabella);
        add(tabella, BorderLayout.CENTER);
        setSize(700, 320);
    }
}

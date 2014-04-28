package mscarpa.gui;

import mscarpa.magazzino.BollaConsegna;
import mscarpa.magazzino.Componenti;
import mscarpa.magazzino.Magazzino;
import mscarpa.magazzino.RigaBolla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestoreComponentiBolla extends JDialog {
    private Tabella tabella;
    private JPanel setPulsanti = new JPanel();
    private JButton ok = new JButton("Salva");
    private JButton aggiungiElemento = new JButton("Aggiungi elemento");
    private JButton eliminaElemento = new JButton("Elimina elemento");
    private Magazzino magazzino = Magazzino.getMagazzino();
    private BollaConsegna bollaConsegna;

    public GestoreComponentiBolla(JFrame mainFrame, BollaConsegna bollaConsegna) {
        super(mainFrame, "Magazzino");
        this.bollaConsegna = bollaConsegna;
        this.tabella = new Tabella<RigaBolla>(bollaConsegna.getComponenti(), Componenti.getNomeCampi());
        pulsanti();
        refreshTable();
        add(setPulsanti, BorderLayout.SOUTH);
        setSize(700, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void pulsanti() {
        refreshTable();
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.saveState();
            }
        });
        aggiungiElemento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){}
        });
        setPulsanti.add(aggiungiElemento);
        setPulsanti.add(ok);
        setPulsanti.setLayout(new GridLayout(1, 3));
    }

    public void refreshTable() {
        tabella.aggiorna(magazzino.getComponenti());
        remove(tabella);
        add(tabella, BorderLayout.CENTER);
        setSize(701, 320);
        setSize(700, 320);//Non togliere perchè altrimenti non si aggiorna la finestra
    }
}
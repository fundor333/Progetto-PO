package mscarpa.gui;

import mscarpa.magazzino.BollaConsegna;
import mscarpa.magazzino.Componenti;
import mscarpa.magazzino.Magazzino;
import mscarpa.magazzino.RigaBolla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestoreNuovaBolla extends JDialog {
    private Tabella tabella;
    private JPanel setPulsanti = new JPanel();
    private JButton ok = new JButton("Salva");
    private JButton aggiungiElemento = new JButton("Aggiungi elemento");
    private Magazzino magazzino = Magazzino.getMagazzino();
    private BollaConsegna bollaConsegna;

    public GestoreNuovaBolla(JFrame mainFrame, BollaConsegna bolla) {
        super(mainFrame, "Nuova Bolla");
        this.bollaConsegna = bolla;
        Magazzino.getMagazzino().addBolla(bollaConsegna);
        this.tabella = new Tabella<RigaBolla>(bollaConsegna.getComponenti(), RigaBolla.getNomeCampi(),1000,200);
        pulsanti();
        refreshTable();
        add(setPulsanti, BorderLayout.SOUTH);
        setSize(1100, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }


    private void pulsanti() {
        refreshTable();
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.saveState();
                setVisible(false);
            }
        });
        aggiungiElemento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){
               PopolaBolla pb = new PopolaBolla(GestoreNuovaBolla.this,bollaConsegna);
                pb.setVisible(true);
            }
        });
        setPulsanti.add(aggiungiElemento);
        setPulsanti.add(ok);
        setPulsanti.setLayout(new GridLayout(1, 3));
    }

    public void refreshTable() {
        tabella.aggiorna(bollaConsegna.getComponenti());
        remove(tabella);
        add(tabella, BorderLayout.CENTER);
        setSize(1101, 300);
        setSize(1100, 300);//Non togliere perchè altrimenti non si aggiorna la finestra
    }
}
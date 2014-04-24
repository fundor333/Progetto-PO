package mscarpa.gui;

import mscarpa.exception.*;
import mscarpa.magazzino.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GestoreComponenti extends JDialog {
    private Tabella tabella;
    private JPanel setPulsanti = new JPanel();
    private JButton ok = new JButton("Salva");
    private JButton aggiungiElemento = new JButton("Aggiungi elemento");
    private JButton eliminaElemento = new JButton("Elimina elemento");
    private Magazzino magazzino=Magazzino.getMagazzino();

    public GestoreComponenti(JFrame mainFrame) {
        super(mainFrame, "Magazzino");
        GenericoElemento g = new Componenti("nome", "posizione", 0, "cara", 0, 0, magazzino.getTIPODIBASE());
        this.tabella = new Tabella<Componenti>(magazzino.getComponenti(), g.getNomeCampi());
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
        setSize(701, 320);
        setSize(700, 320);//Non togliere perchè altrimenti non si aggiorna la finestra
    }
}
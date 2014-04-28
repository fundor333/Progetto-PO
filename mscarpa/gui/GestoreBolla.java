package mscarpa.gui;

import mscarpa.magazzino.BollaConsegna;
import mscarpa.magazzino.Componenti;
import mscarpa.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.GregorianCalendar;

public class GestoreBolla extends JDialog {
    private Tabella tabella;
    private JPanel setPulsanti = new JPanel();
    private JButton ok = new JButton("Salva");
    private JButton aggiungiElemento = new JButton("Aggiungi elemento");
    private JComboBox aggiungi =new JComboBox();
    private Magazzino magazzino = Magazzino.getMagazzino();

    public GestoreBolla(JFrame mainFrame) {
        super(mainFrame, "Gestore Bolle");
        this.tabella = new Tabella<BollaConsegna>(magazzino.getBolla(), BollaConsegna.getNomeCampi());
        pulsanti();
        refreshTable();
        add(setPulsanti, BorderLayout.SOUTH);
        setSize(700, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void pulsanti() {
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.saveState();
            }
        });
        aggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GestoreComponentiBolla ag = new GestoreComponentiBolla(GestoreBolla.this, new BollaConsegna(System.currentTimeMillis() ) );
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
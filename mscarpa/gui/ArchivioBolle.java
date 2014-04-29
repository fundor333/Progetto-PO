package mscarpa.gui;

import mscarpa.magazzino.BollaConsegna;
import mscarpa.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArchivioBolle extends JDialog {
    private Tabella tabella;
    private JPanel setPulsanti = new JPanel();
    private JButton ok = new JButton("Ok");
    private JButton elimina = new JButton("Elimina elemento");
    private Magazzino magazzino = Magazzino.getMagazzino();

    public ArchivioBolle(JFrame mainFrame) {
        super(mainFrame, "Gestore Bolle");
        this.tabella = new Tabella<BollaConsegna>(magazzino.getBolla(), BollaConsegna.getNomeCampi(),400,200);
        pulsanti();
        refreshTable();
        add(setPulsanti, BorderLayout.SOUTH);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void pulsanti() {
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.saveState();
                setVisible(false);
            }
        });
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog gm = new EliminaBolla(ArchivioBolle.this);
                gm.setVisible(true);
                refreshTable();
            }
        });
        refreshTable();
        setPulsanti.add(elimina);
        setPulsanti.add(ok);
        setPulsanti.setLayout(new GridLayout(1, 3));
    }

    public void refreshTable() {
        tabella.aggiorna(magazzino.getBolla());
        remove(tabella);
        add(tabella, BorderLayout.CENTER);
        setSize(501, 320);
        setSize(500, 300);//Non togliere perchè altrimenti non si aggiorna la finestra
    }
}
package mscarpa.gui;

import mscarpa.exception.TipoInvalido;
import mscarpa.magazzino.GenericoElemento;
import mscarpa.magazzino.Magazzino;
import mscarpa.magazzino.TipoComponenti;
import mscarpa.magazzino.TipoGenerico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestoreTipi extends JDialog {
    private Tabella tabella;
    private JPanel setPulsanti = new JPanel();
    private JButton ok = new JButton("Salva");
    private JButton refresh = new JButton("Refresh");
    private JButton aggiungiElemento = new JButton("Aggiungi elemento");

    public GestoreTipi(JFrame mainFrame) {
        super(mainFrame, "Tipi di componenti");
        try {
            GenericoElemento g = new TipoComponenti("nome", "generico testo", Magazzino.getMagazzino().getTIPODIBASE());
            Tabella tcom = new Tabella<TipoComponenti>(Magazzino.getMagazzino().getTipiComponenti(), g.getNomeCampi());
            this.tabella = tcom;
            pulsanti();
            refreshTable();
            add(setPulsanti, BorderLayout.SOUTH);
            setSize(700, 320);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setModal(true);
        } catch (TipoInvalido tipoInvalido) {
            tipoInvalido.printStackTrace();
        }

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
                AggiungiTipi ag = new AggiungiTipi(GestoreTipi.this);
                ag.setVisible(true);
                refreshTable();
            }
        });
        refreshTable();
        setPulsanti.add(aggiungiElemento);
        setPulsanti.add(refresh);
        setPulsanti.add(ok);
        setPulsanti.setLayout(new GridLayout(1, 3));
    }
    // matricola lemMATTEO 841883

    public void refreshTable() {
        tabella.aggiorna(Magazzino.getMagazzino().getTipi());
        remove(tabella);
        add(tabella, BorderLayout.CENTER);
        setSize(701, 320);
        setSize(700, 320);//Non togliere perchè altrimenti non si aggiorna la finestra
    }
}

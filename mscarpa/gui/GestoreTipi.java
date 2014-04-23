package mscarpa.gui;

import mscarpa.exception.TipoInvalido;
import mscarpa.magazzino.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by matteoscarpa on 18/04/14.
 */
public class GestoreTipi extends JDialog {
    private Tabella tabella;
    private JPanel setPulsanti = new JPanel();
    private JButton ok = new JButton("Salva");
    private JButton refresh = new JButton("Refresh");
    private JButton aggiungiElemento = new JButton("Aggiungi elemento");

    public GestoreTipi(JFrame mainFrame) {
        super(mainFrame, "Magazzino");
        try{
        GenericoElemento g=new TipoComponenti("nome","generico testo",Magazzino.getMagazzino().getTIPODIBASE());
        Tabella tcom=new Tabella<TipoGenerico>(Magazzino.getMagazzino().getTipi(),g.getNomeCampi());
        this.tabella = tcom;
        pulsanti();
        refreshTable();
        add(setPulsanti, BorderLayout.SOUTH);
        setSize(700, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);}
        catch (TipoInvalido tipoInvalido) {
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
        setPulsanti.setLayout(new GridLayout(1,3));
    }

    public void refreshTable() {
        tabella.aggiorna(Magazzino.getMagazzino().getTipi());
        remove(tabella);
        add(tabella, BorderLayout.CENTER);
        setSize(701, 320);
        setSize(700, 320);//Non togliere perchè altrimenti non si aggiorna la finestra
    }
}

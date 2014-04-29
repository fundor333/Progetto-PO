package mscarpa.gui;

import mscarpa.exception.TipoInvalido;
import mscarpa.magazzino.GenericoElemento;
import mscarpa.magazzino.Magazzino;
import mscarpa.magazzino.Tipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestoreTipi extends JDialog {
    private Tabella tabella;
    private JPanel setPulsanti = new JPanel();
    private JButton ok = new JButton("Salva");
    private JButton aggiungiElemento = new JButton("Aggiungi elemento");
    private JButton eliminaElemento = new JButton("Elimina elemento");
    private Magazzino magazzino = Magazzino.getMagazzino();

    public GestoreTipi(JFrame mainFrame) {
        super(mainFrame, "Tipi di componenti");
        try {
            GenericoElemento g = new Tipo("nome", "generico testo", magazzino.getTIPODIBASE());
            this.tabella = new Tabella<Tipo>(magazzino.getTipi(), Tipo.getNomeCampi(),700,200);
            pulsanti();
            refreshTable();
            add(setPulsanti, BorderLayout.SOUTH);
            setSize(800, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setModal(true);
            setVisible(false);
        } catch (TipoInvalido tipoInvalido) {
            tipoInvalido.printStackTrace();
        }
    }

    private void pulsanti() {
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.saveState();
                setVisible(false);
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
        eliminaElemento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EliminaTipi ag = new EliminaTipi(GestoreTipi.this);
                ag.setVisible(true);
                refreshTable();
            }
        });

        refreshTable();
        setPulsanti.add(aggiungiElemento);
        setPulsanti.add(eliminaElemento);
        setPulsanti.add(ok);
        setPulsanti.setLayout(new GridLayout(1, 3));
    }

    public void refreshTable() {
        tabella.aggiorna(magazzino.getTipi());
        remove(tabella);
        add(tabella, BorderLayout.CENTER);
        setSize(801, 300);
        setSize(800, 300);//Non togliere perchè altrimenti non si aggiorna la finestra
    }
}

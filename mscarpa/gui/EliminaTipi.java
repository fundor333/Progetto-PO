package mscarpa.gui;

import mscarpa.exception.TipoGiaPresente;
import mscarpa.exception.TipoInvalido;
import mscarpa.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by matteoscarpa on 19/04/14.
 */
public class EliminaTipi extends JDialog {

    private Magazzino magazzino = Magazzino.getMagazzino();
    private JButton ok = new JButton("OK");
    private Magazzino M = Magazzino.getMagazzino();

    private GestoreTipi parent;

    private JPanel labels = new JPanel();
    private String[] nomiTipi;


    // Le varie enuple testo e campo di inserimento, una enupla per ogni attributo del componente
    private JLabel supertipoL = new JLabel("Tipo");
    private JComboBox supertipoT;

    private void setLabels() {
        labels.add(supertipoL);
        labels.setLayout(new GridLayout(1, 1));
    }

    private JPanel textField = new JPanel();

    private void setTextField() {
        textField.add(supertipoT);
        textField.setLayout(new GridLayout(1, 1));
    }


    public EliminaTipi(GestoreTipi parent) {
        super(parent, "Nuovo Componente");
        this.parent = parent;
        inizializzaElementi();
        add(labels, BorderLayout.WEST);
        //Uso center in quanto si espande dinamicamente
        add(textField, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);
        setSize(300, 90);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void inizializzaElementi() {
        setTipoT();
        setLabels();
        setTextField();

        ok.addActionListener(new ActionListener() {
            @Override
            //TODO scorretto lancio delle eccezioni(non c'è) e non corretto uso del tipi generico
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Magazzino.getMagazzino().eliminaTipiComponenti(magazzino.getTipeWithName((String)(supertipoT.getSelectedItem())));
                    parent.refreshTable();}
                    finally {
                    parent.refreshTable();
                }
            }
        });
    }

    private void setTipoT() {
        nomiTipi = new String[magazzino.getTipi().size()];
        System.out.println(magazzino.getTipi().size());
        System.out.println(magazzino.getTipi().get(0));
        for (int i = 0; i < magazzino.getTipi().size(); i++) {
            System.out.println(magazzino.getTipi().get(i).getNometipo());
            this.nomiTipi[i] = magazzino.getTipi().get(i).getNometipo();
        }
        supertipoT = new JComboBox(nomiTipi);
        setSize(500, 300);
    }

}
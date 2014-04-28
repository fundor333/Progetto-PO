package mscarpa.gui;

import mscarpa.exception.ErroreMancanoComponenti;
import mscarpa.magazzino.BollaConsegna;
import mscarpa.magazzino.Componenti;
import mscarpa.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by matteoscarpa on 19/04/14.
 */
public class PopolaBolla extends JDialog {

    private Magazzino magazzino = Magazzino.getMagazzino();
    private JButton ok = new JButton("OK");
    private Magazzino M = Magazzino.getMagazzino();

    private GestoreNuovaBolla parent;

    private JPanel labels = new JPanel();
    private String[] nomeBolle;


    // Le varie enuple testo e campo di inserimento, una enupla per ogni attributo del componente
    private JLabel componenteL = new JLabel("Componente");
    private JComboBox componenteB;
    private JLabel quantitaL = new JLabel("Quantità");
    private JTextField quantitaT = new JTextField();
    private JPanel textField = new JPanel();

    private BollaConsegna bolla;

    public PopolaBolla(GestoreNuovaBolla parent,BollaConsegna bolla) {
        super(parent, "Nuovo Componente");
        this.bolla=bolla;
        this.parent = parent;
        inizializzaElementi();
        add(labels, BorderLayout.WEST);
        //Uso center in quanto si espande dinamicamente
        add(textField, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void setLabels() {
        labels.add(componenteL);
        labels.add(quantitaL);
        labels.setLayout(new GridLayout(7, 1));
    }

    private void setTextField() {
        textField.add(componenteB);
        textField.add(quantitaT);
        textField.setLayout(new GridLayout(7, 1));
    }

    private void inizializzaElementi() {
        setTipoT();
        setLabels();
        setTextField();

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int quantita = Integer.parseInt(quantitaT.getText());
                    Componenti c = (M.getComponentiWithName((String) componenteB.getSelectedItem()));
                    bolla.addComponente(c,quantita);
                    setVisible(false);
                } catch (ErroreMancanoComponenti erroreMancanoComponenti) {
                    JOptionPane.showMessageDialog(null,"Non ci sono componenti a sufficienza");
                }
                parent.refreshTable();
            }
        });
    }

    private void setTipoT() {
        nomeBolle = new String[magazzino.getComponenti().size()];
        for (int i = 0; i < magazzino.getComponenti().size(); i++) {
            this.nomeBolle[i] = magazzino.getComponenti().get(i).getNome();
        }
        componenteB = new JComboBox(nomeBolle);
        setSize(500, 300);
    }

}
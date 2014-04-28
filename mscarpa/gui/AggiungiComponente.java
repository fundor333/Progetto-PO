package mscarpa.gui;

import mscarpa.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by matteoscarpa on 19/04/14.
 */
public class AggiungiComponente extends JDialog {

    private Magazzino magazzino = Magazzino.getMagazzino();
    private JButton ok = new JButton("OK");
    private Magazzino M = Magazzino.getMagazzino();

    private GestoreComponenti parent;

    private JPanel labels = new JPanel();
    private String[] nomiTipi;
    private JPanel textField = new JPanel();
    // Le varie enuple testo e campo di inserimento, una enupla per ogni attributo del componente
    private JLabel nomeL = new JLabel("Nome:");
    private JTextField nomeT = new JTextField();
    private JLabel codiceaBarreL = new JLabel("Codice a Barre");
    private JTextField codiceaBarreT = new JTextField();
    private JLabel caratteristicheL = new JLabel("Carratteristiche");
    private JTextField caratteristicheT = new JTextField();
    private JLabel posizioneL = new JLabel("Posizione");
    private JTextField posizioneT = new JTextField();
    private JLabel quantitaL = new JLabel("Quantità");
    private JTextField quantitaT = new JTextField();
    private JLabel prezzoL = new JLabel("Prezzo");
    private JTextField prezzoT = new JTextField();
    private JLabel tipoL = new JLabel("Tipo");
    private JComboBox tipoT;
    public AggiungiComponente(GestoreComponenti parent) {
        super(parent, "Nuovo Componente");
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
        labels.add(nomeL);
        labels.add(codiceaBarreL);
        labels.add(caratteristicheL);
        labels.add(posizioneL);
        labels.add(quantitaL);
        labels.add(prezzoL);
        labels.add(tipoL);
        labels.setLayout(new GridLayout(7, 1));
    }

    private void setTextField() {
        textField.add(nomeT);
        textField.add(codiceaBarreT);
        textField.add(caratteristicheT);
        textField.add(posizioneT);
        textField.add(quantitaT);
        textField.add(prezzoT);
        textField.add(tipoT);
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
                    Magazzino.getMagazzino().addComponenti(nomeT.getText(), posizioneT.getText(), Long.parseLong(codiceaBarreT.getText()), caratteristicheT.getText(), Integer.parseInt(quantitaT.getText()), Double.parseDouble(prezzoT.getText()), M.getTipeWithName((String) (tipoT.getSelectedItem())));
                    parent.refreshTable();
                    setVisible(false);
                } catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(null, "Il numero inserito non è valido");
                } finally {
                    parent.refreshTable();
                }
            }
        });
    }

    private void setTipoT() {
        nomiTipi = new String[magazzino.getTipi().size()];
        for (int i = 0; i < magazzino.getTipi().size(); i++) {
            this.nomiTipi[i] = magazzino.getTipi().get(i).getNometipo();
        }
        tipoT = new JComboBox(nomiTipi);
        setSize(500, 300);
    }

}
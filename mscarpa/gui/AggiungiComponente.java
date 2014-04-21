package mscarpa.gui;

import mscarpa.magazzino.TipoComponenti;

import javax.swing.*;
import java.awt.*;

/**
 * Created by matteoscarpa on 19/04/14.
 */
public class AggiungiComponente extends JDialog {

    private JButton ok = new JButton("OK");

    private GestoreComponenti parent;

    private JPanel labels = new JPanel();

    private void setLabels(){
        labels.add(nomeL);
        labels.add(codiceaBarreL);
        labels.add(caratteristicheL);
        labels.add(posizioneL);
        labels.add(quantitaL);
        labels.add(prezzoL);
        labels.add(tipoL);
        labels.setLayout(new GridLayout(7,1));
    }

    private JPanel textField = new JPanel();

    private void setTextField(){
        textField.add(nomeT);
        textField.add(codiceaBarreT);
        textField.add(caratteristicheT);
        textField.add(posizioneT);
        textField.add(quantitaT);
        textField.add(prezzoT);
        textField.add(tipoT);
        textField.setLayout(new GridLayout(7,1));
    }

    // Le varie enuple testo e campo di inserimento, una enupla per ogni attributo del componente
    private JLabel nomeL = new JLabel("Nome:");
    private JTextField nomeT = new JTextField();
    private JLabel codiceaBarreL = new JLabel("Codice a Barre");
    private JTextField codiceaBarreT =new JTextField();
    private JLabel caratteristicheL = new JLabel("Carratteristiche");
    private JTextField caratteristicheT =new JTextField();
    private JLabel posizioneL = new JLabel("Posizione");
    private JTextField posizioneT =new JTextField();
    private JLabel quantitaL = new JLabel("Quantità");
    private JTextField quantitaT =new JTextField();
    private JLabel prezzoL = new JLabel("Prezzo");
    private JTextField prezzoT =new JTextField();
    private JLabel tipoL = new JLabel("Tipo");
    private JTextField tipoT =new JTextField();

    public AggiungiComponente (GestoreComponenti parent){
        super(parent,"Nuovo Componente");
        this.parent=parent;
        inizializzaElementi();
        add(labels, BorderLayout.WEST);
        //Uso center in quanto si espande dinamicamente
        add(textField,BorderLayout.CENTER);
        add(ok,BorderLayout.SOUTH);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void inizializzaElementi() {
        setLabels();
        setTextField();

        //TODO inserire l'actionListener per i due elementi necessari. Bisogna anche gestire le eccezioni
    }

}

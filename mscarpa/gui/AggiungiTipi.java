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
public class AggiungiTipi extends JDialog {

    private Magazzino magazzino = Magazzino.getMagazzino();
    private JButton ok = new JButton("OK");
    private Magazzino M = Magazzino.getMagazzino();

    private GestoreTipi parent;

    private JPanel labels = new JPanel();
    private String[] nomiTipi;


    // Le varie enuple testo e campo di inserimento, una enupla per ogni attributo del componente
    private JLabel nomeL = new JLabel("Nome:");
    private JTextField nomeT = new JTextField();
    private JLabel caratteristicheL = new JLabel("Carratteristiche");
    private JTextField caratteristicheT = new JTextField();
    private JLabel supertipoL = new JLabel("Tipo");
    private JComboBox supertipoT;
    private JPanel textField = new JPanel();

    public AggiungiTipi(GestoreTipi parent) {
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
        labels.add(caratteristicheL);
        labels.add(supertipoL);
        labels.setLayout(new GridLayout(7, 1));
    }

    private void setTextField() {
        textField.add(nomeT);
        textField.add(caratteristicheT);
        textField.add(supertipoT);
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
                    Magazzino.getMagazzino().addTipiComponenti(nomeT.getText(), caratteristicheT.getText(), M.getTipeWithName((String) supertipoT.getSelectedItem()));
                    parent.refreshTable();
                } catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(null, "Il numero inserito non è valido");
                } catch (TipoGiaPresente tipoGiaPresente) {
                } catch (TipoInvalido tipoInvalido) {
                    JOptionPane.showMessageDialog(null, tipoInvalido.getMessage());
                } finally {
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
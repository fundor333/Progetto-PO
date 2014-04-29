package mscarpa.gui;

import mscarpa.magazzino.GenericoElemento;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class Tabella<E extends GenericoElemento> extends JPanel {
    private JTable matrice;
    private String[] etichette;
    private int larghezza;
    private int lunghezza;

    public Tabella(List<E> elementi, String[] etichette,int larghezza, int lunghezza) {
        this.etichette = etichette;
        this.larghezza=larghezza;
        this.lunghezza=lunghezza;
        inizializza(elementi);
    }

    public void aggiorna(List<E> elementi) {
        inizializza(elementi);
    }

    private Object[][] adattatore(List<E> a, String[] s) {
        Object[][] o;
        try {
            o = new Object[a.size()][s.length];
            for (int i = 0; i < a.size(); i++) {
                for (int j = 0; j < s.length; j++) {
                    o[i][j] = (a.get(i).getCampi())[j];
                }
            }
        } catch (IndexOutOfBoundsException e) {
            o = new Object[1][s.length];
            for (int i = 0; i < s.length; i++) {
                o[0][i] = "";
            }}

        return o;
    }

    private void inizializza(List<E> elementi) {
        Object[][] elementiTabella;
        super.removeAll();
        elementiTabella = adattatore(elementi, etichette);
        matrice = new JTable(elementiTabella, etichette) {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        matrice.setLayout(new BorderLayout());
        //TODO Trovare un sistema per ridimensionare in modo dinamico la tabella
        matrice.setPreferredScrollableViewportSize(new Dimension(larghezza, lunghezza));
        matrice.setFillsViewportHeight(true);
        matrice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.add(matrice.getTableHeader(), BorderLayout.PAGE_START);
        super.add(matrice, BorderLayout.CENTER);
        super.setAutoscrolls(true);
        JScrollPane scrollPane = new JScrollPane(matrice);
        super.add(scrollPane);
    }
}

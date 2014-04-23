package mscarpa.gui;

import mscarpa.magazzino.GenericoElemento;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.util.List;


public class Tabella<E  extends GenericoElemento> extends JPanel {
    private JTable matrice;
    private String[] etichette;

    public Tabella(List<E> elementi,String[] etichette){
        this.etichette=etichette;
        inizializza(elementi);
    }

    public void aggiorna(List<E> elementi){
        inizializza(elementi);
    }

    private Object[][] adattatore (List<E> a, String[] s){
        Object[][] o;
        try {
            o = new Object[a.size()][s.length];
            for (int i = 0; i < a.size(); i++) {
                for (int j = 0; j < s.length; j++) {
                    o[i][j] = (a.get(i).getCampi())[j];
                }
            }
        }
        catch (NullPointerException np){
            o=new Object[1][s.length];
            for (int i=0;i<s.length;i++)
            {
                o[0][i]="";
            }
        }
        catch (IndexOutOfBoundsException e){
            o=new Object[1][s.length];
            for (int i=0;i<s.length;i++)
            {
                o[0][i]="";
            }
        }
        return o;
    }

    private void inizializza(List<E> elementi){
        Object[][] elementiTabella;
        super.removeAll();
        elementiTabella=adattatore(elementi,etichette);
        matrice = new JTable(elementiTabella,etichette){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        matrice.setLayout(new BorderLayout());
        matrice.setPreferredScrollableViewportSize(new Dimension(670, 190));
        matrice.setFillsViewportHeight(true);
        matrice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.add(matrice.getTableHeader(), BorderLayout.PAGE_START);
        super.add(matrice, BorderLayout.CENTER);
        super.setAutoscrolls(true);
        JScrollPane scrollPane = new JScrollPane(matrice);
        super.add(scrollPane);
    }
}

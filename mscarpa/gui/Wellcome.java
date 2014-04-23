package mscarpa.gui;

import mscarpa.magazzino.Magazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wellcome extends Frame {

    /*Set delle dimensioni della finestra*/
    public final static int LARGHEZZA = 200;
    public final static int ALTEZZA = 200;
    private final static Magazzino magazzino = Magazzino.getMagazzino();

    public static void main(String[] args) {
        /*Set della posizione relativa della finestra*/

        final JPanel nord = new JPanel();

        final int ALTEZZA_REL = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2.5);
        final int LARGHEZZA_REL = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3);

        /*Dichiarazione della finestra da visualizzare*/
        final JFrame mainFrame = new JFrame("Benvenuto");
        /*Dichiarazione degli altri elementi presenti*/
        final JButton gestoreComponenti = new JButton("Magazzino");
        final JButton gestoreTipi = new JButton("Tipi di componenti");
        final JButton salvaStato = new JButton(("Chiudi"));


        /*Comandi di Listener*/
        gestoreComponenti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog gm = new GestoreComponenti(mainFrame);
                gm.setVisible(true);
            }
        });

        gestoreTipi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog gm = new GestoreTipi(mainFrame);
                gm.setVisible(true);
            }
        });

        salvaStato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.saveState();
                System.exit(0);
            }
        });

        /*Comandi di visualizzazione elementi*/
        nord.add(gestoreComponenti);
        nord.add(gestoreTipi);
        nord.add(salvaStato);
        nord.setLayout(new GridLayout(3, 1));

        mainFrame.add(nord);

        /*Comandi di impostazione di visualizzazione*/
        mainFrame.setLayout(new GridLayout(1, 1));
        mainFrame.setSize(LARGHEZZA, ALTEZZA);
        mainFrame.setLocation(ALTEZZA_REL, LARGHEZZA_REL);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
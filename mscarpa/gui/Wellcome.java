package mscarpa.gui;

import mscarpa.magazzino.Componenti;
import mscarpa.magazzino.GenericoElemento;
import mscarpa.magazzino.Magazzino;
import mscarpa.magazzino.TipoComponenti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wellcome extends Frame {

    /*Set delle dimensioni della finestra*/
    public final static int LARGHEZZA = 300;
    public final static int ALTEZZA = 200;
    private final static Magazzino magazzino=Magazzino.getMagazzino();

    public static void main(String[] args) {
        /*Set della posizione relativa della finestra*/

        final JPanel nord=new JPanel();
        final JPanel exit=new JPanel();
        final JPanel center=new JPanel();

        final int ALTEZZA_REL = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2.5);
        final int LARGHEZZA_REL = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3);

        /*Dichiarazione della finestra da visualizzare*/
        final JFrame mainFrame = new JFrame("Benvenuto");
        /*Dichiarazione degli altri elementi presenti*/
        final JButton gestoreComponenti = new JButton("Magazzino");
        final JButton salvaStato = new JButton(("Chiudi"));


        /*Comandi di Listener*/
        gestoreComponenti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                GenericoElemento g=new Componenti("nome","posizione",0,"cara",0,0,Magazzino.getMagazzino().getTIPODIBASE());
                Tabella tComponenti=new Tabella<Componenti>(magazzino.getComponenti(),g.getNomeCampi());
                JDialog gm = new GestoreComponenti(mainFrame,tComponenti);
                gm.setVisible(true);
            }
        });

        salvaStato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Magazzino.saveState();
                System.exit(0);
            }
        });
//TODO aggiungere il secondo gestore (quello dei tipi)

        /*Comandi di visualizzazione elementi*/
        nord.add(gestoreComponenti);
        nord.setLayout(new GridLayout(1, 1));
        center.setLayout(new GridLayout(2, 2));
        exit.add(salvaStato);
        exit.setLayout(new GridLayout(1, 1));

        mainFrame.add(nord);
        mainFrame.add(center);
        mainFrame.add(exit);

        /*Comandi di impostazione di visualizzazione*/
        mainFrame.setLayout(new GridLayout(3,1));
        mainFrame.setSize(LARGHEZZA, ALTEZZA);
        mainFrame.setLocation(ALTEZZA_REL, LARGHEZZA_REL);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

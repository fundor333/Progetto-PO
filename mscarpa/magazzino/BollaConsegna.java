package mscarpa.magazzino;

import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BollaConsegna implements GenericoElemento {

    private final long nome;
    private ArrayList<RigaBolla> listacomponenti;
    private double valore;

    public BollaConsegna(long nome) {
        this.valore = 0.0;
        this.nome = nome;
        listacomponenti = new ArrayList<RigaBolla>();
    }

    public static String[] getNomeCampi() {
        return new String[]{"Nome", "Numero componenti", "Totale"};
    }

    public void addComponente(Componenti c, int i) throws ErroreMancanoComponenti {
        try {
            c.modificaQuantita(0 - i);
        } catch (ComponenteTerminato componenteTerminato) {
            JOptionPane.showMessageDialog(null, "Hai finito i " + c.getNome());
            Magazzino.getMagazzino().getComponenti().remove(c);
        }
        addRigaBolla(new RigaBolla(c, i));
        this.calcolaValore(c, i);
    }

    private void addRigaBolla(RigaBolla rb) {
        if (listacomponenti.contains(rb)) {
            listacomponenti.get(listacomponenti.indexOf(rb)).addQuantita(rb.getQuantita());
        } else {
            listacomponenti.add(rb);
        }
    }

    private void calcolaValore(Componenti c, int i) {
        this.valore = +(c.getPrezzo() * i);
    }

    public String[] getCampi() {
        String[] result = new String[3];
        result[0] = String.valueOf(nome);
        try {
            result[1] = String.valueOf(listacomponenti.size());
        } catch (NullPointerException e) {
            result[2] = "0";
        }
        result[2] = String.valueOf(valore);
        return result;
    }

    public List<RigaBolla> getComponenti() {
        return this.listacomponenti;
    }

    public String getNome() {
        return String.valueOf(this.nome);
    }
}

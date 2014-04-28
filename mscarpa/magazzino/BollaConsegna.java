package mscarpa.magazzino;

import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

import java.util.ArrayList;
import java.util.List;

public class BollaConsegna implements GenericoElemento {

    private final long nome;
    private ArrayList<RigaBolla> listacomponenti;
    private double valore;

    public BollaConsegna(long nome) {
        this.valore = 0.0;
        this.nome = nome;
    }

    public static String[] getNomeCampi() {
        return new String[]{"Nome", "Numero di componenti", "Valore totale"};
    }

    void addComponente(Componenti c, Integer i) throws ErroreMancanoComponenti, ComponenteTerminato {
        c.modificaQuantita(i);
        this.listacomponenti.add(new RigaBolla(c, i));
        this.calcolaValore(c, i);
    }

    private void calcolaValore(Componenti c, int i) {
        this.valore = this.valore + (c.getPrezzo() * i);
    }

    public String[] getCampi() {
        String[] result = new String[3];
        result[0] = String.valueOf(nome);
        result[1] = String.valueOf(listacomponenti.size());
        result[2] = String.valueOf(valore);
        return result;
    }

    public List<RigaBolla> getComponenti() {
        return this.listacomponenti;
    }
}

package mscarpa.magazzino;

import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

import java.util.Map;

/**
 * @author matteoscarpa
 */
public class Pacco {

    private final String nome;
    private Map<Componenti, Integer> listacomponenti;
    private double valore;

    public Pacco(String nome) {
        this.valore = 0.0;
        this.nome = nome;
    }

    public void addComponente(Componenti c, Integer i) throws ErroreMancanoComponenti, ComponenteTerminato {
        c.modificaQuantita(i);
        this.listacomponenti.put(c, i);
        this.calcolaValore(c, i);
    }

    public void calcolaValore(Componenti c, int i) {
        this.valore = this.valore + (c.getPrezzo() * i);
    }

}

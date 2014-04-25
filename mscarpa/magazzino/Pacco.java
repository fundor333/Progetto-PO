package mscarpa.magazzino;

import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

import java.io.Serializable;
import java.util.Map;

public class Pacco implements GenericoElemento {

    private final String nome;
    private Map<Componenti, Integer> listacomponenti;
    private double valore;

    public Pacco(String nome) {
        this.valore = 0.0;
        this.nome = nome;
    }

    void addComponente(Componenti c, Integer i) throws ErroreMancanoComponenti, ComponenteTerminato {
        c.modificaQuantita(i);
        this.listacomponenti.put(c, i);
        this.calcolaValore(c, i);
    }

    private void calcolaValore(Componenti c, int i) {
        this.valore = this.valore + (c.getPrezzo() * i);
    }

    @Override
    public String[] getCampi() {
        String[] result=new String[3];
        result[0]=nome;
        result[1]=String.valueOf(listacomponenti.size());
        result[2]=String.valueOf(valore);
        return result;
    }

    public static String[] getNomeCampi() {
        return new String[] {"Nome","Numero di componenti","Valore totale"};
    }
}

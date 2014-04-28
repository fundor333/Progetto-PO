package mscarpa.magazzino;

import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContenitoreBolla implements Serializable {
    private final static ContenitoreBolla ref = new ContenitoreBolla();
    private ArrayList<BollaConsegna> bolle = new ArrayList<BollaConsegna>();

    private ContenitoreBolla() {
    }

    public static ContenitoreBolla gerContenitorePacco() {
        return ref;
    }

    public void changeBolla(BollaConsegna p, Componenti c, int quantita) throws ComponenteTerminato, ErroreMancanoComponenti {
        p.addComponente(c, quantita);
    }

    public void add(BollaConsegna b){
        bolle.add(b);
    }

    public void remove(BollaConsegna p) {
        this.bolle.remove(p);
    }

    public List<BollaConsegna> getBolla() {
        return bolle;
    }
}

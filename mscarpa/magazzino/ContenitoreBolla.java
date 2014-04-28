package mscarpa.magazzino;

import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContenitoreBolla implements Serializable {
    private final static ContenitoreBolla ref = new ContenitoreBolla();
    private ArrayList<BollaConsegna> lista = new ArrayList<BollaConsegna>();

    private ContenitoreBolla() {
    }

    public static ContenitoreBolla gerContenitorePacco() {
        return ref;
    }

    public void changeBolla(BollaConsegna p, Componenti c, int quantita) throws ComponenteTerminato, ErroreMancanoComponenti {
        p.addComponente(c, quantita);
    }

    public void add(BollaConsegna b){
        lista.add(b);
    }

    public void remove(BollaConsegna p) {
        this.lista.remove(p);
    }

    public List<BollaConsegna> getBolla() {
        return lista;
    }

    public BollaConsegna getBollaWithName(String name) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equals(name))
                return lista.get(i);
        }
        return null;
    }
}

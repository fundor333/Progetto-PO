package mscarpa.magazzino;

import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContenitoreBolla implements Serializable {
    private final static ContenitoreBolla ref = new ContenitoreBolla();
    private ArrayList<BollaConsegna> pacchi = new ArrayList<BollaConsegna>();

    private ContenitoreBolla() {
    }

    public static ContenitoreBolla gerContenitorePacco() {
        return ref;
    }

    public void add(BollaConsegna p, Componenti c, int quantita) throws ComponenteTerminato, ErroreMancanoComponenti {
        p.addComponente(c, quantita);
    }

    public void remove(BollaConsegna p) {
        this.pacchi.remove(p);
    }

    public List<BollaConsegna> getPacchi() {
        return pacchi;
    }
}

package mscarpa.magazzino;

import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContenitorePacco implements Serializable {
    private ArrayList<Pacco> pacchi=new ArrayList<Pacco>();
    private final static ContenitorePacco ref=new ContenitorePacco();

    private ContenitorePacco(){}

    public static ContenitorePacco gerContenitorePacco(){
        return ref;
    }

    //TODO Trovare un sistema per accettare multipli oggetti senza sapere quanti sono
    public void add(Pacco p, Componenti c, int quantita) throws ComponenteTerminato, ErroreMancanoComponenti {
            p.addComponente(c,quantita);
    }

    public void remove(Pacco p){
        this.pacchi.remove(p);
    }

    public List<Pacco> getPacchi() {
        return pacchi;
    }
}

package mscarpa.magazzino;

import mscarpa.exception.TipoGiaPresente;
import mscarpa.exception.TipoInvalido;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matteoscarpa on 23/04/14.
 */
public class ContenitoreTipo implements Serializable {
    private static final ContenitoreTipo ref = new ContenitoreTipo();
    private ArrayList<TipoGenerico> lista= new ArrayList<TipoGenerico>();
    private TipoGenerico BASE=new TipoGenerico("Generico", "Generico tipo di elemento");

    public static ContenitoreTipo getContenitoreTipo() {
        return ref;
    }

    private ContenitoreTipo() {
        this.lista.add(0,BASE);
        System.out.println(BASE);
    }

    public void add(String nome, String commenti, TipoGenerico tipo) {
        TipoComponenti t = null;
        try {
            t = new TipoComponenti(nome, commenti, tipo);

            add(t);
            Magazzino.saveState();
        } catch (TipoInvalido ti) {
            JOptionPane.showMessageDialog(null, ti.getMessage());
        }
    }

    public void add(TipoGenerico t) {
        try {
            if (this.lista.contains(t)) {
                throw new TipoGiaPresente();
            } else {
                this.lista.add(t);
            }
        } catch (TipoGiaPresente tipoGiaPresente) {
        }
    }

    public TipoGenerico getWithName(String name) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNometipo() == name)
                return lista.get(i);
        }
        return null;
    }

    public TipoGenerico Generico() {
        return BASE;
    }

    public ArrayList<TipoGenerico> getList() {
        return lista;
    }

    public List<TipoComponenti> getListComponenti() {
        ArrayList<TipoComponenti> risultato=new ArrayList<TipoComponenti>();
        for (int i=0; i<this.lista.size() ;i++){
            if (lista.get(i) instanceof TipoComponenti)
                risultato.add((TipoComponenti)lista.get(i));
        }
        return risultato;
    }
}

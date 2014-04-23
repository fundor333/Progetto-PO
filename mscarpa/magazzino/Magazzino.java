package mscarpa.magazzino;

import mscarpa.exception.*;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author matteoscarpa
 */
public class Magazzino implements Serializable {

    private static Magazzino RIFERIMENTO;
    private ContenitoreTipo tipi;
    private ContenitoreComponenti componenti;


    private Magazzino() throws TipoInvalido {
        this.componenti = ContenitoreComponenti.getContenitoreComponenti();
        this.tipi = ContenitoreTipo.getContenitoreTipo();
    }

    public static Magazzino getMagazzino() {
        if (RIFERIMENTO == null) {
            RIFERIMENTO = (Magazzino) DataBaseManager.load();
            if (RIFERIMENTO == null)
                try {
                    RIFERIMENTO = new Magazzino();
                } catch (TipoInvalido tipoInvalido) {}
        }

        return RIFERIMENTO;
    }

    public static void saveState() {
        DataBaseManager.save(Magazzino.getMagazzino());
    }

    /**
    *Funzione che restituisce l'oggetto con il nome indicato. Se non presente ritorna NULL
    *
    * @param name Nome del tipo cercato
    */
    public TipoGenerico getTipeWithName(String name){
        return tipi.getWithName(name);
    }


    public void addComponenti(String nome, String posizione, long codiceaBarre, String caratteristiche, int quantita, double prezzo, TipoGenerico t){
        componenti.add(nome,posizione,codiceaBarre,caratteristiche,quantita,prezzo,t);
    }

    public void addTipiComponenti(String nometipo, String annotazioni, TipoGenerico tipeWithName) throws TipoGiaPresente, TipoInvalido {
        tipi.add(nometipo,annotazioni,tipeWithName);
    }


    public List<Componenti> getComponenti(){
        return componenti.getList();
    }


    public Componenti getComponentiWithName(String s) {
        return componenti.getWithName(s);
    }


    public TipoGenerico getTIPODIBASE() {
        return this.tipi.Generico();
    }

    public List<TipoGenerico> getTipi(){
        return tipi.getList();
    }
}

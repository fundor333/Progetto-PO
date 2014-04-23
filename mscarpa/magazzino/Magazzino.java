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

    private final TipoGenerico TIPODIBASE;
    private static Magazzino RIFERIMENTO;
    private List<TipoGenerico> tipi;
    private List<Componenti> componenti;


    private Magazzino() throws TipoInvalido {
        TIPODIBASE=new TipoGenerico("Generico","Tipo generico di componenti");
        this.componenti = new ArrayList();
        this.tipi = new ArrayList();
        tipi.add(TIPODIBASE);
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
        try {
            return this.tipi.get(this.tipi.indexOf(new TipoComponenti(name,"something",Magazzino.getMagazzino().getTIPODIBASE())));
        } catch (TipoInvalido tipoInvalido) {
            JOptionPane.showMessageDialog(null,"Impossibile da visualizzare");
            return null;
        }
    }

    /**
     * Aggiunge il componente a quelli già presenti in negozio. Se è già
     * presente ne incrementa solamente la quantità
     *
     * @param nome            Nome del componente
     * @param posizione       Posizione del componente nel magazzino
     * @param codiceaBarre    Codice univoco del componente
     * @param caratteristiche Caratteristiche tecniche del componente
     * @param quantita        Quantità disponibile in magazzino del prodotto
     * @param prezzo          Prezzo unitario
     * @param t               Tipo di componente
     */

    public void addComponenti(String nome, String posizione, long codiceaBarre, String caratteristiche, int quantita, double prezzo, TipoGenerico t) throws ErroreMancanoComponenti, ComponenteGiaPresente, ComponenteCreate {
        Componenti c;
        try{c = new Componenti(nome, posizione, codiceaBarre, caratteristiche, quantita, prezzo, t);}
        catch (NullPointerException n){
            c=new Componenti(nome,posizione,codiceaBarre,caratteristiche,quantita,prezzo,TIPODIBASE);
        }
        if (this.componenti.contains(c)) /*Se per caso l'ogetto creato è già esistente viene semplicemente aumentata la quantita*/ {
            try {
                /*Se la modifica della quantità del prodotto lo porta ad avere una quantità pari o inferiore a zero viene elminato il riferimento all'oggetto*/
                this.componenti.get(this.componenti.indexOf(c)).modificaQuantita(c.getQuantita());
                throw new ComponenteGiaPresente();
            } catch (ComponenteTerminato ex) {
                this.componenti.remove(c);
            }
        } else /*Aggiunge l'oggetto alla lista*/ {
            this.componenti.add(c);
            throw new ComponenteCreate();
        }
        saveState();
    }


    /**
     * Aggiunge il nuovo tipo di componente a quelli già presenti nel magazzino
     *
     * @param nometipo    Nome del nuovo tipo di componenti
     * @param annotazioni Annotazioni sul nuovo tipo di componenti
     */

    /**
     * Aggiunge il nuovo tipo di componente a quelli già presenti nel magazzino
     *
     * @param nometipo     Nome del nuovo tipo di componenti
     * @param annotazioni  Annotazioni sul nuovo tipo di componenti
     * @param tipeWithName Tipo padre del nuovo tipo di componente
     */
    public void addTipiComponenti(String nometipo, String annotazioni, TipoComponenti tipeWithName) throws TipoGiaPresente, TipoInvalido {
        TipoComponenti t = new TipoComponenti(nometipo, annotazioni, tipeWithName);
        if (this.tipi.contains(t)) {
            throw new TipoGiaPresente();
        } else {
            this.tipi.add(t);
        }
        saveState();
    }


    public List<Componenti> getComponenti(){
        return this.componenti;
    }

    public List<TipoGenerico> getTipi() {
        return tipi;
    }

    public Componenti getComponentiWithName(String s) {
        for (int i=0;i<componenti.size();i++)
        {
            if (componenti.get(i).getNome().equalsIgnoreCase(s))
            {
                return componenti.get(i);
            }
        }
        return null;
    }

    public TipoGenerico getTIPODIBASE() {
        return TIPODIBASE;
    }
}

package mscarpa.magazzino;

import mscarpa.exception.ComponenteCreate;
import mscarpa.exception.ComponenteGiaPresente;
import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matteoscarpa on 23/04/14.
 */
public class ContenitoreComponenti implements Serializable{
    private ArrayList<Componenti> lista;
    private static final ContenitoreComponenti ref= new ContenitoreComponenti();

    public static ContenitoreComponenti getContenitoreComponenti(){
        return ref;
    }

    private ContenitoreComponenti(){
        lista=new ArrayList<Componenti>();
    }

    public boolean contains(Componenti c) {
        return lista.contains(c);
    }

    public void add(String nome, String posizione, long codiceaBarre, String caratteristiche, int quantita, double prezzo, TipoGenerico t) {
        Componenti c = new Componenti(nome, posizione, codiceaBarre, caratteristiche, quantita, prezzo, t);
        if (this.lista.contains(c)) /*Se per caso l'ogetto creato è già esistente viene semplicemente aumentata la quantita*/ {
            try {
                /*Se la modifica della quantità del prodotto lo porta ad avere una quantità pari o inferiore a zero viene elminato il riferimento all'oggetto*/
                this.lista.get(this.lista.indexOf(c)).modificaQuantita(c.getQuantita());
                throw new ComponenteGiaPresente();
            } catch (ComponenteTerminato ex) {
                this.lista.remove(c);
            } catch (ErroreMancanoComponenti erroreMancanoComponenti) {
                erroreMancanoComponenti.printStackTrace();
            } catch (ComponenteGiaPresente componenteGiaPresente) {
                componenteGiaPresente.printStackTrace();
            }
        } else /*Aggiunge l'oggetto alla lista*/ {
            this.lista.add(c);
            for (int i = 0; i < c.getCampi().length; i++) {
                System.out.println(c.getCampi()[i]);
            }
            try {
                throw new ComponenteCreate();
            } catch (ComponenteCreate componenteCreate) {
                JOptionPane.showMessageDialog(null,componenteCreate.getMessage());
            }

        }
        Magazzino.saveState();
    }
    public int size() {
        return lista.size();
    }

    public void remove(Componenti c){
        lista.remove(c);
    }

        public Componenti getWithName(String s) {
            for (int i=0;i<lista.size();i++)
            {
                if (lista.get(i).getNome()==s)
                    return lista.get(i);
            }
            return null;
    }

    public ArrayList<Componenti> getList() {
        return lista;
    }
}

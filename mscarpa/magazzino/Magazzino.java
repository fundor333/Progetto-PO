package mscarpa.magazzino;

import mscarpa.exception.*;

import java.io.Serializable;
import java.util.List;

public class Magazzino implements Serializable {

    private static Magazzino RIFERIMENTO;
    private ContenitoreTipo tipi;
    private ContenitoreComponenti componenti;
    private ContenitorePacco pacco;


    private Magazzino() throws TipoInvalido {
        this.componenti = ContenitoreComponenti.getContenitoreComponenti();
        this.tipi = ContenitoreTipo.getContenitoreTipo();
        this.pacco = ContenitorePacco.gerContenitorePacco();
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
    public Tipo getTipeWithName(String name){
        return tipi.getWithName(name);
    }

    public void addComponenti(String nome, String posizione, long codiceaBarre, String caratteristiche, int quantita, double prezzo, Tipo t){
        componenti.add(nome,posizione,codiceaBarre,caratteristiche,quantita,prezzo,t);
    }

    public void addTipiComponenti(String nometipo, String annotazioni, Tipo tipeWithName) throws TipoGiaPresente, TipoInvalido {
        tipi.add(nometipo,annotazioni,tipeWithName);
    }

    public List<Componenti> getComponenti(){
        return componenti.getList();
    }

    public Tipo getTIPODIBASE() {
        return this.tipi.Generico();
    }

    public List<Tipo> getTipi(){
        return tipi.getList();
    }

    public List<Pacco> getPacchi() {return pacco.getPacchi();}

    public void eliminaTipiComponenti(Tipo tipeWithName) {
        this.tipi.elimina(tipeWithName);
    }

    public void eliminaComponenti(Componenti c){this.componenti.elimina(c);}

    public void eliminaPacchi(Pacco p){this.pacco.remove(p);}
}

package mscarpa.magazzino;

import mscarpa.exception.TipoGiaPresente;
import mscarpa.exception.TipoInvalido;

import java.io.Serializable;
import java.util.List;

public class Magazzino implements Serializable {

    private static Magazzino RIFERIMENTO;
    private ContenitoreTipo tipi;
    private ContenitoreComponenti componenti;
    private ContenitoreBolla pacco;


    private Magazzino() throws TipoInvalido {
        this.componenti = ContenitoreComponenti.getContenitoreComponenti();
        this.tipi = ContenitoreTipo.getContenitoreTipo();
        this.pacco = ContenitoreBolla.gerContenitorePacco();
    }

    public static Magazzino getMagazzino() {
        if (RIFERIMENTO == null) {
            RIFERIMENTO = (Magazzino) DataBaseManager.load();
            if (RIFERIMENTO == null)
                try {
                    RIFERIMENTO = new Magazzino();
                } catch (TipoInvalido tipoInvalido) {
                }
        }
        return RIFERIMENTO;
    }

    public static void saveState() {
        DataBaseManager.save(Magazzino.getMagazzino());
    }

    /**
     * Funzione che restituisce l'oggetto con il nome indicato. Se non presente ritorna NULL
     *
     * @param name Nome del tipo cercato
     */
    public Tipo getTipeWithName(String name) {
        return tipi.getWithName(name);
    }

    public void addComponenti(String nome, String posizione, long codiceaBarre, String caratteristiche, int quantita, double prezzo, Tipo t) {
        componenti.add(nome, posizione, codiceaBarre, caratteristiche, quantita, prezzo, t);
    }

    public void addTipiComponenti(String nometipo, String annotazioni, Tipo tipeWithName) throws TipoGiaPresente, TipoInvalido {
        tipi.add(nometipo, annotazioni, tipeWithName);
    }

    public List<Componenti> getComponenti() {
        return componenti.getList();
    }

    public Componenti getComponentiWithName(String s) {
        return this.componenti.getWithName(s);
    }

    public Tipo getTIPODIBASE() {
        return this.tipi.Generico();
    }

    public List<Tipo> getTipi() {
        return tipi.getList();
    }

    public void eliminaTipiComponenti(Tipo tipeWithName) {
        componenti.cambiaTipo(tipeWithName);
        this.tipi.elimina(tipeWithName);
    }

    public List<BollaConsegna> getBolla() {
        return this.pacco.getBolla();
    }

    public void addBolla(BollaConsegna bollaConsegna) {
        this.pacco.add(bollaConsegna);
    }

    public void eliminaBolla(BollaConsegna b) {
        this.pacco.remove(b);
    }

    public BollaConsegna getBollaWithName(String selectedItem) {
        return this.pacco.getBollaWithName(selectedItem);
    }
}

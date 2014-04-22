package mscarpa.magazzino;

import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

import java.io.Serializable;

public class Componenti implements Serializable,GenericoElemento {

    private final String nome;
    private final Long codiceaBarre;
    private final String caratteristiche;
    private String posizione;
    private Integer quantita;
    private Double prezzo;
    private TipoGenerico tipo;


    public Componenti(String nome, String posizione, long codiceaBarre, String caratteristiche, int quantita, double prezzo, TipoGenerico t) {
        this.tipo = t;
        this.nome = nome;
        this.posizione = posizione;
        this.codiceaBarre = codiceaBarre;
        this.caratteristiche = caratteristiche;
        this.quantita = Math.abs(quantita);
        this.prezzo = Math.abs(prezzo);
    }

    public String getNome() {
        return nome;
    }

    public int getQuantita() {
        return quantita;
    }

    void modificaQuantita(int add) throws ErroreMancanoComponenti, ComponenteTerminato {
        if (this.quantita + add < 0) {
            throw new ErroreMancanoComponenti("Non abbiamo abbastanza scorte");
        } else {
            this.quantita = this.quantita + add;
            DataBaseManager.save(Magazzino.getMagazzino());
        }
        if (!(this.quantita > 0)) {
            throw new ComponenteTerminato();
        }
    }

    public double getPrezzo() {
        return prezzo;
    }

    public TipoGenerico getTipo() {
        return tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.codiceaBarre ^ (this.codiceaBarre >>> 32));
        return hash;
    }

    /*Due componenti sono uguali se e solo se hanno lo stesso codice a barre*/
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Componenti other = (Componenti) obj;
        return this.codiceaBarre == other.codiceaBarre;
    }


    @Override
    public String[] getCampi() {
        String[] s= new String[]{nome,codiceaBarre.toString(),caratteristiche,posizione,quantita.toString(),prezzo.toString(),tipo.getNometipo()};
        return s;
    }

    public String[] getNomeCampi(){
        return new String[]{"Nome","Codice a Barre","Caratteristiche","Posizione","Quantità","Prezzo","Tipo"};
    }
}

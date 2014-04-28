package mscarpa.magazzino;

import mscarpa.exception.ComponenteTerminato;
import mscarpa.exception.ErroreMancanoComponenti;

public class Componenti implements GenericoElemento {

    private final String nome;
    private final Long codiceaBarre;
    private final String caratteristiche;
    private String posizione;
    private Integer quantita;
    private Double prezzo;
    private Tipo tipo;


    public Componenti(String nome, String posizione, long codiceaBarre, String caratteristiche, int quantita, double prezzo, Tipo t) {
        this.tipo = t;
        this.nome = nome;
        this.posizione = posizione;
        this.codiceaBarre = codiceaBarre;
        this.caratteristiche = caratteristiche;
        this.quantita = Math.abs(quantita);
        this.prezzo = Math.abs(prezzo);
    }

    public static String[] getNomeCampi() {
        return new String[]{"Nome", "Codice a Barre", "Caratteristiche", "Posizione", "Quantità", "Prezzo", "Tipo"};
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

    public Tipo getTipo() {
        return tipo;
    }

    private boolean equals(Componenti c) {
        if (c.codiceaBarre == this.codiceaBarre)
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Componenti that = (Componenti) o;

        if (!codiceaBarre.equals(that.codiceaBarre)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codiceaBarre.hashCode();
    }

    @Override
    public String[] getCampi() {
        String[] s = new String[]{nome, codiceaBarre.toString(), caratteristiche, posizione, quantita.toString(), prezzo.toString(), tipo.getNometipo()};
        return s;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}

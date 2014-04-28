package mscarpa.magazzino;

public class RigaBolla implements GenericoElemento {
    private Componenti componente;
    private Integer quantita;

    RigaBolla(Componenti componente, int quantita) {
        this.componente = componente;
        this.quantita = quantita;
    }

    public Componenti getComponente() {
        return componente;
    }

    public int getQuantita() {
        return quantita;
    }

    @Override
    public String[] getCampi() {
        String[] s = new String[8];
        for (int i = 0; i < 6; i++) {
            s[i] = componente.getCampi()[i];
        }
        s[7] = quantita.toString();
        return s;
    }

    public static String[] getNomeCampi() {
        String[] s=new String[]{"Nome", "Codice a Barre", "Caratteristiche", "Posizione", "Quantità", "Prezzo", "Tipo","Numero Componenti"};
        return s;
    }
}

package mscarpa.exception;

/**
 * Created by matteoscarpa on 17/04/14.
 */
public class ComponenteGiaPresente extends Exception {
    public ComponenteGiaPresente(String s) {
        super(s);
    }

    public ComponenteGiaPresente() {
        super("Il componente è stato aggiornato perchè era gìà presente in magazzino");
    }
}

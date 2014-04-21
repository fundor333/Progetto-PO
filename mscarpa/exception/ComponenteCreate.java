package mscarpa.exception;

/**
 * Created by matteoscarpa on 17/04/14.
 */
public class ComponenteCreate extends Exception {
    public ComponenteCreate() {
        super("Il componente è stato creato correttamente");
    }

    public ComponenteCreate(String s) {
        super(s);
    }
}

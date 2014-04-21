package mscarpa.exception;

/**
 * Created by matteoscarpa on 17/04/14.
 */
public class TipoInvalido extends Exception{
    public TipoInvalido() {
        super("Il tipo inserito non è valido");
    }

    public TipoInvalido(String s) {
        super(s);
    }
}

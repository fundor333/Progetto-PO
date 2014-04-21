package mscarpa.exception;

/**
 * Created by matteoscarpa on 17/04/14.
 */
public class TipoGiaPresente extends Exception {
    public TipoGiaPresente()
    {
        super("Attenzione, tipo di componente già presente");
    }

    public TipoGiaPresente(String s)
    {
        super(s);
    }
}

package mscarpa.magazzino;

import mscarpa.exception.TipoInvalido;

import java.io.Serializable;

/**
 * Created by matteoscarpa on 22/04/14.
 */
public class TipoGenerico implements GenericoElemento {
    private final String nometipo;
    private final String annotazioni;
    //TODO Fare sotto forma di Singeton


    public TipoGenerico(String nomet, String ann){
        if(nomet==null){
        this.nometipo="Senza nome";
        this.annotazioni="";
        }
        else{
        this.nometipo = nomet;
        this.annotazioni = ann;
        }
    }

    public String getNometipo() {
        return nometipo;
    }

    public String getAnnotazioni() {
        return annotazioni;
    }


    @Override
    public boolean equals(Object o) {
    if(o instanceof TipoGenerico)
        return equals((TipoGenerico)o);
        else
        return false;
    }

    private boolean equals(TipoGenerico o){
        if(this.nometipo==o.nometipo)
            return true;
        else
            return false;
    }

    public String[] getCampi(){
        return new String[]{this.getNometipo(),this.getAnnotazioni()};
    }

    public String[] getNomeCampi(){
        return new String[]{"Nome del Tipo", "Annotazioni", "Supertipo"};
    }
}
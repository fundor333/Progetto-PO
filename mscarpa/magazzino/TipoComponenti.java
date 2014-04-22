/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscarpa.magazzino;

import mscarpa.exception.TipoInvalido;


/**
 * @author matteoscarpa
 */
public class TipoComponenti extends TipoGenerico {

    private final TipoGenerico padre;

    TipoComponenti(String nometipo, String annotazioni) throws TipoInvalido {
        super(nometipo, annotazioni);
        this.padre=Magazzino.getMagazzino().getTIPODIBASE();
    }

    public TipoComponenti(String nometipo, String annotazioni, TipoGenerico padre) throws TipoInvalido {
        super(nometipo, annotazioni);
        this.padre = padre;
    }

    public TipoGenerico getPadre(){return padre;}

    private boolean equals(TipoGenerico o){
        if ( o instanceof TipoComponenti)
            return equals((TipoComponenti)o);
        else
            return false;
    }

    private boolean equals(TipoComponenti o){
        if (super.equals(o) && o.padre==this.padre)
            return true;
        else
            return false;
    }

}

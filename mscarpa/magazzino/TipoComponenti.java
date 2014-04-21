/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscarpa.magazzino;

import mscarpa.exception.TipoInvalido;

import java.io.Serializable;

/**
 * @author matteoscarpa
 */
public class TipoComponenti implements Serializable,GenericoElemento {

    private final String nometipo;
    private final String annotazioni;
    private final TipoComponenti padre;

    TipoComponenti(String nometipo, String annotazioni, TipoComponenti padre) throws TipoInvalido {
        if(nometipo.equalsIgnoreCase(annotazioni) && nometipo=="")
            throw new TipoInvalido();
        this.nometipo = nometipo;
        this.annotazioni = annotazioni;
        this.padre = padre;
    }

    public String getNometipo() {
        return nometipo;
    }

    public String getAnnotazioni() {
        return annotazioni;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoComponenti that = (TipoComponenti) o;

        if (!nometipo.equals(that.nometipo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nometipo.hashCode();
        result = 31 * result + annotazioni.hashCode();
        return result;
    }

    public String[] getCampi(){
        return new String[]{this.getNometipo(),this.getAnnotazioni(),this.padre.getNometipo()};
    }

    @Override
    public String[] getNomeCampi() {
        return new String[]{"Nome del Tipo", "Annotazioni", "Supertipo"};
    }
}

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
public class Tipo implements GenericoElemento {
    private final String nometipo;
    private final String annotazioni;
    private Tipo padre;

    public Tipo(String nometipo, String annotazioni, Tipo padre) throws TipoInvalido {
        if(nometipo==null){
            this.nometipo="Senza nome";
            this.annotazioni="";
        }
        else{
            this.nometipo = nometipo;
            this.annotazioni = annotazioni;
        }
        this.padre = padre;
    }

    Tipo(String nometipo,String annotazioni){
        if(nometipo==null){
            this.nometipo="Senza nome";
            this.annotazioni="";
        }
        else{
            this.nometipo = nometipo;
            this.annotazioni = annotazioni;
        }
    }

    public Tipo getPadre(){return padre;}

    public boolean equals(Object o){
        if ( o instanceof Tipo)
            return equals((Tipo)o);
        else
            return false;
    }

    private boolean equals(Tipo o){
        if (super.equals(o) && o.padre==this.padre)
            return true;
        else
            return false;
    }

    public String getNometipo(){
        return nometipo;
    }

    public static String[] getNomeCampi(){
        return new String[]{"Nome del Tipo", "Annotazioni", "Supertipo"};
    }

    public String[] getCampi(){
        try {
            return new String[]{this.nometipo, this.annotazioni, this.padre.nometipo};
        }catch (NullPointerException np){
            return new String[]{this.nometipo, this.annotazioni, "NULL"};
        }
    }
}

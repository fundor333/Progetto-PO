/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mscarpa.exception;

/**
 *
 * @author matteoscarpa
 */
public class ComponenteTerminato extends Exception {
    
    public ComponenteTerminato(String s){
        super(s);
    }

    public ComponenteTerminato()
    {
        super("Attenzione, il prodotto è terminato");
    }
}

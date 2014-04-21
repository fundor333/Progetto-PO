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
public class ErroreMancanoComponenti extends Exception {

  public ErroreMancanoComponenti()
  {
    super("Attenzione, ci sono problemi con la quantità");
  }
  
  public ErroreMancanoComponenti(String s)
  {
      super(s);
  }
}
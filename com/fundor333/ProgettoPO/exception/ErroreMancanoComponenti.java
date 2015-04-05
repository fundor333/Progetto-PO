/*
 * Copyright (c)
 * This code was created by f333. It's distributed as
 * part of the Progetto-PO.
 *
 * Progetto-PO is Open Source and distributed under the
 * GNU General Public License, version 2
 *
 * https://www.gnu.org/licenses/gpl-2.0.html
 *
 * File Updated 5/4/2015
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fundor333.ProgettoPO.exception;


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
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

package com.fundor333.ProgettoPO.exception;

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

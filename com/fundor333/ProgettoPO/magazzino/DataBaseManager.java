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

package com.fundor333.ProgettoPO.magazzino;

import java.io.*;

class DataBaseManager implements Serializable {
    private static final String NOMEFILE = "magazzino.dat";

    static boolean save(Serializable s) {
        try {
            FileOutputStream file = new FileOutputStream(NOMEFILE);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(s);
            output.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static Object load() {
        Object o = null;
        try {
            FileInputStream file = new FileInputStream(NOMEFILE);
            ObjectInputStream input = new ObjectInputStream(file);
            o = input.readObject();
            input.close();
            return o;
        } catch (Exception e) {
            return null;
        }
    }
}

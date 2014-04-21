package mscarpa.magazzino;

import java.io.*;

/**
 * Created by matteoscarpa on 16/04/14.
 */
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

package Persistance;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Generic class for data serialization
 * @param <T> Serializable class
 */
public abstract class Serializer<T> {
    private final String filename;

    public Serializer(String filename) {
        this.filename = filename;
    }

    public T deserialize() {
        File file = new File(filename);
        try {
            file.createNewFile();
            FileInputStream fis = new FileInputStream(filename);
            XMLDecoder decoder = new XMLDecoder(fis);
            T result = (T) decoder.readObject();
            decoder.close();
            fis.close();
            return result;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public boolean serialize(T model) {
        File file = new File(filename);
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(filename);
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(e -> e.printStackTrace());
            encoder.writeObject(model);
            encoder.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}


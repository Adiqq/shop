package Persistance;

import Infrastructure.FailResult;
import Infrastructure.OkResult;
import Infrastructure.Response;
import Infrastructure.Result;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Abstract class with common methods for persisting aggregate roots
 * @param <T>
 */
public abstract class StoreSerializer<T> implements IStore<T> {
    private final String filename;

    public StoreSerializer(String filename) {
        this.filename = filename;
    }

    public Response<T> deserialize() {
        File store = new File(filename);
        try {
            store.createNewFile();
            FileInputStream fis = new FileInputStream(filename);
            XMLDecoder decoder = new XMLDecoder(fis);
            T result = (T) decoder.readObject();
            decoder.close();
            fis.close();
            return new Response<>(new OkResult(), result);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Response<>(new FailResult(e.getLocalizedMessage()), null);
        } catch (IOException e) {
            return new Response<>(new FailResult(e.getLocalizedMessage()), null);
        }
    }

    public Result serialize(T aggregateRoot) {
        File store = new File(filename);
        try {
            store.createNewFile();
            FileOutputStream fos = new FileOutputStream(filename);
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(e -> e.printStackTrace());
            encoder.writeObject(aggregateRoot);
            encoder.close();
            fos.close();
            return new OkResult();
        } catch (IOException e) {
            e.printStackTrace();
            return new FailResult(e.getLocalizedMessage());
        }
    }
}

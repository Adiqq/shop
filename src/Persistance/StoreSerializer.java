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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adiq on 16.10.2016.
 */
public abstract class StoreSerializer<T> {
    private final String filename;

    public StoreSerializer(String filename) {
        this.filename = filename;
        serialize(new ArrayList<T>());
    }

    protected Response<List<T>> deserialize() {
        File store = new File(filename);
        try {
            store.createNewFile();
            FileInputStream fis = new FileInputStream(filename);
            XMLDecoder decoder = new XMLDecoder(fis);
            List<T> result = (List<T>) decoder.readObject();
            decoder.close();
            fis.close();
            return new Response<>(new OkResult(), result);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Response<>(new OkResult(), new ArrayList<T>());
        } catch (IOException e) {
            return new Response<>(new FailResult(e.getLocalizedMessage()), null);
        }
    }

    protected Result serialize(List<T> services) {
        File store = new File(filename);
        try {
            store.createNewFile();
            FileOutputStream fos = new FileOutputStream(filename);
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(services);
            encoder.close();
            fos.close();
            return new OkResult();
        } catch (IOException e) {
            e.printStackTrace();
            return new FailResult(e.getLocalizedMessage());
        }
    }
}

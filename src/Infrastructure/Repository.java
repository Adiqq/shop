package Infrastructure;

import Persistance.IStore;

import java.util.List;

/**
 * Created by Adiq on 16.10.2016.
 */
public abstract class Repository<T> {
    protected List<T> items;
    private IStore<T> store;

    public Repository(IStore<T> store) {
        this.store = store;
    }


    protected Response<List<T>> getResponse() {
        if (items == null) {
            Response<List<T>> response = store.deserializeStore();
            if (response.getResult().isSuccess()) {
                items = response.getContent();
            }
            return response;
        }
        return new Response<>(new OkResult(), items);
    }

    public Result Save() {
        return this.store.serializeStore(items);
    }

    public void Clear() {
        items = null;
    }
}

package Infrastructure;

import Persistance.IStore;

public abstract class Repository<T> {
    private IStore<T> store;

    public Repository(IStore<T> store) {
        this.store = store;
    }


    public Response<T> get() {
        return store.deserialize();
    }

    public Result save(T aggregateRoot) {
        return store.serialize(aggregateRoot);
    }
}

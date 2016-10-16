package Persistance;

import Infrastructure.Response;
import Infrastructure.Result;

import java.util.List;

public interface IStore<T> {
    Response<List<T>> deserializeStore();

    Result serializeStore(List<T> services);
}

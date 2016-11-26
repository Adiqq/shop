package Persistance;

import Infrastructure.Response;
import Infrastructure.Result;

public interface IStore<T> {
    Response<T> deserialize();

    Result serialize(T aggregateRoot);
}

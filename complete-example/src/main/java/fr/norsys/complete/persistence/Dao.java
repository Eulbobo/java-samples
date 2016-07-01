package fr.norsys.complete.persistence;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, K extends Serializable> {

    void save(final T element);

    T getById(K id);

    List<T> findAll();

    List<T> findLike(T example);
}

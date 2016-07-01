package fr.norsys.complete.persistence;

import java.util.List;

public interface Dao<T, K extends Number> {

    K save(final T element);

    T getById(K id);

    List<T> findAll();

    List<T> findLike(T example);
}

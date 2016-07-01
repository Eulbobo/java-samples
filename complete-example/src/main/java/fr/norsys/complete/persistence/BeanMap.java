package fr.norsys.complete.persistence;

import java.util.Map;

public interface BeanMap<T> {

    Map<String, Object> getBeanMap(T element);
}

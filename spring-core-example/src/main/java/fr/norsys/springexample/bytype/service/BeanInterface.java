package fr.norsys.springexample.bytype.service;

public interface BeanInterface<T> {

    T getBean();

    void saveBean(T bean);
}

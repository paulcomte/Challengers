package fr.rqndomhax.challengers.inventories;

public interface CustomConsumer<T, S, U, V> {

    void accept(T t, S s, U u, V v);

}

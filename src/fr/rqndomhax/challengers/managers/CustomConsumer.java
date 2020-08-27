/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.managers;

public interface CustomConsumer<T, S, U, V> {

    void accept(T t, S s, U u, V v);

}
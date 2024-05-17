package model;

import java.util.ArrayList;

public interface DAO<T> {

    boolean delete(T t);

    boolean update(T t);


    boolean exists(T t);

    boolean create(T t);

    boolean read(T t);


}

package model;

import java.util.ArrayList;

public interface DAO<T> {

    boolean insert(T t);

    boolean delete(T t);

    boolean update(T t);

    boolean select(T t);

    boolean exists(T t);

    boolean create(T t);

    ArrayList<T> read(T t);



}

package com.adso.dao.interfaces;

import java.util.List;

public interface DAO<T, K> {
//    void insert(T record);
//    void update(T record);
//	void delete(K idRecord);
    void save(T record);
    void remove(T record);
    void undo(T record);
//    T getById(K idRecord);
//    List<T> getAll();
}

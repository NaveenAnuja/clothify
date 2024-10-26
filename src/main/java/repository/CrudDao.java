package repository;

import entity.ItemEntity;
import entity.SupplierEntity;
import entity.UserEntity;

import java.util.List;

public interface CrudDao <T> extends SuperDao{
    boolean save(T t);
    boolean delete(String id);
    T search(String id);
    boolean update(T t);
    List<T> getAll();
}

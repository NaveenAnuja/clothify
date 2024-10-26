package repository.custom;

import entity.SupplierEntity;
import entity.UserEntity;
import repository.CrudDao;

import java.util.List;

public interface SupplierDao extends CrudDao<SupplierEntity> {
    boolean save(SupplierEntity entity);

    boolean update(SupplierEntity entity);

    SupplierEntity search(String id);

    boolean delete(String id);

    List<SupplierEntity> getAll();
}

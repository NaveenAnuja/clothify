package repository.custom;

import entity.UserEntity;
import repository.CrudDao;

import java.util.List;

public interface UserDao extends CrudDao<UserEntity> {

    boolean save(UserEntity userEntity);
    UserEntity search(String id);
    boolean delete(String id);
    boolean update(UserEntity entity);

    List<UserEntity> getAll();
}

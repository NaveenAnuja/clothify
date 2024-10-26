package repository.custom;

import entity.ItemEntity;
import entity.UserEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

import java.util.List;

public interface ItemDao extends CrudDao<ItemEntity> {
    boolean save(ItemEntity entity);

    boolean update(ItemEntity entity);

    List<ItemEntity> getAll();

    boolean delete(String id);

    ItemEntity search(String id);

    ObservableList<String> getAllIds();
}

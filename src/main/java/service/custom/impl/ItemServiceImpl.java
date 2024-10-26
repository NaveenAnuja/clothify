package service.custom.impl;

import entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemDao;
import service.custom.ItemService;
import util.DaoType;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Override
    public boolean addItem(Item item) {
        ItemEntity entity = new ModelMapper().map(item, ItemEntity.class);
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.Item);
        itemDao.save(entity);
        return true;
    }

    @Override
    public boolean updateItem(Item item) {
        ItemEntity entity = new ModelMapper().map(item, ItemEntity.class);
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.Item);
        itemDao.update(entity);
        return true;
    }

    @Override
    public ObservableList<Item> getAll() {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.Item);
        List<ItemEntity> allItems = itemDao.getAll();
        ObservableList<Item> items = FXCollections.observableArrayList();
        for (ItemEntity entity : allItems) {
            Item item = new ModelMapper().map(entity, Item.class);
            items.add(item);
        }
        return items;
    }

    @Override
    public boolean deleteUser(String id) {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.Item);
        return itemDao.delete(id);
    }

    @Override
    public Item searchItem(String id) {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.Item);
        ItemEntity entity = itemDao.search(id);
        Item item = new ModelMapper().map(entity, Item.class);
        return item;
    }

    @Override
    public ObservableList<String> getAllItemIds() {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.Item);
        return itemDao.getAllIds();
    }
}

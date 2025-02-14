package service.custom;

import javafx.collections.ObservableList;
import model.Item;
import service.SuperService;

public interface ItemService extends SuperService {
    boolean addItem(Item item);

    boolean updateItem(Item item);

    ObservableList<Item> getAll();

    boolean deleteUser(String text);

    Item searchItem(String text);

    ObservableList<String> getAllItemIds();
}

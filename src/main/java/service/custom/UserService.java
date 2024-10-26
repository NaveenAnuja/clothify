package service.custom;

import javafx.collections.ObservableList;
import model.User;
import service.SuperService;

public interface UserService extends SuperService {
    boolean addUser(User user);

    User searchUser(String id);

    boolean deleteUser(String text);

    boolean updateUser(User user);

    ObservableList<User> getAll();

}

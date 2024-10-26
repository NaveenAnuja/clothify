package service.custom.impl;

import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.UserDao;
import service.custom.UserService;
import util.DaoType;
import util.HibernateUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public boolean addUser(User user) {
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.User);
        userDao.save(entity);
        return true;
    }

    @Override
    public User searchUser(String id) {
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.User);
        UserEntity entity = userDao.search(id);
        User user = new ModelMapper().map(entity, User.class);
        return user;
    }

    @Override
    public boolean deleteUser(String id) {
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.User);
        return userDao.delete(id);
    }

    @Override
    public boolean updateUser(User user) {
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.User);
        userDao.update(entity);
        return true;
    }

    @Override
    public ObservableList<User> getAll() {
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.User);
        List<UserEntity> allUsers = userDao.getAll();
        ObservableList<User> users = FXCollections.observableArrayList();
        for (UserEntity entity : allUsers) {
            User user = new ModelMapper().map(entity, User.class);
            users.add(user);
        }
        return users;

    }
}

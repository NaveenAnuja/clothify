package repository.custom.impl;

import entity.UserEntity;
import org.hibernate.Session;
import repository.custom.UserDao;
import util.HibernateUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean save(UserEntity userEntity) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(userEntity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public UserEntity search(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        UserEntity userEntity = session.get(UserEntity.class, id);

        session.getTransaction().commit();
        session.close();
        if (userEntity != null) {
            return userEntity;
        } else {
            System.out.println("Employee not found!");
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        UserEntity userToDelete = session.get(UserEntity.class, id);

        if (userToDelete != null) {
            session.delete(userToDelete);
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            System.out.println("User not found!");
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean update(UserEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        UserEntity userToUpdate = session.get(UserEntity.class, entity.getId());

        if (userToUpdate != null) {
            userToUpdate.setId(entity.getId());
            userToUpdate.setName(entity.getName());
            userToUpdate.setAddress(entity.getAddress());
            userToUpdate.setEmail(entity.getEmail());
            userToUpdate.setPassword(entity.getPassword());
            userToUpdate.setContact(entity.getContact());

            session.update(userToUpdate);
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            System.out.println("User not found!");
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    @Override
    public List<UserEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<UserEntity> userList = session.createQuery("FROM UserEntity", UserEntity.class).list();

        session.getTransaction().commit();
        session.close();

        return userList;
    }
}

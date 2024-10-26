package repository.custom.impl;

import entity.ItemEntity;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import repository.custom.ItemDao;
import util.HibernateUtil;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(ItemEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        ItemEntity itemToUpdate = session.get(ItemEntity.class, entity.getCode());

        if (itemToUpdate != null) {
            itemToUpdate.setCode(entity.getCode());
            itemToUpdate.setDescription(entity.getDescription());
            itemToUpdate.setUnitPrice(entity.getUnitPrice());
            itemToUpdate.setQty(entity.getQty());
            itemToUpdate.setSize(entity.getSize());

            session.update(itemToUpdate);
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            System.out.println("Item not found!");
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    @Override
    public List<ItemEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<ItemEntity> itemList = session.createQuery("FROM ItemEntity", ItemEntity.class).list();
        session.getTransaction().commit();
        session.close();

        return itemList;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        ItemEntity itemToDelete = session.get(ItemEntity.class, id);

        if (itemToDelete != null) {
            session.delete(itemToDelete);
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            System.out.println("Item not found!");
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    @Override
    public ItemEntity search(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        ItemEntity itemEntity = session.get(ItemEntity.class, id);

        session.getTransaction().commit();
        session.close();
        if (itemEntity != null) {
            return itemEntity;
        } else {
            System.out.println("Item not found!");
            return null;
        }
    }

    @Override
    public ObservableList<String> getAllIds() {
        ObservableList<String> ids = FXCollections.observableArrayList();
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<String> itemList = session.createQuery("SELECT code FROM ItemEntity", String.class).list();
        ids.addAll(itemList);
        session.getTransaction().commit();
        session.close();
        return ids;
    }

}

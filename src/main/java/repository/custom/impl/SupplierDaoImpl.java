package repository.custom.impl;

import entity.SupplierEntity;
import entity.UserEntity;
import org.hibernate.Session;
import repository.custom.SupplierDao;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        SupplierEntity supplierToUpdate = session.get(SupplierEntity.class, entity.getId());

        if (supplierToUpdate != null) {
            supplierToUpdate.setId(entity.getId());
            supplierToUpdate.setName(entity.getName());
            supplierToUpdate.setItem(entity.getItem());
            supplierToUpdate.setAddress(entity.getAddress());
            supplierToUpdate.setEmail(entity.getEmail());
            supplierToUpdate.setCompany(entity.getCompany());

            session.update(supplierToUpdate);
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            System.out.println("Supplier not found!");
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    @Override
    public SupplierEntity search(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        SupplierEntity supplierEntity = session.get(SupplierEntity.class, id);

        session.getTransaction().commit();
        session.close();
        if (supplierEntity != null) {
            return supplierEntity;
        } else {
            System.out.println("Employee not found!");
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        SupplierEntity supplierToDelete = session.get(SupplierEntity.class, id);

        if (supplierToDelete != null) {
            session.delete(supplierToDelete);
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            System.out.println("Supplier not found!");
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    @Override
    public List<SupplierEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<SupplierEntity> supplierList = session.createQuery("FROM SupplierEntity", SupplierEntity.class).list();

        session.getTransaction().commit();
        session.close();

        return supplierList;
    }
}

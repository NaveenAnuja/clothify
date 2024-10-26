package service.custom.impl;

import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Supplier;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.Supplier);
        supplierDao.save(entity);
        return true;
    }

    @Override
    public Supplier searchSupplier(String id) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.Supplier);
        SupplierEntity entity = supplierDao.search(id);
        Supplier supplier = new ModelMapper().map(entity, Supplier.class);
        return supplier;
    }

    @Override
    public boolean deleteSupplier(String id) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.Supplier);
        return supplierDao.delete(id);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.Supplier);
        supplierDao.update(entity);
        return true;
    }

    @Override
    public ObservableList<Supplier> getAll() {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.Supplier);
        List<SupplierEntity> allSuppliers = supplierDao.getAll();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        for (SupplierEntity entity : allSuppliers) {
            Supplier supplier = new ModelMapper().map(entity, Supplier.class);
            suppliers.add(supplier);
        }
        return suppliers;
    }
}

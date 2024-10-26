package service.custom;

import javafx.collections.ObservableList;
import model.Supplier;
import model.User;
import service.SuperService;

public interface SupplierService extends SuperService {
    boolean addSupplier(Supplier supplier);
    Supplier searchSupplier(String id);

    boolean deleteSupplier(String text);

    boolean updateSupplier(Supplier supplier);

    ObservableList<Supplier> getAll();
}

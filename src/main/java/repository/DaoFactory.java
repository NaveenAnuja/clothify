package repository;

import repository.custom.impl.ItemDaoImpl;
import repository.custom.impl.OrderDaoImpl;
import repository.custom.impl.SupplierDaoImpl;
import repository.custom.impl.UserDaoImpl;
import util.DaoType;

public class DaoFactory {

    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case User : return (T) new UserDaoImpl();
            case Item : return (T) new ItemDaoImpl();
            case Supplier : return (T) new SupplierDaoImpl();
            case Order : return (T) new OrderDaoImpl();
        }
        return null;
    }
}

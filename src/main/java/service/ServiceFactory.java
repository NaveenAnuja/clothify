package service;

import service.custom.impl.ItemServiceImpl;
import service.custom.impl.OrderServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import service.custom.impl.UserServiceImpl;
import util.ServiceType;

public class ServiceFactory {

    private ServiceFactory(){}

    public static ServiceFactory instance;

    public static ServiceFactory getInstance(){
        return instance==null ? instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type) {
            case User: return (T) new UserServiceImpl();
            case Item: return (T) new ItemServiceImpl();
            case Supplier: return (T) new SupplierServiceImpl();
            case Order: return (T) new OrderServiceImpl();
        }
        return null;
    }
}

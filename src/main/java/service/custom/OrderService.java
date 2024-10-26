package service.custom;

import model.Order;
import service.SuperService;

public interface OrderService extends SuperService {
     boolean addOrder(Order order);

}

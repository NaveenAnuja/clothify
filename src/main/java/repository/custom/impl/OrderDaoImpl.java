package repository.custom.impl;

import entity.OrderEntity;
import entity.UserEntity;
import repository.custom.OrderDao;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public OrderEntity search(String id) {
        return null;
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public List<OrderEntity> getAll() {
        return null;
    }
}

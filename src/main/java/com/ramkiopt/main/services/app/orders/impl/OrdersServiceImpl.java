package com.ramkiopt.main.services.app.orders.impl;

import com.ramkiopt.main.dto.OrdersDto;
import com.ramkiopt.main.entities.Orders;
import com.ramkiopt.main.repositories.OrdersRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.base.RowStatus;
import com.ramkiopt.main.services.app.orders.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

@Service
public class OrdersServiceImpl extends BaseServiceAbstract<Orders, OrdersDto> implements OrdersService<OrdersDto> {

    @Autowired
    private OrdersRepository ordersRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(ordersRepository);
    }

    @Override
    public OrdersDto create(OrdersDto dto) {
        dto.setStatus(RowStatus.ENABLE);
        return createInDb(new Orders(), dto);
    }

    @Override
    public OrdersDto get(Long id) {
        OrdersDto dto = readFromDb(id, new OrdersDto());
        if (dto.getStatus().equals(RowStatus.DELETED)) {
            throw new EntityNotFoundException();
        }
        return readFromDb(id, new OrdersDto());
    }

    @Override
    public OrdersDto update(Long id, OrdersDto dto) {
        return updateInDb(id, dto);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteInDb(id);
    }

    @Override
    public boolean deleteInDb(Long id) {
        Orders orders = jpaRepository.getOne(id);
        if (orders == null) {
            return false;
        }
        orders.setStatus(RowStatus.DELETED);
        jpaRepository.save(orders);
        return true;
    }
}

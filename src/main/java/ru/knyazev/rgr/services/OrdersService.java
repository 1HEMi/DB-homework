package ru.knyazev.rgr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knyazev.rgr.models.Client;
import ru.knyazev.rgr.models.Order;
import ru.knyazev.rgr.models.Product;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.repositories.OrdersRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> findAll(){
        return ordersRepository.findAll();
    }

    public Order findOne(int id){
        Optional<Order> foundOrder = ordersRepository.findById(id);

        return foundOrder.orElse(null);
    }

    @Transactional
    public void save(Order order) {
        order.setOrderDate(new Date());
        ordersRepository.save(order);
    }

    @Transactional
    public void update(int id, Order updatedOrder) {
        Order orderToBeUpdated = ordersRepository.findById(id).get();
        updatedOrder.setId(id);
        updatedOrder.setOwnerClient(orderToBeUpdated.getOwnerClient());
        updatedOrder.setProducts(orderToBeUpdated.getProducts());
        ordersRepository.save(updatedOrder);
    }

    @Transactional
    public void delete(int id) {
        ordersRepository.deleteById(id);

    }

    public Client getOrderOwner(int id){
        return ordersRepository.findById(id).map(Order::getOwnerClient).orElse(null);
    }

    public List<Product> getProductsByOrderId(int id){
        Optional<Order> order = ordersRepository.findById(id);

        return order.get().getProducts();
    }
}

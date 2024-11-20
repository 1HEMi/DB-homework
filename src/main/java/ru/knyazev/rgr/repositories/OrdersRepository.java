package ru.knyazev.rgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knyazev.rgr.models.Order;

public interface OrdersRepository extends JpaRepository<Order, Integer> {
}

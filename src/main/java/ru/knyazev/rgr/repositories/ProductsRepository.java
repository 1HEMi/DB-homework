package ru.knyazev.rgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knyazev.rgr.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
}

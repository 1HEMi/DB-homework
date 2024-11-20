package ru.knyazev.rgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knyazev.rgr.models.Supplier;

public interface SuppliersRepository extends JpaRepository<Supplier, Integer> {
}

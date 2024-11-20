package ru.knyazev.rgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knyazev.rgr.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}

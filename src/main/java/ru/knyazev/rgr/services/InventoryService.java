package ru.knyazev.rgr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knyazev.rgr.models.Inventory;
import ru.knyazev.rgr.models.Product;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.repositories.InventoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> findAll(){
        return inventoryRepository.findAll();
    }

    public Inventory findOne(int id){
        Optional<Inventory> foundInventory = inventoryRepository.findById(id);

        return foundInventory.orElse(null);
    }

    @Transactional
    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Transactional
    public void update(int id, Inventory updatedInventory) {


        updatedInventory.setId(id);
        inventoryRepository.save(updatedInventory);
    }

    @Transactional
    public void delete(int id) {
        inventoryRepository.deleteById(id);

    }

    public List<Product> getProductsByInventoryId(int id){
        Optional<Inventory> inventory = inventoryRepository.findById(id);

        return inventory.get().getProducts();
    }


}

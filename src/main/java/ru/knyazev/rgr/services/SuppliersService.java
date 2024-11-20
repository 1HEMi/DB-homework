package ru.knyazev.rgr.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knyazev.rgr.models.Product;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.repositories.SuppliersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SuppliersService {

    private final SuppliersRepository suppliersRepository;

    @Autowired
    public SuppliersService(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    public List<Supplier> findAll(){
        return suppliersRepository.findAll();
    }

    public Supplier findOne(int id){
        Optional<Supplier> foundSupplier = suppliersRepository.findById(id);

        return foundSupplier.orElse(null);
    }

    public List<Product> getProductsBySupplierId(int id){
        Optional<Supplier> supplier = suppliersRepository.findById(id);

        return supplier.get().getProducts();
    }

    @Transactional
    public void save(Supplier supplier) {
        suppliersRepository.save(supplier);
    }

    @Transactional
    public void update(int id, Supplier updatedSupplier) {
        updatedSupplier.setId(id);
        suppliersRepository.save(updatedSupplier);
    }

    @Transactional
    public void delete(int id) {
        suppliersRepository.deleteById(id);

    }
}


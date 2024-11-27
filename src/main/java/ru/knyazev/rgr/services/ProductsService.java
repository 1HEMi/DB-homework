package ru.knyazev.rgr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knyazev.rgr.models.Inventory;
import ru.knyazev.rgr.models.Product;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll(){
        return productsRepository.findAll();
    }

    public Product findOne(int id){
        Optional<Product> foundProduct = productsRepository.findById(id);

        return foundProduct.orElse(null);
    }

    @Transactional
    public void save(Product product) {
        productsRepository.save(product);
    }

    @Transactional
    public void update(int id, Product updatedProduct) {
        Product productToBeUpdated = productsRepository.findById(id).get();

        updatedProduct.setId(id);
        updatedProduct.setOwner(productToBeUpdated.getOwner());
        updatedProduct.setOwnerInventory(productToBeUpdated.getOwnerInventory());
        productsRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(int id) {
        productsRepository.deleteById(id);

    }

    public Supplier getProductOwner(int id){
        return productsRepository.findById(id).map(Product::getOwner).orElse(null);
    }

    public Inventory getProductInventory(int id){
        return productsRepository.findById(id).map(Product::getOwnerInventory).orElse(null);
    }

}

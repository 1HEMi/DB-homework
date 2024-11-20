package ru.knyazev.rgr.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CollectionId;

import java.util.List;

@Entity
@Table(name = "Inventory")
public class Inventory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "ownerInventory")
    private List<Product> products;

    @Column(name="quantity_in_stock")
    private int quantityInStock;

    public Inventory() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}

package ru.knyazev.rgr.models;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

import java.security.PrivateKey;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity_available")
    private int quantityAvailable;

    @ManyToOne
    @JoinColumn(name="supplier_id",referencedColumnName = "id")
    private Supplier owner;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order ownerProduct;

    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private Inventory ownerInventory;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Supplier getOwner() {
        return owner;
    }

    public void setOwner(Supplier owner) {
        this.owner = owner;
    }

    public Inventory getOwnerInventory() {
        return ownerInventory;
    }

    public void setOwnerInventory(Inventory ownerInventory) {
        this.ownerInventory = ownerInventory;
    }

    public Order getOwnerProduct() {
        return ownerProduct;
    }

    public void setOwnerProduct(Order ownerProduct) {
        this.ownerProduct = ownerProduct;
    }
}

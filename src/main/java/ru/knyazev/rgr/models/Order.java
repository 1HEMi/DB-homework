package ru.knyazev.rgr.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Order")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="client_id",referencedColumnName = "id")
    private Client ownerClient;

//    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    private Product product;

    @OneToMany(mappedBy = "ownerProduct")
    private List<Product> products;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "order_date")
    private Timestamp orderDate;

    public Order() {
    }

    public Order(Client ownerClient) {
        this.ownerClient = ownerClient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getOwnerClient() {
        return ownerClient;
    }

    public void setOwnerClient(Client ownerClient) {
        this.ownerClient = ownerClient;
    }

//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

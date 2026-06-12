package com.rajiv;

import jakarta.persistence.*;

@Entity
@Table(name="Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="ID")
    private int id;
    @Column(name = "Product_Name")
    private String name;
    @Column(name = "Price")
    private double price;
    @Column (name="Product_Quantity")
    private int quantity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_detail_id")
    private InventoryDetail inventoryDetail;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id" )
    private Supplier supplier;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InventoryDetail getInventoryDetail() {
        return inventoryDetail;
    }

    public void setInventoryDetail(InventoryDetail inventoryDetail) {
        this.inventoryDetail = inventoryDetail;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", inventoryDetail=" + inventoryDetail +
                ", supplier=" + supplier +
                '}';
    }
}

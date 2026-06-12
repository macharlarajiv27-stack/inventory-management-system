package com.rajiv;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InventoryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String WarehouseLocation;
    private String LastUpdated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarehouseLocation() {
        return WarehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        WarehouseLocation = warehouseLocation;
    }

    public String getLastUpdated() {
        return LastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        LastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "InventoryDetail{" +
                "id=" + id +
                ", WarehouseLocation='" + WarehouseLocation + '\'' +
                ", LastUpdated='" + LastUpdated + '\'' +
                '}';
    }
}

package dk.bilabonnement.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String licensePlate;
    private int year;
    private String transmission;
    private double pricePerDay;
    private double purchasePrice;
    private String imageUrl;
    private String status = "LEDIG";

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getYear() {
        return year;
    }

    public String getTransmission() {
        return transmission;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
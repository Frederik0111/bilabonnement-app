package dk.bilabonnement.app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rental_agreement")
public class RentalAgreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;
    private String status = "UDLEJET";
    private boolean hasDamage;
    private String damageDescription;
    private double damagePrice;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public RentalAgreement() {
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }

    public boolean isHasDamage() {
        return hasDamage;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public double getDamagePrice() {
        return damagePrice;
    }

    public Car getCar() {
        return car;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHasDamage(boolean hasDamage) {
        this.hasDamage = hasDamage;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public void setDamagePrice(double damagePrice) {
        this.damagePrice = damagePrice;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
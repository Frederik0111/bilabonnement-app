package dk.bilabonnement.app.service;

import dk.bilabonnement.app.model.Car;
import dk.bilabonnement.app.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository repo;

    private static final String STATUS_LEDIG = "LEDIG";
    private static final String STATUS_UDLEJET = "UDLEJET";
    private static final String STATUS_VAERKSTED = "VÆRKSTED";

    public CarService(CarRepository repo) {
        this.repo = repo;
    }

    public List<Car> getAllCars() {
        return repo.findAll();
    }

    public Car getCarById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }

    public Car saveCar(Car car) {
        return repo.save(car);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Car> getAvailableCars() {
        return repo.findByStatus(STATUS_LEDIG);
    }

    public List<Car> getRentedCars() {
        return repo.findByStatus(STATUS_UDLEJET);
    }

    public List<Car> getWorkshopCars() {
        return repo.findByStatus(STATUS_VAERKSTED);
    }

    public long countRentedCars() {
        return getRentedCars().size();
    }

    public long countWorkshopCars() {
        return getWorkshopCars().size();
    }

    public double getTotalRentedDailyIncome() {
        return getRentedCars().stream()
                .mapToDouble(Car::getPricePerDay)
                .sum();
    }

    public double getTotalRentedPurchasePrice() {
        return getRentedCars().stream()
                .mapToDouble(Car::getPurchasePrice)
                .sum();
    }
}
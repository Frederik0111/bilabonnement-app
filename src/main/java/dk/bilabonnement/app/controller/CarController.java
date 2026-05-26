package dk.bilabonnement.app.controller;

import dk.bilabonnement.app.model.Car;
import dk.bilabonnement.app.service.CarService;
import dk.bilabonnement.app.service.RentalAgreementService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    private final CarService service;
    private final RentalAgreementService rentalService;

    public CarController(CarService service,
                         RentalAgreementService rentalService) {

        this.service = service;
        this.rentalService = rentalService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("cars", service.getAvailableCars());

        model.addAttribute("rentedCount", service.countRentedCars());

        model.addAttribute("rentedDailyIncome", service.getTotalRentedDailyIncome());

        model.addAttribute("rentedPurchasePrice", service.getTotalRentedPurchasePrice());

        model.addAttribute("workshopCount", service.countWorkshopCars());

        model.addAttribute("workshopDamagePrice", rentalService.getTotalWorkshopDamagePrice());

        return "index";
    }

    @GetMapping("/cars/new")
    public String newCarForm(Model model) {

        model.addAttribute("car", new Car());

        model.addAttribute("images",
                getAvailableImages());

        return "add-car";
    }

    @PostMapping("/cars")
    public String addCar(@ModelAttribute Car car) {

        car.setStatus("LEDIG");

        service.saveCar(car);

        return "redirect:/";
    }

    @GetMapping("/cars/edit/{id}")
    public String editCarForm(@PathVariable Long id,
                              Model model) {

        model.addAttribute("car",
                service.getCarById(id));

        model.addAttribute("images",
                getAvailableImages());

        return "edit-car";
    }

    @PostMapping("/cars/update/{id}")
    public String updateCar(@PathVariable Long id,
                            @ModelAttribute Car updatedCar) {

        Car car = service.getCarById(id);

        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setLicensePlate(updatedCar.getLicensePlate());
        car.setYear(updatedCar.getYear());
        car.setTransmission(updatedCar.getTransmission());
        car.setPricePerDay(updatedCar.getPricePerDay());
        car.setPurchasePrice(updatedCar.getPurchasePrice());
        car.setImageUrl(updatedCar.getImageUrl());

        service.saveCar(car);

        return "redirect:/";
    }

    @PostMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id) {

        service.delete(id);

        return "redirect:/";
    }

    @GetMapping("/workshop")
    public String workshop(Model model) {

        model.addAttribute("cars",
                service.getWorkshopCars());

        return "workshop";
    }

    @PostMapping("/cars/return-to-available/{id}")
    public String returnToAvailable(@PathVariable Long id) {

        Car car = service.getCarById(id);

        car.setStatus("LEDIG");

        service.saveCar(car);

        return "redirect:/workshop";
    }

    private List<String> getAvailableImages() {

        List<String> images = new ArrayList<>();

        File folder = new File(
                "src/main/resources/static/images");

        File[] files = folder.listFiles();

        if (files != null) {

            for (File file : files) {

                images.add("/images/" + file.getName());
            }
        }

        return images;
    }
}
package dk.bilabonnement.app.controller;

import dk.bilabonnement.app.model.Car;
import dk.bilabonnement.app.model.RentalAgreement;
import dk.bilabonnement.app.service.CarService;
import dk.bilabonnement.app.service.RentalAgreementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RentalAgreementController {

    private final RentalAgreementService rentalService;
    private final CarService carService;

    public RentalAgreementController(RentalAgreementService rentalService,
                                     CarService carService) {
        this.rentalService = rentalService;
        this.carService = carService;
    }

    @GetMapping("/rentals")
    public String showRentalAgreements(Model model) {
        model.addAttribute("rentals", rentalService.getAll());
        return "rentals";
    }

    @GetMapping("/rentals/new/{carId}")
    public String showRentalForm(@PathVariable Long carId, Model model) {
        Car car = carService.getCarById(carId);

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setCar(car);
        rentalAgreement.setStatus("UDLEJET");

        model.addAttribute("car", car);
        model.addAttribute("rentalAgreement", rentalAgreement);

        return "add-rental";
    }

    @PostMapping("/rentals/save/{carId}")
    public String saveRentalAgreement(@PathVariable Long carId,
                                      @ModelAttribute RentalAgreement rentalAgreement) {
        Car car = carService.getCarById(carId);

        rentalAgreement.setCar(car);
        rentalAgreement.setStatus("UDLEJET");

        rentalService.save(rentalAgreement);

        car.setStatus("UDLEJET");
        carService.saveCar(car);

        return "redirect:/rentals";
    }

    @GetMapping("/rentals/edit/{id}")
    public String showEditRentalForm(@PathVariable Long id, Model model) {
        RentalAgreement rental = rentalService.getById(id);

        model.addAttribute("rental", rental);

        return "edit-rental";
    }

    @PostMapping("/rentals/update/{id}")
    public String updateRental(@PathVariable Long id,
                               @ModelAttribute RentalAgreement updatedRental) {
        RentalAgreement existingRental = rentalService.getById(id);

        existingRental.setCustomerName(updatedRental.getCustomerName());
        existingRental.setCustomerEmail(updatedRental.getCustomerEmail());
        existingRental.setCustomerPhone(updatedRental.getCustomerPhone());
        existingRental.setStartDate(updatedRental.getStartDate());
        existingRental.setEndDate(updatedRental.getEndDate());
        existingRental.setNotes(updatedRental.getNotes());

        rentalService.save(existingRental);

        return "redirect:/rentals";
    }

    @GetMapping("/rentals/end/{id}")
    public String showEndRentalForm(@PathVariable Long id, Model model) {
        RentalAgreement rental = rentalService.getById(id);

        model.addAttribute("rental", rental);

        return "end-rental";
    }

    @PostMapping("/rentals/end/{id}")
    public String endRental(@PathVariable Long id,
                            @RequestParam(required = false, defaultValue = "false") boolean hasDamage,
                            @RequestParam(required = false) String damageDescription,
                            @RequestParam(required = false, defaultValue = "0") double damagePrice) {
        RentalAgreement rental = rentalService.getById(id);
        Car car = rental.getCar();

        rental.setStatus("AFSLUTTET");
        rental.setHasDamage(hasDamage);
        rental.setDamageDescription(damageDescription);
        rental.setDamagePrice(damagePrice);

        if (hasDamage) {
            car.setStatus("VÆRKSTED");
        } else {
            car.setStatus("LEDIG");
        }

        rentalService.save(rental);
        carService.saveCar(car);

        return "redirect:/rentals";
    }

    @PostMapping("/rentals/delete/{id}")
    public String deleteRental(@PathVariable Long id) {

        RentalAgreement rental = rentalService.getById(id);

        Car car = rental.getCar();

        car.setStatus("LEDIG");
        carService.saveCar(car);

        rentalService.delete(id);

        return "redirect:/rentals";
    }
}
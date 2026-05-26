package dk.bilabonnement.app.service;

import dk.bilabonnement.app.model.RentalAgreement;
import dk.bilabonnement.app.repository.RentalAgreementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rentalAgreementService")
public class RentalAgreementService {

    private final RentalAgreementRepository repo;

    public RentalAgreementService(RentalAgreementRepository repo) {
        this.repo = repo;
    }

    public List<RentalAgreement> getAll() {
        return repo.findAll();
    }

    public RentalAgreement save(RentalAgreement rentalAgreement) {
        return repo.save(rentalAgreement);
    }

    public RentalAgreement getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental agreement not found with id: " + id));
    }

    public RentalAgreement getLatestByCarId(Long carId) {
        return repo.findTopByCarIdOrderByIdDesc(carId);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public double getTotalWorkshopDamagePrice() {
        return repo.findByCarStatus("VÆRKSTED").stream()
                .mapToDouble(RentalAgreement::getDamagePrice)
                .sum();
    }
}
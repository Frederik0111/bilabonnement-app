package dk.bilabonnement.app.repository;

import dk.bilabonnement.app.model.RentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Long> {

    RentalAgreement findTopByCarIdOrderByIdDesc(Long carId);

    List<RentalAgreement> findByCarStatus(String status);
}
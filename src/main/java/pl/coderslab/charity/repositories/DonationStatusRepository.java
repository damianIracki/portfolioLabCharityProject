package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entities.DonationStatus;

import java.util.List;

public interface DonationStatusRepository extends JpaRepository<DonationStatus, Long> {

    DonationStatus findFirstByName(String name);

    List<DonationStatus> findAll();
}

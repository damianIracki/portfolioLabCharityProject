package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entities.Donation;

import java.util.List;


public interface DonationRepository extends JpaRepository<Donation, Long>{

    @Query("select sum(d.quantity) from Donation d")
    Integer findSumQuantity ();


    @Query("SELECT d FROM Donation d GROUP BY d.institution")
    List<Donation> countOfInstitutions();

    @Override
    <S extends Donation> S save(S s);


}

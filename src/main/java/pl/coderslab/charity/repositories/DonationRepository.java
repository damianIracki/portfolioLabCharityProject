package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.User;

import java.util.List;


public interface DonationRepository extends JpaRepository<Donation, Long>{

    @Query("select sum(d.quantity) from Donation d")
    Integer findSumQuantity ();


    @Query("SELECT COUNT (DISTINCT d.institution) FROM Donation d")
    Integer countOfInstitutions();

    @Override
    <S extends Donation> S save(S s);

    List<Donation> findAllByUserOrderByPickUpDateAscPickUpTimeAscCreateDateAsc(User user);

}

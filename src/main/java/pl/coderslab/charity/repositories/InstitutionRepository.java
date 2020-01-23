package pl.coderslab.charity.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entities.Institution;

import java.util.List;
import java.util.Optional;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {


    List<Institution> findAllByOrderByNameAsc();

    Institution findFirstByName(String name);

    Institution save(Institution institution);

    Institution findFirstById(Long id);


}

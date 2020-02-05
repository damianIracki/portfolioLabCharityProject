package pl.coderslab.charity.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.charity.entities.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);


}

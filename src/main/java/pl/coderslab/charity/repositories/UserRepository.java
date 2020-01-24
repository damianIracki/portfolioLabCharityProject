package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUserName(String userName);

    List<User> findAllByRolesContainsOrderById(Role role);

    List<User> findAllByRolesNotContainsOrderById(Role role);

    List<User> findAllByRolesNotContainsAndRolesContainsOrderById(Role role1, Role role2);


    User findFirstById(Long id);


}

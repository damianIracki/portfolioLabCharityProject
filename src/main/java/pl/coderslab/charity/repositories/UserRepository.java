package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.entities.User;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUserName(String userName);

    List<User> findAllByRolesContainsOrderById(Role role);

    List<User> findAllByRolesNotContainsOrderById(Role role);

    List<User> findAllByRolesNotContainsAndRolesContainsOrderById(Role role1, Role role2);

    User findFirstById(Long id);

    @Modifying
    @Query("UPDATE User u SET u.password= ?1 WHERE u.userName= ?2")
    void updateUserPassword(String password, String userName);


}

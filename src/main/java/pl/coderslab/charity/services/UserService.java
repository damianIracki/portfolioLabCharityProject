package pl.coderslab.charity.services;

import pl.coderslab.charity.entities.User;

public interface UserService {

    User findByUserName(String username);

    void saveUser(User user);
}

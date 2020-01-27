package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.RoleRepository;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/usersManagement")
@Secured("ROLE_ADMIN")
public class UserManagementController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String usersList(Model model){
        Role adminRole = roleRepository.findByRole("ROLE_ADMIN");
        Role userRole = roleRepository.findByRole("ROLE_USER");
        List<User> users = userRepository.findAllByRolesNotContainsOrderById(adminRole);
        model.addAttribute("users", users);
        return "usersManagement/usersList";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id){
        User user = userRepository.findFirstById(id);
        userRepository.delete(user);
        return "redirect:/usersManagement/list";
    }

    @RequestMapping(path = "/ban/{id}", method = RequestMethod.PUT)
    public String banUser(@PathVariable Long id){
        User user  = userRepository.findFirstById(id);
        if(user.getActive()==true){
            user.setActive(false);
        } else {
            user.setActive(true);
        }
        userRepository.save(user);
        return "redirect:/usersManagement/list";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, Model model){
        User user = userRepository.findFirstById(id);
        model.addAttribute("user", user);
        return "editUserForm";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.PUT)
    public String saveEditUser(@ModelAttribute User user, @PathVariable Long id){
        User userToSave = userRepository.findFirstById(id);
        userToSave.setEmail(user.getEmail());
        userToSave.setUserName(user.getEmail());
        userToSave.setFirstName(user.getFirstName());
        userToSave.setLastName(user.getLastName());
        userRepository.save(userToSave);
        return "redirect:/usersManagement/list";
    }
}

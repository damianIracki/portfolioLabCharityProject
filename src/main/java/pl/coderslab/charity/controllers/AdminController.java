package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.RoleRepository;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(path = "/panel")
    public String adminPanel(){
        return "admin/adminMainPage";
    }

    @RequestMapping(path = "/list")
    public String adminList(Model model){
        Role role = roleRepository.findByRole("ROLE_ADMIN");
        List<User> admins = userRepository.findAllByRolesContainsOrderById(role);
        model.addAttribute("admins", admins);
        return "admin/adminList";
    }

    @RequestMapping(path = "/add")
    public String addAdminList(Model model){
        Role role = roleRepository.findByRole("ROLE_ADMIN");
        List<User> users = userRepository.findAllByRolesNotContainsOrderById(role);
        model.addAttribute("users", users);
        return "admin/addAdminForm";
    }

    @RequestMapping(path = "/add/{id}")
    public String addAdmin(@PathVariable Long id){
        User user=  userRepository.findFirstById(id);
        Role role = roleRepository.findByRole("ROLE_ADMIN");
        user.getRoles().add(role);
        userRepository.save(user);
        return "redirect:/admin/list";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteAdmin(@PathVariable Long id){
        Role role = roleRepository.findByRole("ROLE_ADMIN");
        List<User> admins = userRepository.findAllByRolesContainsOrderById(role);
        if(admins.size() <= 1){
            return "admin/adminListError";
        }
        User user = userRepository.findFirstById(id);
        user.getRoles().remove(role);
        userRepository.save(user);
        return "redirect:/admin/list";
    }

    @RequestMapping(path="/edit/{id}", method = RequestMethod.GET)
    public String editAdmin(Model model, @PathVariable Long id){
        User admin = userRepository.findFirstById(id);
        model.addAttribute("user", admin);
        return "editUserForm";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
    public String saveEditAdmin(@ModelAttribute() User user, @PathVariable Long id){
        User adminToSave = userRepository.findFirstById(id);
        adminToSave.setUserName(user.getEmail());
        adminToSave.setFirstName(user.getFirstName());
        adminToSave.setLastName(user.getLastName());
        adminToSave.setEmail(user.getEmail());

        userRepository.save(adminToSave);
        return "redirect:/admin/list";
    }
}

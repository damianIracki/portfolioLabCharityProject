package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

@Controller
@RequestMapping("/user")
@Secured("ROLE_USER")
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("")
    public String userMainPage(@AuthenticationPrincipal UserDetails customUser, Model model){
        model.addAttribute("customUser", customUser);
        User user = userRepository.findByUserName(customUser.getUsername());
        model.addAttribute("user", user);
        return "userMainPage";
    }

    @RequestMapping("/profile")
    public String showUserProfile(@AuthenticationPrincipal UserDetails customUser, Model model){
        model.addAttribute("customUser", customUser);
        User user = userRepository.findByUserName(customUser.getUsername());
        model.addAttribute("user", user);
        return "userProfile";
    }


}

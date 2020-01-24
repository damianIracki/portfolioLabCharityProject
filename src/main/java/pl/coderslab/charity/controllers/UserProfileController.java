package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.ChangePasswordDto;
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
        return "user/userMainPage";
    }

    @RequestMapping("/profile")
    public String showUserProfile(@AuthenticationPrincipal UserDetails customUser, Model model){
        model.addAttribute("customUser", customUser);
        User user = userRepository.findByUserName(customUser.getUsername());
        model.addAttribute("user", user);
        return "user/userProfile";
    }

    @RequestMapping("/settings")
    public String userSettings(){
        return "user/userSettings";
    }

    @RequestMapping(path="/editProfile", method = RequestMethod.GET)
    public String editProfile(@AuthenticationPrincipal UserDetails customUser, Model model){
        User user = userRepository.findByUserName(customUser.getUsername());
        model.addAttribute("user", user);
        return "user/editUserForm";
    }

    @RequestMapping(path = "/editProfile", method = RequestMethod.POST)
    public String saveEditUser(@ModelAttribute User user, @AuthenticationPrincipal UserDetails customUser){
        User userToSave = userRepository.findByUserName(customUser.getUsername());
        userToSave.setFirstName(user.getFirstName());
        userToSave.setLastName(user.getLastName());
        userToSave.setEmail(user.getEmail());
        userToSave.setUserName(user.getEmail());
        userRepository.save(userToSave);
        return "redirect:/user/profile";
    }

    @RequestMapping(path = "/changePassword" , method = RequestMethod.GET)
    public String changePassword(Model model){
        ChangePasswordDto changePasswordDto = new ChangePasswordDto();
        model.addAttribute("changePasswordDto", changePasswordDto);
        return "user/changePassword";
    }

    @RequestMapping(path = "/changePassword", method = RequestMethod.POST)
    public String saveChangedPassword(@AuthenticationPrincipal UserDetails customUser, @ModelAttribute ChangePasswordDto changePasswordDto){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUserName(customUser.getUsername());
        if(bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())){
            if(changePasswordDto.getConfirmPassword().equals(changePasswordDto.getNewPassword())){
                userRepository.updateUserPassword(bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword()), user.getUserName());
                return "user/successfullyChangedPassword";
            }
        }
        return "user/unsuccessfullyChangedPassword";
    }
}

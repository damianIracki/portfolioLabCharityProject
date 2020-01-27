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
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/user")
@Secured("ROLE_USER")
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonationRepository donationRepository;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userMainPage(@AuthenticationPrincipal UserDetails customUser, Model model){
        model.addAttribute("customUser", customUser);
        User user = userRepository.findByUserName(customUser.getUsername());
        model.addAttribute("user", user);
        return "user/userMainPage";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
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

    @RequestMapping(path = "/editProfile", method = RequestMethod.PUT)
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

    @RequestMapping(path = "/changePassword", method = RequestMethod.PUT)
    public String saveChangedPassword(@AuthenticationPrincipal UserDetails customUser, @ModelAttribute ChangePasswordDto changePasswordDto){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUserName(customUser.getUsername());
        if(bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())){
            if(changePasswordDto.getConfirmPassword().equals(changePasswordDto.getNewPassword())){
                userRepository.updateUserPassword(bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword()), user.getUserName());
                return "user/successfullyChangedPassword";
            }
        }
        return "unsuccessfullyChangedPassword";
    }

    @RequestMapping(path = "/myDonations", method = RequestMethod.GET)
    public String myDonationsList(Model model, @AuthenticationPrincipal UserDetails customUser){
        User user = userRepository.findByUserName(customUser.getUsername());
        List<Donation> donations = donationRepository.findAllByUserOrderByReceivedAscPickUpDateAscPickUpTimeAscCreateDateAsc(user);
        model.addAttribute("myDonations", donations);
        return "donation/myDonationsList";
    }

    @RequestMapping(path = "/donationDetails/{id}",method = RequestMethod.GET)
    public String donationDetail(Model model, @AuthenticationPrincipal UserDetails customUser, @PathVariable Long id){
        User user = userRepository.findByUserName(customUser.getUsername());
        Donation donation = donationRepository.getOne(id);
        if(donation.getUser().getId() != user.getId()){
            return "redirect:/403";
        }
        model.addAttribute("donation", donation);
        return "donation/donationDetails";
    }
}

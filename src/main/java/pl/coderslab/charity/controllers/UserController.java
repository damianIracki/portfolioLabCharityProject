package pl.coderslab.charity.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.UserDto;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.mailSander.EmailSender;
import pl.coderslab.charity.services.UserService;



@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private EmailSender emailSender;

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String userRegisterForm(Model model){
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getEmail());
        userService.saveUser(user);

        emailSender.sendEmail(userDto.getEmail(), "Pomyślna rejestracja", "Zarejestorwano ponyślnie!");

        return "redirect:/";
    }

}

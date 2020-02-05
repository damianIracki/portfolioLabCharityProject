package pl.coderslab.charity.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.UserDto;
import pl.coderslab.charity.entities.ConfirmationToken;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.mailSander.EmailSender;
import pl.coderslab.charity.repositories.ConfirmationTokenRepository;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.services.UserService;



@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;

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
        //Create user form userDTO
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getEmail());

        //Save user
        userService.saveUser(user);

        //Token
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        //Send email
        emailSender.sendEmail(userDto.getEmail(),
                "Pomyślna rejestracja",
                "Zarejestorwano pomyślnie! <br>Aby aktywować konto kliknij tutaj: " + "http://localhost:8080/confirmAccount/" + confirmationToken.getConfirmationToken());

        return "redirect:/";
    }

    @RequestMapping(path = "/confirmAccount/{confirmationToken}")
    public String confirmAccount(@PathVariable String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null){
            User user = userRepository.findByUserNameIgnoreCase(token.getUser().getEmail());
            user.setConfirmed(true);
            userRepository.save(user);
            return "messages/successfullyConfirmed";
        }
        return "messages/failedConfirmation";
    }
}

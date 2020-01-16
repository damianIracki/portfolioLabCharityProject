package pl.coderslab.charity.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.UserDto;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.RoleRepository;
import pl.coderslab.charity.services.UserService;


@Controller
public class UserController {

    @Autowired
    private  UserService userService;



    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String userRegisterForm(Model model){
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        userService.saveUser(user);
        return "index";
    }

    @GetMapping ("/createUser")
    @ResponseBody
    public String createUser(){
        User user = new User();
        user.setUsername("admin@gmail.com");
        user.setPassword("admin");
        userService.saveUser(user);
        return "create";

    }



}

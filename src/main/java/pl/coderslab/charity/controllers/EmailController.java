package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.mailSander.EmailSender;

import javax.naming.Context;

@Controller
public class EmailController {

    @Autowired
    private EmailSender emailSender;

    @RequestMapping(path = "/email")
    public String sendEmail(){
        emailSender.sendEmail("testDlaOdebrania@gmail.com", "Opis Testowy", "pomyślnie wysłano email");
        return "redirect:/";
    }
}

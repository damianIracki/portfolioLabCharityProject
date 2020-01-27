package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {


    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private DonationRepository donationRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeAction(Model model){
        Integer quantityOfBags = donationRepository.findSumQuantity();
        model.addAttribute("quantityOfBags", quantityOfBags);

        Integer quantityOfSupportedInstitutions = donationRepository.countOfInstitutions();
        model.addAttribute("quantityOfSupportedInstitutions", quantityOfSupportedInstitutions);

        List<Institution> institutions = institutionRepository.findAll();
        model.addAttribute("institutions", institutions);

        return "index";
    }


}

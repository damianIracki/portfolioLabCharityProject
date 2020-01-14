package pl.coderslab.charity.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @RequestMapping("/")
    public String homeAction(Model model){
        Integer quantityOfBags = donationRepository.findSumQuantity();
        model.addAttribute("quantityOfBags", quantityOfBags);

        List<Donation> donations = donationRepository.countOfInstitutions();
        Integer quantityOfSupportedInstitutions = donations.size();
        model.addAttribute("quantityOfSupportedInstitutions", quantityOfSupportedInstitutions);

        List<Institution> institutions = institutionRepository.findAll();
        model.addAttribute("institutions", institutions);

        return "index";
    }


}

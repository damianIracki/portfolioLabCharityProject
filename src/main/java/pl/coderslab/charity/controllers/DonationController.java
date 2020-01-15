package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.List;

@Controller
public class DonationController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private DonationRepository donationRepository;

    @RequestMapping(path = "/donation", method = RequestMethod.GET)
    public String addDonationForm(Model model){
        List<Category> categories = categoryRepository.findAllByOrderByIdAsc();
        model.addAttribute("categories", categories);

        model.addAttribute("donation", new Donation());

        List<Institution> institutions = institutionRepository.findAllByOrderByNameAsc();
        model.addAttribute("institutions", institutions);
        return "form";
    }

    @RequestMapping(path = "/donation", method = RequestMethod.POST)
    public String saveDonation(@ModelAttribute("donation") Donation donation){
        donationRepository.save(donation);
        return "form-confirmation";
    }

}

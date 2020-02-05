package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.entities.*;
import pl.coderslab.charity.repositories.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DonationController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonationStatusRepository donationStatusRepository;

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
    public String saveDonation(@ModelAttribute("donation") Donation donation, @AuthenticationPrincipal UserDetails customUser){
        if(customUser != null){
            User user = userRepository.findByUserNameIgnoreCase(customUser.getUsername());
            donation.setUser(user);
        }
        donation.setCreateDate(LocalDate.now());
        DonationStatus donationStatus = donationStatusRepository.findFirstByName("nieodebrane");
        donation.setDonationStatus(donationStatus);
        donationRepository.save(donation);
        return "form-confirmation";
    }

}

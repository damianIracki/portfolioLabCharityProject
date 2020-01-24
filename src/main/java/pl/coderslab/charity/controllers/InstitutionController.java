package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.List;

@Controller
@RequestMapping("/institution")
@Secured("ROLE_ADMIN")
public class InstitutionController {

    @Autowired
    private InstitutionRepository institutionRepository;

    @RequestMapping(path = "/")
    public String trustedInstitutionsList(Model model){
        List<Institution> institutions = institutionRepository.findAllByOrderByNameAsc();
        model.addAttribute("institutions", institutions);
        return "institution/trustedInstitutionsList";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addInstitution(Model model){
        Institution institution = new Institution();
        model.addAttribute("institution", institution);
        return "institution/institutionForm";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String saveInstitution(@ModelAttribute Institution institution){
        institutionRepository.save(institution);
        return "redirect:/institution/";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String editInstitution(@PathVariable("id") Long id, Model model){
        Institution institution = institutionRepository.findFirstById(id);
        model.addAttribute("institution", institution);
        return "institution/editInstitutionForm";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
    public String saveEditInstitution(@ModelAttribute Institution institution){
        institutionRepository.save(institution);
        return "redirect:/institution/";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteInstitution(@PathVariable Long id){
        Institution institution = institutionRepository.findFirstById(id);
        institutionRepository.delete(institution);
        return "redirect:/institution/";
    }
}

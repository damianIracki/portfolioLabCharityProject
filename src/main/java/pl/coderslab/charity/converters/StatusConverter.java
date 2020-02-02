package pl.coderslab.charity.converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.entities.DonationStatus;
import pl.coderslab.charity.repositories.DonationStatusRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

public class StatusConverter implements Converter<String, DonationStatus> {

    @Autowired
    DonationStatusRepository donationStatusRepository;

    @Override
    public DonationStatus convert(String s){
        return donationStatusRepository.findFirstByName(s);
    }
}

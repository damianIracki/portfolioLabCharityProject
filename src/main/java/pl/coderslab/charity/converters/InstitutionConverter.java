package pl.coderslab.charity.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;




public class InstitutionConverter implements Converter<String, Institution> {

    @Autowired
    InstitutionRepository institutionRepository;

    @Override
    public Institution convert(String s) {
        return institutionRepository.findFirstByName(s);
    }
}

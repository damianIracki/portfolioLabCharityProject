package pl.coderslab.charity.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.repositories.CategoryRepository;

public class CategoryConverter implements Converter<Long, Category> {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category convert(Long id){
        return categoryRepository.findFirstById(id);
    }
}

package pl.coderslab.charity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.charity.converters.CategoryConverter;
import pl.coderslab.charity.converters.InstitutionConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getInstitutionConverter());
        registry.addConverter(getCategoryConverter());
    }

    @Bean
    public InstitutionConverter getInstitutionConverter(){
        return new InstitutionConverter();
    }


    @Bean
    public CategoryConverter getCategoryConverter(){
        return new CategoryConverter();
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("403");
    }
}

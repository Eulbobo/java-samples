package fr.norsys.springexample.complex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.norsys.springexample.complex.elements.repository.AnotherSimpleRepository;
import fr.norsys.springexample.complex.elements.repository.SimpleRepository;
import fr.norsys.springexample.domain.BeanSimpleRepositoryInterface;

@Configuration
public class ApplicationConfiguration {

    // Ici, nous allons configurer plusieurs implémentation de notre repository

    /**
     * Le nom de la méthode permet de référencer le bean
     * Ici, nous configurons le bean aSimpleImplementation
     */
    @Bean
    public BeanSimpleRepositoryInterface aSimpleImplementation(){
        return new SimpleRepository(1);
    }

    /**
     * Le nom de la méthode permet de référencer le bean
     * Ici, nous configurons le bean anotherSimpleImplementation
     */
    @Bean
    public BeanSimpleRepositoryInterface anotherSimpleImplementation(){
        return new SimpleRepository(2);
    }

    /**
     * Le nom de la méthode permet de référencer le bean
     * Ici, nous configurons le bean method_name_is_the_key_to_identify_bean
     */
    @Bean
    public BeanSimpleRepositoryInterface method_name_is_the_key_to_identify_bean(){
        return new AnotherSimpleRepository();
    }
}

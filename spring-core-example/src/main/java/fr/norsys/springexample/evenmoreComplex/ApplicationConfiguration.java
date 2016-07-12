package fr.norsys.springexample.evenmoreComplex;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "fr.norsys.springexample.evenmoreComplex")
public class ApplicationConfiguration {

    /**
     * Et on se rend compte que �a ne devient pas plus compliqu� parce qu'il y a plus d'�l�ments !
     *
     * On a d�fini un componentScan au niveau de la configuration g�n�rale : tous les beans dans le package indiqu� sont
     * automatiquement charg�s si on utilise cette objet de configuration
     */

}

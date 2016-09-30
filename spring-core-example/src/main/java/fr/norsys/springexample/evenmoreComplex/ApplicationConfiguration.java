package fr.norsys.springexample.evenmoreComplex;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "fr.norsys.springexample.evenmoreComplex")
public class ApplicationConfiguration {

    /**
     * Et on se rend compte que ça ne devient pas plus compliqué parce qu'il y a plus d'éléments !
     *
     * On a défini un componentScan au niveau de la configuration générale : tous les beans dans le package indiqué sont
     * automatiquement chargés si on utilise cette objet de configuration
     */

}

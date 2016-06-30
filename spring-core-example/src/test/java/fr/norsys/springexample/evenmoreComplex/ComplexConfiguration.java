package fr.norsys.springexample.evenmoreComplex;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * On remarque que dans la configuration du package, on ne mappe que les éléments dans
 * fr.norsys.springexample.evenmoreComplex.elements
 *
 * Toutes les classes/services et repository au dessus ne sont pas mappés et donc pas visibles
 * C'est un bon moyen de tester l'adhérence interpackages
 */
@Configuration
@ComponentScan(basePackages = "fr.norsys.springexample.evenmoreComplex.elements")
public class ComplexConfiguration {

    /**
     * pas si complexe que ça n'est-ce pas?
     *
     * En fait, c'est ici qu'on défini les éléments variables dans les tests
     * Par exemple : définir une datasource différente
     *
     * Le twist étant qu'on peut simplement le faire par configuration dans le répertoire test/resources
     * Donc au final avec une configuration pour les test qui est la même que pour l'exécution normale
     */

}

package fr.norsys.springexample.evenmoreComplex;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * On remarque que dans la configuration du package, on ne mappe que les �l�ments dans
 * fr.norsys.springexample.evenmoreComplex.elements
 *
 * Toutes les classes/services et repository au dessus ne sont pas mapp�s et donc pas visibles
 * C'est un bon moyen de tester l'adh�rence interpackages
 */
@Configuration
@ComponentScan(basePackages = "fr.norsys.springexample.evenmoreComplex.elements")
public class ComplexConfiguration {

    /**
     * pas si complexe que �a n'est-ce pas?
     *
     * En fait, c'est ici qu'on d�fini les �l�ments variables dans les tests
     * Par exemple : d�finir une datasource diff�rente
     *
     * Le twist �tant qu'on peut simplement le faire par configuration dans le r�pertoire test/resources
     * Donc au final avec une configuration pour les test qui est la m�me que pour l'ex�cution normale
     */

}

Pr�sentation d'exemples d'utilisation de Spring Core 
===================
Ce projet permet d'illustrer l'utilisation de quelques fonctionnalit�s basiques de Spring, la principale fonctionnalit� utilis�e dans cet exemple �tant l'injection de d�pendances

Comme nous utilisons une version r�cente de Spring, toute la configuration est faite en Java, dans des classes de configuration ou par annotations.

Contenu du projet
-------------------
Le projet est lui-m�me d�coup� en plusieurs modules distincts
> - domain : ce package contient des beans et interfaces utilis�s par tous les autres exemples (un bean, une interface de service, une interface de repository)
> - properties : d�monstration de l'utilisation de properties depuis un fichier externe
> - simple : un service qui utilise un repository, avec autowiring, une classe de configuration, et une classe de lancement (SimpleApplicationExample)
> - complex : trois impl�mentations diff�rentes de l'interface repository et comment les r�cup�rer (autowiring par type, par nom, ...)
> - bytype : d�monstration de l'autowire par type g�n�rique (dans le cas d'une interface typ�e)
> - evenmoreComplex : des services et des repositories avec diff�rents branchements

Utilisation
-------------------
Chaque module du projet dispose d'un fichier XXXApplicationExample qui est un ex�cutable permettant de charger et r�cup�rer les �l�ments Spring. Des tests unitaires sont aussi disponible pour chacune des classes.
Pour chaque module, on trouve aussi un fichier ApplicationConfiguration sp�cifique pour configurer les beans et/ou le package � scanner pour trouver les annotations.

Les tests sont compos�s de tests unitaires et de tests d'int�gration.
Pour lancer les tests unitaires, il faut lancer la commande ````mvn test```. 
Pour les tests d'int�gration, il faut lancer la commande ```mvn verify```.

Les tests d'int�gration ne fonctionnent pas tous : un commentaire explique pourquoi (� vous de corriger !)

Liens
-------------------
> - [Documentation de r�f�rence Spring Core](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/index.html)
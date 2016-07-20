Présentation d'exemples d'utilisation de Spring Core 
===================
Ce projet permet d'illustrer l'utilisation de quelques fonctionnalités basiques de Spring, la principale fonctionnalité utilisée dans cet exemple étant l'injection de dépendances

Comme nous utilisons une version récente de Spring, toute la configuration est faite en Java, dans des classes de configuration ou par annotations.

Contenu du projet
-------------------
Le projet est lui-même découpé en plusieurs modules distincts
> - domain : ce package contient des beans et interfaces utilisés par tous les autres exemples (un bean, une interface de service, une interface de repository)
> - properties : démonstration de l'utilisation de properties depuis un fichier externe
> - simple : un service qui utilise un repository, avec autowiring, une classe de configuration, et une classe de lancement (SimpleApplicationExample)
> - complex : trois implémentations différentes de l'interface repository et comment les récupérer (autowiring par type, par nom, ...)
> - bytype : démonstration de l'autowire par type générique (dans le cas d'une interface typée)
> - evenmoreComplex : des services et des repositories avec différents branchements

Utilisation
-------------------
Chaque module du projet dispose d'un fichier XXXApplicationExample qui est un exécutable permettant de charger et récupérer les éléments Spring. Des tests unitaires sont aussi disponible pour chacune des classes.
Pour chaque module, on trouve aussi un fichier ApplicationConfiguration spécifique pour configurer les beans et/ou le package à scanner pour trouver les annotations.

Les tests sont composés de tests unitaires et de tests d'intégration.
Pour lancer les tests unitaires, il faut lancer la commande ````mvn test```. 
Pour les tests d'intégration, il faut lancer la commande ```mvn verify```.

Les tests d'intégration ne fonctionnent pas tous : un commentaire explique pourquoi (à vous de corriger !)

Liens
-------------------
> - [Documentation de référence Spring Core](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/index.html)
Présentation des exemples Mockito
===================
Mockito est un framework permettant de simplement créer des Mock facilement, simples à lire, simples à mettre en place, simples à maintenir.


Contenu du projet
-------------------
Le projet contient le squelette d'une application avec 
- un package ``domain`` qui contient un bean de domaine, une interface repository, une énumération et une exception
- un package ``service`` qui utilise le repository

Aucune implémentation de l'interface BeanDeDomainRepository n'est fournie.
Il faut tout de même pouvoir tester que le service ``ServiceUtilisantUneInterface`` se comporte comme prévu.

Utilisation
-------------------
Tout se passe dans les tests, et plus particulièrement la classe ``ServiceUtilisantUneInterfaceTest``.

Pour chaque test, on initialise la classe ServiceUtilisantUneInterface avec un mock du repository utilisé. 
Reste alors à configurer le mock pour définir son comportement pour chaque test.


A noter qu'il reste des tests à faire pour atteindre la totalité de la couverture du code, ainsi que des différents cas fonctionnel.
Sans l'ajout de plus de tests pour valider le comportement, il pourrait y avoir des bugs (et il y en a, volontairement)

Liens
-------------------
> - [Home page projet Mockito](http://mockito.org/)
> - [Mockito sur GitHub](https://github.com/mockito/mockito)
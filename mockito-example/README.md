Pr�sentation des exemples Mockito
===================
Mockito est un framework permettant de simplement cr�er des Mock facilement, simples � lire, simples � mettre en place, simples � maintenir.


Contenu du projet
-------------------
Le projet contient le squelette d'une application avec 
- un package ``domain`` qui contient un bean de domaine, une interface repository, une �num�ration et une exception
- un package ``service`` qui utilise le repository

Aucune impl�mentation de l'interface BeanDeDomainRepository n'est fournie.
Il faut tout de m�me pouvoir tester que le service ``ServiceUtilisantUneInterface`` se comporte comme pr�vu.

Utilisation
-------------------
Tout se passe dans les tests, et plus particuli�rement la classe ``ServiceUtilisantUneInterfaceTest``.

Pour chaque test, on initialise la classe ServiceUtilisantUneInterface avec un mock du repository utilis�. 
Reste alors � configurer le mock pour d�finir son comportement pour chaque test.


A noter qu'il reste des tests � faire pour atteindre la totalit� de la couverture du code, ainsi que des diff�rents cas fonctionnel.
Sans l'ajout de plus de tests pour valider le comportement, il pourrait y avoir des bugs (et il y en a, volontairement)

Liens
-------------------
> - [Home page projet Mockito](http://mockito.org/)
> - [Mockito sur GitHub](https://github.com/mockito/mockito)
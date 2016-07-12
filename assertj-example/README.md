Pr�sentation des exemples d'utilisation d'assertJ
===================
Ce projet contient diff�rents exemples d'utilisation basiques d'assertJ

Contenu du projet
-------------------
Le projet contient dans ses sources plusieurs classes � tester.  
A chaque classe correspond un test (sauf pour les exceptions qui ne sont pas test�es) situ�e dans le m�me package que la classe test�e.
La classe de test poss�de le m�me nom que la classe test�e et poss�de le suffixe `Test`

Chaque classe contient des commentaires explicatif du fonctionnement des diff�rents �l�ments.


Les tests sont �crit au format AAA
- Arrange : pr�paration des donn�es
- Act : lancement d'une action
- Assert : test du r�sultat attendu


Vous noterez qu'il n'y a pas de logger dans les tests : chaque test �tant ind�pendant, on n'a pas besoin de mettre des logs.
De plus, en cas d'erreur, les logs diront pr�cisement ce qui ne marche pas.

Utilisation
-------------------
Afin de pouvoir lancer les tests, vous avez deux possibilit�
- Mettez vous en ligne de commande dans le r�pertoire au niveau du fichier pom.xml, et lancez la commande `mvn test`
- Dans Eclipse, clic droit sur le package src/test/java -> Run as -> JUnit test

Il ne doit pas y avoir d'erreur dans les tests.

Vous pouvez alors apporter des modifications dans les classes à tester pour voir le comportement des tests quand on les lance.

Liens
-------------------
- [Projet AssertJ](http://joel-costigliola.github.io/assertj/)
- [Sources Github du projet](https://github.com/joel-costigliola/assertj-core)
- [Exemples d'utilisation dans le projet Github](https://github.com/joel-costigliola/assertj-examples) : les exemples sont pour les versions supportant java7 ou java8, donc pas totalement compatible avec la version java6
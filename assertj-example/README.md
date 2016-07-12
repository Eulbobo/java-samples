Présentation des exemples d'utilisation d'assertJ
===================
Ce projet contient différents exemples d'utilisation basiques d'assertJ

Contenu du projet
-------------------
Le projet contient dans ses sources plusieurs classes à tester.  
A chaque classe correspond un test (sauf pour les exceptions qui ne sont pas testées) située dans le même package que la classe testée.
La classe de test possède le même nom que la classe testée et possède le suffixe `Test`

Chaque classe contient des commentaires explicatif du fonctionnement des différents éléments.


Les tests sont écrit au format AAA
- Arrange : préparation des données
- Act : lancement d'une action
- Assert : test du résultat attendu


Vous noterez qu'il n'y a pas de logger dans les tests : chaque test étant indépendant, on n'a pas besoin de mettre des logs.
De plus, en cas d'erreur, les logs diront précisement ce qui ne marche pas.

Utilisation
-------------------
Afin de pouvoir lancer les tests, vous avez deux possibilité
- Mettez vous en ligne de commande dans le répertoire au niveau du fichier pom.xml, et lancez la commande `mvn test`
- Dans Eclipse, clic droit sur le package src/test/java -> Run as -> JUnit test

Il ne doit pas y avoir d'erreur dans les tests.

Vous pouvez alors apporter des modifications dans les classes Ã  tester pour voir le comportement des tests quand on les lance.

Liens
-------------------
- [Projet AssertJ](http://joel-costigliola.github.io/assertj/)
- [Sources Github du projet](https://github.com/joel-costigliola/assertj-core)
- [Exemples d'utilisation dans le projet Github](https://github.com/joel-costigliola/assertj-examples) : les exemples sont pour les versions supportant java7 ou java8, donc pas totalement compatible avec la version java6
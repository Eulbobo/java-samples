Présentation des exemples 
===================
Présentation
-------------------
Ce projet contient différents exemples d'utilisation basiques d'open-pojo


Contenu du projet
-------------------
Le projet contient dans ses sources plusieurs "beans" à tester. 

Dans les tests, on a une seule classe qui va permettre de tester tous les beans d'un coup.

Utilisation
-------------------
Afin de pouvoir lancer les tests, vous avez deux possibilité
- Mettez vous en ligne de commande dans le répertoire au niveau du fichier pom.xml, et lancez la commande `mvn test`
- Dans Eclipse, clic droit sur le package src/test/java -> Run as -> JUnit test

Il ne doit pas y avoir d'erreur dans les tests.


Vous pouvez alors apporter des modifications dans les classes à tester pour voir le comportement des tests quand on les lance.

Certaines classe possèdent du code commenté qui changent le contrat des beans : si vous le décommentez, les tests ne passeront plus.

Liens
-------------------
[Projet OpenPojo](http://openpojo.com)  
[Sources Github du projet](https://github.com/oshoukry/openpojo)
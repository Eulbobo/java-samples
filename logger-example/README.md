Présentation des exemples de logger
===================
Présentation
-------------------
Ce projet contient différents exemples de logger de différentes implémentations.

Les trois exemples d'implémentation choisis sont :
- java.util.logging : API de journalisation dans le coeur java depuis la version 1.4
- log4j : API de journalisation très utilisée pour sa légèreté et facilité d'utilisation
- [slf4j](https://git.norsys.fr/jboniol/documentation-generale/wikis/SLF4J) : framework d'abstration de journalisation

Contenu du projet
-------------------
Ce projet prossède plusieurs classes d'exemple d'utilisation des différents frameworks
> - PresentationDifferentLoggers : classe présentant l'utilisation des différents loggers
> - PresentationSpecificitesSlf4J : classe présentant les spécificités de slf4j

Et aussi des exemples d'utilisation
> - ServiceAvecBeaucoupTropDeLog : un service avec beaucoup trop de logs (pollution d'information)
> - ServiceAvecDesLogs : un service correctement loggé
> - ServiceAvecPasAssezDeLog : un service incorrectement loggé

La configuration des loggers se trouve dans le répertoire src/test/resources
- log4j.properties : fichier de configuration de log4j pour les tests
- logging.properties : fichier de configuration de java.logging pour les tests

Chaque classe contient des commentaires explicatif du fonctionnement des différents éléments

Le projet contient aussi des classes de test permettant d'observer le rendu des différents loggers selon leur utilisation

Les tests ont été réalisés avec JUnit et assertJ.

Utilisation
-------------------
Afin de pouvoir lancer les tests, vous avez deux possibilité
- Mettez vous en ligne de commande dans le répertoire au niveau du fichier pom.xml, et lancez la commande `mvn test`
- Dans Eclipse, clic droit sur le package src/test/java -> Run as -> JUnit test

Liens
-------------------
- [Les loggers par JM-Doudoux](http://www.jmdoudoux.fr/java/dej/chap-logging.htm)
- [java.logging par cyberzoide](http://cyberzoide.developpez.com/tutoriels/java/logging/)
- [Log4J](http://logging.apache.org/log4j/2.x/)
- [slf4j](http://www.slf4j.org/)
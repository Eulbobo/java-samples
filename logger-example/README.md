Pr�sentation des exemples de logger
===================
Pr�sentation
-------------------
Ce projet contient diff�rents exemples de logger de diff�rentes impl�mentations.

Les trois exemples d'impl�mentation choisis sont :
- java.util.logging : API de journalisation dans le coeur java depuis la version 1.4
- log4j : API de journalisation tr�s utilis�e pour sa l�g�ret� et facilit� d'utilisation
- [slf4j](https://git.norsys.fr/jboniol/documentation-generale/wikis/SLF4J) : framework d'abstration de journalisation

Contenu du projet
-------------------
Ce projet pross�de plusieurs classes d'exemple d'utilisation des diff�rents frameworks
> - PresentationDifferentLoggers : classe pr�sentant l'utilisation des diff�rents loggers
> - PresentationSpecificitesSlf4J : classe pr�sentant les sp�cificit�s de slf4j

Et aussi des exemples d'utilisation
> - ServiceAvecBeaucoupTropDeLog : un service avec beaucoup trop de logs (pollution d'information)
> - ServiceAvecDesLogs : un service correctement logg�
> - ServiceAvecPasAssezDeLog : un service incorrectement logg�

La configuration des loggers se trouve dans le r�pertoire src/test/resources
- log4j.properties : fichier de configuration de log4j pour les tests
- logging.properties : fichier de configuration de java.logging pour les tests

Chaque classe contient des commentaires explicatif du fonctionnement des diff�rents �l�ments

Le projet contient aussi des classes de test permettant d'observer le rendu des diff�rents loggers selon leur utilisation

Les tests ont �t� r�alis�s avec JUnit et assertJ.

Utilisation
-------------------
Afin de pouvoir lancer les tests, vous avez deux possibilit�
- Mettez vous en ligne de commande dans le r�pertoire au niveau du fichier pom.xml, et lancez la commande `mvn test`
- Dans Eclipse, clic droit sur le package src/test/java -> Run as -> JUnit test

Liens
-------------------
- [Les loggers par JM-Doudoux](http://www.jmdoudoux.fr/java/dej/chap-logging.htm)
- [java.logging par cyberzoide](http://cyberzoide.developpez.com/tutoriels/java/logging/)
- [Log4J](http://logging.apache.org/log4j/2.x/)
- [slf4j](http://www.slf4j.org/)
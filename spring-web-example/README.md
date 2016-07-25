Pr�sentation d'exemples d'utilisation de spring-web
===================
Ce projet permet d'illustrer l'utilisation de spring-web, un ensemble d'outils permettant de faciliter la cr�ation de servlets/filtres tout en les int�grant dans un contexte Spring.

Contenu du projet
-------------------
Le projet contient de nombreux exemples de configuration �parpill�s dans les diff�rents fichiers afin de monter l'ensemble des possibilit� plut�t que de faire autant de projets qu'il existe de configurations possibles (ce qui aurait �t� plus lisible cas par cas, mais qui aurait n�cessit� la cr�ation d'au moins quatre projets distincts).


Ce projet contient quatre classes servant de contr�leur, deux filtres, et deux classes de configuration. 
Les classes et objets Spring sont initialis�es automatiquement au d�marrage gr�ce � la pr�sence d'une classe impl�mentant `WebApplicationInitializer`.
Les autres �l�ments utilisant les annotations `@WebFilter` et `@WebServlet` sont d�tect�es automatiquement par le conteneur lors du chargement de l'application.

Chaque contr�leur r�cup�re un message de l'une des impl�mentation de l'interface de service `ISpeakService`.


### Configuration
#### ApplicationConfiguration
- Classe de configuration Spring d�clar�e par annotation et permettant le lancement du scan de package

#### WebAppInit
- Classe qui impl�mente `WebApplicationInitializer`
- Permet l'initialisation compl�te du contexte Spring
- D�finit les mappings pour les filtres et les servlets, ainsi que les beans correspondants

### Filtres
#### MyFilter
- Classe qui �tend `javax.servlet.Filter`
- D�finie dans le contexte Spring via l'annotation `@Component`
- Configur�e par d�l�gation dans la classe `WebAppInit` via un `DelegatingFilterProxy`
- Appel�e pour toutes les requ�tes
- Affiche un message avant et apr�s l'ex�cution de chaque requ�te

#### MyOtherFilter
- Classe qui �tend `javax.servlet.Filter`
- Configur�e par annotation web `@WebFilter`. 
- Non visible dans Spring.
- Appel�e pour toutes les requ�tes
- Affiche un message avant l'ex�cution de chaque requ�te

### Servlets
#### HelloServlet
- Classe qui �tend `javax.servlet.HttpServlet`
- Configur�e par annotation web (`@WebServlet` non spring) pour pr�ciser son mapping `/hello`
- Initialis�e dans Spring via sa m�thode init

#### GoodbyeServlet
- Classe qui impl�mente `HttpRequestHandler` (ce n'est donc pas une Servlet techniquement parlant)
- D�finie dans le contexte Spring via l'annotation `@Controller`
- Configur�e dans l'application web dans la classe WebAppInit qui d�finit une servlet de type `HttpRequestHandlerServlet` d�l�guant le traitement de la requ�te `/goodBye` � la classe `GoodbyeServlet`

#### StillAliveServlet
- Classe qui �tend `javax.servlet.HttpServlet`
- D�finie dans le contexte Spring via l'annotation `@Controller`
- Configur�e dans l'application web dans la classe WebAppInit qui d�finit une servlet de type `HttpServletHandler` d�l�guant le traitement de la requ�te `/stillAlive` � la servlet `StillAliveServlet`

#### AnotherServlet
- Classe �tendant `javax.servlet.HttpServlet`
- D�finie dans le contexte Spring via l'annotation `@Component` (parce que �a marche aussi, m�me s'il faudrait mieux le d�clarer en tant que `@Controller` 
- Configur�e dans l'application web dans la classe `AnotherServletMapping`, classe qui �tend `HttpServletHandler` (c'est donc une servlet) et qui poss�de une configuration par annotation `WebServlet` avec un mapping vers `/another` et qui porte comme nom `anotherServlet`. C'est ce nom qui permet de faire le lien vers la classe `AnotherServlet`.

Cette derni�re configuration est l� uniquement pour montrer qu'il est possible d'�tendre `HttpServletHandler` pour d�finir la configuration. Cependant, cel� fait cr�er une classe de plus qui ne porte pas de sens suppl�mentaire que celui d'indiquer la configuration � utiliser, ce qui est d�j� le r�le de la classe 


Utilisation
-------------------
Cet exemple est un projet web sans test unitaires (ce qui n'est pas bien, mais dans notre cas, il n'y a pas grand chose � tester � part la configuration).

Pour fonctionner, il faudra un conteneur web quelconque qui supporte la configuration par annotation (servlet-api 3) comme [tomcat 7](https://tomcat.apache.org/download-70.cgi). La liste compl�te pour le support est disponible sur [cette page](http://tomcat.apache.org/whichversion.html).


Une fois l'application d�ploy�e sur votre conteneur, les exemples sont disponibles directement depuis l'index : http://localhost:8080/spring-web-example/ 


Cette url appelle la jsp d'index permettant d'avoir acc�s aux 4 servlets d�clar�es avec le mapping suivant :
- Hello -> /hello : HelloServlet
- still Alive -> /stillAlive : StillAliveServlet
- goodBye -> /goodBye : GoodbyeServlet
- another one -> /another : AnotherServlet 


Liens
-------------------

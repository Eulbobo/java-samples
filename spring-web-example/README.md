Présentation d'exemples d'utilisation de spring-web
===================
Ce projet permet d'illustrer l'utilisation de spring-web, un ensemble d'outils permettant de faciliter la création de servlets/filtres tout en les intégrant dans un contexte Spring.

Contenu du projet
-------------------
Le projet contient de nombreux exemples de configuration éparpillés dans les différents fichiers afin de monter l'ensemble des possibilité plutôt que de faire autant de projets qu'il existe de configurations possibles (ce qui aurait été plus lisible cas par cas, mais qui aurait nécessité la création d'au moins quatre projets distincts).


Ce projet contient quatre classes servant de contrôleur, deux filtres, et deux classes de configuration. 
Les classes et objets Spring sont initialisées automatiquement au démarrage grâce à la présence d'une classe implémentant `WebApplicationInitializer`.
Les autres éléments utilisant les annotations `@WebFilter` et `@WebServlet` sont détectées automatiquement par le conteneur lors du chargement de l'application.

Chaque contrôleur récupère un message de l'une des implémentation de l'interface de service `ISpeakService`.


### Configuration
#### ApplicationConfiguration
- Classe de configuration Spring déclarée par annotation et permettant le lancement du scan de package

#### WebAppInit
- Classe qui implémente `WebApplicationInitializer`
- Permet l'initialisation complète du contexte Spring
- Définit les mappings pour les filtres et les servlets, ainsi que les beans correspondants

### Filtres
#### MyFilter
- Classe qui étend `javax.servlet.Filter`
- Définie dans le contexte Spring via l'annotation `@Component`
- Configurée par délégation dans la classe `WebAppInit` via un `DelegatingFilterProxy`
- Appelée pour toutes les requêtes
- Affiche un message avant et après l'exécution de chaque requête

#### MyOtherFilter
- Classe qui étend `javax.servlet.Filter`
- Configurée par annotation web `@WebFilter`. 
- Non visible dans Spring.
- Appelée pour toutes les requêtes
- Affiche un message avant l'exécution de chaque requête

### Servlets
#### HelloServlet
- Classe qui étend `javax.servlet.HttpServlet`
- Configurée par annotation web (`@WebServlet` non spring) pour préciser son mapping `/hello`
- Initialisée dans Spring via sa méthode init

#### GoodbyeServlet
- Classe qui implémente `HttpRequestHandler` (ce n'est donc pas une Servlet techniquement parlant)
- Définie dans le contexte Spring via l'annotation `@Controller`
- Configurée dans l'application web dans la classe WebAppInit qui définit une servlet de type `HttpRequestHandlerServlet` déléguant le traitement de la requête `/goodBye` à la classe `GoodbyeServlet`

#### StillAliveServlet
- Classe qui étend `javax.servlet.HttpServlet`
- Définie dans le contexte Spring via l'annotation `@Controller`
- Configurée dans l'application web dans la classe WebAppInit qui définit une servlet de type `HttpServletHandler` déléguant le traitement de la requête `/stillAlive` à la servlet `StillAliveServlet`

#### AnotherServlet
- Classe étendant `javax.servlet.HttpServlet`
- Définie dans le contexte Spring via l'annotation `@Component` (parce que ça marche aussi, même s'il faudrait mieux le déclarer en tant que `@Controller` 
- Configurée dans l'application web dans la classe `AnotherServletMapping`, classe qui étend `HttpServletHandler` (c'est donc une servlet) et qui possède une configuration par annotation `WebServlet` avec un mapping vers `/another` et qui porte comme nom `anotherServlet`. C'est ce nom qui permet de faire le lien vers la classe `AnotherServlet`.

Cette dernière configuration est là uniquement pour montrer qu'il est possible d'étendre `HttpServletHandler` pour définir la configuration. Cependant, celà fait créer une classe de plus qui ne porte pas de sens supplémentaire que celui d'indiquer la configuration à utiliser, ce qui est déjà le rôle de la classe 


Utilisation
-------------------
Cet exemple est un projet web sans test unitaires (ce qui n'est pas bien, mais dans notre cas, il n'y a pas grand chose à tester à part la configuration).

Pour fonctionner, il faudra un conteneur web quelconque qui supporte la configuration par annotation (servlet-api 3) comme [tomcat 7](https://tomcat.apache.org/download-70.cgi). La liste complète pour le support est disponible sur [cette page](http://tomcat.apache.org/whichversion.html).


Une fois l'application déployée sur votre conteneur, les exemples sont disponibles directement depuis l'index : http://localhost:8080/spring-web-example/ 


Cette url appelle la jsp d'index permettant d'avoir accès aux 4 servlets déclarées avec le mapping suivant :
- Hello -> /hello : HelloServlet
- still Alive -> /stillAlive : StillAliveServlet
- goodBye -> /goodBye : GoodbyeServlet
- another one -> /another : AnotherServlet 


Liens
-------------------

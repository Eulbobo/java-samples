Présentation d'exemples d'utilisation de spring-jdbc
===================
Ce projet permet d'illustrer l'utilisation de spring-jdbc, une surcouche de JDBC qui simplifie l'écriture de requêtes et la récupération de données.


Contenu du projet
-------------------
Le projet contient un bean générique appelé User dans le package `beans`, et dans le package `operations` se trouvent les exemples des fonctionnalités principales de l'API spring-jdbc.

> - jdbctemplate : package contenant la présentation de l'outil de requête principal
> - mapper : package contenant des exemples sur la façon de mapper des objets et de récupérer des résultats depuis un objet ResultSet
> - namedParameter : extension de jdbcTemplate qui permet d'utiliser des paramètres nommés
> - jdbcInsert : package expliquant l'utilisation de l'insertion de données avec spring-jdbc 

Utilisation
-------------------
Le projet dispose d'une classe Application qui ne sert qu'à prouver le bon fonctionnement de la connexion à la base de données embarquée H2. Tout le fonctionnement se situe dans les tests.


Ordre de lecture conseillé

- jdbctemplate > query : méthodes pour lire des données en base (select)
- jdbctemplate > modify : méthodes pour mettre à jour des données (update/delete)
- jdbctemplate > batch : méthodes pour faire des traitements de masse (insert/update/delete)
- namedParameter : utilisation de namedParameterJdbcTemplate pour réaliser n'importe quelle opération
- jdbcInsert : insertion de données

Les tests sont composés de tests unitaires et de tests d'intégration.
Pour lancer les tests unitaires, il faut lancer la commande ````mvn test```. 
Pour les tests d'intégration, il faut lancer la commande ```mvn verify```.

Autre éléments
-------------------
Ce projet ne couvre pas l'appel à des procédures stockées via la classe org.springframework.jdbc.core.simple.SimpleJdbcCall, mais des exemples existent dans la [documentation spring jdbc](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html#jdbc-simple-jdbc-call-1).

Certaines classes permettent de simplifier d'autres actions comme le `MappingSqlQuery` qui permet de définir une classe avec une requête et un mapping (récupération d'éléments par identifiants), le `SqlUpdate` qui permet une mise à jour simplifiée d'une entité donnée.

Liens
-------------------
> - [Documentation générale Spring-jdbc](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html)
> - [Tuto spring jdbc](https://spring.io/guides/gs/relational-data-access/)
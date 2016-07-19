Pr�sentation d'exemples d'utilisation de spring-jdbc
===================
Ce projet permet d'illustrer l'utilisation de spring-jdbc, une surcouche de JDBC qui simplifie l'�criture de requ�tes et la r�cup�ration de donn�es.


Contenu du projet
-------------------
Le projet contient un bean g�n�rique appel� User dans le package `beans`, et dans le package `operations` se trouvent les exemples des fonctionnalit�s principales de l'API spring-jdbc.

> - jdbctemplate : package contenant la pr�sentation de l'outil de requ�te principal
> - mapper : package contenant des exemples sur la fa�on de mapper des objets et de r�cup�rer des r�sultats depuis un objet ResultSet
> - namedParameter : extension de jdbcTemplate qui permet d'utiliser des param�tres nomm�s
> - jdbcInsert : package expliquant l'utilisation de l'insertion de donn�es avec spring-jdbc 

Utilisation
-------------------
Le projet dispose d'une classe Application qui ne sert qu'� prouver le bon fonctionnement de la connexion � la base de donn�es embarqu�e H2. Tout le fonctionnement se situe dans les tests.


Ordre de lecture conseill�

- jdbctemplate > query : m�thodes pour lire des donn�es en base (select)
- jdbctemplate > modify : m�thodes pour mettre � jour des donn�es (update/delete)
- jdbctemplate > batch : m�thodes pour faire des traitements de masse (insert/update/delete)
- namedParameter : utilisation de namedParameterJdbcTemplate pour r�aliser n'importe quelle op�ration
- jdbcInsert : insertion de donn�es

Les tests sont compos�s de tests unitaires et de tests d'int�gration.
Pour lancer les tests unitaires, il faut lancer la commande ````mvn test```. 
Pour les tests d'int�gration, il faut lancer la commande ```mvn verify```.

Autre �l�ments
-------------------
Ce projet ne couvre pas l'appel � des proc�dures stock�es via la classe org.springframework.jdbc.core.simple.SimpleJdbcCall, mais des exemples existent dans la [documentation spring jdbc](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html#jdbc-simple-jdbc-call-1).

Certaines classes permettent de simplifier d'autres actions comme le `MappingSqlQuery` qui permet de d�finir une classe avec une requ�te et un mapping (r�cup�ration d'�l�ments par identifiants), le `SqlUpdate` qui permet une mise � jour simplifi�e d'une entit� donn�e.

Liens
-------------------
> - [Documentation g�n�rale Spring-jdbc](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html)
> - [Tuto spring jdbc](https://spring.io/guides/gs/relational-data-access/)
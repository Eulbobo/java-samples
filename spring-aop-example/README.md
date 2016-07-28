Présentation d'exemples d'utilisation de Spring-Aop avec AspectJ
===================
Ce projet permet d'illustrer l'utilisation de quelques fonctionnalités basiques de spring-aop avec AspectJ

Dans cet exemple, on reste sur l'utilisation la plus simple possible des aspects : pilotés par Spring, sans loadtime-weaving.

Définition d'un aspect
-------------------
Le but d'un aspect est de pouvoir exécuter du code transverse ou technique sur du code en cours de développement, mais aussi sur du code déjà compilé. Celà permet d'exécuter automatiquement des tâches en factorisant le code désiré à un endroit plutôt que de le voir répété à de nombreux endroits, et sans compter sur de la pure délégation.

Un aspect ne devrait cependant jamais avoir d'impact direct sur l'exécution du code autour duquel il s'exécute, étant donné qu'en lisant le code il n'existe aucun élément pour déterminer si un aspect se greffera avant ou après l'exécution de la fonction.
Si l'aspect modifie le comportement attendu des classes, des bugs peuvent apparaître même si l'exécution des tests unitaires indépendants pour l'aspect et la méthode visée sont corrects.
Il faut voir un aspect comme du code qu'on exécute à chaque fois parce qu'on le DOIT à chaque fois.

Un avantage majeur de l'utilisation des aspects est de pouvoir factoriser de nombreux éléments techniques répétitifs, permettant d'alléger la partie purement fonctionnelle du code, qui se retrouve limitée à sa plus simple expression.

Quelques exemples d'utilisation d'aspects :
- Des logs avant et après exécution, avec affichage des paramètres et des temps de traitement.
- Monitoring applicatif : capacité à automatiquement générer des données d'utilisation des méthodes, des métriques, etc...
- Gestion des transactions : ouverture et fermeture automatique de transactions autour de l'exécution d'une méthode (même si c'est géré par la présence du tag @Transactionnal qui fait cela pour nous automatiquement... Via un aspect)
- Gestion d'exception : même si ce point est discutable, les aspects peuvent permettre de gérer automatiquement par exemple les logs applicatifs des exceptions. Ou de faire du monitoring spécifique sur les exceptions levées afin d'augmenter les possibilités de traitement en cas d'erreur (envoi de mail en cas d'apparition d'une erreur spécifique, déclaration automatique d'un bug dans un tracker...)
- Débugging : pendant les phases de développement, la présence de certains aspects peut permettre rapidement et simplement d'afficher des informations utiles au débug (paramètres passés, élément retourné, exception levées) sans pour autant surcharger le code de pistes de log qui ne feront qu'alourdir le code et qui n'auront pas de sens une fois la phase de production terminée. Pour le passage en prod, il suffit de ne pas activer l'aspect
- Trigger de persistance : par exemple appeler une procédure stockée en base de données avant chaque opération, préparer la session en cours, ajouter automatiquemenet des informations dans les beans passés en paramètre (user, date de création, date de mise à jour...)
- Securité : Interdiction d'utilisation de certains méthodes. C'est possible, mais dangereux étant donné que l'interdiction ne sera visible qu'à l'exécution et que cela impacte le fonctionnement attendu du code appelé.
- Gestion des préconditions : Les aspects peuvent être utilisés pour ajouter automatiquement des tests de précondition sur certains méthodes. Cela a aussi un impact sur le fonctionnement du code, du coup n'est pas conseillé.

Fonctionnement des aspects spring-aop
-------------------
Dans les exemples que nous allons voir, nous allons utiliser les aspects comme des composants Spring. Durant l'injection de dépendances, Spring va automatiquement créer des classes proxy contenant le code des aspects pour les beans récupérés à l'exécution.
Une particularité supplémentation de spring-aop c'est qu'il permet de gérer les aspects sur des instances spécifiques de beans. Si vous avez plusieurs implémentations d'une classe de service, vous pouvez n'intégrer un aspect que sur certains d'entre eux (ce qui n'est pas possible avec AspectJ qui travaille au niveau de la classe).

Cela veut dire que TOUS les objets que nous allons récupérer via Spring seront des classes proxifiées. Cela veut dire que pour permettre de générer des proxy, vos classes doivent POUVOIR être proxifiées, à savoir :
- Implémenter une ou plusieurs interfaces : c'est le cas idéal, mais ce n'est pas nécessaire : Spring peut aussi automatiquement créer une classe fille du bean cible, et utiliser la puissance du polymorphisme pour réaliser son travail.
- Ne pas être déclarées comme `final` (ce qui est de toute façon une mauvaise idée dans un contexte de bean Spring), sinon un poxy ne pourra pas être créé

Déclarer un bean comme `final` est une bonne technique pour empêcher la création d'un proxy, mais une fois de plus, ce blocage aura lieu à l'exécution. Mais une fois de plus, ce n'est pas une bonne idée.

Notions de base essentielles sur les aspects
-------------------
Quelques notions essentielles à la compréhension du développement orientée aspect est expliqué [sur le site de JmDoudoux](http://www.jmdoudoux.fr/java/dej/chap-aop.htm#aop)

Il y a certains concepts qui sont propres à ce mode de programmation :
- JoinPoint : point d'exécution/de jonction. Celà représente un endroit particulier où il est possible d'invoquer un 'greffon' (advice) dans le flux. Presque n'importe quelle instruction peut devenir un JoinPoint
- PointCut : point de coupe/d'action/greffe/recouvrement. C'est l'endroit où le 'greffon' sera invoqué lors du 'tissage' (weaving). C'est un sous-ensemble défini de JoinPoint
- Advice : greffon. Il contient les traitements techniques qui seront insérés à des JoinCut
- Aspect : encapsule une fonctionnalité et est composé d'un ou plusieurs points de coupes et greffons
- Weaving : tissage : action d'insertion des aspect
- Weaver : outil qui réalise le tissage


Syntaxe de base AspectJ
-------------------
Toutes les expressions de `PointCut` utilisées dans les exemples sont des expressions provenant directement de AspectJ.

### Recherche de signatures de méthodes
L'expression qui permet de désigner une signature de méthode est la suivante :
- commence par le mot clé `execution` puis possède une expression de définition de la signature de la méthode à matcher
- la signature se compose comme suit : <visibilite désirée> <type de retour> <package>.<classe>.<methode>(<arguments>)
- on ne peut préciser un type de retour (en précisant le package) que si on a précise la visibilité `(* User UserServiceImpl.*(..))` est une expression invalide

#### Toutes les méthodes dans une classe particulière
L'expression est la suivante : ```execution(* fr.norsys.aop.application.UserServiceImpl.*(..))```
La première étoile permet de matcher toute les visibilités et tous les types de retour

#### Pas de déclaration de package
Il est possible de ne pas déclarer le package si la classe visée par l'aspect se trouve dans le même package que la classe visée.
Par exemple, si on a un aspect dans le package `fr.norsys.aop.application`, l'expression précédente pourra s'écrire : ```execution(* UserServiceImpl.*(..))```

#### Toutes les méthodes publiques
L'expression est la suivante : ```execution(public * fr.norsys.aop.application.UserServiceImpl.*(..))```

#### Toutes les méthodes publiques qui renvoie un objet User
L'expression est la suivante : ```execution(public fr.norsys.aop.domain.bean.User fr.norsys.aop.application.UserServiceImpl.*(..))```

#### Toutes les méthodes publiques qui renvoie un objet User avec un identifiant de type Long en premier paramètre
L'expression est la suivante : ```execution(public fr.norsys.aop.domain.bean.User fr.norsys.aop.application.UserServiceImpl.*(Long id, ..))```


### Recherche de types signatures de méthodes
Contrairement au cas vu avant, ce mode de fonctionnement fait une recherche par type de classe. Mais permet de faire d'autres choses

#### Toutes les méthodes des classes dans un package
L'expression est la suivante : ```within(fr.norsys.aop.application.*)```
Celle-ci permettra de brancher l'aspect sur toutes les classes du package `fr.norsys.aop.application` (et uniquement celui-là, et pas ses fils)

#### Toutes les méthodes des classes dans un package, en incluant les sous-packages
L'expression est la suivante : ```within(fr.norsys.aop.application..*)```
Un point de plus qui fait la différence

#### Toutes les méthodes dans une classe
L'expression est la suivante : ```within(fr.norsys.aop.application.UserServiceImpl)```
Même chose que ```execution(* fr.norsys.aop.application.UserServiceImpl.*(..))```

#### Toutes les méthodes dans une classe du même package que l'aspect
L'expression est la suivante : ```within(UserServiceImpl)```
si l'aspect se trouve dans le package `fr.norsys.aop.application`

#### Toutes les implémentations d'une interface
L'expression est la suivante : ```within(fr.norsys.aop.domain.service.UserService+)```
Toutes les implémentations, quel que soit leur package, sera matchée

### Recherche de beans par leur nom
Cela permet de mapper directement le nom des beans Spring pour les aspects

#### Tous les beans nommés `Manager`
L'expression est la suivante : ```bean(Manager)```

#### Tous les beans qui ont un nom finissant par `Impl`
L'expression est la suivante : ```bean(*Impl)```

### Combinaison des règles
Toutes les règles de Pointcut vues jusqu'à présent peuvent être liées entre elles pour créer des expression plus complexes.
Les opérateurs pour réaliser cela sont les suivants (les mêmes qu'en java):
- `||` : ou logique
- `&&` : et logique
- `!` : not

#### Exemple de tous les beans finissant par `Impl` ou par `Dao`
L'expression est la suivante : ```bean(*Impl) || bean(*Dao)```
 
#### Exemple de tous les beans finissant par `Impl`sauf ceux du package `fr.norsys.aop`
L'expression est la suivante : ```bean(*Impl) && !within(fr.norsys.aop.*)```


TODO fonctionnement des arguments

Contenu du projet
-------------------
Le projet ne contient pas de tests sur les aspects, pour deux raisons principales
- presque tous les aspects sont déclarés comme privés
- ils n'ont pas d'impact sur le code (sauf pour les cas des exceptions à ne pas faire)

Si les aspects avaient eu des méthodes publiques, il aurait été possible de les tester correctement.

Par contre, il est possible de tester le bon fonctionnement de l'intégralité du code avec les aspects tels qu'ils existent.
Et il est aussi possible de tester le bon fonctionnement du code en désactivant l'utilisaton des aspects. Ce qui permet dans les tests de voir deux types de tests bien distincts au fonctionnement totalement différents selon qu'on fasse un test unitaire ou d'intégration.

TODO terminer

Utilisation
-------------------
TODO

Liens
-------------------
> - [Documentation de référence Spring Core](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/index.html)
> - [Spring Aop par JmDoudoux](http://www.jmdoudoux.fr/java/dej/chap-spring-aop.htm)
> - [Exemples d'expressions aspectJ](http://howtodoinjava.com/spring/spring-aop/writing-spring-aop-aspectj-pointcut-expressions-with-examples/)
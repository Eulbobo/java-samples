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

Chaque expression est qualifiée par un désignateur qui permet de désignée la cible :
- execution : correspondance avec l'exécution d'une signature de méthode
- within : correspondante au sein d'un ensemble (package, classe)
- bean : correspondance avec un bean de même nom
- this : correspondance au sein d'un type classe où le proxy implémente un type
- target : correspondance aux proxy pour lesquels l'objet cible implémente un type (classe, interface)
- args : correspondance aux éléments ayant les paramètres correspondants (type)
- @target : correspondance aux éléments pour lequels l'objet cible possède une annotation du type donnée à l'exécution (l'annotation doit être préservée au runtime)
- @args : correspondance aux éléments pour lesquels les arguments passés possèdent l'annotation donnée à l'exécution (l'annotation doit être préservée au runtime)
- @within : correspondance aux éléments pour lequels la classe de l'objet cible possède une annotation donnée à l'exécution (l'annotation doit être préservée au runtime)
- @annotation : correspondance aux éléments annotés (l'annotation doit être préservée au runtime)

### execution
Exemples d'utilisation de l'expression `execution` : correspondance avec l'exécution d'une signature de méthode

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


### within
Exemples d'utilisation de l'expression `within` : correspondante au sein d'un ensemble (package, classe)

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

### bean 
Exemples d'utilisation de l'expression `bean` : correspondance avec un bean de même nom

#### Tous les beans nommés `Manager`
L'expression est la suivante : ```bean(Manager)```

#### Tous les beans qui ont un nom finissant par `Impl`
L'expression est la suivante : ```bean(*Impl)```

### target - this
Exemples d'utilisation de l'expression `this` : correspondance au sein d'un proxy qui implémente un type et `target` : correspondance aux proxy pour lesquels l'objet cible implémente un type.
Différence avec target : ici, la correspondance est faite sur l'objet proxy généré. Un proxy est une classe fille générée automatiquement d'une classe à proxifier, mais on peut aussi lui faire implémenter/étendre d'autre classes. D'où la différence entre les deux éléments.
L'usage de `target` est à préférer à `this` qui est à réserver dans le cas de création de proxy spécifiques.

#### Tous les beans proxy qui implémentent la classe `UserService`
L'expression est la suivante : ```this(fr.norsys.aop.domain.service.UserService)```

#### Tous les beans proxy dans lesquels l'object cible qui implémentent la classe `UserServiceImpl`
L'expression est la suivante : ```target(fr.norsys.aop.application.UserServiceImpl)```

### args
Exemples d'utilisation de l'expression `args` : correspondance aux éléments ayant les paramètres correspondants (type)

#### Toutes les méthodes auxquelles on passe un seul argument de type Long
L'expression est la suivante : ```args(java.lang.Long)```

#### Toutes les méthodes auxquelles on passe un type Long en premier
L'expression est la suivante : ```args(java.lang.Long, ..)```
A noter que cette expression correspondra aux signatures de méthode suivantes
- void doThings(Long value);
- void doOthersThings(Long value, String stringValue);

#### Autre façon de faire : utiliser la déclaration de la méthode
Exemple d'expression combinée à la déclaration
```java
	@Before("args(longValue)")
    private static void logForLong(final Long longValue) {
        LOGGER.info("Passage du paramètre {}", longValue);
    }
```
Dans cet exemple, on passe le nom du paramètre, dont le type est récupéré dans la signature. Attention, le nom de la variable est case sensitive

### @annotation - @target
- `@target` : correspondance aux éléments pour lequels l'objet cible possède une annotation du type donnée à l'exécution (l'annotation doit être préservée au runtime).
- `@annotation` : correspondance aux éléments annotés de la classe cible (l'annotation doit être préservée au runtime)
Les deux expressions `@annotation` et `target` sont très proches et fonctionnent sur le même principe que `this` vs `target`. 
L'expression `@target` vérifie le type à l'exécution, tandis que `@annotation` vérifie le type par rapport au type déclaré dans la classe cible avant proxification.
Pour s'assurer du fonctionnement d'un aspect par annotation, il faut donc préférer le `@annotation`.

#### Toutes les méthodes qui possèdent l'annotation `@Log`
Imaginons une annotations @Log qui soit preservée à l'exécution et que l'on puisse mettre sur une méthode. Pour exécuter du code autour de ces méthodes, l'expression est la suivante : ```@annotation(fr.norsys.aop.annotation.Log)```

### @within
Exemples d'utilisation de l'expression `@within` : correspondance aux éléments pour lequels la classe de l'objet cible possède une annotation donnée à l'exécution (l'annotation doit être préservée au runtime)
Une fois de plus, celà ressemble beaucoup à l'usage de `@annotation` sauf qu'on parle ici d'une annotation au niveau de la classe

#### Toutes les classes qui portent l'annotation `@Loggable`
L'expression est la suivante : ```@within(fr.norsys.aop.annotation.Loggable)```

### @args 
Exemples d'utilisation de l'expression `@args` : correspondance aux éléments pour lesquels les arguments passés possèdent l'annotation donnée à l'exécution (l'annotation doit être préservée au runtime)

#### Toutes les méthodes qui prennent pour seul paramètre un objet dont la classe cible possède l'annotation `@Id` (si la classe est fournie par le conteneur)
L'expression est la suivante : ```@args(fr.norsys.aop.annotation.Id)```


Syntaxe avancée
-------------------
Toutes les règles précédentes peuvent être combinées entre elles avec des opérateurs logiques, ou être spécialisées grâce au système de 'binding form'

### Opérateurs logiques
Toutes les règles de Pointcut vues jusqu'à présent peuvent être liées entre elles pour créer des expression plus complexes.
Les opérateurs pour réaliser cela sont les suivants (les mêmes qu'en java):
- `||` : ou logique
- `&&` : et logique
- `!` : not

#### Exemple de tous les beans finissant par `Impl` ou par `Dao`
L'expression est la suivante : ```bean(*Impl) || bean(*Dao)```
 
#### Exemple de tous les beans finissant par `Impl`sauf ceux du package `fr.norsys.aop`
L'expression est la suivante : ```bean(*Impl) && !within(fr.norsys.aop.*)```

### Binding Form
Le binding form permet de définir une variable par son nom dans le point de coupe, et de déclarer son type dans le greffon.

Ainsi, on peut vouloir une expression de la forme `arg(firstValue, ..)` qui signifiera : n'importe quel méthode qui a au moins un paramètre.
Définission le point de coupe comme suit :
```java
    @Pointcut("annotation(firstValue, ..)")
    private void hasArgument(Long firstValue) { }
```
On a défini un point de coupe sur les méthodes qui prenaient en paramètre 1 ou plusieurs arguments dont le premier est de type Long.

Cela fonctionne avec toutes les expressions, et c'est particulièrement utile dans le cas de greffon tels que `@AfterThrowing`, `@AfterReturning`, où tous les greffons où il est important de connaitre les éléments en entrée et/ou sortie.

Contenu du projet
-------------------
Le projet ne contient pas de tests sur les aspects, pour deux raisons principales
- presque tous les aspects sont déclarés comme privés
- ils n'ont pas d'impact sur le code (sauf pour les cas des exceptions à ne pas faire)

Si les aspects avaient eu des méthodes publiques, il aurait été possible de les tester correctement.
Vu que la plupart des aspects ne devraient pas avoir d'effet sur le code autour d'eux, il est difficile de tester leur comportement a moins de pouvoir injecter (par setter ou par constructeur) des éléments variables afin de suivre leur bon fonctionnement.

Par contre, il est possible de tester le bon fonctionnement de l'intégralité du code avec les aspects tels qu'ils existent.
Et il est aussi possible de tester le bon fonctionnement du code en désactivant l'utilisaton des aspects. Ce qui permet dans les tests de voir deux types de tests bien distincts au fonctionnement totalement différents selon qu'on fasse un test unitaire ou d'intégration.

Le projet contient une classe `Application` exécutable qui permet de lancer les méthodes principales du service `UserService` afin de voir l'effet des aspects (sur les logs)

Utilisation
-------------------
TODO

Liens
-------------------
> - [Documentation de référence Spring aop](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html)
> - [Spring Aop par JmDoudoux](http://www.jmdoudoux.fr/java/dej/chap-spring-aop.htm)
> - [Exemples d'expressions aspectJ](http://howtodoinjava.com/spring/spring-aop/writing-spring-aop-aspectj-pointcut-expressions-with-examples/)
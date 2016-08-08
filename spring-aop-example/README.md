Pr�sentation d'exemples d'utilisation de Spring-Aop avec AspectJ
===================
Ce projet permet d'illustrer l'utilisation de quelques fonctionnalit�s basiques de spring-aop avec AspectJ

Dans cet exemple, on reste sur l'utilisation la plus simple possible des aspects : pilot�s par Spring, sans loadtime-weaving.

D�finition d'un aspect
-------------------
Le but d'un aspect est de pouvoir ex�cuter du code transverse ou technique sur du code en cours de d�veloppement, mais aussi sur du code d�j� compil�. Cel� permet d'ex�cuter automatiquement des t�ches en factorisant le code d�sir� � un endroit plut�t que de le voir r�p�t� � de nombreux endroits, et sans compter sur de la pure d�l�gation.

Un aspect ne devrait cependant jamais avoir d'impact direct sur l'ex�cution du code autour duquel il s'ex�cute, �tant donn� qu'en lisant le code il n'existe aucun �l�ment pour d�terminer si un aspect se greffera avant ou apr�s l'ex�cution de la fonction.
Si l'aspect modifie le comportement attendu des classes, des bugs peuvent appara�tre m�me si l'ex�cution des tests unitaires ind�pendants pour l'aspect et la m�thode vis�e sont corrects.
Il faut voir un aspect comme du code qu'on ex�cute � chaque fois parce qu'on le DOIT � chaque fois.

Un avantage majeur de l'utilisation des aspects est de pouvoir factoriser de nombreux �l�ments techniques r�p�titifs, permettant d'all�ger la partie purement fonctionnelle du code, qui se retrouve limit�e � sa plus simple expression.

Quelques exemples d'utilisation d'aspects :
- Des logs avant et apr�s ex�cution, avec affichage des param�tres et des temps de traitement.
- Monitoring applicatif : capacit� � automatiquement g�n�rer des donn�es d'utilisation des m�thodes, des m�triques, etc...
- Gestion des transactions : ouverture et fermeture automatique de transactions autour de l'ex�cution d'une m�thode (m�me si c'est g�r� par la pr�sence du tag @Transactionnal qui fait cela pour nous automatiquement... Via un aspect)
- Gestion d'exception : m�me si ce point est discutable, les aspects peuvent permettre de g�rer automatiquement par exemple les logs applicatifs des exceptions. Ou de faire du monitoring sp�cifique sur les exceptions lev�es afin d'augmenter les possibilit�s de traitement en cas d'erreur (envoi de mail en cas d'apparition d'une erreur sp�cifique, d�claration automatique d'un bug dans un tracker...)
- D�bugging : pendant les phases de d�veloppement, la pr�sence de certains aspects peut permettre rapidement et simplement d'afficher des informations utiles au d�bug (param�tres pass�s, �l�ment retourn�, exception lev�es) sans pour autant surcharger le code de pistes de log qui ne feront qu'alourdir le code et qui n'auront pas de sens une fois la phase de production termin�e. Pour le passage en prod, il suffit de ne pas activer l'aspect
- Trigger de persistance : par exemple appeler une proc�dure stock�e en base de donn�es avant chaque op�ration, pr�parer la session en cours, ajouter automatiquemenet des informations dans les beans pass�s en param�tre (user, date de cr�ation, date de mise � jour...)
- Securit� : Interdiction d'utilisation de certains m�thodes. C'est possible, mais dangereux �tant donn� que l'interdiction ne sera visible qu'� l'ex�cution et que cela impacte le fonctionnement attendu du code appel�.
- Gestion des pr�conditions : Les aspects peuvent �tre utilis�s pour ajouter automatiquement des tests de pr�condition sur certains m�thodes. Cela a aussi un impact sur le fonctionnement du code, du coup n'est pas conseill�.

Fonctionnement des aspects spring-aop
-------------------
Dans les exemples que nous allons voir, nous allons utiliser les aspects comme des composants Spring. Durant l'injection de d�pendances, Spring va automatiquement cr�er des classes proxy contenant le code des aspects pour les beans r�cup�r�s � l'ex�cution.
Une particularit� suppl�mentation de spring-aop c'est qu'il permet de g�rer les aspects sur des instances sp�cifiques de beans. Si vous avez plusieurs impl�mentations d'une classe de service, vous pouvez n'int�grer un aspect que sur certains d'entre eux (ce qui n'est pas possible avec AspectJ qui travaille au niveau de la classe).

Cela veut dire que TOUS les objets que nous allons r�cup�rer via Spring seront des classes proxifi�es. Cela veut dire que pour permettre de g�n�rer des proxy, vos classes doivent POUVOIR �tre proxifi�es, � savoir :
- Impl�menter une ou plusieurs interfaces : c'est le cas id�al, mais ce n'est pas n�cessaire : Spring peut aussi automatiquement cr�er une classe fille du bean cible, et utiliser la puissance du polymorphisme pour r�aliser son travail.
- Ne pas �tre d�clar�es comme `final` (ce qui est de toute fa�on une mauvaise id�e dans un contexte de bean Spring), sinon un poxy ne pourra pas �tre cr��

D�clarer un bean comme `final` est une bonne technique pour emp�cher la cr�ation d'un proxy, mais une fois de plus, ce blocage aura lieu � l'ex�cution. Mais une fois de plus, ce n'est pas une bonne id�e.

Notions de base essentielles sur les aspects
-------------------
Quelques notions essentielles � la compr�hension du d�veloppement orient�e aspect est expliqu� [sur le site de JmDoudoux](http://www.jmdoudoux.fr/java/dej/chap-aop.htm#aop)

Il y a certains concepts qui sont propres � ce mode de programmation :
- JoinPoint : point d'ex�cution/de jonction. Cel� repr�sente un endroit particulier o� il est possible d'invoquer un 'greffon' (advice) dans le flux. Presque n'importe quelle instruction peut devenir un JoinPoint
- PointCut : point de coupe/d'action/greffe/recouvrement. C'est l'endroit o� le 'greffon' sera invoqu� lors du 'tissage' (weaving). C'est un sous-ensemble d�fini de JoinPoint
- Advice : greffon. Il contient les traitements techniques qui seront ins�r�s � des JoinCut
- Aspect : encapsule une fonctionnalit� et est compos� d'un ou plusieurs points de coupes et greffons
- Weaving : tissage : action d'insertion des aspect
- Weaver : outil qui r�alise le tissage


Syntaxe de base AspectJ
-------------------
Toutes les expressions de `PointCut` utilis�es dans les exemples sont des expressions provenant directement de AspectJ.

Chaque expression est qualifi�e par un d�signateur qui permet de d�sign�e la cible :
- execution : correspondance avec l'ex�cution d'une signature de m�thode
- within : correspondante au sein d'un ensemble (package, classe)
- bean : correspondance avec un bean de m�me nom
- this : correspondance au sein d'un type classe o� le proxy impl�mente un type
- target : correspondance aux proxy pour lesquels l'objet cible impl�mente un type (classe, interface)
- args : correspondance aux �l�ments ayant les param�tres correspondants (type)
- @target : correspondance aux �l�ments pour lequels l'objet cible poss�de une annotation du type donn�e � l'ex�cution (l'annotation doit �tre pr�serv�e au runtime)
- @args : correspondance aux �l�ments pour lesquels les arguments pass�s poss�dent l'annotation donn�e � l'ex�cution (l'annotation doit �tre pr�serv�e au runtime)
- @within : correspondance aux �l�ments pour lequels la classe de l'objet cible poss�de une annotation donn�e � l'ex�cution (l'annotation doit �tre pr�serv�e au runtime)
- @annotation : correspondance aux �l�ments annot�s (l'annotation doit �tre pr�serv�e au runtime)

### execution
Exemples d'utilisation de l'expression `execution` : correspondance avec l'ex�cution d'une signature de m�thode

#### Toutes les m�thodes dans une classe particuli�re
L'expression est la suivante : ```execution(* fr.norsys.aop.application.UserServiceImpl.*(..))```
La premi�re �toile permet de matcher toute les visibilit�s et tous les types de retour

#### Pas de d�claration de package
Il est possible de ne pas d�clarer le package si la classe vis�e par l'aspect se trouve dans le m�me package que la classe vis�e.
Par exemple, si on a un aspect dans le package `fr.norsys.aop.application`, l'expression pr�c�dente pourra s'�crire : ```execution(* UserServiceImpl.*(..))```

#### Toutes les m�thodes publiques
L'expression est la suivante : ```execution(public * fr.norsys.aop.application.UserServiceImpl.*(..))```

#### Toutes les m�thodes publiques qui renvoie un objet User
L'expression est la suivante : ```execution(public fr.norsys.aop.domain.bean.User fr.norsys.aop.application.UserServiceImpl.*(..))```

#### Toutes les m�thodes publiques qui renvoie un objet User avec un identifiant de type Long en premier param�tre
L'expression est la suivante : ```execution(public fr.norsys.aop.domain.bean.User fr.norsys.aop.application.UserServiceImpl.*(Long id, ..))```


### within
Exemples d'utilisation de l'expression `within` : correspondante au sein d'un ensemble (package, classe)

#### Toutes les m�thodes des classes dans un package
L'expression est la suivante : ```within(fr.norsys.aop.application.*)```
Celle-ci permettra de brancher l'aspect sur toutes les classes du package `fr.norsys.aop.application` (et uniquement celui-l�, et pas ses fils)

#### Toutes les m�thodes des classes dans un package, en incluant les sous-packages
L'expression est la suivante : ```within(fr.norsys.aop.application..*)```
Un point de plus qui fait la diff�rence

#### Toutes les m�thodes dans une classe
L'expression est la suivante : ```within(fr.norsys.aop.application.UserServiceImpl)```
M�me chose que ```execution(* fr.norsys.aop.application.UserServiceImpl.*(..))```

#### Toutes les m�thodes dans une classe du m�me package que l'aspect
L'expression est la suivante : ```within(UserServiceImpl)```
si l'aspect se trouve dans le package `fr.norsys.aop.application`

#### Toutes les impl�mentations d'une interface
L'expression est la suivante : ```within(fr.norsys.aop.domain.service.UserService+)```
Toutes les impl�mentations, quel que soit leur package, sera match�e

### bean 
Exemples d'utilisation de l'expression `bean` : correspondance avec un bean de m�me nom

#### Tous les beans nomm�s `Manager`
L'expression est la suivante : ```bean(Manager)```

#### Tous les beans qui ont un nom finissant par `Impl`
L'expression est la suivante : ```bean(*Impl)```

### target - this
Exemples d'utilisation de l'expression `this` : correspondance au sein d'un proxy qui impl�mente un type et `target` : correspondance aux proxy pour lesquels l'objet cible impl�mente un type.
Diff�rence avec target : ici, la correspondance est faite sur l'objet proxy g�n�r�. Un proxy est une classe fille g�n�r�e automatiquement d'une classe � proxifier, mais on peut aussi lui faire impl�menter/�tendre d'autre classes. D'o� la diff�rence entre les deux �l�ments.
L'usage de `target` est � pr�f�rer � `this` qui est � r�server dans le cas de cr�ation de proxy sp�cifiques.

#### Tous les beans proxy qui impl�mentent la classe `UserService`
L'expression est la suivante : ```this(fr.norsys.aop.domain.service.UserService)```

#### Tous les beans proxy dans lesquels l'object cible qui impl�mentent la classe `UserServiceImpl`
L'expression est la suivante : ```target(fr.norsys.aop.application.UserServiceImpl)```

### args
Exemples d'utilisation de l'expression `args` : correspondance aux �l�ments ayant les param�tres correspondants (type)

#### Toutes les m�thodes auxquelles on passe un seul argument de type Long
L'expression est la suivante : ```args(java.lang.Long)```

#### Toutes les m�thodes auxquelles on passe un type Long en premier
L'expression est la suivante : ```args(java.lang.Long, ..)```
A noter que cette expression correspondra aux signatures de m�thode suivantes
- void doThings(Long value);
- void doOthersThings(Long value, String stringValue);

#### Autre fa�on de faire : utiliser la d�claration de la m�thode
Exemple d'expression combin�e � la d�claration
```java
	@Before("args(longValue)")
    private static void logForLong(final Long longValue) {
        LOGGER.info("Passage du param�tre {}", longValue);
    }
```
Dans cet exemple, on passe le nom du param�tre, dont le type est r�cup�r� dans la signature. Attention, le nom de la variable est case sensitive

### @annotation - @target
- `@target` : correspondance aux �l�ments pour lequels l'objet cible poss�de une annotation du type donn�e � l'ex�cution (l'annotation doit �tre pr�serv�e au runtime).
- `@annotation` : correspondance aux �l�ments annot�s de la classe cible (l'annotation doit �tre pr�serv�e au runtime)
Les deux expressions `@annotation` et `target` sont tr�s proches et fonctionnent sur le m�me principe que `this` vs `target`. 
L'expression `@target` v�rifie le type � l'ex�cution, tandis que `@annotation` v�rifie le type par rapport au type d�clar� dans la classe cible avant proxification.
Pour s'assurer du fonctionnement d'un aspect par annotation, il faut donc pr�f�rer le `@annotation`.

#### Toutes les m�thodes qui poss�dent l'annotation `@Log`
Imaginons une annotations @Log qui soit preserv�e � l'ex�cution et que l'on puisse mettre sur une m�thode. Pour ex�cuter du code autour de ces m�thodes, l'expression est la suivante : ```@annotation(fr.norsys.aop.annotation.Log)```

### @within
Exemples d'utilisation de l'expression `@within` : correspondance aux �l�ments pour lequels la classe de l'objet cible poss�de une annotation donn�e � l'ex�cution (l'annotation doit �tre pr�serv�e au runtime)
Une fois de plus, cel� ressemble beaucoup � l'usage de `@annotation` sauf qu'on parle ici d'une annotation au niveau de la classe

#### Toutes les classes qui portent l'annotation `@Loggable`
L'expression est la suivante : ```@within(fr.norsys.aop.annotation.Loggable)```

### @args 
Exemples d'utilisation de l'expression `@args` : correspondance aux �l�ments pour lesquels les arguments pass�s poss�dent l'annotation donn�e � l'ex�cution (l'annotation doit �tre pr�serv�e au runtime)

#### Toutes les m�thodes qui prennent pour seul param�tre un objet dont la classe cible poss�de l'annotation `@Id` (si la classe est fournie par le conteneur)
L'expression est la suivante : ```@args(fr.norsys.aop.annotation.Id)```


Syntaxe avanc�e
-------------------
Toutes les r�gles pr�c�dentes peuvent �tre combin�es entre elles avec des op�rateurs logiques, ou �tre sp�cialis�es gr�ce au syst�me de 'binding form'

### Op�rateurs logiques
Toutes les r�gles de Pointcut vues jusqu'� pr�sent peuvent �tre li�es entre elles pour cr�er des expression plus complexes.
Les op�rateurs pour r�aliser cela sont les suivants (les m�mes qu'en java):
- `||` : ou logique
- `&&` : et logique
- `!` : not

#### Exemple de tous les beans finissant par `Impl` ou par `Dao`
L'expression est la suivante : ```bean(*Impl) || bean(*Dao)```
 
#### Exemple de tous les beans finissant par `Impl`sauf ceux du package `fr.norsys.aop`
L'expression est la suivante : ```bean(*Impl) && !within(fr.norsys.aop.*)```

### Binding Form
Le binding form permet de d�finir une variable par son nom dans le point de coupe, et de d�clarer son type dans le greffon.

Ainsi, on peut vouloir une expression de la forme `arg(firstValue, ..)` qui signifiera : n'importe quel m�thode qui a au moins un param�tre.
D�finission le point de coupe comme suit :
```java
    @Pointcut("annotation(firstValue, ..)")
    private void hasArgument(Long firstValue) { }
```
On a d�fini un point de coupe sur les m�thodes qui prenaient en param�tre 1 ou plusieurs arguments dont le premier est de type Long.

Cela fonctionne avec toutes les expressions, et c'est particuli�rement utile dans le cas de greffon tels que `@AfterThrowing`, `@AfterReturning`, o� tous les greffons o� il est important de connaitre les �l�ments en entr�e et/ou sortie.

Contenu du projet
-------------------
Le projet ne contient pas de tests sur les aspects, pour deux raisons principales
- presque tous les aspects sont d�clar�s comme priv�s
- ils n'ont pas d'impact sur le code (sauf pour les cas des exceptions � ne pas faire)

Si les aspects avaient eu des m�thodes publiques, il aurait �t� possible de les tester correctement.
Vu que la plupart des aspects ne devraient pas avoir d'effet sur le code autour d'eux, il est difficile de tester leur comportement a moins de pouvoir injecter (par setter ou par constructeur) des �l�ments variables afin de suivre leur bon fonctionnement.

Par contre, il est possible de tester le bon fonctionnement de l'int�gralit� du code avec les aspects tels qu'ils existent.
Et il est aussi possible de tester le bon fonctionnement du code en d�sactivant l'utilisaton des aspects. Ce qui permet dans les tests de voir deux types de tests bien distincts au fonctionnement totalement diff�rents selon qu'on fasse un test unitaire ou d'int�gration.

Le projet contient une classe `Application` ex�cutable qui permet de lancer les m�thodes principales du service `UserService` afin de voir l'effet des aspects (sur les logs)

Utilisation
-------------------
TODO

Liens
-------------------
> - [Documentation de r�f�rence Spring aop](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html)
> - [Spring Aop par JmDoudoux](http://www.jmdoudoux.fr/java/dej/chap-spring-aop.htm)
> - [Exemples d'expressions aspectJ](http://howtodoinjava.com/spring/spring-aop/writing-spring-aop-aspectj-pointcut-expressions-with-examples/)
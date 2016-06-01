Présentation de SLF4J (Simple Logging Facade For Java)
===================

Présentation
-------------------
SLF4J est un framework permettant d'ajouter une couche d'abstraction au mécanisme de journalisation, permettrant ainsi de faire varier son implémentation par configuration.
Il apporte aussi un certain nombre d'outils et méthodes permettant de simplifier le fonctionnement de la journalisation

Fonctionnement
-------------------
SLF4J en soit n'est qu'une interface qui n'est pas utilisable en l'état. Pour fonctionner, elle a besoin d'un pont d'implémentation.

Il existe plusieurs ponts selon l'implémentation choisie
> - slf4j-simple implémentation simple
> - slf4j-log4j12 pour utiliser log4j
> - slf4j-jdk14 pour utiliser le logger java.util.logging.Logger
> - slf4j-nop quand on ne veut pas de log du tout (No Operation)
> - slf4j-jcl pour utiliser JCL (Jakarta Commons Logging)

A chaque action de journalisation dans le pont SLF4J correspond une opération sur l'implémentation cible :
```java
    // méthode de l'API SLF4J
    public boolean isDebugEnabled() {
        // logger est une implémentation log4j
        return logger.isDebugEnabled();
    }
    
    ...
    // méthode de l'API SLF4J
    public void info(String msg) {
        // logger est une implémentation log4j
        logger.log(FQCN, Level.INFO, msg, null);
    }
```

Il existe aussi des frameworks de journalisation qui implémentent directement l'API SLF4J, 
comme Logback (ce qui évite de devoir faire des conversions comme pour les autres)

Utilisation
-------------------
Prenons l'exemple de l'utilisation de l'api SLF4J avec une implémentation Log4j. Nous avons besoin de 3 librairies dans le CLASSPATH
> - slf4j-api : l'API de base
> - slf4j-log4j12 : le bridge permettant d'encapsuler log4j avec l'interface slf4j
> - log4j : l'implémentation de la journalisation

Dans notre cas, comme nous utilisons une implémentation log4j, c'est une configuration log4j que nous devons utiliser pour paramétrer les logs

Mais alors c'est quoi l'intérêt de rajouter de l'abstration ?
-------------------
A priori, pas grand chose étant donné que log4j fait déjà beaucoup des choses que SLF4J fait aussi. La question est donc légimite.

Cependant, slf4j possède quelques petits trucs en plus (techniquement ou fonctionnellement)

#### Techniquement : Paramétrage des logs
Avec un logger log4j standard, si on veut créer un log avec un message formaté selon des paramètres, on a deux solution
```java
    // Solution 1 : Construire le message en amont
    String message = String.format("Ce message %s plusieurs %s.", "possède", "paramètres");
    LOG4J_LOGGER.info(message); // log de "Ce message possède plusieurs paramètres."
    
    // Solution 2 : Concaténer les éléments
    String param1 = "possède";
    String param2 = "plusieurs";
    LOG4J_LOGGER.info("Ce message "+ param1 +" plusieurs "+ param2+"."); // log de "Ce message possède plusieurs paramètres."
```
L'inconvénient majeur de cette solution, c'est que le message est construit même s'il n'est pas utilisé.
Dans notre cas, si on ne collecte que des mails au niveau ERROR, on aura perdu du temps à construire un message pour rien.

Avec SLF4J, ce problème ne se pose pas. On peut ajouter autant de paramètre que l'on veut au message, 
ce dernier ne sera effectivement construit uniquement s'il doit être utilisé
```java
    SLF4J_LOGGER.info("Ce message {} plusieurs {}.", "possède", "paramètres"); // log de "Ce message possède plusieurs paramètres."
```
On a l'avantage du formatage clair du message, sans l'inconvénient de sa construction si on ne l'utilise pas.

#### Techniquement : Markers
Le système des markers (qui est un système n'existant pas ailleurs) permet d'associer des tags à certains logs. 
Ces logs seront alors traités de manière particulière selon la configuration (autre fichier, autre appender...)

Imaginons un système où un événement important devrait être notifié de manière spécifique, mais pas forcement pérenne... 
Et imaginons que le monde de rendu de cet événement puisse changer :
- sur la première version, on veut que ça mette un log warn
- sur la deuxième version, on veut une notification mail
- sur la troisème version, on veut que ça envoie le message via une socket SSL

Si vous n'avez pas de Marker, vous allez devoir développer et tester trois méthodes différentes sans passer par un logger.

Avec les markers, vous n'aurez qu'à définir un Appender spécifique pour chaque version.

Voici un exemple de la configuration possible avec LogBack:
```xml
<appender name="IMPORTANT_LOG" class="ch.qos.logback.core.FileAppender">
    <file>superImportant.log</file>
    <append>true</append>
    <encoder>
        <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
    </encoder>
    <evaluator class="ch.qos.logback.classic.boolex.OnMarkerEvaluator">
        <marker>SUPER_IMPORTANT</marker>
    </evaluator>
</appender>
```

```java
    Marker confidentialMarker = MarkerFactory.getMarker("SUPER_IMPORTANT");
    logger.warn(confidentialMarker, "Ce message est super important !");
```

Et pour la deuxième version, vous n'avez qu'à changer l'appender :
```xml
<configuration>   
  <appender name="IMPORTANT_LOG" class="ch.qos.logback.classic.net.SMTPAppender">
    <smtpHost>ADDRESS-OF-YOUR-SMTP-HOST</smtpHost>
    <to>EMAIL-DESTINATION</to>
    <to>ANOTHER_EMAIL_DESTINATION</to>
    <from>SENDER-EMAIL</from>
    <subject>Important : %logger{20} - %m</subject>
    <layout class="ch.qos.logback.classic.PatternLayout">
        <pattern>%date %-5level %logger{35} - %message%n</pattern>
    </layout>  
    <evaluator class="ch.qos.logback.classic.boolex.OnMarkerEvaluator">
        <marker>SUPER_IMPORTANT</marker>
    </evaluator>
  </appender>
```

etc...


A noter cependant que seule l'implémentation LogBack permet de bénéficier de cette fonctionnalité. 
Pour toutes les autres implémentation, rien ne se passera.

#### Techniquement : Redirection d'anciennes implémentations
Certaines applications ont pu au cours du temps disposer de plusieurs implémentations différentes dont certaines perdurent et dont on voudrait bien se débarasser.  
Seulement, dans certains cas, il n'est pas possible de tout changer d'un coup, ou il est nécessaire que d'anciennes méthodes de log utilisant d'anciennes API fonctionnent toujours 
(dans le cas de l'utilisation de framework/dépendances les utilisant par exemple).

La solution est alors de mettre en place des ponts des anciennes api vers de nouvelles.

Par exemple, si on a une application qui utilisait JCL, il suffit de supprimer le jar contenant l'implémentation jcl et de le remplacer par jcl-over-slf4j.
Celui-ci dispose de la même interface que JCL, mais redirige vers une implémentation SLF4J (par exemple logback).
Si cette même application disposait de l'utilisation de log4j, si on remplace le fichier log4j.jar par log4j-over-slf4j.jar, 
tous les logs se retrouvent automatiquement dans logback avec le gain de n'avoir qu'un fichier de configuration pour tous les journaux.


#### Fonctionnellement : Changement d'implémentation selon l'environnement
Prenons l'exemple d'une application standard possédant des logs. Il est décidé d'utiliser SLF4J.

Les developpeurs déclarent dont SLF4J dans le classpath de l'application, puis pour avoir des logs chez eux déclarent aussi loj4 et le bridge.

Mais l'application déployée tournera dans un serveur qui fournit une implémentation logback permettant d'envoyer les logs sur un serveur central (type graylog).
On a donc le choix de modifier comment on veut journaliser nos évènements.


La couche d'abstraction fournie par SLF4J permet de pouvoir décorréler l'interface de l'implémentation. 
De même, si sera plus facile de migrer vers une autre implémentation d'outil de journalisation si on utilise une interface.


Liens
-------------------
[Manuel de Slf4J](http://www.slf4j.org/manual.html)  
[Présentation sur Developpez.com](http://baptiste-wicht.developpez.com/tutoriels/java/slf4j/)  
[LogBack](http://logback.qos.ch/index.html)  
[Les appenders de Logback](http://logback.qos.ch/manual/appenders.html)  
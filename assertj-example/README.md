Présentation de AssertJ
===================

Présentation
-------------------
Le but de ce framework est de permettre des expressions de tests plus lisibles et plus claires que les assertions JUnit.   
L'idée est d'avoir pour chaque objet testé un ensemble de règles liées spécifiques au type précis de l'objet.  

Vous testez un String? Vous aurez des méthodes permettant de tester une chaîne de caractère.  
Vous testez une Map? Vous n'aurez pas les même méthodes.  

Pour faire vos tests, vous n'aurez la plupart du temps qu'une classe d'entrée à utiliser : org.assertj.core.api.Assertions  
La suite se fera automatiquement en fonction du paramètre passé.

Règles et normes autour des tests
-------------------
Quelques règles de base doivent être respectées pour la création de tests unitaires :
> - Une classe de test unitaire est dans une arborescence différence que la classe testée
> - Une classe de test unitaire est dans le même package que la classe testée
> - Une classe de test unitaire porte le même nom que la classe testée suffixée avec `Test`
> - Une classe de test doit être courte !
> - On peut scinder les tests d'une même classe en plusieurs classes sous la forme suivante MaClasse_NomDeLaMethodeTest


Idéologie des tests unitaires
-------------------
Un bon test unitaire est un test qui : 
> - Echoue uniquement quand un bug a été introduit
> - Echoue rapidement pour avoir un retour rapide sur le problème
> - Possède un scénario de test qui permet de comprendre rapidement ce qui est testé et ce qui doit être corrigé

De fait, il est recommandé d'écrire un grand nombre de tests très courts, testant à chaque fois un seul élément indépendamment des autres

Dans ce projet, la norme AAA est utilisée.
Pour chaque test, on retrouve
> - **Arrange** : Préparer les données (@Before avec jUnit)
> - **Act** : Lancer une action
> - **Assert** : Tester le résultat

Afin de simplifier la lecture et d'offrir à un relecteur une autodocumentation des tests, 
chaque méthode de test possède un nom qui explique ce qui se passe. 
Le plus simple est de le nommer sous la forme suivante : should_obtain_something_when_using_this_on_that()

Par exemple, vous voulez tester un couple de getter/setter sur un bean (c'est un exemple, il y a [openpojo](openpojo) pour faire ça pour vous)

```java
@Test
public void should_get_id_forty_two_when_setting_it_to_forty_two(){
    // Arrange
    MyBean bean = new MyBean();
    
    // Act
    bean.setId(42l);
    long beanId = bean.getId();
    
    // Assert
    assertThat(beanId)
    	.isEqualTo(42l);
} 
```
Dans la liste des tests, vous pourrez donc voir précisement où se trouvent les erreurs et quelles sont les causes sans avoir à mettre des logger

Liens utiles
-------------------

[Projet AssertJ](http://joel-costigliola.github.io/assertj/)  
[Sources Github du projet](https://github.com/joel-costigliola/assertj-core)
[Exemples d'utilisation dans le projet Github](https://github.com/joel-costigliola/assertj-examples) : les exemples sont pour les versions supportant java7 ou java8, donc pas totalement compatible avec la version java6

# ACL_2020_Goodenough
# Projet Pacman ACL 
* POIREL Florian
* LIAUD Alexis
* RIBEYROLLES Matthieu
* PALMIERI Adrien (Akahiro54)
* HYPPOLITE Ephraïm

#Pré-requis : 
* Maven 3.6.0 / 4.0.0+
* Java 11

Pour lancer le PacMan, il faut exécuter les commandes suivantes :

```bash
mvn clean
```

```bash
mvn javafx:compile
```

```bash
mvn javafx:run
```

#Description :
Le projet est un jeu inspiré de Pacman. Ce projet a été développé dans le cadre du cours d'ACL de M1. 
L'objectif du jeu est d'augmenter son score le plus possible, en collectant des pastilles dans des labyrinthes générés aléatoirement. Ces derniers contiennent des monstres qui tueront le joueur. La difficulté du jeu augmente au fur et à mesure des niveaux, augmantant la difficultée des monstres (leurs facons de se déplacer), le nombre de pastilles du labyrinthe et le temps attribué au joueur pour compléter le labyrinthe.

![Image illustration](https://github.com/liaud1u/ACL_2020_Goodenough/blob/Sprint2/pacman.png?raw=true)

#Commandes :
Les déplacements se font gràce aux flêches directionnelles uniquement. 
La souris permettra de valider le passage aux niveaux suivants. 
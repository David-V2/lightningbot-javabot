
## C'est quoi LightningBot  
  
LightningBot est un jeu au tour par tour. C'est à dire que passé la phase d'initialisation,  les joueurs ont un temps 
limité pour donner leur action du tour. Puis le jeu calcul le résultat et commence le tour suivant.  
  
Le but du jeu est simple : être le dernier bot vivant. Pour cela, chaque joueur contrôle son bot via une API.  
  
Les actions possibles sont limités : se déplacer vers le haut, le bas, la gauche ou la droite. Facile !  Aucun risque de sortir du terrai : si un bot atteins la limite à gauche, il se retrouve à droite, s'il franchis le haut de la grille, il apparaît en bas.
  
Mais chaque bot laisse une trace derrière lui, et lorsqu'un bot percute un autre bot ou une trace, il est détruit.  
  
  
## Comment fonctionne une partie  
  
Lorsque le premier joueur lance la partie, les autres bots ont 20 secondes pour le rejoindre.  
Ils ont ensuite 2 secondes pour demander et exploiter les informations sur la partie (taille de la grille et  position de départ des autres joueurs).  Enfin les tours commenceront à s’enchaîner.  
  
A chaque tour les joueurs auront 2 secondes pour réclamer les informations du tour précédents (actions des autres  joueurs), les utiliser (ou pas !) pour prendre une décision et donner leur action pour le tour en cour.  
  
Attention, donner son action trop tôt ou trop tard provoque une erreur !  
  
## A quoi sert le code source fourni ?  
  
Le code source fourni vous permet de ne pas vous préoccuper des chronomètres et des appels API !  
  
Toute la partie "mesure du temps" et "orchestration" de la partie est prise en charge par la classe abstraite "Basic".  
  
Toute la partie API est prise en charge par la classe "GameClient".  
  
Il ne vous reste qu'a coder l'intelligence : dans la classe MyAI  
  
## L'intelligence ?  
  
Il y a 3 méthodes ou coder l'intelligence de votre bot :  
- receiveInformation  
- receiveDirections  
- choseDirection  
  
Chaque méthode à implémenter est commenté dans la classe "MyAI", mais je conseille fortement la lecture des règles :  https://lightningbot.cf/doc

## Lancement de la partie

Une fois l'application lancée, pour démarrer une partie il faut appeler le endpoint /start.
Notez que pour les parties classées, il faudra renseigner votre token et désactiver le mode test dans "application.yaml"
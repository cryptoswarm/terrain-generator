# INF5153 : Generation Procédurale de Terrain

  - Auteur : Sébastien Mosser
  - Version : 2020.10
  
## Idée générale du projet

Le but du projet est de définir un générateur procédural de terrain, qui va recevoir en entrée un maillage géométrique (un ensemble de polygones pavant le plan). Sur la base de ce maillage, le programme va fabriquer un terrain (lacs, montagnes, villages, routes, forêts, ...), et produire en sortie un maillage "enrichi" qui pourra être visualisé. 

La génération des maillages, ainsi que leur visualisation, ne sont pas dans le périmètre du projet (outils fournis). Votre objectif est de concevoir et développer uniquement le générateur de terrain.

## Description du dépôt initial

Le dépot de code est un projet _Maven_ classique. Le repertoire `src/main/java` contient le code source de l'application, et le répertoire `src/test/java` les tests unitaires.

Le projet vient avec deux outils externes pour gérer la dimension "mathématique" des terrains générés : _(i)_ un générateur de maillages (`tools/generator-X.y.jar`) et _(ii)_ un visualisateur de maillages (`tools/visualizer-X.y.jar`). A la date de démarrage du projet, ces deux outils sont en version `1.0` (donc X=1 et y=0).

Des maillages de références sont fournis, dans le repertoire `sample`. Chaque maillage de référence est identifié selon la convention `sample-W-H-P.mesh`, où `W` est la largeur du maillage, `H` sa hauteur et `P` son degré de précision (en nombre de polygones).

Pour compiler le projet, il suffit d'invoquer Maven depuis la ligne de commande : 

```
mosser@lucifer projet-ptg % mvn clean package
``` 

Une fois le projet compilé, la version initiale prend en charge deux options : _(i)_ `-i` pour spécifier le maillage fourni en entrée, et _(ii)_ `-o` pour spécifier le nom du maillage enrichi qui sera fourni en sorti.

```
mosser@lucifer projet-ptg % mvn exec:java -Dexec.args="-i samples/sample-1000-800-512.mesh -o demo.mesh"
``` 

Ou, plus simplement, vous pouvez utiliser le script `ptg.sh` fourni, qui fait le relais avec Maven : 

```
mosser@lucifer projet-ptg % ./ptg.sh -i samples/sample-1000-800-512.mesh -o demo.mesh
```

## Outils disponibles

Les deux outils nécéssitent une version de Java égale ou supérieure à 13 pour s'éxecuter. 

### Code initial (`src/main/java`)

Le code initial est un code procédural qui sert de démonstration à la lecture d'un maillage, son enrichissement, puis l'écriture du maillage enrichi. Il utilise la bibliothèque Apache Commons CLI pour analyser la ligne de commande.

### Visualisation de maillages (`tools/visualizer-X.y.jar`)

Pour visualiser un maillage, vous avez l'outil à disposition l'outil `visualizer` dans le repertoire `tools`. C'est un jar auto-exécutable, qui répond aux options suivantes : 

  - `-i` : le maillage a visualiser (défaut : `data.mesh`)
  - `-o` : le fichier où la visualisation sera enregistrée (défaut : `output.svg`)
  - `-t` : le format de la visualisation (défaut : `svg`)

L'outil de visualisation peut générer des images vectorielles (formats `svg` ou `pdf`), ou _bitmap_ (formats `png` ou `jpg`). 

```
mosser@lucifer tools % java -jar visualizer-1.0.jar -o demo.png -t png -i samples/sample-1000-800-512.mesh 
mosser@lucifer tools % java -jar visualizer-1.0.jar -o demo.pdf -t pdf -i samples/sample-1000-800-512.mesh 
```

La visualisation dispose d'un mode de déboguage (`-d`), qui va afficher sur la sortie standard une description textuelle du maillage, et générer une image qui dessine uniquement les polygones et leurs relations de voisinages.

```
mosser@lucifer tools % java -jar visualizer-1.0.jar -d -o demo.png -t png -i samples/sample-1000-800-512.mesh > description.txt
```

### Génération de maillages (`tools/generator-X.y.jar`)

Si vous souhaitez génerer vos propre maillages, vous pouvez utiliser l'outil `generator` dans le répertoire `tools`. C'est un jar auto-exécutable, qui répond aux options suivantes : 

  - `-o` : fichier de sortie pour enregistrer le maillage (défaut : `data.mesh`)
  - `-W` : largeur (_width_) du maillage (défaut : `1000`)
  - `-H` : hauteur (_height_) du maillage (défaut : `1000`)
  - `-P` : précision (nombre de polygones) du maillage (défaut : `512`)
  - `-L` : coefficient de relaxation (arrondissement) de Lloyd (défaut : `10`)

Pour génerer dans le fichier `demo.mesh` un maillage de 750 pixels de large et 500 pixels de haut, incluant 300 polygones, il faut donc invoquer : 

```
mosser@lucifer tools % java -jar generator-1.0.jar -o demo.mesh -W 750 -H 500 -P 300
```

## Dépendances logicielle & Écosystème disponible

### Test unitaires : JUnit 4.12

Pour simplifier l'écriture et l'exécution de tests unitaire dans Maven, vous **devez** utiliser JUnit en version `4.12`. La version `5` est certes plus récente, mais génère des problèmes sur certaines machines, et dans un contexte à distance ça rend le suivi des projet trop difficile.

### Gestion de la ligne de commande : Apache Commons CLI 1.4

Cette bibliothèque permet de gérer facilement l'analyse de la ligne de commande pour extraire les options de configuration du générateur de terrain. Vous pouvez vous inspirer de l'exemple fourni

### Intégration continue : Github Actions

Dans le repertoire `.github/workflows` se trouve un _pipeline_ pour compiler votre projet à chaque `push` sur Github (fichier `java.yaml`)

# [CPOO5] Compléments en Programmation Orientée Objet, Projet : Serpents 

## Auteurs

Ce projet a été réalisé par:
- GUETTEVILLE Nathan
- SOAN Tony Ly

## Dépendances

```txt
$ java --version
openjdk 17.0.9 2023-10-17
OpenJDK Runtime Environment Temurin-17.0.9+9 (build 17.0.9+9)
OpenJDK 64-Bit Server VM Temurin-17.0.9+9 (build 17.0.9+9, mixed mode, sharing)
```

Ce projet contient aussi une configuration gradle (`build.gradle`, `settings.gradle`).

La configuration gradle contient les déclarations de toutes ces dépendances, ainsi que des définitions de tâches pour exécuter les différentes démos. Notamment :

- `./gradlew run` : lance le jeu dans une fenêtre graphique.
- `./gradlem test` : lance les tests du projet qui ont été effectués à l'aide de `JUnit5` et `Mockito` (définis dans `src/test/java`). Vous pourrez lire le rapport généré dans le fichier `build/reports/tests/test/index.html`.

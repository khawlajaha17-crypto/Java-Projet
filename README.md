# Gestion des notes

Petit projet Java pour gérer des étudiants et leurs notes en utilisant un fichier CSV.

## Description

- Application console minimale qui démarre depuis la classe `app.Main`.
- Le projet inclut un contrôleur `StudentController`, un modèle `Student` et des utilitaires CSV (`CsvUtils`).
- Le fichier de données exemple est `notes.csv` à la racine du projet.

## Prérequis

- JDK 11 ou supérieur installé.
- Java `javac` et `java` disponibles dans le PATH, ou ouvrir le projet dans un IDE (IntelliJ IDEA recommandé).

## Structure du projet

- `src/app/Main.java` : point d'entrée principal (`package app`).
- `src/app/controllers/StudentController.java` : logique de menu et gestion des étudiants.
- `src/models/Student.java` : modèle `Student`.
- `src/utils/CsvUtils.java` : lecture/écriture du fichier CSV.
- `notes.csv` : fichier de données (exemple).

## Compiler et exécuter (PowerShell recommandé)

1. Depuis la racine du projet :

```powershell
mkdir out  # si inexistant
Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object FullName > sources.txt
javac -d out @sources.txt
```

2. Lancer l'application :

```powershell
java -cp out app.Main
```

Remarque : si vous utilisez un IDE, importez le projet comme projet Java/Maven (selon configuration) et lancez `app.Main`.

## Utilisation

- Suivre le menu affiché par `StudentController` pour ajouter/listing/modifier des étudiants.
- Le fichier `notes.csv` est lu/écrit par `CsvUtils` (vérifiez le chemin relatif si vous exécutez depuis un IDE différent).

## FAQ rapide

- Q : Où est le point d'entrée ?
- A : `app.Main` (package `app`).

## Contact

Pour des améliorations, tests ou questions, ajouter une issue ou modifier le README.

# Docker

Avec la dernière version du répertoire git, vous devriez avoir les fichiers docker-compos nécessaires et utiliser les commandes suivantes :
- ``docker-compose -f docker-compose-pg.yml up -d``
- ``docker-compose -f docker-compose-nosithouss.yml up -d``
- ``docker-compose -f docker-compose-etl.yml up -d`` (Si vous souhaitez lancer l'etl...)
- ``docker-compose -f docker-compose-web.yml up -d``

Cependant si ça ne marche pas, suivez la procédure ci-dessous.

# BDD - postgres

Lancer la base de données postgres avec docker-compose :

### Prérequis

- docker, docker-compose ou docker Desktop, avoir une connexion internet, avoir les droits administrateurs sur sa machine.

### Procédure

- Se rendre dans le dossier docker du projet :
    - ```cd docker/```
- Executer le fichier docker-compose-pg.yml avec docker compose :
    - ```docker-compose -f docker-compose-pg.yml up -d```
- Verifier que le conteneur est bien lancé :
    - ``docker container ls -a``
        - Verifier que le conteneur est bien ``UP`` avec le port ``0.0.0.0:5432->5432/tcp`` avec le nom ``mspr_java``
    - ``docker image ls -a``
        - le répertoire est ``postgres``, tag : ``lastest``

### En cas d'erreur

- Stopper touts les autres conteneurs:
    - ``docker stop mon_conteneur``
- Vérifier qu'une autre image de postgres n'existe pas déjà, dans le doute, on la supprime...
    - ``docker image rm image_id``
- Vérifier que postgres n'est pas lancé, si c'est le cas on le stop :
    - ``systemctl stop postgresql``
- Vérifier que le port n'est pas déjà utilisé pas un autre processus si c'est le cas, on le stop
    - ``lsof -i :5432``

En cas d'erreur, il faut supprimer le conteneur et son image (mspr_java et postgres_java) et refaire la procédure.

### Se connecter

Se rendre sur l'icône database de son intellij :

![img](../.assets/1.png)

Cliquer sur le "+" pour ajouter une datasource, sélectionner postgres :

![img](../.assets/2.png)

L'url de connexion est : ``jdbc:postgresql://localhost:5432/arosaje``, les informations de connexion se trouvent dans le
fichier ``docker-compose-pg.yml``

![img](../.assets/3.png)

S'assurer que les bons schémas sont sélectionnés (se rendre dans l'onglet schémas) :

![img](../.assets/4.png)

Vous devriez avoir ce résultat, si vous n'avez pas lancé le back, le nombre de tables doit être différent :

![img](../.assets/5.png)

# Nosithouss - app java servlet

Lancer l'application java nosithouss en mode serveur web.

### Prérequis

- docker, docker-compose ou docker Desktop
- Connexion internet
- Droits administrateurs de sa machine
- Aucun service n'utilise le port (Ne pas lancer l'application avec intellij)

### Procédure

- Il faut tout d'abord créer un dossier "data-nosithouss" dans votre dossier "docker"  et notre conteneur pourras aller lire
  dedans
- Déployez votre projet afin de récupérer votre fichier .jar, à la racine du projet : ``mvn clean install``
- Récupérez ce fichier ``./target/nosithouss.jar`` fraîchement créer et copier le dans votre
  dossier ``./docker/data-noithouss/`` sous le nom de`` nosithouss.jar``, sous
  linux : ``sudo cp ./target/nosithouss-0.0.1.jar ./docker/data-nosithouss/nosithouss.jar``
- Plus qu'a lancer votre docker container : ``docker-compose -f docker-compose-nosithouss up -d``
- Petite vérification : ``docker ps -a``, le résultat attendu est :
    - ``fc4da229f3a3 // openjdk:21 // "java -jar /app/nosi…" // 8 minutes ago // UP // nosithouss``

### En cas d'erreur

- Analysez les logs du conteneur : ``docker logs nosithouss``
- Vérifier que le port n'est pas déjà utilisé pas un autre processus si c'est le cas, on le stop : ``lsof -i :8080``
- Vérifiez les profiles : ``./ressources/application.ym``, ``./ressources/application-dev.ym``, ``./ressources/application-prod.ym``

# Nosithouss - ETL

``docker-compose -f docker-compose-etl.yml up -d``
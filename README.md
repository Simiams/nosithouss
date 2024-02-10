# Nosithous

## ETL - extract data from trefle.io

L'ETL permet de remplir sa base de donnée via l'api de trefle.io, voici les étapes pour le lancer:

- Dans votre fichier ressource/application.yml modifier ces chap de cette façon;
    - ``spring.main.web-application-type: none``
    - ``spring.main.etl: true``
- Sur intellij, editez la configuration de lancement de votre application (clique droit sur les trois petit points + edit):

![img](./.assets/8.png)

-  Et ajoutez une nouvelle configuration de la sorte, la limite correspond au nombre de poste que vous voulez extraire:

![img](./.assets/6.png)

### Note
Pour revenir à une configuration serverlet de base, il suffit juste de remplacer ces champs dans votre fichier ressource/application.yml:
- ``spring.main.web-application-type: none``
- ``spring.main.etl: true``

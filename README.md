# Nosithouss

## Livrable

Vous trouverez touts les livrables (Benchmark, maquette, documentation) sur les document préfixés de "LIVRABLE-"

Un second readme se trouve dans le dossier docker

---

### Swagger

Tout nos endpoints sont documentés via la docApi grâce à swagger-ui disponible à cet url (Veuillez lancer l'application
au préalable soit via le container soit via votre IDE): [Swagger](http://localhost:8080/swagger-ui/index.html#/)

Vous y trouverez les schémas de réponse, les attendus en paramètre, une description de l'endpoint.

### Migrations

FlyWay est un outil de gestion de migrations, vous trouvez toutes les requêtes sql dans le
dossier ``ressources/migrations`` elle seront jouées dans l'ordre, ``V1 -> V6``. Comme vous le remarquez, le
fichier ``V1_Init.sql`` génere toutes nos tables avec les index et les clés étrangères associées. Néanmoins, la configuration
de nos ORM tel que spring boot le permet est correct, ce fichier est en fait une DLL que nous avons extrait une fois que
spring ai créer nos tables. Nous l'avons créé pour garder la main sur nos relations et conserver une logique (ordre,
nommage, etc...)

Vous trouverez donc un jeu de donnée assez intéressant prouvant les différentes relations entre les tables...

## Toolbox - DEV

---

## ETL - extract data from trefle.io

L'ETL permet de remplir sa base de données via l'api de trefle.io, voici les étapes pour le lancer :

- Dans votre fichier ressource/application.yml modifiez ces champs de cette façon :
    - ``spring.main.web-application-type: none``
    - ``spring.main.etl: true``
- Sur intellij, éditez la configuration de lancement de votre application (clique droit sur les trois petits points +
  edit) :

![img](./.assets/8.png)

- Et ajoutez une nouvelle configuration de la sorte, la limite correspond au nombre de poste que vous voulez extraire (
  si vous ne spécifiez pas de limite ou qu'elle est inferieur ou égale à zéro, le programme récuperera toute les données
  de trefle.io). Tapez ``alt+R`` pour avoir le champ argument du programme,
  sinon: ``Modify options -> 'Java' -> Program arguments``:

![img](./.assets/6.png)

### Note

Pour revenir à une configuration serverlet de base, il suffit juste de remplacer ces champs dans votre fichier
ressource/application.yml:

- ``spring.main.web-application-type: none``
- ``spring.main.etl: true``

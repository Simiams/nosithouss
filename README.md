# Nosithous

## Livrable

---

### Swagger

Tout nos endpoint sont documenté via la docApi grâce à swagger-ui disponnible à cet url (Veuillez à lancer l'application
au préalable soit via le container soit via votre IDE): [Swagger](http://localhost:8080/swagger-ui/index.html#/)

Vous y trouverai les schemas de réponse, les attendu en paramétre, une description de l'endpoint

### Migrations

FlyWay est un outil de gestion de migrations, vous trouvez toutes les requete sql dans le
dossier ``ressources/migrations`` elle serons jouées dans l'ordre, ``V1 -> V6``. Comme vous le remarquez, le
fichier ``V1_Init.sql`` génere toute nos table avec les index et les foerign key associée. Néanmoins, la configuration
de nos orm tel que spring boot le permet est coorect, ce fichier est enfaite un ddl que nous avons extrait une fois que
spring ai créer nos table. Nous l'avons créer pour garder la main sur nos relation et conserver une logique (orde,
nommage, etc...)

Vous trouverez donc un jeu de donnée assez interessant prouvant les different reltino entre les tables...

## Toolbox - DEV

---

## ETL - extract data from trefle.io

L'ETL permet de remplir sa base de donnée via l'api de trefle.io, voici les étapes pour le lancer:

- Dans votre fichier ressource/application.yml modifier ces chap de cette façon;
    - ``spring.main.web-application-type: none``
    - ``spring.main.etl: true``
- Sur intellij, editez la configuration de lancement de votre application (clique droit sur les trois petit points +
  edit):

![img](./.assets/8.png)

- Et ajoutez une nouvelle configuration de la sorte, la limite correspond au nombre de poste que vous voulez extraire (
  si vous ne specifiez pas de limite ou qu'elle est inferieur ou egale à zéro, le programme récupéreras toute les donnée
  de trefle.io). Tapez ``alt+R`` pour avoir le champ argument de programme,
  sinon: ``Modify options -> 'Java' -> Program arguments``:

![img](./.assets/6.png)

### Note

Pour revenir à une configuration serverlet de base, il suffit juste de remplacer ces champs dans votre fichier
ressource/application.yml:

- ``spring.main.web-application-type: none``
- ``spring.main.etl: true``

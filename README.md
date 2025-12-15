# PalAPI - API REST Palworld

Une API REST en Java/Spring Boot pour gérer les créatures Pal de Palworld. Simple, fonctionnelle, et dockerisée.

**Auteur :** Ayoub AMRANI  
**Date :** 15 Décembre 2025

---

## C'est quoi ce projet ?

PalAPI est une API qui permet de gérer 137 créatures Pal (issues de Palworld) via des requêtes HTTP classiques. Le projet tourne avec PostgreSQL pour la persistance et est entièrement containerisé avec Docker.

En gros : vous lancez un `docker compose up`, et boom, vous avez une API fonctionnelle qui répond sur le port 8081.

## Ce qu'il fait

- CRUD complet sur les Pals (créer, lire, modifier, supprimer)
- Système de compteurs pour des opérations incrémentales/décrémentales
- Base PostgreSQL embarquée
- Documentation Swagger intégrée
- Healthchecks sur tous les services
- Image Docker optimisée (multi-stage build)
- Sécurité : utilisateur non-root dans le conteneur

## Architecture simplifiée

```
Vous (navigateur/Postman)
         ↓
    PalAPI (Java Spring Boot) - port 8081
         ↓
    PostgreSQL (base de données)
```

Tout est orchestré via Docker Compose. Chaque service a son healthcheck pour vérifier qu'il tourne bien.

## Comment lancer ce truc ?

### Prérequis

- Docker (version 24+)
- Docker Compose (version 2.20+)

C'est tout. Pas besoin de Java installé, pas besoin de PostgreSQL, Docker gère tout.

### Installation rapide

```bash
# 1. Cloner le repo
git clone https://github.com/yurri775/palapi-pal-default.git
cd palapi-pal-default

# 2. Configurer les variables d'environnement
cp .env.example .env
# Ouvrez .env et changez le mot de passe PostgreSQL

# 3. Builder l'image
docker compose build

# 4. Lancer l'application
docker compose up -d

# 5. Vérifier que tout tourne
docker compose ps
```

Voilà, c'est lancé. L'API est accessible sur `http://localhost:8081`.

### Accès aux services

- **API REST** : http://localhost:8081/api/pals
- **Documentation Swagger** : http://localhost:8081/swagger-ui.html
- **Health Check** : http://localhost:8081/actuator/health

### Arrêter l'application

```bash
# Arrêter proprement
docker compose down

# Arrêter ET supprimer les données (volumes)
docker compose down -v
```

## Endpoints disponibles

### Pals

- `GET /api/pals` - Liste tous les Pals
- `GET /api/pals/{id}` - Récupère un Pal spécifique
- `POST /api/pals` - Crée un nouveau Pal
- `PUT /api/pals/{id}` - Modifie un Pal existant
- `DELETE /api/pals/{id}` - Supprime un Pal

### Compteurs

- `POST /api/counters?name={name}` - Crée un compteur
- `POST /api/counters/{name}/increment` - Incrémente (+1)
- `POST /api/counters/{name}/decrement` - Décrémente (-1)
- `GET /api/counters/{name}` - Récupère la valeur actuelle

## Pourquoi ces choix techniques ?

**Java 21** : Version LTS, stable, performante. Virtual Threads inclus.

**PostgreSQL** : Une vraie base de données relationnelle pour simuler un environnement réel. Pas de H2 en mémoire, on veut de la persistence.

**Multi-Stage Build** : L'image Docker est construite en deux étapes : compilation avec Maven, puis exécution avec un JRE minimal. Résultat : image finale plus légère.

**Alpine Linux** : Distribution minimaliste. Moins de poids, moins de surface d'attaque.

**Utilisateur non-root** : Le conteneur tourne avec un user dédié (pas `root`). Bonne pratique de sécurité standard.

## Sécurité

L'image a été scannée avec **Trivy** et **Docker Scout**. Le rapport complet est dans `security_report.txt`.

Pour scanner vous-même :

```bash
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
  aquasec/trivy image yurri775/palapi:latest
```

Aucune vulnérabilité critique détectée.

## Image Docker Hub

L'image est publique et prête à l'emploi :

**Docker Hub** : [yurri775/palapi](https://hub.docker.com/r/yurri775/palapi)

```bash
# Pull l'image directement
docker pull yurri775/palapi:1.0.0

# Ou lancer directement
docker run -p 8081:8081 yurri775/palapi:1.0.0
```

## Notes du projet

Ce projet a été réalisé dans le cadre d'un cours sur la containerisation Docker. Toutes les bonnes pratiques demandées ont été implémentées : multi-stage build, healthchecks, utilisateur non-root, scan de sécurité, documentation complète.

Version actuelle : **1.0.0**

## Contact

**Ayoub AMRANI**

- GitHub : [yurri775](https://github.com/yurri775)
- Docker Hub : [yurri775/palapi](https://hub.docker.com/r/yurri775/palapi)

## Références

- [Docker Best Practices](https://docs.docker.com/develop/dev-best-practices/)
- [Spring Boot Docker Guide](https://spring.io/guides/topicals/spring-boot-docker)

---

**Licence** : Projet académique - Libre d'utilisation

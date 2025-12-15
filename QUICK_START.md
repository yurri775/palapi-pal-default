# 🚀 Guide de Démarrage Rapide - PalAPI

**Auteur:** Marwa (yurri775)
**Date:** Janvier 2025

---

## 📦 Option 1 : Utiliser l'image depuis Docker Hub (Le plus rapide)

### Prérequis

- Docker installé
- 2 GB d'espace disque

### Étapes

```bash
# 1. Pull l'image depuis Docker Hub
docker pull yurri775/palapi:1.0.0

# 2. Créer un réseau
docker network create palapi-network

# 3. Lancer PostgreSQL
docker run -d \
  --name palapi-postgres \
  --network palapi-network \
  -e POSTGRES_DB=paldb \
  -e POSTGRES_USER=paluser \
  -e POSTGRES_PASSWORD=palpass \
  postgres:16.1-alpine

# 4. Attendre 10 secondes que PostgreSQL démarre
sleep 10

# 5. Lancer PalAPI
docker run -d \
  --name palapi-backend \
  --network palapi-network \
  -p 8081:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://palapi-postgres:5432/paldb \
  -e SPRING_DATASOURCE_USERNAME=paluser \
  -e SPRING_DATASOURCE_PASSWORD=palpass \
  yurri775/palapi:1.0.0

# 6. Vérifier que ça fonctionne
curl http://localhost:8081/actuator/health
```

### Accéder à l'application

- **Swagger UI:** http://localhost:8081/swagger-ui.html
- **API REST:** http://localhost:8081/api/pals
- **Health:** http://localhost:8081/actuator/health

---

## 🏗️ Option 2 : Build depuis les sources

### Prérequis

- Docker + Docker Compose
- Git

### Étapes

```bash
# 1. Cloner le projet
git clone https://github.com/yurri775/palapi.git
cd palapi

# 2. Copier les variables d'environnement
cp .env.example .env

# 3. Lancer avec docker-compose
docker compose up -d

# 4. Vérifier les logs
docker compose logs -f

# 5. Tester
curl http://localhost:8081/actuator/health
```

---

## 🧪 Tester le système de compteurs

```bash
# Créer un compteur "visiteurs"
curl -X POST "http://localhost:8081/api/counters?name=visiteurs"

# Incrémenter (+1)
curl -X POST "http://localhost:8081/api/counters/visiteurs/increment"

# Incrémenter encore 5 fois
for i in {1..5}; do
  curl -X POST "http://localhost:8081/api/counters/visiteurs/increment"
done

# Voir la valeur actuelle
curl http://localhost:8081/api/counters/visiteurs
# Résultat: {"name":"visiteurs","value":6}

# Décrémenter (-1)
curl -X POST "http://localhost:8081/api/counters/visiteurs/decrement"

# Réinitialiser à 0
curl -X POST "http://localhost:8081/api/counters/visiteurs/reset"
```

---

## 🛑 Arrêter l'application

### Si lancé avec docker run

```bash
docker stop palapi-backend palapi-postgres
docker rm palapi-backend palapi-postgres
docker network rm palapi-network
```

### Si lancé avec docker-compose

```bash
docker compose down
# Ou pour supprimer aussi les données:
docker compose down -v
```

---

## 📊 Commandes utiles

```bash
# Voir les conteneurs actifs
docker ps

# Voir les logs en temps réel
docker logs -f palapi-backend

# Entrer dans le conteneur
docker exec -it palapi-backend sh

# Voir les métriques
docker stats palapi-backend

# Vérifier la base de données
docker exec -it palapi-postgres psql -U paluser -d paldb
```

---

## 🔗 Liens utiles

- **Docker Hub:** https://hub.docker.com/r/yurri775/palapi
- **GitHub:** https://github.com/yurri775/palapi
- **Swagger UI:** http://localhost:8081/swagger-ui.html
- **API Docs:** http://localhost:8081/v3/api-docs

---

## 🆘 Besoin d'aide ?

Consultez le [README.md](README.md) complet pour plus de détails.

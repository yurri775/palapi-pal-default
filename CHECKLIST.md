# ✅ Checklist de rendu - PalAPI

## 📦 1. Image sur Docker Hub

- [ ] Compte Docker Hub créé
- [ ] Image buildée localement : `docker compose build`
- [ ] Image taggée : `docker tag palapi:latest username/palapi:1.0.0`
- [ ] Image pushée : `docker push username/palapi:1.0.0`
- [ ] Image accessible publiquement (vérifier sur hub.docker.com)
- [ ] Lien Docker Hub ajouté dans le README

**Commandes à exécuter :**

```bash
docker login
docker compose build
docker tag palapi:latest VOTRE_USERNAME/palapi:1.0.0
docker push VOTRE_USERNAME/palapi:1.0.0
```

## 🔒 2. Scan de sécurité

- [ ] Dossier `security-reports/` créé
- [ ] Scan Docker Scout exécuté
- [ ] Scan Trivy exécuté (optionnel mais recommandé)
- [ ] Rapports sauvegardés dans `security-reports/`
- [ ] Résumé ajouté dans le README
- [ ] Analyse des vulnérabilités faite

**Commandes à exécuter :**

```bash
mkdir security-reports
docker scout quickview palapi:latest > security-reports/scout-summary.txt
docker scout cves palapi:latest > security-reports/scout-detailed.txt
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
  aquasec/trivy image --format table --output security-reports/trivy-report.txt palapi:latest
```

## 📝 3. Documentation

- [ ] README.md complet et "HUMAN MADE"
- [ ] Section "Build" avec commandes exactes
- [ ] Section "Run" avec commandes exactes
- [ ] Section "Choix techniques" justifiés
- [ ] Section "Image sur Docker Hub"
- [ ] Section "Rapport de sécurité"
- [ ] .env.example présent
- [ ] COMMANDS.md créé

## 🐳 4. Docker

- [ ] Dockerfile avec multi-stage build
- [ ] Utilisateur non-root (UID 1001)
- [ ] Versions pinnées (SHA256)
- [ ] .dockerignore présent
- [ ] docker-compose.yml avec PostgreSQL
- [ ] Healthchecks configurés
- [ ] Variables d'environnement documentées

## 🎯 5. Fonctionnalités

- [ ] Système de compteurs incr/decr fonctionnel
- [ ] API Pals CRUD fonctionnel
- [ ] PostgreSQL persistant
- [ ] Swagger UI accessible
- [ ] Healthchecks répondent

**Test rapide :**

```bash
docker compose up -d
curl http://localhost:8081/actuator/health
curl -X POST "http://localhost:8081/api/counters?name=test"
curl -X POST "http://localhost:8081/api/counters/test/increment"
```

## 📊 6. Fichiers à rendre

- [ ] Code source complet
- [ ] Dockerfile
- [ ] docker-compose.yml
- [ ] .dockerignore
- [ ] .env.example
- [ ] README.md
- [ ] security-reports/ (dossier complet)
- [ ] COMMANDS.md
- [ ] CHECKLIST.md (ce fichier)

## 🎓 7. Avant de rendre

- [ ] Tester le `docker pull` de votre image
- [ ] Tester le `docker compose up` complet
- [ ] Vérifier que tous les endpoints fonctionnent
- [ ] Relire le README pour les fautes
- [ ] Vérifier que les rapports de sécurité sont lisibles
- [ ] Supprimer les fichiers de test/debug
- [ ] Commit et push sur GitHub (si demandé)

---

**Date de vérification :** ******\_\_\_******
**Nom :** Marwa
**Projet :** PalAPI - Containerisation Docker

✅ **TOUT EST PRÊT POUR LE RENDU !**

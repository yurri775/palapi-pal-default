# 🪟 Instructions Windows - PalAPI

Guide spécifique pour utilisateurs Windows.

---

## ⚠️ Prérequis Windows

1. **Docker Desktop** installé et **démarré**

   - Télécharger : https://www.docker.com/products/docker-desktop/
   - ✅ Vérifier qu'il tourne dans la barre des tâches

2. **PowerShell ou CMD** (déjà installé)

---

## 📦 Étape 1 : Créer le dossier scripts

```cmd
mkdir scripts
```

---

## 🚀 Étape 2 : Push vers Docker Hub

### Commande manuelle (étape par étape)

```cmd
REM 1. Login Docker Hub
docker login

REM 2. Build l'image
docker compose build

REM 3. Tag l'image
docker tag palapi:latest yurri775/palapi:1.0.0
docker tag palapi:latest yurri775/palapi:latest

REM 4. Push vers Docker Hub
docker push yurri775/palapi:1.0.0
docker push yurri775/palapi:latest

REM 5. Vérifier
docker pull yurri775/palapi:1.0.0
```

### OU avec le script automatisé

```cmd
REM Lancer le script
scripts\push-to-dockerhub.bat
```

---

## 🔒 Étape 3 : Scan de sécurité

### Méthode 1 : Docker Scout (Recommandé)

```cmd
REM Créer le dossier pour les rapports
mkdir security-reports

REM Vue rapide
docker scout quickview palapi:latest

REM Rapport détaillé
docker scout cves palapi:latest > security-reports\scout-report.txt
```

### Méthode 2 : Trivy via Docker

```cmd
REM Scan avec Trivy (pas besoin d'installation)
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy image palapi:latest

REM Sauvegarder le rapport
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy image --format table palapi:latest > security-reports\trivy-report.txt
```

### OU avec le script automatisé

```cmd
REM Lancer le script de scan
scripts\security-scan.bat
```

---

## 🧪 Étape 4 : Tester l'application

```cmd
REM Lancer l'application
docker compose up -d

REM Attendre 30 secondes que tout démarre
timeout /t 30

REM Vérifier la santé
curl http://localhost:8081/actuator/health

REM Tester les compteurs
curl -X POST "http://localhost:8081/api/counters?name=test"
curl -X POST "http://localhost:8081/api/counters/test/increment"
curl http://localhost:8081/api/counters/test

REM Ouvrir Swagger dans le navigateur
start http://localhost:8081/swagger-ui.html
```

---

## 🛑 Arrêter l'application

```cmd
REM Arrêter les conteneurs
docker compose down

REM Arrêter ET supprimer les données
docker compose down -v
```

---

## 🔧 Dépannage Windows

### Problème : "Docker daemon is not running"

```cmd
REM Solution : Démarrer Docker Desktop
REM 1. Cherchez "Docker Desktop" dans le menu Démarrer
REM 2. Lancez-le
REM 3. Attendez que l'icône soit verte dans la barre des tâches
```

### Problème : "permission denied" sur /var/run/docker.sock

```cmd
REM Sur Windows, utilisez cette commande pour Trivy:
docker run --rm -v //var/run/docker.sock:/var/run/docker.sock aquasec/trivy image palapi:latest
REM Notez les deux slashes // au début
```

### Problème : "curl" n'est pas reconnu

```cmd
REM Option 1: Installer curl via Chocolatey
choco install curl

REM Option 2: Utiliser Invoke-WebRequest (PowerShell)
Invoke-WebRequest -Uri http://localhost:8081/actuator/health

REM Option 3: Ouvrir dans le navigateur
start http://localhost:8081/actuator/health
```

### Problème : Le port 8081 est déjà utilisé

```cmd
REM Trouver le processus qui utilise le port
netstat -ano | findstr :8081

REM Tuer le processus (remplacez PID par le numéro trouvé)
taskkill /PID <PID> /F

REM OU changer le port dans .env
echo HOST_PORT=8082 >> .env
```

---

## 📋 Commandes rapides

```cmd
REM Tout en une ligne - Build, Tag, Push
docker compose build && docker tag palapi:latest yurri775/palapi:1.0.0 && docker push yurri775/palapi:1.0.0

REM Voir les images
docker images

REM Voir les conteneurs
docker ps -a

REM Nettoyer tout
docker system prune -a --volumes
```

---

## 🎯 Checklist finale

- [ ] Docker Desktop est démarré
- [ ] `docker compose build` fonctionne
- [ ] `docker scout quickview palapi:latest` génère un rapport
- [ ] `docker login` réussit
- [ ] `docker push yurri775/palapi:1.0.0` réussit
- [ ] Image visible sur https://hub.docker.com/r/yurri775/palapi
- [ ] Rapports dans `security-reports\`
- [ ] README.md à jour
- [ ] Projet prêt pour le rendu

---

## 🆘 Besoin d'aide ?

1. Vérifiez que Docker Desktop est démarré
2. Relancez votre terminal CMD/PowerShell en tant qu'administrateur
3. Consultez les logs : `docker compose logs -f`

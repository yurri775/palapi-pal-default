# 🔧 Commandes Docker - PalAPI

**Username Docker Hub:** yurri775

## 📦 Push vers Docker Hub

### Étape 1 : Se connecter à Docker Hub

```bash
docker login
# Username: yurri775
# Password: [votre mot de passe]
```

### Étape 2 : Builder l'image

```bash
# Depuis la racine du projet
docker compose build
```

### Étape 3 : Tagger l'image

```bash
docker tag palapi:latest yurri775/palapi:1.0.0
docker tag palapi:latest yurri775/palapi:latest
```

### Étape 4 : Pusher l'image

```bash
docker push yurri775/palapi:1.0.0
docker push yurri775/palapi:latest
```

### Étape 5 : Vérifier sur Docker Hub

Allez sur : https://hub.docker.com/r/yurri775/palapi

---

## 🚀 OU utiliser le script automatisé

### Linux/Mac

```bash
chmod +x scripts/push-to-dockerhub.sh
./scripts/push-to-dockerhub.sh
```

### Windows

```cmd
scripts\push-to-dockerhub.bat
```

---

## 🔒 Scan de sécurité

### Méthode 1 : Docker Scout (Recommandé, intégré à Docker Desktop)

```bash
# Vue rapide des vulnérabilités
docker scout quickview palapi:latest

# Rapport détaillé
docker scout cves palapi:latest

# Sauvegarder dans un fichier
docker scout cves palapi:latest > security-reports/scout-report.txt
```

### Méthode 2 : Trivy (Très populaire)

```bash
# Installation (si pas déjà installé)
# Windows (via Chocolatey)
choco install trivy

# Linux/Mac (via brew)
brew install aquasecurity/trivy/trivy

# Ou via Docker (sans installation)
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
  aquasec/trivy image palapi:latest

# Sauvegarder dans un fichier
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
  aquasec/trivy image --format table --output security-reports/trivy-report.txt palapi:latest
```

### Méthode 3 : Snyk (Nécessite un compte gratuit)

```bash
# Installation
npm install -g snyk

# Login
snyk auth

# Scan
snyk container test palapi:latest

# Sauvegarder en JSON
snyk container test palapi:latest --json > security-reports/snyk-report.json
```

---

## 📊 Comparaison des outils

| Outil        | Installation   | Gratuit   | Détails CVE | Recommandations |
| ------------ | -------------- | --------- | ----------- | --------------- |
| Docker Scout | ✅ Intégré     | ✅ Oui    | ✅ Oui      | ✅ Oui          |
| Trivy        | 📦 À installer | ✅ Oui    | ✅ Oui      | ✅ Oui          |
| Snyk         | 📦 À installer | ⚠️ Limité | ✅ Oui      | ✅ Oui          |

**Recommandation pour le projet :** Utilisez **Docker Scout** car il est déjà intégré à Docker Desktop.

---

## 🎯 Commandes complètes pour le rendu

```bash
# 1. Builder l'image
docker compose build

# 2. Scanner avec Docker Scout
mkdir security-reports
docker scout quickview palapi:latest > security-reports/scout-summary.txt
docker scout cves palapi:latest > security-reports/scout-detailed.txt

# 3. Scanner avec Trivy
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
  aquasec/trivy image --format table --output security-reports/trivy-report.txt palapi:latest

# 4. Login Docker Hub
docker login

# 5. Tag et Push
docker tag palapi:latest yurri775/palapi:1.0.0
docker tag palapi:latest yurri775/palapi:latest
docker push yurri775/palapi:1.0.0
docker push yurri775/palapi:latest

# 6. Vérifier le push
docker pull yurri775/palapi:1.0.0
```

---

## 📝 Template de rapport de sécurité

Après avoir exécuté les scans, créez un fichier `security-reports/FINAL_REPORT.md` avec ce contenu :

```markdown
# Rapport de Sécurité - PalAPI

**Date :** [Date actuelle]
**Image :** palapi:1.0.0
**Base Image :** eclipse-temurin:21-jre-alpine

## Outils utilisés

- ✅ Docker Scout (version intégrée)
- ✅ Trivy (via Docker)

## Résumé des vulnérabilités

[Copier-coller le résumé de docker scout quickview]

## Vulnérabilités détaillées

[Copier-coller les résultats de docker scout cves]

## Analyse

- **Vulnérabilités critiques :** [Nombre]
- **Vulnérabilités hautes :** [Nombre]
- **Vulnérabilités moyennes :** [Nombre]
- **Vulnérabilités basses :** [Nombre]

## Recommandations

1. [Si des vulnérabilités critiques] Mettre à jour les packages X, Y, Z
2. [Si acceptable] L'image est sécurisée pour un usage en production

## Conclusion

✅ L'image Docker est [ACCEPTÉE / NÉCESSITE DES CORRECTIONS] pour déploiement.

**Signature :** [Votre nom]
**Date :** [Date]
```

---

## 🐛 Dépannage

### "docker: Cannot connect to the Docker daemon"

```bash
# Windows : Démarrez Docker Desktop
# Linux :
sudo systemctl start docker
```

### "unauthorized: authentication required"

```bash
# Reconnectez-vous
docker logout
docker login
```

### "denied: requested access to the resource is denied"

```bash
# Vérifiez que vous utilisez bien VOTRE username
docker tag palapi:latest votre_vrai_username/palapi:1.0.0
```

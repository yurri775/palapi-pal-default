#!/bin/bash

# ============================================
# Script de push vers Docker Hub
# Auteur: Marwa (yurri775)
# ============================================

set -e  # Arrêter si une commande échoue

# Configuration
DOCKER_USERNAME="yurri775"
APP_NAME="palapi"
VERSION="1.0.0"
IMAGE_LOCAL="palapi:latest"
IMAGE_REGISTRY="${DOCKER_USERNAME}/${APP_NAME}"

echo "🚀 Script de déploiement Docker Hub"
echo "===================================="
echo "Username: ${DOCKER_USERNAME}"
echo "Image: ${IMAGE_REGISTRY}"
echo "Version: ${VERSION}"
echo ""

# Étape 1: Vérifier Docker
echo "📋 Vérification de Docker..."
if ! command -v docker &> /dev/null; then
    echo "❌ Docker n'est pas installé!"
    exit 1
fi
echo "✅ Docker trouvé: $(docker --version)"
echo ""

# Étape 2: Login Docker Hub
echo "🔐 Connexion à Docker Hub..."
docker login
if [ $? -ne 0 ]; then
    echo "❌ Échec de connexion à Docker Hub!"
    exit 1
fi
echo "✅ Connecté à Docker Hub"
echo ""

# Étape 3: Build de l'image
echo "🏗️ Build de l'image..."
docker compose build
if [ $? -ne 0 ]; then
    echo "❌ Échec du build!"
    exit 1
fi
echo "✅ Build réussi"
echo ""

# Étape 4: Vérifier que l'image existe
echo "🔍 Vérification de l'image locale..."
if ! docker images | grep -q "palapi"; then
    echo "❌ Image palapi:latest introuvable!"
    exit 1
fi
echo "✅ Image trouvée"
echo ""

# Étape 5: Tagging
echo "🏷️ Tagging de l'image..."
docker tag ${IMAGE_LOCAL} ${IMAGE_REGISTRY}:${VERSION}
docker tag ${IMAGE_LOCAL} ${IMAGE_REGISTRY}:latest
echo "✅ Images taguées:"
echo "   - ${IMAGE_REGISTRY}:${VERSION}"
echo "   - ${IMAGE_REGISTRY}:latest"
echo ""

# Étape 6: Push vers Docker Hub
echo "📤 Push vers Docker Hub..."
echo "   Push version ${VERSION}..."
docker push ${IMAGE_REGISTRY}:${VERSION}
echo "   Push latest..."
docker push ${IMAGE_REGISTRY}:latest
echo "✅ Push réussi!"
echo ""

# Étape 7: Vérification
echo "🎉 SUCCÈS! Image disponible sur Docker Hub"
echo "=========================================="
echo ""
echo "📦 Pour utiliser l'image:"
echo "   docker pull ${IMAGE_REGISTRY}:${VERSION}"
echo "   docker pull ${IMAGE_REGISTRY}:latest"
echo ""
echo "🌐 Voir sur Docker Hub:"
echo "   https://hub.docker.com/r/${DOCKER_USERNAME}/${APP_NAME}"
echo ""
echo "🚀 Lancer directement:"
echo "   docker run -p 8081:8080 ${IMAGE_REGISTRY}:${VERSION}"

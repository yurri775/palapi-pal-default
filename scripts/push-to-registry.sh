#!/bin/bash

# Configuration
DOCKER_USERNAME="votre_username"  # CHANGEZ CECI
APP_NAME="palapi"
VERSION="1.0.0"

echo "🔐 Connexion à Docker Hub..."
docker login

echo "🏗️ Build de l'image..."
docker compose build

echo "🏷️ Tagging de l'image..."
docker tag palapi:latest ${DOCKER_USERNAME}/${APP_NAME}:${VERSION}
docker tag palapi:latest ${DOCKER_USERNAME}/${APP_NAME}:latest

echo "📤 Push vers Docker Hub..."
docker push ${DOCKER_USERNAME}/${APP_NAME}:${VERSION}
docker push ${DOCKER_USERNAME}/${APP_NAME}:latest

echo "✅ Image disponible sur:"
echo "   docker pull ${DOCKER_USERNAME}/${APP_NAME}:${VERSION}"

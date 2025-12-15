#!/bin/bash

IMAGE_NAME="palapi:latest"
REPORT_DIR="./security-reports"
DATE=$(date +%Y%m%d_%H%M%S)

echo "🔍 Démarrage du scan de sécurité..."

# Créer le dossier de rapports
mkdir -p $REPORT_DIR

# ============================================
# Méthode 1 : Docker Scout (Recommandé)
# ============================================
echo "📊 Méthode 1 : Docker Scout"
echo "================================" > $REPORT_DIR/scout-report-$DATE.txt
docker scout quickview $IMAGE_NAME >> $REPORT_DIR/scout-report-$DATE.txt
echo "" >> $REPORT_DIR/scout-report-$DATE.txt
docker scout cves $IMAGE_NAME >> $REPORT_DIR/scout-report-$DATE.txt

# ============================================
# Méthode 2 : Trivy
# ============================================
echo "📊 Méthode 2 : Trivy"
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
  aquasec/trivy image --format table --output $REPORT_DIR/trivy-report-$DATE.txt $IMAGE_NAME

# ============================================
# Méthode 3 : Snyk (si installé)
# ============================================
if command -v snyk &> /dev/null; then
    echo "📊 Méthode 3 : Snyk"
    snyk container test $IMAGE_NAME --json > $REPORT_DIR/snyk-report-$DATE.json
fi

# ============================================
# Résumé
# ============================================
echo "✅ Rapports générés dans $REPORT_DIR/"
ls -lh $REPORT_DIR/

echo ""
echo "📄 Fichiers créés:"
echo "   - scout-report-$DATE.txt"
echo "   - trivy-report-$DATE.txt"

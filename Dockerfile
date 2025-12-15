# ============================================
# Stage 1: Build
# ============================================
FROM eclipse-temurin:21-jdk-alpine AS builder

# Métadonnées
LABEL maintainer="yurri775"
LABEL version="1.0.0"
LABEL description="PalAPI - API REST Palworld"

WORKDIR /build

# Copier les fichiers Maven wrapper
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Rendre le wrapper exécutable
RUN chmod +x mvnw

# Télécharger les dépendances (layer cacheable)
RUN ./mvnw dependency:go-offline -B || true

# Copier le code source
COPY src ./src

# Build avec optimisations
RUN ./mvnw clean package -DskipTests -B -T 1C && \
    mv target/*.jar target/app.jar

# ============================================
# Stage 2: Runtime
# ============================================
FROM eclipse-temurin:21-jre-alpine AS runtime

# Installation des outils de diagnostic
RUN apk add --no-cache curl dumb-init

WORKDIR /app

# Créer utilisateur non-root
RUN addgroup -g 1001 -S spring && \
    adduser -u 1001 -S spring -G spring && \
    mkdir -p /app/logs && \
    chown -R spring:spring /app

# Copier le JAR depuis builder
COPY --from=builder --chown=spring:spring /build/target/app.jar ./app.jar

# Changer vers utilisateur non-root
USER spring:spring

# Exposer le port
EXPOSE 8080

# Variables d'environnement optimisées
ENV JAVA_OPTS="-Xms512m -Xmx1024m \
    -XX:+UseG1GC \
    -XX:MaxGCPauseMillis=200 \
    -XX:+UseStringDeduplication \
    -Djava.security.egd=file:/dev/./urandom"

# Healthcheck
HEALTHCHECK --interval=30s --timeout=10s --start-period=90s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Point d'entrée avec dumb-init
ENTRYPOINT ["/usr/bin/dumb-init", "--"]
CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

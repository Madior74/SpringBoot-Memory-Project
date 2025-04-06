# Utilise une image Java officielle comme base
FROM openjdk:17-jdk-slim

# Définis le répertoire de travail dans le conteneur
WORKDIR /app

# Copie le fichier .jar généré dans le conteneur
COPY target/SchoolAdmin-0.0.1-SNAPSHOT.jar app.jar

# Expose le port utilisé par ton application Spring Boot
EXPOSE 9000

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]

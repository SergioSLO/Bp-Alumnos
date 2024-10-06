# Usa una imagen base de Maven para construir el proyecto
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias del proyecto
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente y compila la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# Usa una imagen base de OpenJDK para ejecutar el proyecto
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copia el archivo JAR generado desde la fase anterior
COPY --from=build /app/target/bp_alumnos.jar app.jar

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8080



# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

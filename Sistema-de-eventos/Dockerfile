# Usar imagem base do Maven com OpenJDK 17
FROM maven:3.8-openjdk-17 AS build

# Diretório de trabalho
WORKDIR /app

# Copiar arquivos do projeto
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar o restante dos arquivos
COPY src ./src

# Fazer o build do projeto
RUN mvn clean package -DskipTests

# Usar imagem base do OpenJDK para execução
FROM openjdk:17-jdk-slim

# Diretório de trabalho no container
WORKDIR /app

# Copiar o JAR gerado
COPY --from=build /app/target/sistema-de-eventos-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para iniciar o backend
ENTRYPOINT ["java", "-jar", "app.jar"]

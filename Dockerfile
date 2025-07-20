# Stage 1: Build com Maven e OpenJDK
FROM maven:3.8-openjdk-17 AS builder

WORKDIR /app

# Copia todos os arquivos do projeto para /app
COPY . .

# Compila o projeto e baixa dependências
RUN mvn clean package -DskipTests

# Stage 2: Imagem runtime OpenJDK
FROM openjdk:17-slim

WORKDIR /app

# Copia os .class compilados do build Maven
COPY --from=builder /app/target/classes /app/classes

# Copia as dependências (libs)
COPY --from=builder /app/target/dependency /app/lib

# Copia os scripts launcher (assumindo que você os colocou na pasta launcher)
COPY ./launcher /app/launcher

# Dá permissão executável aos scripts
RUN chmod +x /app/launcher/*.sh

EXPOSE 2106 9014 7777

# Comando para rodar os servidores via script start_servers.sh
CMD ["bash", "/app/launcher/start_servers.sh"]

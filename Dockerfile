# Stage 1: Build com OpenJDK (compilação manual)
FROM openjdk:17 AS builder

WORKDIR /app

# Copia o código fonte para o container
COPY ./sl2/java ./java
COPY ./sl2 ./sl2

# Compila todos os arquivos .java em ./java, gerando .class em ./classes
RUN mkdir classes && \
    find java -name "*.java" > sources.txt && \
    javac -d classes @sources.txt

# Stage 2: Imagem runtime OpenJDK slim
FROM openjdk:17-slim

WORKDIR /app

# Copia os arquivos compilados
COPY --from=builder /app/classes /app/classes

# Copia os scripts launcher
COPY ./sl2/launcher /app/launcher

# Dá permissão executável aos scripts
RUN chmod +x /app/launcher/*.sh

EXPOSE 2106 9014 7777

CMD ["bash", "/app/launcher/start_servers.sh"]

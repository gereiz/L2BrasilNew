FROM openjdk:24-slim-bullseye

RUN apt-get update && \
    apt-get install -y bash wget && \
    apt-get clean

WORKDIR /app

COPY ./sl2 /app/sl2

# Dá permissão aos scripts
RUN chmod +x /app/sl2/launcher/Acconts.launch && \
    chmod +x /app/sl2/launcher/Gameserver.launch && \
    chmod +x /app/sl2/launcher/Loginserver.launch && \
    chmod +x /app/sl2/launcher/start_servers.sh

EXPOSE 2106 9014 7777

CMD ["bash", "/app/sl2/launcher/start_servers.sh"]

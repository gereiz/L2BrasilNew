FROM openjdk:24-slim-bullseye

RUN apt-get update && \
    apt-get install -y bash wget && \
    apt-get clean

WORKDIR /app

COPY ./sl2 /app/sl2

# Só dar permissão ao start_servers.sh se quiser
#RUN chmod +x /app/sl2/start_servers.sh
RUN find /app/sl2 -type f -name "*.sh" -exec chmod +x {} \;

EXPOSE 2106 9014 7777

CMD ["bash", "/app/sl2/start_servers.sh"]

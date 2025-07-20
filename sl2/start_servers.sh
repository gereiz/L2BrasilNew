#!/bin/bash

# Criar logs
mkdir -p /app/sl2/{game,login}/log
chmod 777 /app/sl2/{game,login}/log

# 🛠️ Garantir permissões de execução dos scripts
chmod +x /app/sl2/login/RegisterGameServer.sh
chmod +x /app/sl2/login/startLoginServer.sh
chmod +x /app/sl2/game/startGameServer.sh

# Iniciar o Register Server
cd /app/sl2/login/ || exit 1
./RegisterGameServer.sh &

# Iniciar LoginServer em segundo plano
cd /app/sl2/login/ || exit 1
./startLoginServer.sh &

# Iniciar GameServer em segundo plano
cd /app/sl2/game/ || exit 1
./startGameServer.sh &

# Manter o container vivo
tail -f /dev/null

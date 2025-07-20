#!/bin/bash

# Criar diretórios de log, se não existirem
mkdir -p /app/sl2/game/log /app/sl2/login/log
chmod 777 /app/sl2/game/log /app/sl2/login/log

# 🛠️ Garantir permissões de execução dos scripts
chmod +x /app/sl2/login/RegisterGameServer.sh
chmod +x /app/sl2/login/startLoginServer.sh
chmod +x /app/sl2/game/startGameServer.sh

# Iniciar o Register Server
echo "Iniciando RegisterGameServer..."
/app/sl2/login/RegisterGameServer.sh &

# Iniciar Login Server
echo "Iniciando LoginServer..."
/app/sl2/login/startLoginServer.sh &

# Iniciar Game Server
echo "Iniciando GameServer..."
/app/sl2/game/startGameServer.sh &

# ✅ Mensagem final
echo "Todos os servidores foram iniciados em background."

# Manter o container ativo
tail -f /dev/null

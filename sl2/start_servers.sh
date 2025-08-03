#!/bin/bash

echo "🔧 Iniciando script de inicialização dos servidores..."

# Criar diretórios de log
echo "📁 Criando diretórios de log em /app/sl2/game/log e /app/sl2/login/log..."
mkdir -p /app/sl2/{game,login}/log
chmod 777 /app/sl2/{game,login}/log

# Garantir permissões de execução dos scripts
echo "🔐 Garantindo permissões de execução dos scripts..."
chmod +x /app/sl2/login/LoginServer.sh
chmod +x /app/sl2/game/GameServer.sh
chmod +x /app/sl2/login/LoginServerTask.sh
chmod +x /app/sl2/game/GameServerTask.sh

# Iniciar LoginServer
echo "🚀 Iniciando LoginServer..."
cd /app/sl2/login/ || { echo "❌ Falha ao acessar /app/sl2/login/"; exit 1; }
./LoginServer.sh &
echo "✅ LoginServer iniciado."

# Iniciar GameServer
echo "🚀 Iniciando GameServer..."
cd /app/sl2/game/ || { echo "❌ Falha ao acessar /app/sl2/game/"; exit 1; }
./GameServer.sh &
echo "✅ GameServer iniciado."

# Mensagem final
echo "🎉 Todos os servidores foram iniciados em segundo plano."

# Manter o container vivo
tail -f /dev/null

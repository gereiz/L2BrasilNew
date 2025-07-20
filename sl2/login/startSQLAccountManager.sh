#!/bin/bash

echo ""

# Salva o classpath atual (se necessário)
original_classpath=$CLASSPATH

# Carrega o classpath personalizado (precisa converter o classpath.bat para classpath.sh)
source ./classpath.sh

# Executa o Account Manager
java -Djava.util.logging.config.file=console.cfg net.sf.l2j.accountmanager.SQLAccountManager

# Restaura o classpath original
export CLASSPATH=$original_classpath

# Aguarda o usuário pressionar Enter
read -p "Pressione Enter para continuar..."

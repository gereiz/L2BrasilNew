#!/bin/bash

echo "Aguardando MySQL iniciar..."
sleep 10

echo "Iniciando instalação do banco de dados..."

# Define o banco de dados manualmente (se a variável não estiver disponível)
DB_NAME="l2jteste"
MYSQL_USER="root"
MYSQL_PASS="142536"

# Caminhos corrigidos no container MySQL
GAME_SQL_DIR="/docker-entrypoint-initdb.d/sql/game"
LOGIN_SQL_DIR="/docker-entrypoint-initdb.d/sql/login"

# Função para importar arquivos .sql
import_sql_files() {
  local DIR=$1
  for sql_file in "$DIR"/*.sql; do
    if [ -f "$sql_file" ]; then
      echo "Importando: $sql_file"
      mysql -u$MYSQL_USER -p$MYSQL_PASS $DB_NAME < "$sql_file"
    fi
  done
}

# Importa arquivos das duas pastas
import_sql_files "$GAME_SQL_DIR"
import_sql_files "$LOGIN_SQL_DIR"

echo "Banco de dados instalado com sucesso."

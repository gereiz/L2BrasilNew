#!/bin/bash

echo "Excluindo logs"
rm -rf sl2/game/log/*
rm -rf sl2/login/log/*

echo "ZIP processando..."
# Nome do arquivo ZIP
ZIP_NAME="sl2jrevMobiusInterlude.zip"

# Remove o ZIP antigo, se existir
if [ -f "$ZIP_NAME" ]; then
    echo "Removendo arquivo ZIP existente: $ZIP_NAME"
    rm "$ZIP_NAME"
fi

# Diretórios e arquivos a serem excluídos do zip (compactacao)
EXCLUDES=(".git/*" "mysql/*" "sl2/game/log/*" "sl2/login/log/*")

# Cria o arquivo ZIP com exclusões
echo "Criando o arquivo ZIP apenas para a pasta 'sl2': $ZIP_NAME"
zip -r "$ZIP_NAME" "sl2" $(printf " -x '%s'" "${EXCLUDES[@]}")

# Verifica se o ZIP foi criado com sucesso
if [ $? -eq 0 ]; then
    echo "Arquivo ZIP criado com sucesso: $ZIP_NAME"
else
    echo "Erro ao criar o arquivo ZIP."
fi

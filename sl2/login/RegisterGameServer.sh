#!/bin/bash

# Executa o GameServerRegister com os arquivos JAR no classpath
java -Djava.util.logging.config.file=console.cfg \
     -cp "c3p0-0.9.1.2.jar:lib/l2mwxserver.jar:mysql-connector-java-5.0.7-bin.jar" \
     net.sf.l2j.gsregistering.GameServerRegister

# Espera o usuário pressionar Enter (equivalente ao pause do .bat)
read -p "Pressione Enter para continuar..."

#!/bin/bash

# Define título do terminal (opcional)
echo -ne "\033]0;Login Server L2 Esenn\007"

# ----------- Configuração do CLASSPATH embutida -------------------
CLASSPATH=""
CLASSPATH="${CLASSPATH}:./lib/mmocore.jar"
CLASSPATH="${CLASSPATH}:./lib/l2mwxserver.jar"
CLASSPATH="${CLASSPATH}:./lib/bsf.jar"
CLASSPATH="${CLASSPATH}:./lib/bsh-2.0b4.jar"
CLASSPATH="${CLASSPATH}:./lib/c30-0.91.2.jar"
CLASSPATH="${CLASSPATH}:./lib/jython.jar"
CLASSPATH="${CLASSPATH}:./lib/commons-logging-1.1.jar"
CLASSPATH="${CLASSPATH}:./lib/javolution.jar"
CLASSPATH="${CLASSPATH}:./lib/mysql-connector-java-5.0.7-bin.jar"
CLASSPATH="${CLASSPATH}:./config/:."
export CLASSPATH
# ------------------------------------------------------------------

# Função principal para iniciar o LoginServer
start_server() {
    java -Dfile.encoding=UTF-8 -Xmx128m net.sf.l2j.loginserver.L2LoginServer
    return $?
}

# Loop para reinicialização ou erro
while true; do
    start_server
    exit_code=$?

    if [ $exit_code -ge 2 ]; then
        echo ""
        echo "Admin Restart ..."
        echo ""
        continue
    elif [ $exit_code -ge 1 ]; then
        echo ""
        echo "Server terminated abnormally"
        echo ""
        break
    else
        break
    fi
done

# Mensagem final
echo ""
echo "Server terminated"
echo ""
read -p "Pressione Enter para sair..."

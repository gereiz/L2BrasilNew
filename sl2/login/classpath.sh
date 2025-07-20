#!/bin/bash

# MMOCore
CLASSPATH="$CLASSPATH:./lib/mmocore.jar"

# L2J-mwx Core
CLASSPATH="$CLASSPATH:./lib/l2mwxserver.jar"

# Jython
CLASSPATH="$CLASSPATH:./lib/bsf.jar"
CLASSPATH="$CLASSPATH:./lib/bsh-2.0b4.jar"
CLASSPATH="$CLASSPATH:./lib/c30-0.91.2.jar"
CLASSPATH="$CLASSPATH:./lib/jython.jar"

# Commons
CLASSPATH="$CLASSPATH:./lib/commons-logging-1.1.jar"

# Javolution
CLASSPATH="$CLASSPATH:./lib/javolution.jar"

# MySQL Connector
CLASSPATH="$CLASSPATH:./lib/mysql-connector-java-5.0.7-bin.jar"

# Config directory and current directory
CLASSPATH="$CLASSPATH:./config/:."

# Exporta a variável para que o Java use
export CLASSPATH

# Mensagens no terminal
echo "Starting L2JMwX Game Server - Use e abuse :D"
echo ""

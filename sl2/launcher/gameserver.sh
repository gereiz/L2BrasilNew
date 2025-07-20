#!/bin/bash
set -e

WORKDIR="/app/sl2/game"
MAIN_CLASS="com.dream.game.L2GameServer"
CLASSPATH="/app/sl2/bin:/app/sl2/lib/*"

cd "$WORKDIR"

echo "Iniciando L2GameServer..."
java -cp "$CLASSPATH" "$MAIN_CLASS"

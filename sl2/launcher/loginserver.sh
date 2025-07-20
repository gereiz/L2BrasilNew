#!/bin/bash
set -e

WORKDIR="/app/sl2/login"
MAIN_CLASS="com.dream.auth.L2AuthServer"
CLASSPATH="/app/sl2/bin:/app/sl2/lib/*"

cd "$WORKDIR"

echo "Iniciando L2AuthServer..."
java -cp "$CLASSPATH" "$MAIN_CLASS"

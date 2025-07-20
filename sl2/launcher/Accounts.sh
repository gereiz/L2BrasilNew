#!/bin/bash
set -e

WORKDIR="/app/sl2/login"
MAIN_CLASS="com.dream.accountmanager.AccountManager"
CLASSPATH="/app/sl2/bin:/app/sl2/lib/*"

cd "$WORKDIR"

java -cp "$CLASSPATH" "$MAIN_CLASS"

#!/bin/bash
set -e

bash /app/sl2/launcher/accounts.sh &
bash /app/sl2/launcher/gameserver.sh &
bash /app/sl2/launcher/loginserver.sh &

wait

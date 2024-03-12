#!/bin/bash

ps xao pid,ppid,state,comm | head -n1 | sed --expression='s/S/ESTADO/g' | sed --expression='s/COMMAND/COMANDO/g'
ps -a | sed --expression='1d' | awk '{print $1}' | xargs -L1 ./ejercicio13.sh | awk '$0 !~ /PID/'
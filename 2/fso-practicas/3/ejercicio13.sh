#!/bin/bash

if test $# -lt 1
then
echo "Debe introducir un PID"
exit
fi

ps xao pid,ppid,state,comm | head -n1 | sed --expression='s/S/ESTADO/g' | sed --expression='s/COMMAND/COMANDO/g'
ps xao pid,ppid,state,comm | awk '$1=="'$1'" {print}'
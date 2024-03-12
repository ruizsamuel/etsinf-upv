#!/bin/bash

if test $# -lt 1
then
echo "Debe introducir el nombre de un fichero"
exit
fi

if test -f $1
then
rm $1
else
    if test -d $1
    then
    echo "El archivo: $1 es un directorio"
    exit
    fi
echo "El archivo: $1 no es un archivo regular o no existe"
fi
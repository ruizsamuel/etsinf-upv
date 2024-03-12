#!/bin/bash

echo "runghc Ejercicio1A.hs: "
runghc Ejercicio1A.hs
echo "--------------"

echo "runghc Ejercicio1B.hs: "
runghc Ejercicio1B.hs
echo "--------------"

echo "Compilando con ghc --make..."
ghc --make Ejercicio1A.hs
ghc --make Ejercicio1B.hs

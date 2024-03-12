# Práctica 3

## Ejercicio 1

__¿Cuáles son las dos últimas instrucciones del programa principal?__

li $v0,10
syscall

__¿Cuál es la última instrucción de la función?__

jr $ra

__Busque las cuatro llamadas al sistema utilizadas en el programa. ¿Qué hace cada una?__

Las dos primeras (código 5), guardan un entero introducido por entrada estándar.
La siguiente (código 1), imprime un entero por la salida estándar.
La última (código 10), termina el programa.

__Busque un bucle dentro de la función. ¿Cuántas veces se ejecuta este bucle?__

MultFor sería un bucle. Se ejecuta a1 veces (la cantidad de veces que tendrá que ser sumado a0 a v0)

__¿Qué hace la función exactamente?__

Realiza una multiplicación de dos enteros (solicitados al usuario por entrada estándar), sumando a1 veces el contenido de a0
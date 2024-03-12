# Práctica 1

## Ejercicio 1

__¿Cuántas instrucciones máquina comprende el programa?__

Cuatro.

__¿En qué instrucciones máquina se traducen las pseudoinstrucciones presentes?__

La pseudoinstrucción li $t0, 25 --> ori $8, $1, 25

La pseudoinstrucción li $t1, 30 --> ori $9, $0, 30

__¿En qué dirección de memoria se encuentra la instrucción add $s0,$s0,$s0?__

0x0040000c

__¿Qué instrucción del programa se codifica como 0x01288020?__

add $16, $9, $8

__Diga en hexadecimal el valor del perímetro calculado por el programa.__

0x2b232

__Modifique el programa para que calcule el perímetro de un rectángulo con lados 75369 y 12976 y compruebe el resultado. En este caso, ¿en qué instrucciones máquina se traducen las pseudoinstruccions presentes? Razone su respuesta.__

La pseudoinstrucción li $t0, 75369 --> lui $1, 1  +  ori $8, $1, 9833

La pseudoinstrucción li $t1, 12976 --> ori $9, $0, 12976

La primera pseudoinstrucción se traduciría en dos instrucciones puesto que el dato inmediato no cabe en los 16 bits reservados en las instrucciones tipo I.

## Ejercicio 2

__¿Cuántos bytes de la memoria principal están ocupados por las variables del programa?__

12 bytes. 4 bytes/word * 2 word + 1 bytes/space * 4 space.

__¿Cuántas instrucciones de acceso a la memoria contiene el programa?__

Tres. Dos lw y una sw.

__¿En qué dirección se escribe el valor del perímetro?__

0x10000008

__¿Por qué la pseudoinstrucció la $t0,A se traduce en sólo una instrucción máquina y la $t1,B lo hace en dos?__

Porque en la segunda pseudoinstrucción, el programa necesita sumarle 4 a la dirección de la etiqueta A (la primera, que se ecuentra en la dirección 0x10000000) para encontrar el contenido de B (4 bytes después de A).

__Justificar el valor (4) que aparece en la directiva .space 4?__

Su función es reservar 4 bytes para almacenar el valor del perímetro.

__Afectaría al valor final de P si en lugar de la directiva .space 4 hiciésemos uso de la directiva .word 0?__

Podría haber problemas si el contenido de esa posición de memoria no fuera 0x00000000.

___¿Qué valor contiene el registro $t1 cuando se ejecuta la instrucción lw $s1,0($t1)?__

La dirección de memoria de B: 0x10000004

## Ejercicio 3

__¿Cuál es la codificación de la instrucción máquina syscall?__

0x0000000c

__¿En qué instrucción máquina se ha traducido la pseudoinstrucción move $a0,$a1?__

addu $4, $0, $18

__Substituya ahora la instrucción sw $s2,0($t2) por sw $s2,2($t2). ¿Qué ocurre cuando se intenta ejecutar el programa? Razone la respuesta.__

Al desplazar la dirección de destino 2 bytes, obtendremos un error, puesto que el dato a guardar no quedaría alineado en la memoria.
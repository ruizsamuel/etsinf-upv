# Práctica 6

## Multiplicación mediante sumas y desplazamientos

### Escriba el código necesario para multiplicar el contenido del registro $a0 por la constante 36 y devolver el resultado en el registro $v0 utilizando sumas y desplazamientos.

Puesto que 36 = 100100 (base 2) = 2^5 + 2^2

```
li $a0, X
sll $v0, $a0, 5
sll $t0, $a0, 2
addu $v0, $v0, $t0
```

## Multiplicación mediante sumas, restas y desplazamientos

### Escriba el código necesario para multiplicar el contenido del registro $a0 por la constante 31 y devolver el resultado en el registro $v0 utilizando sumas, restas y desplazamientos.

```
li $a0, X
sll $v0, $a0, 5
subu $v0, $v0, $a0
```

## Incremento de reloj

### Escriba el código necesario para inicializar una variable reloj con el valor 21:15:45 e incrementarlo en tres horas y 40 segundos. Use lasrubrutinas inicializa_reloj, pasa_hora y pasa_segundo. ¿Cuál es el valor final del reloj?

```
la $a0, reloj

li $a1, 0x00150F2D
jal inicializa_reloj

la $a0, reloj
jal pasa_hora
jal pasa_hora
jal pasa_hora

li $v1, 40
bucle:  
jal pasa_segundo
addi $v1, $v1, -1 
bgtz $v1, bucle

la $a0, reloj
jal imprime_reloj
```

El valor final del reloj será: 00:16:25 (3h y 40 segundos más tarde de la inicial)
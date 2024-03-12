# Práctica 5

## Codificación de un formato horario y su inicialización

### ¿Qué valor del reloj representa la palabra de bits 0x0017080A?

23:08:10

### ¿Qué valor del reloj representa la palabra de bits 0xF397C84A?

23:08:10

### Indique tres codificaciones distintas de la variable reloj para el valor horario 16:32:28

Bit a bit: XXXXXXXXXXX10000XX100000XX011100
Posibles codificaciones:
- 0x0010201C
- 0x1010201C
- 0x0110201C

### Cargue el fichero reloj.s y ejecútelo en el simulador ¿Por qué se ha impreso la hora 00:00:00?

Porque no se ha cargado ninguna palabra en el registro que se imprime.

### La siguiente subrutina opera sobre una variable reloj cuya dirección se pasa como argumento en el registro $a0 y con un valor X que se pasa en el byte menos significativo de $a1. Explique razonadamente qué efecto produce la ejecución de la subrutina.

```
subrutina:  lw $t0, 0($a0)
            li $t1, 0x00FFFF00
            and $t0, $t0, $t1
            or $t1, $t0, $a1
            sw $t1, 0($a0)
            jr $ra
```

En primer lugar, con la operación and, se limpia con "0" el byte menos significativo de la palabra que contiene la dirección del reloj.
Después, con la operacion or, se inserta en la palabra del reloj, el byte que se ha pasado como argumento con $a1.
Por último, se guarda en memoria (en la dirección del reloj) la nueva palabra.

En resumen, la subrutina cambia los segundos del reloj por los que se pasan como argumento a través de $a1.

## La operación de multiplicación: conversión de HH:MM:SS a segundos

### Para leer de memoria por separado cada uno de los campos del reloj (HH, MM y SS) se puedeusar una instrucción de lectura de byte. Razone si hay que utilizar lb (load byte) o lbu (load byte unsigned).

Al estar trabajando con números sin signo es más adecuado utilizar lbu.

### ¿Qué tipo de instrucciones de suma han de utilizarse en la subrutina, add o addu?

Al trabajar con números sin signo y no necesitar acarreo es más adecuado utilizar addu.

### ¿Cuántas instrucciones de multiplicación se ejecutan en la subrutina devuelve_reloj_en_s?

Dos. Una para las horas y otra para los minutos, multiplicando por 3600 y 60, respectivamente.

### Cuántas instrucciones de movimiento de información entre los registros del banco de enteros y los registros hi y lo se ejecutan en la subrutina diseñada?

Dos. Para ambos resultados de las dos multiplicaciones.
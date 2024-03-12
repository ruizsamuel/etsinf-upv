# Práctica 7

## Ejercicio 1. Anote los valores que aparecen en la sección “Superbloque” de Minix Viewer.

|                      | Datos del Superbloque mostrados por Minix Viewer |
|----------------------|--------------------------------------------------|
| Número de nodos i    | 4096                                             |
| Número de zonas      | 12288                                            |
| Primera zona de datos| 133                                              |

## Ejercicio 2. Responda de forma precisa a las siguientes preguntas

__¿Cuántos bloques ocupa el mapa de bits de nodos-i?__ 

El mapa de bits ocupa un único bloque

__¿Qué nodos-i se encuentran ocupados? ¿A qué corresponden esos nodos-i?__ 

Hay un nodo ocupado (nodo 1) que se corresponde con el directorio raiz

__¿Cuántos bloques ocupa el mapa de bits de zonas?__ 

Dos bloques (bloques 3 y 4)

__¿Cuántos bloques se encuentran ocupados por los nodos-i?__ 

128 bloques

__Observe en el visor “Zone map” y compruebe que el primer bloque de la zona de datos se encuentra ocupado. ¿Qué fichero y qué nodo-i corresponde a este bloque?__ 

A ese bloque le corresponde el directorio raíz (/) y el nodo-i 1

## Ejercicio 3. Seleccione la ficha “i-nodes” del visor y rellene la tabla para el número de nodo-i 1 y justifique los valores obtenidos

|              | Nodo-i 1       |
|--------------|----------------|
| Modo         | 40755          |
| UID          | 1000           |
| Tamaño       | 32             |
| Instante     | 1544923363     |
| GID          | 232            |
| Enlaces      | 2              |
| Zona 0       | 133            |
| Zona 1       | 0              |
| Zona 2       | 0              |
| Zona 3       | 0              |
| Zona 4       | 0              |
| Zona 5       | 0              |
| Zona 6       | 0              |
| Zona SI      | 0              |
| Zona DI      | 0              |

__Justifique el valor del campo Tamaño__ 

El tamaño son 32 bytes, puesto que este direcotio tiene dos entradas (. y ..), cada una de 16 bytes.

__Justifique el valor del campo Enlaces__

Tiene 2 enlaces, que apuntan al mismo directorio o nodo i (. y ..), puesto que estamos en el directorio raiz.

## Ejercicio 4. Con la nueva estructura creada (minixfs01):

__¿Han cambiado los datos del superbloque después de crear la estructura de archivos? Tanto si han cambiado como si no, justifique por qué__

Los datos del superbloque no han cambiado puesto que el disco tiene la misma estructura y tamaño.

__¿Cuántos y cuáles nodos-i se encuentran ocupados?__

Se encuentran ocupados 14 bloques, del 1 al 14 (incluidos).

__¿Cuántos y cuáles son los bloques de datos que se encuentran ocupados?__

Hay ocupados 561 bloques de datos, desde el 133 hasta el 693 (incluidos).

## Ejercicio 5. Rellene la siguiente tabla para el nodo-i 1

|              | Nodo-i 1       |
|--------------|----------------|
| Modo         | 40755          |
| UID          | 1000           |
| Tamaño       | 96             |
| Instante     | 1545058285     |
| GID          | 232            |
| Enlaces      | 5              |
| Zona 0       | 133            |
| Zona 1       | 0              |
| Zona 2       | 0              |
| Zona 3       | 0              |
| Zona 4       | 0              |
| Zona 5       | 0              |
| Zona 6       | 0              |
| Zona SI      | 0              |
| Zona DI      | 0              |

## Ejercicio 6. Justifique los cambios que aparecen en los valores sus campos, comparándolos con los obtenidos anteriormente.

Ha cambiado el tamaño (puesto que ahora contiene más entradas), el instante (se creó en un momento diferente) y el número de enlances (contiene más entradas).

## Ejercicio 7. Fíjese en el campo modo que ha rellenado en la cuestión 5 ¿a qué tipo de archivo corresponde? Compruebe que el valor de “Modo” es coherente con el tipo de archivo y los bits de permiso asociados en la palabra de protección. Indique el significado de la posición de los dígitos del campo “Modo”.

El campo Modo contiene el valor 40755. El 40 indica que se trata de un directorio y 755 los permisos, que corresponden con rwx r-x r-x para propietario, grupo y otros, respectivamente.

## Ejercicio 8.  cargue el archivo “minixfs02”. Utilizando la opción de la aplicación “Contenido de un Fichero o Directorio” visualice el contenido de los ficheros users/felipe/mish y usr/f1, y compárelos con los de bin/cat y users/ana/f1, anotando el contenido ASCII de la primera línea en la tabla siguiente.

|                    |                                                            |
|--------------------|------------------------------------------------------------|
| users/felipe/mish  | Hola, soy cat                                              |
| urs/f1             | /users/ana/f1                                              |
| bin/cat            | Hola, soy cat                                              |
| users/ana/f1       | hola soy f1                                                |

## Ejercicio 9. Consultando las opciones adecuadas de la aplicación rellene la siguiente tabla

|                                                       |        usr/prac3         |       bin/usuarios    |         unix             |
|-------------------------------------------------------|--------------------------|-----------------------|--------------------------|
| Tipos de direccionamiento                             | Directo                  | Directo               | Todos (directo, SI y DI) |
| Número de bloques ocupados con referencia a bloque    | Ninguno                  | Ninguno               | 3                        |
| Tamaño en bytes del fichero                           | 5850                     | 11                    | 535316                   |
| Número de bloques ocupados con información de fichero | 6                        | 1                     | 523                      |
| Total de bloques ocupados                             | 6                        | 1                     | 526                      |
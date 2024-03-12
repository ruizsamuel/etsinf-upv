# Práctica 6

## Cuestión 1: Analice el código y el resultado de la ejecución y responda. (descriptor.c)

### 1. ¿Qué variables corresponden a los descriptores de archivo en el código propuesto?

Las variables fda y fdb

### 2. Justifique el número asignado por el sistema a la variable fda

Al ser el primer archivo abierto por el proceso, se le asigna el descriptor 3, justo después del 0,1 y 2, correspondientes a las salidas y entradas del sistema.

### 3. Justifique el número asignado por el sistema a la variable fdb

Al no cerrar el archivo anterior, el descriptor asociado es 4.

## Cuestión 2: Analice el código y el resultado de la ejecución y responda. (descriptor_output.c)

### 1. ¿Qué mensajes se imprimen en la pantalla?

men1: Writing in descriptor 1 (std_output)
men2: Writing in descriptor 2 (std_error)
men4: Writing in descriptor 2 (std_error)

### 2. Justifique por qué no se imprimen cada uno de los mensajes que faltan

Porque el proceso cierra las salidas antes de escribir el resto de mensajes.

### 3. Rellene la tabla de descriptores de archivos abiertos correspondiente a dicho proceso antes del return(0)

0 : std_in
1 : std_out
2 : std_err
3 :
4 :

## Cuestión 3: Analice el código y el resultado de la ejecución y responda (share_file.c)

### 1. ¿Cuál es el contenido del archivo messages.txt?

parent message 
child message 
parent message 

### 2. Tanto el proceso padre como el hijo han escrito su mensaje en el archivo messages.txt. ¿Qué mecanismos/llamadas lo han hecho posible?

Gracias a open (para abrir), write (para escribir), fork (para crear una copia del proceso padre), wait (para esperar a que el proceso hijo termine su ejecución) y exit (para indicar la terminación del proceso hijo)

### 3. Rellene la tabla de descriptores de archivos abiertos correspondiente al proceso padre e hijo antes de ejecutar close(fd);

Padre

0 : std_in
1 : std_out
2 : std_err
3 : messages.txt
4 :

Hijo

0 : std_in
1 : std_out
2 : std_err
3 : messages.txt
4 :

## Cuestión 4: Analice el código y el resultado de la ejecución y responda (redir_output.c)

### 1. Justifique, utilizando las instrucciones del código, el contenido del fichero output.txt

El contenido del fichero output.txt es "out: Output redirected". Se ha escrito en él el mensaje que se escribe en la salida estándar ya que gracias a la función dup2 se ha sustituido el descriptor asociado a la salida estándar por el del archivo. 
No ocurre lo mismo con el mesaje que se escribe en la salida estándar de errores que sí que muestra normalmente en el terminal.

### 2. Justifique por qué la llamada open() se ha invocado con los flags "O_RDWR|O_CREAT"

Estos flags indican respectivamente que el archivo se abrirá en modo lectura y escritura y que en caso de que no exista, se creará.

### 3. Rellene la tabla de descriptores de archivos abiertos correspondiente al proceso, justo antes del "if(dup...)"

0 : std_in
1 : std_out
2 : std_err
3 : messages.txt
4 :

### 4. Rellene la tabla de descriptores de archivos abiertos correspondiente al proceso, justo antes del "return (0)"

0 : std_in
1 : messages.txt
2 : std_err
3 : 
4 :

## Cuestión 5: Analice el código y el resultado de la ejecución y responda a la siguiente cuestión (ls_redir.c)

### 1. Tras la ejecución justifique dónde se ha almacenado el contenido del directorio actual

El contenido del directorio actual (la salida de ls -la) se ha almacenado en el archivo ls_salida.txt, ya que antes de invocar exec para ejecutar ls, se había modificado la entrada en la tabla de descriptores de la salida estándar, redirigiéndola al archivo ls_salida.txt.

## Cuestión 6: Analice el código y el resultado de la ejecución y responda a la siguiente cuestión (cat_redir.c)

### 1. ¿Qué ha sido necesario modificar en el código del ejercicio 5 para llevar a cabo el ejercicio 6?

Se ha tenido que modificar la llamada a open (cambiando RDWR por RDONLY) y la llamada a dup2 (cambiando STDOUT_FILENO por STDIN_FILENO) para redireccionar la entrada estándar al archivo ls_salida.txt

## Cuestión 7: Analice el código y el resultado de la ejecución y responda (a_pipe.c)

### 1. ¿Qué muestra el proceso en la salida estándar?

El proceso muestra en la salida estándar: 15.
Es decir, el número de archivos que hay en el directorio de trabajo, puesto que el programa simula la ejecución de la orden __$ls –la | wc –l__, creando un tubo que conecta la salida de ls con la entrada de wc.
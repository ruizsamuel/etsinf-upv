# Practica 5

## Cuestiones Ejercicio 1.

__Elimine las llamadas pthread_join del hilo principal. ¿Qué ocurre?__

Al eliminar las llamadas a pthread_join el hilo principal no espera a que finalicen el resto de hilos de tal forma que no asegura que se vaya a ejecutar la función Print. Esto se debe a que cunado el hilo principal terminal, el resto también.

__Sustituya las llamadas pthread_join por una llamada pthread_exit(0), cerca del punto del programa marcado como EJERCICIO1.a) ¿Completa el progama su ejecición?__

El programa completa su ejecución puesto que con pthread_exit(0) el hilo principal termina pero no el programa, dejando que los otros dos completen su tarea.

__Elimine (o comente) cualquier llamada a pthread_join o pthread_exit (cerca del comentario EJERCICIO1.a) e introduzca en ese mismo punto un retraso de 1 segundo ¿Qué ocurre?__

Mientras el hilo principal espera, el resto completa su ejecución completamente.

__Introduzca ahora un retraso de 2 segundos cerca del comentario EJERCICIO1.b__

No termina la ejecución, puesto que el hilo principal termina antes de que se imprima nada.



## Ejercicio 2.

__Compile y ejecute el código implementado. Observe el valor de V y determine de forma justificada si se ha producido una condición de carrera o no.__

Sí se produce condición de carrra, puesto que el valor final de V debería ser 100.

## Ejercicio 3

Vemos que con pocas repeticiones no se produce condición de carrera, mientras que con muchas sí. Debido a que con pocas no da tiempo al procesador a cambiar de contexto.

Tiempo real de ejecución: 0.233s
Tiempo de ejecución en modo usuario: 0.450s
Tiempo de ejecución en modo sistema: 0.00s

## Ejercicio 4

__CondCarrT (Figura 4. Dentro del bucle)__

Tiempo real de ejecución: 1.913s
Tiempo de ejecución en modo usuario: 3.777s
Tiempo de ejecución en modo sistema: 0.00s

__CondCarrTB (Figura 5. Fuera del bucle)__

Tiempo real de ejecución: 0.368s
Tiempo de ejecución en modo usuario: 0.640s
Tiempo de ejecución en modo sistema: 0.00s

__¿Qué ha ocurrido al utilizar el esquema de sincronización de la Figura 5?__
Al utilizar el esquema de la Figura 5, solo se llama a tes_and_set una vez para cada función, protegiendo la sección crítica hasta el finde cada función, ganando mucho más tiempo al ahorrarse ejecuciónes (4 millones) de instruciones relativamente pesadas.

__¿Qué ventaja tiene sincronizar las secciones críticas como se indica en la Figura 4?__

Una de las ventajas es que de esta manera los hilos se van turnando permitiendo la programación concurrente.

## Ejercicio 5

__CondCarrS (Semáforos POSIX)__

Tiempo real de ejecución: 0.158s
Tiempo de ejecución en modo usuario: 0.132s
Tiempo de ejecución en modo sistema: 0.009s

## Ejercicio 6

__CondCarrM (Semáforos MUTEX)__

Tiempo real de ejecución: 0.157s
Tiempo de ejecución en modo usuario: 0.150s
Tiempo de ejecución en modo sistema: 0.00s
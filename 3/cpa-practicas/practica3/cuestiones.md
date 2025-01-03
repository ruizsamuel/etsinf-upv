# Práctica 3. Paralelización con MPI

## Introducción

Esta práctica aborda la paralelización con MPI a través de cuatro sesiones. Cada sesión corresponde a un apartado y utiliza el material de partida proporcionado.

## 1. Primeros pasos con MPI

### 1.1. Hola Mundo
El programa inicial "Hola Mundo" en MPI permite familiarizarse con las funciones básicas de inicialización, ejecución y finalización. El código base es el siguiente:

```c
#include <stdio.h>
#include <mpi.h>

int main (int argc, char *argv[]) {
    MPI_Init(&argc, &argv);
    printf("Hello world\n");
    MPI_Finalize();
    return 0;
}
```

Compilación:
```bash
$ mpicc -Wall -o hello hello.c
```

Ejecución con 4 procesos:
```bash
$ mpiexec -n 4 ./hello
```

**Ejercicio 1:** Realiza diversas ejecuciones del programa usando el sistema de colas, con diferente número de procesos y nodos.
```bash
#!/bin/sh
#SBATCH --nodes=1
#SBATCH --ntasks=6
#SBATCH --time=5:00
#SBATCH --partition=cpa
scontrol show hostnames $SLURM_JOB_NODELIST

mpiexec ./hello
```
```
kahan01
Hello world
Hello world
Hello world
Hello world
Hello world
Hello world
```
---
```bash
#!/bin/sh
#SBATCH --nodes=2
#SBATCH --ntasks=4
#SBATCH --time=5:00
#SBATCH --partition=cpa
scontrol show hostnames $SLURM_JOB_NODELIST

mpiexec ./hello
```
```
kahan03
kahan04
Hello world
Hello world
Hello world
Hello world
```
---
**Ejercicio 2:** Modifica el programa para obtener el identificador de proceso y el número de procesos, y mostrar esa información en el saludo.
```c
#include <stdio.h>
#include <mpi.h>
int main (int argc, char *argv[])
{
    MPI_Init(&argc, &argv);
    int p, k;
    MPI_Comm_size(MPI_COMM_WORLD, &p);
    MPI_Comm_rank(MPI_COMM_WORLD, &k);
    printf("Hola mundo, soy el proceso %d de %d\n", k, p);
    MPI_Finalize();
    return 0;
}
```
```
kahan01
Hola mundo, soy el proceso 1 de 6
Hola mundo, soy el proceso 4 de 6
Hola mundo, soy el proceso 0 de 6
Hola mundo, soy el proceso 2 de 6
Hola mundo, soy el proceso 3 de 6
Hola mundo, soy el proceso 5 de 6
```

### 1.2. Cálculo de Pi
El programa `mpi_pi.c` calcula π mediante integración numérica con el método de los rectángulos.

**Ejercicio 3:** Sustituye la función `MPI_Reduce` por código equivalente que utilice solo `MPI_Send` y `MPI_Recv`.
```c
//  MPI_Reduce(&mypi, &pi, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);
if (myid!=0) {
    MPI_Send(&mypi, 1, MPI_DOUBLE, 0, 1, MPI_COMM_WORLD);
} else {
    pi = mypi;
    int j;
    for (j = 1; j < numprocs; j++) {
        double partial;
        MPI_Recv(&partial, 1, MPI_DOUBLE, j, 1, MPI_COMM_WORLD, NULL);
        pi += partial;
    }
}
```

### 1.3. Programa ping-pong
El programa `ping-pong.c` mide tiempos de comunicación entre procesos. Se pretende utilizarlo con el objetivo de analizar la velocidad de la red de comunicación entre los nodos del clúster `kahan`.

**Ejercicio 4:** Completa el programa `ping-pong.c` para cumplir con los siguientes requisitos. Usa `MPI_Wtime` para medir tiempos.
- El programa tiene como argumento el tamaño del mensaje, n (en bytes).
- Usa la función `MPI_Wtime()` para medir tiempos. Se usa exactamente igual que la función de OpenMP `omp_get_wtime()`.
- Para que los tiempos medidos sean significativos, el programa debe repetir la operación cierto número de veces (`NREPS`) y mostrar el tiempo medio
- Utiliza las primitivas estándar para envío y recepción de mensajes: `MPI_Send` y `MPI_Recv`, indicando `MPI_BYTE` como tipo de los datos a enviar/recibir.

```c
/* COMPLETE: Get current time, using MPI_Wtime() */

t1 = MPI_Wtime();

/* COMPLETE: loop of NREPS iterations.
 In each iteration, P0 sends a message of n bytes to P1, and P1 sends the same
 message back to P0. The data sent is taken from array buf and received into
 the same array. */

for (i = 0; i < NREPS; i++) {
    if (myid==0) {
        MPI_Send(&buf, n, MPI_BYTE, 1, i, MPI_COMM_WORLD);
        MPI_Recv(&buf, n, MPI_BYTE, 1, i, MPI_COMM_WORLD, NULL);
    } else {
        MPI_Recv(&buf, n, MPI_BYTE, 0, i, MPI_COMM_WORLD, NULL);
        MPI_Send(&buf, n, MPI_BYTE, 0, i, MPI_COMM_WORLD);
    }
}

/* COMPLETE: Get current time, using MPI_Wtime() */

t2 = MPI_Wtime();

/* COMPLETE: Only in process 0.
 Compute the time of transmission of a single message (in milliseconds) and print it.
 Take into account there have been NREPS repetitions, and each repetition involves 2
 messages. */

if (myid == 0) {
    double time;
    time = (t2 - t1) / NREPS * 2;
    printf("El tiempo medio de transmisión del mensaje ha sido %.16f segundos\n", time);
}
```

```bash
#!/bin/sh
#SBATCH --nodes=2
#SBATCH --ntasks=2
#SBATCH --time=5:00
#SBATCH --partition=cpa
scontrol show hostnames $SLURM_JOB_NODELIST

mpiexec ./ping-pong
```
**Ejercicio 5:** Explica por qué se envían dos mensajes en cada iteración del bucle. ¿Se puede eliminar el mensaje de respuesta?

Se envían dos mensajes para asegurar que la comunicación se ha realizado correctamente. De esta manera se asegura que ambos mensajes se envían y reciben correctamente.

Si se eliminara el mensaje de respuesta, no se podría garantizar que el mensaje se ha recibido correctamente. Por tanto, la medición sería errónea.

**Ejercicio 6:** Evalúa si `MPI_Sendrecv_replace` puede usarse en lugar de `MPI_Send` y `MPI_Recv` en ambos procesos.

Sí, podrían sustituirse `MPI_Send` y `MPI_Recv` por `MPI_Sendrecv_replace` en ambos procesos. La función `MPI_Sendrecv_replace` envía y recibe un mensaje en un solo paso, por lo que se reduce el número de llamadas a funciones y se simplifica el código.

> [!CAUTION]
> PRÁCTICA NO FINALIZADA

# Práctica 1. Análisis de Prestaciones
Durante la sesión se pretende calcular la fracción de tiempo de un componente de un programa (cálculo de producto escalar) aplicando la ley de Amdahl así como evaluar y comparar las prestaciones de distintas arquitecturas.

## Ley de Amdahl

Recuérdese la ecuación de Amdahl que se usará durante el desarrollo de la práctica.

$$ S' = {1 \over 1 - F + {F \over S}} $$

## Cálculo de la aceleración local S

Se va a estimar el valor de S a partir de las implementaciones de las funciones Scalar() y ScalarSSE() que implementan el producto escalar de dos vectores. La segunda, con instrucciones SSE, debe ser más rápida que la primera. Para calcular la aceleración, se va a observar el tiempo de ejecución de ambas implementaciones para luego obtener la relación con la ecuación de Amdahl.

```bash
$ time scalar-std 100000 1024
```

Rep = 100000 / size = 1024

real    0m0,739s  
user    0m0,735s  
sys     0m0,000s

```bash
$ time scalar-sse 100000 1024
```

Rep = 100000 / size = 1024

real    0m0,631s  
user    0m0,627s  
sys     0m0,000s

```bash
$ time scalar-load 100000 1024
```

Rep = 100000 / size = 1024

real    0m0,494s  
user    0m0,491s  
sys     0m0,000s

Una vez medido el tiempo de ejecución de ambas implementaciones y del load, se puede calcular Tstd y Tsse y Tload

$$ t_{std} = t_{user} + t_{sys} = 0.739s + 0.000s = 0.739s $$  
$$ t_{sse} = t_{user} + t_{sys} = 0.627s + 0.000s = 0.627s $$  
$$ t_{load} = t_{user} + t_{sys} = 0.491 + 0.000s = 0.491 $$

para posteriormente estimar el valor de S:

$$ S = {t_{std} - t_{load} \over t_{sse} - t_{load}} = 1.83 $$

## Cálculo de la fracción de tiempo local F

Se va ahora a calcular S' con ayuda del programa _matrix.c_, para poder así despejar F y obtener la fracción de tiempo que el programa emplea a calcular productos escalares.

Para ello, se volverá a medir el tiempo de ejecución del programa _matrix.c_ con ambas implementaciones (estándar y sse).

```bash
$ time matrix-std 1 1024
```

Rep = 1 / size = 1024

real    0m3,478s  
user    0m3,460s  
sys     0m0,011s  


$$ t_{mat-std} = 3.460s + 0.011s = 3.471s $$

```bash
$ time matrix-sse 1 1024
```

Rep = 1 / size = 1024

real    0m2,095s  
user    0m2,089s  
sys     0m0,000s

$$ t_{mat-sse} = 2.089s $$

Por lo tanto,

$$ S' = {t_{mat-std} \over t_{mat-sse}} = 1.66 $$

Despejando la Ley de Amdahl,

$$ F = -{S(S'-1) \over S'(1-S)} = 0.876 $$

se obtiene la fracción de tiempo que la aplicación _matrix_ emplea en realizar productos escalares F = 87.6%

## Cálculo experimental de la fracción de tiempo local (Fexp)

Dadas las características del programa y del componente de estudio puede estimarse de manera experimental la fracción de tiempo que el programa emplea en calcular productos escalares. Para ello, se debe medir el tiempo residual del programa, utilizando una macro vacía para calcular los productos escalares.

```bash
$ time matrix-res 1 1024
```

Rep = 1 / size = 1024

real    0m0,672s  
user    0m0,667s  
sys     0m0,000s

$$ t_{mat-res} = 0.667s $$

Ahora puede calcularse Fexp de la siguiente manera

$$ F_{exp} = {t_{mat-std} - t_{mat-res} \over t_{mat-std}} = 0.807 $$

## Recogida de medidas para análisis de prestaciones.

En este apartado se van a medir las prestaciones del computador empleando los siguientes programas:  
- Dos benchmarks sintéticos (dhrystone, para aritmética entera, y whetstone, para aritmética en coma flotante)
- Dos aplicaciones reales: el compilador del lenguaje de programación C gcc, que solo utiliza aritmética entera, y la aplicación xv de procesamiento de imagen.

Para ello se miden los tiempos

```bash
$ time dhrystone
```

real    0m5,178s  
user    0m0,583s  
sys     0m0,000s

$$ T_{dhrystone} = 0.583s $$

```bash
$ time whetstone 10000
```

real    0m0,197s  
user    0m0,150s  
sys     0m0,000s

$$ T_{whetstone} = 0.150s $$

```bash
$ cd xv-310a/
$ make clean
$ time make
```

real    0m30,742s  
user    0m11,114s  
sys     0m2,962s

$$ T_{gcc} = 11.114s + 2.962s = 14.076s $$

```bash
$ time xv-310a/xv -wait 5 mundo.jpg
```

real    0m7,092s  
user    0m0,758s  
sys     0m0,100s

$$ T_{xv} = 0.858s $$

## Análisis de Prestaciones

Una vez recogidas las medidas se procede al análisis de prestaciones.  
La siguiente tabla muestra los tiempos de ejecución en 3 máquinas. A y B son dos máquinas hipotéticas.

### Tiempos de ejecución de las aplicaciones y media aritmética

| Programa / Máquina | A | B | C |
|-|-|-|-|
| dhrystone | 5 | 18 | 0.583 |
| whetstone | 2.5 | 10 | 0.150 |
| gcc | 40 | 130 | 14.076 |
| xv | 4.5 | 15 | 0.858 |
| _Media aritmética_ | 13 | 43.25 | 3.917 |

### Tiempos de ejecución normalizados a la máquina B y media geométrica

| Programa / Máquina | A | B | C |
|-|-|-|-|
| dhrystone | 3.6 | 1 | 30.87 |
| whetstone | 4 | 1 | 66.67 |
| gcc | 3.25 | 1 | 9.24 |
| xv | 3.33 | 1 | 17.48 |
| _Media geométrica de los speedups sobre B_ | 3.53 | 1 | 24.01 |

### Preguntas adicionales:
- __¿Cuál es el speedup (o mejora del sistema) del a máquina A sobre la B si se considera media aritmética?__  
$$ S = {43.25 \over 13} = 3.33 $$
- __¿Cuál es el speedup de la máquina A sobre la B si se considera media geométrica de los speedups?__  
$$ S = {3.53 \over 1} = 3.53 $$
# Práctica 4. Planificación Estática de Instrucciones
Durante el desarrollo de la práctica se pretende conocer, comprender y aplicar algunas técnicas de gestión estática de instrucciones.

## Simulador RISC V con operaciones multiciclo
Se dispone de un simulador `codigo/riscv-m` con operaciones multiciclo implementadas (unidad de _load/store_, operador de multiplicación, op. de división, op de suma y op. de comparación).

## Ejempo de programa para RISC V
A continuación, se muestra el código ensamblador correspondiente a un bucle que realiza la suma de un valor escalar a un vector almacenado en la memoria (Z = a + Y , bucle DAPY, `codigo/dapy.s`):

```assembly
        # z = a + y
        # vector size: 60 elements
        # vector y
        .data
		
y:      .double 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0
        .double 10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0
        .double 20.0,21.0,22.0,23.0,24.0,25.0,26.0,27.0,28.0,29.0
        .double 30.0,31.0,32.0,33.0,34.0,35.0,36.0,37.0,38.0,39.0
        .double 40.0,41.0,42.0,43.0,44.0,45.0,46.0,47.0,48.0,49.0
        .double 50.0,51.0,52.0,53.0,54.0,55.0,56.0,57.0,58.0,59.0

        # vector z
        # 60 elements are 480 bytes
z:      .space 480

        # scalar a
a:      .double 1.0

        .text

start:
        addi t1,gp,y     # t1 points to y
        addi t2,gp,z     # t2 points to z
        fld f0,a(gp)     # f0 holds a
        addi t3,t1,480   # 60 elements are 480 bytes
loop:
        fld f1,0(t1)
        fadd.d f2,f0,f1
        fsd f2, 0(t2)
        addi t1,t1,8
        sub t4,t3,t1
        addi t2,t2,8
        bnez t4,loop

        ori a7,zero,10         # end
	ecall
```

Se lanza a ejecución este programa con **cortocircuitos**, **_pnt_** con latencia  y latencia de sumador 5

```bash
$ ./codigo/riscv-m -j -d c -c pnt1 -a 5 -f codigo/dapy.s
```
El resultado puede visualizarse [aqui](https://aic-practicas.netlify.app/p4/ej1.htm)

⇒ Analiza la ejecución de la primera iteración del bucle, accediendo al enlace Estado. Comprueba los ciclos de parada insertados para resolver las dependencias de datos y de control en el código. Responde a las siguientes preguntas sobre la ejecución del programa:

* Instrucciones = _427_
* Ciclos = _729_
* CPI = _1.71_

## Modificación del programa aplicando planificación estática de instrucciones

### _Loop unrolling_
Como la latencia del sumador es de 5 ciclos, el máximo número de ciclos de parada necesario para resolver el riesgo RAW producido es de tres ciclos. Por lo tanto, el código del bucle Z = a + Y debe ser replicado 4 veces (3+1), y se muestra a continuación(`codigo/dapyu1.s`):

```s
        # z = a + y
        # vector size: 60 elements
        # vector y
        .data
		
y:      .double 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0
        .double 10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0
        .double 20.0,21.0,22.0,23.0,24.0,25.0,26.0,27.0,28.0,29.0
        .double 30.0,31.0,32.0,33.0,34.0,35.0,36.0,37.0,38.0,39.0
        .double 40.0,41.0,42.0,43.0,44.0,45.0,46.0,47.0,48.0,49.0
        .double 50.0,51.0,52.0,53.0,54.0,55.0,56.0,57.0,58.0,59.0

        # vector z
        # 60 elements are 480 bytes
z:      .space 480

        # scalar a
a:      .double 1.0

        .text

start:
        addi t1,gp,y     # t1 points to y
        addi t2,gp,z     # t2 points to z
        fld f0,a(gp)     # f0 holds a
        addi t3,t1,480   # 60 elements are 480 bytes
loop:
        fld f1,0(t1)
        fadd.d f2,f0,f1
        fsd f2,0(t2)
        fld f3,8(t1)
        fadd.d f4,f0,f3
        fsd f4,8(t2)
        fld f5,16(t1)
        fadd.d f6,f0,f5
        fsd f6,16(t2)
        fld f7,24(t1)
        fadd.d f8,f0,f7
        fsd f8,24(t2)
        addi t1,t1,32
        sub t4,t3,t1
        addi t2,t2,32
        bnez t4,loop

        ori a7,zero,10         # end
	ecall
```
Se lanza a ejecución este programa con **cortocircuitos**, **_pnt_** con latencia  y latencia de sumador 5
```bash
$ ./codigo/riscv-m -j -d c -c pnt1 -a 5 -f codigo/dapyu1.s
```
El resultado puede visualizarse [aqui](https://aic-practicas.netlify.app/p4/ej2.htm)

⇒ Comprueba la corrección del resultado obtenido. Anota su tiempo de ejecución y los CPI obtenidos. Cuantifica la mejora frente al programa original y relaciónala con la reducción del número de iteraciones del bucle.

* Instrucciones = _247_
* Ciclos = _504_
* CPI = _2.04_

$$ S = {T_{dapy} \over T_{dapyu1}} = {{427 * 1.71 * t} \over {247 * 2.04 * t}} = 1.45 $$

Este código puede modificarse para eliminar todos los riesgos de datos (`codigo/dapyu.s`):

```s
        # z = a + y
        # vector size: 60 elements
        # vector y
        .data
		
y:      .double 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0
        .double 10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0
        .double 20.0,21.0,22.0,23.0,24.0,25.0,26.0,27.0,28.0,29.0
        .double 30.0,31.0,32.0,33.0,34.0,35.0,36.0,37.0,38.0,39.0
        .double 40.0,41.0,42.0,43.0,44.0,45.0,46.0,47.0,48.0,49.0
        .double 50.0,51.0,52.0,53.0,54.0,55.0,56.0,57.0,58.0,59.0

        # vector z
        # 60 elements are 480 bytes
z:      .space 480

        # scalar a
a:      .double 1.0

        .text

start:
        addi t1,gp,y     # t1 points to y
        addi t2,gp,z     # t2 points to z
        fld f0,a(gp)     # f0 holds a
        addi t3,t1,480   # 60 elements are 480 bytes
loop:
        fld f1,0(t1)
        fld f3,8(t1)
        fld f5,16(t1)
        fld f7,24(t1)
        fadd.d f2,f0,f1
        fadd.d f4,f0,f3
        fadd.d f6,f0,f5
        fadd.d f8,f0,f7
        fsd f2,0(t2)
        fsd f4,8(t2)
        fsd f6,16(t2)
        fsd f8,24(t2)
        addi t1,t1,32
        sub t4,t3,t1
        addi t2,t2,32
        bnez t4,loop

        ori a7,zero,10         # end
	ecall
```
Se lanza a ejecución este programa con **cortocircuitos**, **_pnt_** con latencia  y latencia de sumador 5
```bash
$ ./codigo/riscv-m -j -d c -c pnt1 -a 5 -f codigo/dapyu.s
```
El resultado puede visualizarse [aqui](https://aic-practicas.netlify.app/p4/ej3.htm)

⇒ Comprueba la corrección del resultado obtenido. Anota su tiempo de ejecución y los CPI obtenidos. Cuantifica la mejora frente al programa original y relaciónala con la reducción del número de iteraciones del bucle.

* Instrucciones = _247_
* Ciclos = _264_
* CPI = _1.07_

$$ S = {T_{dapy} \over T_{dapyu}} = {{427 * 1.71 * t} \over {247 * 1.07 * t}} = 2.76 $$

⇒ Analiza este último código y razona por qué el factor de replicación debe ser 4. Por ejemplo, ¿qué pasaría si el factor de replicación fuese 3? ¿Y si fuese 5? Puedes modificar fácilmente el código y comprobar tu respuesta.

El factor de replicación debe ser 4 para aprovachar al máximo la ventaja de usar _loop unrolling_. Si fuera 3, se reduciría el tiempo con respecto del programa original, pero no lo suficiente. En caso de ser 5, la última réplica introduciría 3 ciclos de parada que no serían aprovechados, provocando una situación muy parecida a la del bucle original.

### _Software pipelining_
Básicamente, el software pipelining sustituye el bucle original por otro nuevo en el que las instrucciones que se ejecutan pertenecen a iteraciones distintas del bucle original, con lo que se eliminan los riesgos de datos.

El código modificado (almacenado en `codigo/dapysp.s`):
```s
        # z = a + y
        # vector size: 60 elements
        # vector y
        .data
		
y:      .double 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0
        .double 10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0
        .double 20.0,21.0,22.0,23.0,24.0,25.0,26.0,27.0,28.0,29.0
        .double 30.0,31.0,32.0,33.0,34.0,35.0,36.0,37.0,38.0,39.0
        .double 40.0,41.0,42.0,43.0,44.0,45.0,46.0,47.0,48.0,49.0
        .double 50.0,51.0,52.0,53.0,54.0,55.0,56.0,57.0,58.0,59.0

        # vector z
        # 60 elements are 480 bytes
z:      .space 480

        # scalar a
a:      .double 1.0

        .text

start:
        addi t1,gp,y     # t1 points to y
        addi t2,gp,z     # t2 points to z
        fld f0,a(gp)     # f0 holds a
        addi t3,t1,480   # 60 elements are 480 bytes
prepara:
        fld f2,0(t1)
        fadd.d f4,f0,f2
        fld f2,8(t1)
        addi t1,t1,16
loop:
        fsd f4, 0(t2)
        fadd.d f4,f0,f2
        fld f2,0(t1)
        addi t1,t1,8
        sub t4,t3,t1
        addi t2,t2,8
        bnez t4,loop
resto:
        fsd f4, 0(t2)
        fadd.d f4,f0,f2
        fsd f4, 8(t2)

        ori a7,zero,10         # end
	ecall
```
Se ejecuta
```bash
$ ./codigo/riscv-m -j -d c -c pnt1 -a 5 -f codigo/dapysp.s
```
El resultado puede visualizarse [aqui](https://aic-practicas.netlify.app/p4/ej4.htm)

⇒ Comprueba la corrección del resultado obtenido. Anota su tiempo de ejecución y los CPI obtenidos. Cuantifica la mejora frente al programa original y relaciónala con la reducción del número de iteraciones del bucle.

* Instrucciones = _420_
* Ciclos = _485_
* CPI = _1.15_

$$ S =  {T_{dapy} \over T_{dapyu}} = {{427 * 1.71 * t} \over {420 * 1.15 * t}} =  1.51 $$

⇒ ¿Qué paradas debidas a riesgos de datos encuentras durante la ejecución de una iteración del bucle en el código que utiliza _software pipelining_?

Existe un ciclo de parada debido al conflicto existente entre las instrucciones _fsd f4,0(t2)_ y _fadd.d f4,f0,f2_

## Desarrollo de un nuevo programa
Supondremos a partir de ahora que las latencias del sumador y multiplicador son de 2 y 4 ciclos, respectivamente (opciones _-a 2 -m 4_ al lanzar el simulador).

1. Escribe el código RISC V convencional para ejecutar la operación Z = a ∗ X + Y (bucle DAXPY), siendo el tamaño de los vectores a procesar de 60 números en coma flotante de doble precisión (fichero `codigo/daxpy.s`).  
```s
        # z = a*x + y
        # vector size: 60 elements
        # vector x
	.data
x:      .double 0,1,2,3,4,5,6,7,8,9
        .double 10,11,12,13,14,15,16,17,18,19
        .double 20,21,22,23,24,25,26,27,28,29
        .double 30,31,32,33,34,35,36,37,38,39
        .double 40,41,42,43,44,45,46,47,48,49
        .double 50,51,52,53,54,55,56,57,58,59

	# vector y
y:      .double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100

        # vector z
	# 60 elements are 480 bytes
z:      .space 480

        # scalar a
a:      .double 1

        # code
	.text

start:	
        addi t1,gp,y     # t1 points to y
        addi t2,gp,z     # t2 points to z
        addi t5,gp,x     # t5 points to x
        fld f0,a(gp)     # f0 holds a
        addi t3,t1,480   # 60 elements are 480 bytes
loop:
        fld f5, 0(t5)
        fmul.d f2,f0,f5
        fld f1, 0(t1)
        fadd.d f2, f2, f1
        fsd f2, 0(t2)
        addi t1,t1,8
        addi t5,t5,8
        sub t4,t3,t1
        addi t2,t2,8
        bnez t4,loop

	ori a7,zero,10         # end
	ecall
```
Se ejecuta,
```bash
$ ./codigo/riscv-m -j -d c -c pnt1 -a 2 -m 4 -f codigo/daxpy.s
```
El resultado puede visualizarse [aqui](https://aic-practicas.netlify.app/p4/ej5.htm)

* Instrucciones = _608_
* Ciclos = _850_
* CPI = _1.4_

2. Aplica la técnica del _loop unrolling_ al código desarrollado, reorganizándolo, en su caso, también para reducir los ciclos de parada insertados (fichero `codigo/daxpyu1.s`).

Como la latencia del multiplicador es de 4 ciclos, el máximo número de ciclos de parada necesario para resolver el riesgo RAW producido es de dos ciclos. Por lo tanto, el bucle debe replicarse 2 + 1 veces.

```s
        # z = a*x + y
        # vector size: 60 elements
        # vector x
	.data
x:      .double 0,1,2,3,4,5,6,7,8,9
        .double 10,11,12,13,14,15,16,17,18,19
        .double 20,21,22,23,24,25,26,27,28,29
        .double 30,31,32,33,34,35,36,37,38,39
        .double 40,41,42,43,44,45,46,47,48,49
        .double 50,51,52,53,54,55,56,57,58,59

	# vector y
y:      .double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100

        # vector z
	# 60 elements are 480 bytes
z:      .space 480

        # scalar a
a:      .double 1

        # code
	.text

start:	
        addi t1,gp,y     # t1 points to y
        addi t2,gp,z     # t2 points to z
        addi t5,gp,x     # t5 points to x
        fld f0,a(gp)     # f0 holds a
        addi t3, t1, 480 # 60 elements are 480 bytes
loop:
        fld f5, 0(t5)
        fmul.d f2,f0,f5
        fld f1, 0(t1)
        fadd.d f2, f2, f1
        fsd f2, 0(t2)

        fld f10, 8(t5)
        fmul.d f6,f0,f10
        fld f7, 8(t1)
        fadd.d f6, f6, f7
        fsd f6, 8(t2)

        fld f15, 16(t5)
        fmul.d f8,f0,f15
        fld f9, 16(t1)
        fadd.d f8, f8, f9
        fsd f8, 16(t2)

        addi t1,t1,24
        addi t5,t5,24
        sub t4,t3,t1
        addi t2,t2,24
        bnez t4,loop

	ori a7,zero,10         # end
	ecall
```
Se ejecuta,
```bash
$ ./codigo/riscv-m -j -d c -c pnt1 -a 2 -m 4 -f codigo/daxpyu1.s
```
El resultado puede visualizarse [aqui](https://aic-practicas.netlify.app/p4/ej6.htm)

* Instrucciones = _408_
* Ciclos = _610_
* CPI = _1.5_

$$ S = {T_{daxpy} \over T_{daxpyu1}} = {{608 * 1.4 * t} \over {408 * 1.5 * t}} = 1.4 $$

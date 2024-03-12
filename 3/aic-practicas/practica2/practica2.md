# Práctica 2. Unidad de Instrucción Segmentada I
Durante la sesión se pretende conocer el manejo de un simulador de computador segmentado, analizar la influencia de los riesgos de control y datos en la prestaciones de la unidad de instrucción segmentada así como realizar programas en lenguaje ensamblador RISC V.

## Ejemplo de programa para RISC V.

El programa almacenado en el archivo _apxpy.s_ realiza la siguiente operación vectorial:

$$  \vec{Z} = a + \vec{X} + \vec{Y} $$  

### 1. Solución conflictos P (datos) y S3 (control)
Se puede lanzar a ejecución mostrando resultados detallados y resolviendo riesgos de datos mediante ciclos de parada y los de control mediante 3 ciclos de parada con la orden siguiente:  
```bash
$ ./codigo/riscv-m -j -d p -c s3 -f codigo/apxpy.s
```

Pueden verse los resultados de la ejecución en [apxpy_p_s3.htm](./resultados/apxpy_p_s3.htm)

⇒ Considera el cronograma de la primera iteración del bucle y responde a las siguientes preguntas:  
- La contribución de las instrucciones de la primera iteración al tiempo de ejecución comprende desde el ciclo __6__ al ciclo __27__.
- El número de ciclos de reloj consumido por una iteración del bucle es de __21__ ciclos cuando el salto es efectivo.
- Los ciclos de penalización son __11__ ciclos, de los cuales por riesgos de datos son __8__ ciclos y por riesgos de control son __3__ ciclos.
- El bucle ejecuta __10__ instrucciones (en cada iteración).
- El CPI alcanzado es de __2.07__.

⇒ Considera el cronograma de la última iteración del bucle y responde a las siguientes preguntas:
- El número de ciclos de reloj consumido por una iteración del bucle es de __21__ ciclos cuando el salto _no_ es efectivo.

### 2. Solución conflictos P (datos) y PNT3 (control)
Se ejecuta ahora de nuevo el programa cambiando la resolución de riesgos de control a _predict-not-taken_ con tres ciclos de penalización.
```bash
$ ./codigo/riscv-m -j -d p -c pnt3 -f codigo/apxpy.s
```
Pueden verse los resultados de la ejecución en [apxpy_p_pnt3.htm](./resultados/apxpy_p_pnt3.htm)

⇒ Considera el cronograma de la primera iteración del bucle y responde a las siguientes preguntas:  
- La contribución de las instrucciones de la primera iteración al tiempo de ejecución comprende desde el ciclo __6__ al ciclo __27__.
- El número de ciclos de reloj consumido por una iteración del bucle es de __21__ ciclos cuando el salto es efectivo.
- Los ciclos de penalización son __8__ ciclos, de los cuales por riesgos de datos son __6__ ciclos y por riesgos de control son __2__ ciclos.
- El bucle ejecuta __10__ instrucciones (en cada iteración).
- El CPI alcanzado es de __2.05__.

⇒ Considera el cronograma de la última iteración del bucle y responde a las siguientes preguntas:
- El número de ciclos de reloj consumido por una iteración del bucle es de __22__ ciclos cuando el salto _no_ es efectivo.

### 3. Solución conflictos C (datos) y PNT3 (control)
Se ejecuta ahora de nuevo el programa cambiando la resolución de riesgos de datos a _cortocircuito_, manteniendo la estrategia de control (_predict-not-taken_)
```bash
$ ./codigo/riscv-m -j -d c -c pnt3 -f codigo/apxpy.s
```
Pueden verse los resultados de la ejecución en [apxpy_c_pnt3.htm](./resultados/apxpy_c_pnt3.htm)

⇒ Considera el cronograma de la primera iteración del bucle y responde a las siguientes preguntas:  
- La contribución de las instrucciones de la primera iteración al tiempo de ejecución comprende desde el ciclo __6__ al ciclo __21__.
- El número de ciclos de reloj consumido por una iteración del bucle es de __15__ ciclos cuando el salto es efectivo.
- Los ciclos de penalización son __5__ ciclos, de los cuales por riesgos de datos son __2__ ciclos y por riesgos de control son __3__ ciclos.
- El bucle ejecuta __10__ instrucciones (en cada iteración).
- El CPI alcanzado es de __1.48__.

## Realización de modificaciones en el código.
Se pretende ahora efectuar cambios en el código a ejecutar de tal manera que se reduzca en lo posible el número de ciclos de parada.

### 1. Modificación para estrategias C (datos) y PNT3 (control)

Se ha modificado el archivo apxpy.s (guardado en el nuevo archivo [apxpy-p3.s](./codigo/apxpy-p3.s)) cambiando el orden de las instrucciones para mejorar el CPI.

Véase el nuevo bucle _loop_:
```assembly
loop:   ld a0,0(t0)
        addi t0,t0,8
        add a0,t4,a0
        ld a1,0(t1)
        addi t1,t1,8
        add a1,a0,a1
        slt t5,t0,t3
        sd a1,0(t2)
        addi t2,t2,8
        bnez t5,loop
```
Ejecución del nuevo código.

```bash
$ ./codigo/riscv-m -j -d c -c pnt3 -f codigo/apxpy-p3.s
```
Pueden verse los resultados de la ejecución en [apxpy-p3_c_pnt3.htm](./resultados/apxpy-p3_c_pnt3.htm)

⇒ Considera el cronograma de la primera iteración del bucle y responde a las siguientes preguntas:  
- La contribución de las instrucciones de la primera iteración al tiempo de ejecución comprende desde el ciclo __6__ al ciclo __19__.
- El número de ciclos de reloj consumido por una iteración del bucle es de __13__ ciclos cuando el salto es efectivo.
- Los ciclos de penalización son __3__ ciclos, de los cuales por riesgos de datos son __0__ ciclos y por riesgos de control son __3__ ciclos.
- El bucle ejecuta __10__ instrucciones (en cada iteración).
- El CPI alcanzado es de __1.29__.

### 2. Modificación para estrategias C (datos) y PNT1 (control)

No ha sido necesario realizar más modificaciones para mejorar el CPI con estas estrategias. 

```bash
$ ./codigo/riscv-m -j -d c -c pnt1 -f codigo/apxpy-p3.s
```
Pueden verse los resultados de la ejecución en [apxpy-p3_c_pnt1.htm](./resultados/apxpy-p3_c_pnt1.htm)

⇒ Considera el cronograma de la primera iteración del bucle y responde a las siguientes preguntas:  
- La contribución de las instrucciones de la primera iteración al tiempo de ejecución comprende desde el ciclo __6__ al ciclo __19__.
- El número de ciclos de reloj consumido por una iteración del bucle es de __13__ ciclos cuando el salto es efectivo.
- Los ciclos de penalización son __1__ ciclos, de los cuales por riesgos de datos son __0__ ciclos y por riesgos de control son __1__ ciclos.
- El bucle ejecuta __10__ instrucciones (en cada iteración).
- El CPI alcanzado es de __1.11__.

## Desarrollo de un nuevo programa.

Dado el siguiente código en alto nivel,
```javascript
// Cuenta la cantidad de componentes nulas (0) de un vector a
cont = 0;
for (i = 0; i < n; i++) {
    if (a[i] == 0) {
        cont = cont + 1;
    }
}
```
⇒ Se pide 
- Completar el código suministrado. El número de componentes iguales a cero se debe almacenar en la variable cont al finalizar el programa.

Resuelto en [search.s](./codigo/search.s):

```assembly
        .data
a:      .dword  9,8,0,1,0,5,3,1,2,0
tam:    .dword 10         # Vector size
cont:   .dword 0          # Nr of elements == 0

        .text
start:  addi t0,gp,a      # Pointer
        ld t1,tam(gp)     # Vector size
        add t2,zero,zero  # Counter of zeros
 
loop:   ld t3, 0(t0)  
        addi t1, t1, -1
        addi t0, t0, 8
        bne t3, zero, loop
        beq t1, zero, final
        addi t2, t2, 1
        j loop

final:  bne t3, zero, exit
        addi t2, t2, 1
exit:   sd t2, cont(gp)
        ori x17,x0,10     # Exit system call
        ecall
```

- Analizar el programa y comprobar su correcto funcionamiento ejecutándolo en el simulador. Se utilizará la configuración de cortocircuitos y pnt1:
```bash
$ ./codigo/riscv-m -d c -c pnt1 -f codigo/search.s
```
Pueden verse los resultados de la ejecución en [search_c_pnt1.htm](./resultados/search_c_pnt1.htm)

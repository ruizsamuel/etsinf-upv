# Práctica 9.

## Ejercicio 1: Parámetros por referencia

**Tras la ejecución del programa, ¿dónde está el valor de la variable que ha leído?
Técnica experimental: interprete la ventana data segment del simulador.**

Se encuentra en memoria, en la dirección 0x10000000

**Si en el programa principal quisiera sumarle 1 a la variable M que acaba de leer con
InputV, ¿qué opciones de las siguientes serían correctas?**

Las opciones c) y d) son las únicas que cargan el valor de la variable, le suman 1 y vuelven a almacenar en memoria su valor.

```
# c)
jal InputV
lw $s0,M
addi $s0,$s0,1
sw $s0,M
```

```
# d)
jal InputV
lw $s0,0($a1)
addi $s0,$s0,1
sw $s0,0($a1)
```

**¿En qué dirección de memoria se encuentra almacenada la variable R?**

Se encuentra almacenada en 0x10000008

**Ejecute el programa con los valores M=5 y Q=–5. Consulte el segmento de datos del programa y localice los valores de las variables M, Q y R almacenados en la memoria.**

M=0x00000005 (0x10000000)

Q=0xFFFFFFFB (0x10000004)

R=0xFFFFFFE7 (0x10000008)

## Ejercicio 2: Parámetros de tipo cadena de caracteres

**Compile el programa y ejecútelo. ¿Dónde está la cadena que ha tecleado? Búsquela en la ventana data segment del simulador.**

La cadena se encuentra almacenada en la memoria a partir de la dirección 0x1000001A y podrá tener una longitud máxima de 80 caracteres.

## Ejercicio 3: Recorrido de cadenas de caracteres

```
StrLen:   li $v0, -2     
Buc:      lb $t2, ($a0)
          addi $v0, $v0, 1
          addi $a0, $a0, 1
          bne $t2, $zero, Buc
          jr $ra
```

## Cuestiones diversas

**1) Indique en cuál o cuáles instrucciones máquina se podría traducir la pseudoinstrucción lw $t0, var si la dirección de la variable var (o sea, el valor de la etiqueta var) es:**
    
    - 0x1000   -> lw $8, 4096($0)

    - 0x100000 -> lui $1, 16
                  lw $8, 0($1)

    - 0x101000 -> lui $1, 16
                  lw $8, 4096($1)

**2) Suponga que la dirección de la variable A es 0x10000000.Compare estos dos fragmentos de código equivalentes:**

```
# a)
lw $t0, A
addi $t0, $t0, 1
sw $t0, A
```

```
# b)
la $t0, A
lw $t1, 0($t0)
addi $t1, $t1, 1
sw $t1, 0($t0)
```

**¿Cuál de los dos códigos máquina resultantes es el más corto?**

El fragemento a) generará una instrucción adicional por cada lectura/escritura en memoria, mientras que el código b), solo generará una instrucción extra en el _la_ (load address) de la primera línea. Por lo tanto, ambos fragementos generan un código máquina con el **mismo número de instrucciones**.

**3) Considere el fragmento de código siguiente:**

```
alpha: .asciiz "á"
lb $t0, alpha
```

**¿Qué valor tendrá el registro $t0 tras su ejecución? ¿Qué valor contendría si en vez de lb utilizara lbu? ¿Cuál de las dos instrucciones es más apropiado usar en este caso?**

Con la instrucción _lb_, en el registro $t0 se cargaría la palabra 0xffffffc3, puesto que se extendería el bit de signo, al estar tratándolo como un número.

Por el contrario, si se utilizara _lbu_, no se consideraría que el bit más significativo correspoda con el de signo y no lo extedendería, siendo 0x000000c3 el resultado de la palabra cargada en $t0.

**4) Haga esta prueba con el simulador: añada la instrucción addi $ra,$ra,-4 al final del cuerpo de la función InputS, justamente antes de la instrucción jr $ra, y haga que un programa la llame. ¿Qué pasa? Explique el comportamiento.**

Esta instrucción restaría 4 a la dirección de retorno, provocando así que la instrucción _jr_ posterior salte a la instrucción _jal_ que se encarga de entrar en la subrutina, creando un blucle infinito, siendo imposible para el programa salir del fragmento de código de la función.

No obstante, en este caso no se crea un bucle infinito, puesto que en el segundo salto a InputS, no se han cargado argumentos correctos en los registros $a0, $a1 y $a2, provocando un error en una de las llamadas al sistema.
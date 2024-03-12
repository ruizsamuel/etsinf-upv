# Práctica 7

## Representación de los números en coma flotante

### Cargue el programa anterior (fichero formatos.s) y ejecútelo en el simulador. ¿Por qué aparece el valor 2.52344 como contenido del registro $f3?

Porque se ha guardado el valor 8.75 em doble precisión ocupando los registros $f2 y $f3. Al interpretarlo como doble precisión, el valor guardado es correcto. Sin embargo, si se interpreta el registro $f3 individualmente, como de simple precisión, el valor cambia puesto que solo se tienen en cuenta la mitad de los bits del valor real guardado como doble precisión.

### ¿Cuántas representaciones posibles hay para el valor real 0.0 en el estándar IEEE 754 de simple precisión? ¿Cuáles son esas representaciones? Expréselas en hexadecimal.

Existen dos representaciones, con signo positivo y negativo ("+0" y "-0").

"+0" : 0x00000000
"-0" : 0x80000000

### ¿Cuántas representaciones hay para el valor infinito (∞) en el estándar IEEE 754 de simple precisión? ¿Cuáles son esas representaciones? Expréselas en hexadecimal.

Al igual que para el 0, existen dos. Para +∞ y -∞.

+∞ : 0x7F800000
-∞ : 0xFF800000

### Indique en qué instrucciones ha traducido el programa ensamblador la pseudoinstrucción del programa li.d $f2, 8.75. Interprete el código generado.

```
ori $1, $0, 0           # Limpia el registro $1 
mtc1 $1, $2             # Mueve la palabra (0x00000000) del registro $1 a $f2

lui $1, 16417           # Carga el inmediato 0x4021 (que corresponde a los 4 bytes 
                        # más significativos de la palabra que representa 8.75) en la parte alta $1
                        
ori $1, $1, -32768      # Carga el inmediato 0x8000 (que corresponde a los 4 bytes
                        # menos significativos de la palabra que representa 8.75) en la parte baja de $1

mtc1 $1, $3             # Mueve la palabra completa (0x40218000) al registro $f3, para representar
                        # 8.75 en doble precisión
```

### Indique en hexadecimal la representación en simple y doble precisión de la constante 78.325

Simple Precisión: 0x429CA666
Doble Precisión:  0x40218000429CA666

### ¿Cuántas palabras diferentes existen en el formato del estándar IEEE 754 de simple precisión para representar el valor NaN?

Existen 2^24 - 2 palabras diferentes para representar NaN en simple precisión. 

X11111111YYYYYYYYYYYYYYYYYYYYYYY siempre que YYYYYYYYYYYYYYYYYYYYYYY sea diferente de 0 (M != 0)

## Cálculo de la media aritmética

Incompleto

## Cálculo del número π

Incompleto
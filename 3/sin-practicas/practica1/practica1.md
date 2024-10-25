# Práctica 1: Resolución de Problemas Mediante Búsqueda en un Espacio de Estados

## 1. Introducción
En esta práctica se evaluará la eficiencia, coste temporal y espacial de varias estrategias de búsqueda aplicadas al **8-puzzle**. El programa está implementado en Python y permite comparar diversas estrategias de resolución.

### Instalación y Ejecución
1. Descargar el archivo `puzzle.zip` desde PoliformaT.
2. Descomprimir el archivo.
3. Ejecutar el archivo `interface.py` desde un entorno de Python.

### Funcionamiento del Programa
1. Introducir el estado inicial como una secuencia de números (0 representa la casilla en blanco).
2. Seleccionar una estrategia de búsqueda en el menú desplegable.
3. Si es necesario, introducir un nivel de profundidad máximo.
4. Pulsar **Solve** para resolver el puzzle.

Si aparece el mensaje `Cannot solve`, la configuración no es resoluble.

## 2. Aspectos del Juego del Puzzle
- **Tablero**: 3x3 con 8 fichas numeradas y una casilla vacía.
- **Movimientos**: Las fichas se pueden mover arriba, abajo, izquierda o derecha.
- **Objetivo**: Llevar el estado inicial al estado objetivo, ordenando las fichas:

<table>
  <tr>
    <td>1</td>
    <td>2</td>
    <td>3</td>
  </tr>
  <tr>
    <td>8</td>
    <td> </td>
    <td>4</td>
  </tr>
  <tr>
    <td>7</td>
    <td>6</td>
    <td>5</td>
  </tr>
</table>

## 3. Estrategias de Búsqueda
Las estrategias de búsqueda disponibles son:

- **BFS (Anchura)**: Usa `f(n) = g(n)`, donde `g(n)` es el coste en función de la profundidad.
- **DFS (Graph Search)**: Usa `f(n) = -g(n)`.
- **DFS (Backtracking)**: Similar a DFS, pero sin mantener nodos en memoria.
- **Voraz (Manhattan)**: Usa `f(n) = D(n)`, donde `D(n)` es la distancia Manhattan.
- **ID (Profundización Iterativa)**: Realiza búsquedas sucesivas en profundidad.
- **A\* Manhattan**: Usa `f(n) = g(n) + D(n)`, combinando coste y distancia Manhattan.
- **A\* Euclidean**: Similar a A\* Manhattan, pero con la distancia Euclídea.
- **IDA\* Manhattan**: Iterativa con función `f(n) = g(n) + D(n)`.

## 4. Modificación del Código

### Fichero `interface.py`
1. Añadir la nueva estrategia en la lista de algoritmos disponibles en la línea 88.
2. Modificar la función `selectAlgorithm` (línea 248) para incluir la profundidad si es necesario.
3. Añadir la llamada a la nueva estrategia en `solveState` (línea 381).

### Fichero `main.py`
Implementar en este archivo la función heurística correspondiente.

## 5. Trabajo a Realizar

### Heurísticas a Implementar

**1. Piezas descolocadas**.

**h(n) = W(n)**, donde W es una función que dado un estado n devuelve el número de piezas en posición incorrecta en n.

```python
def getPiezasDescolocadas(state):
    tot = 0
    for i in range(9):
        if state[i] != end_state[i]:
            tot+=1
    return tot
```

**2. Secuencias**.

**h(n) = Sec(n)**, tal que Sec(n) = 3\*S(n), siendo S una función que devuelve una suma de puntos tras recorrer el estado del puzzle (menos la casilla central) de manera que:

- Se suman 2 puntos por cada ficha que no va seguida de su sucesora.
- Se suman 0 puntos por las fichas que sí preceden a su sucesora.
- Se suma 1 punto si hay una ficha en el centro.

Un ejemplo:

<table>
  <tr>
    <td>2</td>
    <td>8</td>
    <td>3</td>
  </tr>
  <tr>
    <td>1</td>
    <td>6</td>
    <td>4</td>
  </tr>
  <tr>
    <td>7</td>
    <td> </td>
    <td>5</td>
  </tr>
</table>

Para la ficha 2: Sumar 2 (porque no va seguida de su ficha sucesora 3)  
Para la ficha 8: Sumar 2 (porque no va seguida de su ficha sucesora 1)  
Para la ficha 3: Sumar 0 (porque sí va seguida de su ficha sucesora 4)  
Para la ficha 4: Sumar 0 (porque sí va seguida de su ficha sucesora 5)  
Para la ficha 5: Sumar 2 (porque no va seguida de su ficha sucesora 6)  
Para la ficha 0: Sumar 2 (se sumará 2 puntos siempre que no esté en la casilla central)  
Para la ficha 7: Sumar 2 (porque no va seguida de su ficha sucesora 8)  
Para la ficha 1: Sumar 0 (porque va seguida por su ficha sucesora 2)  
Para la ficha 6: Sumar 1 (porque es la ficha central).  

**TOTAL: S(n) = 11**
**Heurística: h(n) = Sec(n) = 3 \* S(n) = 33**


La función de la heurística h(n) en Python quedaría tal que

```python
def getSecuencias(state):
    tot = 0
    j = 0
    for i in [1,2,5,8,7,6,3]:
        if str(state[i]) == '0':
            tot += 3
        elif int(state[j]) + 1 == int(state[i]):
            tot += 0
        else:
            tot += 2
        j = i
    return tot
```

**3. Filas y Columnas**

**h(n) = FilCol(n)** donde FilCol es una función que dado un estado n, devuelve una suma tal que:

- Suma 1 por cada ficha que no se encuentra en su columna correcta
- Suma 1 por cada ficha que no se encuentra en su fila correcta

El valor mínimo de una ficha será entonces 0 y su máximo 2.

Implemetada en Python:

```python
def getFilasColumnas(state):
    tot = 0
    filas = ['123','804','765']
    columnas = ['187','206','345']

    for i in range(9):
        if state[i] not in filas[i // 3]:
            tot += 1
        if state[i] not in columnas[i % 3]:
            tot += 1
    return tot
```

**4. Cuestiones**

Antes de responder se anotan los resultados de varias configuraciones del puzzle.

<table>
  <tr>
    <td> </td>
    <td>2</td>
    <td>3</td>
  </tr>
  <tr>
    <td>8</td>
    <td>4</td>
    <td>5</td>
  </tr>
  <tr>
    <td>7</td>
    <td>1</td>
    <td>6</td>
  </tr>
</table>

|                 | BFS | DFS (GS)  | DFS (Back) | Voraz | ID   | A* Manh | A* Euclid | IDA* Manh | Desc | Sec  | FilCol |
|-----------------|-----|-----------|------------|-------|------|---------|-----------|-----------|------|------|--------|
| Nodes generated |8761 |3230       |3539        |494    |20086 |215      |286        |307        |503   |106   |316     |
| Nodes expanded  |3250 |1812       |2269        |185    |12726 |79       |106        |196        |186   |37    |117     |
| Max nodes stored|5118 |1817       |27          |298    |27    |135      |174        |22         |300   |70    |196     |
| Solution cost   |14   |14         |14          |34     |14    |14       |14         |14         |14    |14    |14      |
| Search depth    |15   |14         |14          |34     |14    |14       |14         |14         |14    |14    |14      |
| Running time    |0,01s|0,01s      |0,00s       |0,00s  |0,02s |0,00s    |0,00s      |0,00s      |0,00s |0,00s |0,00s   |

<table>
  <tr>
    <td>7</td>
    <td>8</td>
    <td>1</td>
  </tr>
  <tr>
    <td>4</td>
    <td>0</td>
    <td>6</td>
  </tr>
  <tr>
    <td>2</td>
    <td>3</td>
    <td>5</td>
  </tr>
</table>

|                 | BFS   | DFS (GS) | DFS (Back) | Voraz | ID      | A* Manh | A* Euclid | IDA* Manh | Desc | Sec  | FilCol |
|-----------------|-------|----------|------------|-------|---------|---------|-----------|-----------|------|------|--------|
| Nodes generated |233687 |158233    |435995      |82     |2198820  |706      |2781       |1409       |17464 |457   |4384    |
| Nodes expanded  |86871  |60265     |279820      |30     |1390965  |266      |1028       |882        |6452  |151   |1601    |
| Max nodes stored|110728 |60275     |43          |53     |43       |428      |1650       |35         |10067 |273   |2602    |
| Solution cost   |22     |22        |22          |22     |22       |22       |22         |22         |22    |32    |22      |
| Search depth    |23     |22        |22          |22     |22       |22       |22         |22         |22    |32    |22      |
| Running time    |0,34   |0,24 s    |0,34s       |0,00s  |1,65s    |0,01     |0,02s      |0,00s      |0,05s |0,00s |0,02s   |

**4.1 La estrategia de búsqueda implementada con la función heurística Secuencias, ¿es un algoritmo de tipo A\*? Justifica la respuesta**

Para que la estrategia se considere de tipo A* se debe comprobar que satisface los siguientes requisitos.

1. Uso de la función de coste, tal que

$$ f(n) = g(n) + h(n) $$

Donde g(n) representa el coste acumulado desde el nodo inicial hasta el nodo n y h(n) es la estimación heurística del coste desde n hasta el objetivo.

Teniendo en cuenta el enunciado que propone esta función, se ha optado por implementar esta estrategia de búsqueda teniendo en cuenta el coste acumulado (uso de `funcion_1`).

Por lo tanto, se **cumple este primer requisito**.

2. Heurística admisible

Debe verificarse

$$ h(n) \leq h^*(n) $$

Donde `h*(n)` es el coste real mínimo desde el nodo n hasta el objetivo


Atendiendo a este aspecto, es evidente que la heurística no cumple, pudiendo demostrarse con el siguiente contraejemplo:

Partiendo del estado

<table>
  <tr>
    <td>1</td>
    <td>2</td>
    <td>3</td>
  </tr>
  <tr>
    <td>8</td>
    <td>6</td>
    <td>4</td>
  </tr>
  <tr>
    <td>7</td>
    <td> </td>
    <td>5</td>
  </tr>
</table>

Puede verse que `h*(n)` = 1, pues solo se requiere un movimiento hacia abajo para resolver el puzzle.

Como `h(n) = 3 * S(n)`, y `S(n) = 5` (2 puntos porque a 5 no le sigue la ficha 6, y otros 3 puntos por la ficha central), no se cumple la premisa inicial, **demostrando que esta estrategia no es una estrategia A\***


3. Heurística consistente

Este tercer requisito sería el último a comprobar para verificar que la estrategia pueda considerarse de tipo A\*. Puesto que no comple el requisito anterior no tiene sentido pararse a comprar este.

Por lo tanto, esta estrategia **no puede** considerarse de tipo A\*.


**4.2 La estrategia implementada con la función heurística FilCol, ¿es un algoritmo A\*? Justifica la respuesta.**

De manera análoga a la pregunta anterior, será necesario verificar las tres condiciones ya expuestas.

1. Uso de la función de costes

Al igual que en la pregunta anterior, puesto que se usa la `funcion_1`, que tiene en cuenta el coste acumulado, se cumple esta condición.

2. Heurística admisible

En este caso, sí que se cumple la propiedad de admisibilidad. Ya que, si una ficha se encuentra en una columna/fila incorrecta neceritará al menos un movimiento para encontrarse en su posición correcta. Dado que la función heurística nunca sobrepasará este límite, se cumple la premisa anteriormente expuesta:

$$ h(n) \leq h^*(n) $$

3. Heurística consistente

Para garantizar que A* sea eficiente y no requiera reexplorar nodos, la heurística h(n) también debe cumplir la propiedad de consistencia o ser monótona, que implica que

$$ h(n) \leq c(n, n') + h(n') $$

Donde c(n,n′) es el coste de movernos del nodo n al nodo n′. Esto asegura que la función de evaluación f(n) nunca disminuya a medida que avanzamos por el espacio de búsqueda.

En este caso, la función también satisface esta propiedad, pues al mover una ficha, h(n') podrá aumentar como mucho una unidad, que coincide con el coste de moverse de un estado a cualquiera de sus adyacentes. Por lo tanto siempre se cumplirá la propiedad de consistencia.

Debido a que las tres premisas se cumplen, puede decirse que este **algoritmo es de tipo A\*.**

**4.3 Compara la estrategia A\* Manhattan con Secuencias e indica cuál de las dos estrategias devuelve mejores soluciones (calidad de la solución y coste de la búsqueda).**

En las dos pruebas realizadas previamente se hace evidente que además de que Secuencias no arroja la solución óptima (con menor número de pasos), el coste temporal (nodos expandidos) y espacial (max. nodos almacenados) es significativamente mejor en Manhattan A\*.

**4.4 Compara ahora las estrategias Descolocadas y FilCol**

En este caso, ambas devuelven la solución óptima, aunque la estrategia FilCol demuestra un significativamente mejor rendimiento que Descolocadas (a juzgar por las pruebas que se han realizado). No obstante, FilCol no llega a tener un rendimiento tan bueno como la mejor estrategia por ahora (Manhattan A\*).

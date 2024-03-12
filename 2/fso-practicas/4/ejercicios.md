# Practica 4

## Ejercicio 1.

|                  | PID    | PPID    | COMMAND      |
|------------------|--------|---------|--------------|
| Proceso padre    | 632    | 162     | my_child     |
| Proceso hijo     | 633    | 632     | my_child     |

## Ejercicio 2.

|                  | PID    | PPID    | COMMAND        |
|------------------|--------|---------|----------------|
| Proceso padre    | 752    | 162     | range_process  |
| Proceso hijo 1   | 753    | 752     | range_process  |
| Proceso hijo 2   | 754    | 752     | range_process  |
| Proceso hijo 3   | 755    | 752     | range_process  |
| Proceso hijo 4   | 756    | 752     | range_process  |
| Proceso hijo 5   | 757    | 752     | range_process  |

## Ejercicio 3.

|                  | PID    | PPID    | COMMAND         |
|------------------|--------|---------|-----------------|
| Proceso padre    | 752    | 162     | adopted_process |
| Proceso hijo 1   | 753    | 752 - 1 | adopted_process |
| Proceso hijo 2   | 754    | 752 - 1 | adopted_process |
| Proceso hijo 3   | 755    | 752 - 1 | adopted_process |
| Proceso hijo 4   | 756    | 752 - 1 | adopted_process |
| Proceso hijo 5   | 757    | 752 - 1 | adopted_process |
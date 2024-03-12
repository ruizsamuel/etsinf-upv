# Práctica 5. Algoritmo de Tomauslo: _Issue_ y _Writeback_
Se pretende implementar y evaluar las etapas _Issue_ y _Writeback_ del algoritmo de gestión dinámica de instrucciones conocido como Algoritmo de Tomasulo en el simulador RISCV almacenado en `codigo`.

## Ejercicios a realizar

**1. Implementación del algoritmo de Tomasulo**  
Tras familiarizarse con la estructura del simulador (ver apéndice A del boletín), implementar las etapas _Issue_ y _Writeback_ del algoritmo de Tomasulo, implementadas en las funciones *fase_ISS_alum()* y *fase_WB_alum()* de los ficheros `codigo/f_lanzamiento_alum.c` y `codigo/f_transferencia_alum.c`, respectivamente.

```c
// f_lanzamiento_alum.c

boolean fase_ISS_alum() {
    // resto de la función

    case OPCODE_STORE_FP:
        /*** Busca un hueco en el tampón de escritura */
        busca_estacion(s, BUFFER_ALMACEN);
        if (s < 0) return NO;
        /* No hay sitio en la estación de reserva */

        /*** Reserva el tampón de escritura */
        SB[s].ocupado = SI;
        SB[s].OP = I_OP;
        SB[s].rob = b;

        /*** Operando 1 (en Rint) ***/
        if (Rint[I_S1].rob == MARCA_NULA) {
            SB[s].V1 = Rint[I_S1].valor;
            SB[s].Q1 = MARCA_NULA;
        } else if (RB[Rint[I_S1].rob].completado) {
            SB[s].V1 = RB[Rint[I_S1].rob].valor;
            SB[s].Q1 = MARCA_NULA;
        } else {
            SB[s].Q1 = Rint[I_S1].rob;
        }


        /*** Operando 2 (en Rfp) ***/
        
        if (Rfp[I_S2].rob == MARCA_NULA) {
            SB[s].V2 = Rfp[I_S2].valor;
            SB[s].Q2 = MARCA_NULA;
        } else if (RB[Rfp[I_S2].rob].completado) {
            SB[s].V2 = RB[Rfp[I_S2].rob].valor;
            SB[s].Q2 = MARCA_NULA;
        } else {
            SB[s].Q2 = Rfp[I_S2].rob;
        }


        /*** Desplazamiento */
        

        SB[s].desplazamiento = I_INM;


        /*** Reserva la entrada del ROB */

        RB[b].ocupado = SI;
        RB[b].OP = I_OP;
        RB[b].dest = s;
        RB[b].completado = NO;


        SB[s].confirm = NO;
        SB[s].rob = b; /* En teoría, no hace falta para las stores.
                        * Se queda aquí por si es necesario en el simulador. */

        /*** VISUALIZACIÓN ***/
        sprintf(SB[s].etiqueta, "%s", I_ETIQ);

        visualizacion();

        break;

        
    case OPCODE_OP_FP:
        /*** Se manda a la RS que corresponda: multdiv o sumrest */
        if (instruccion_multdiv_fp(I_OP)) {
            /*** Busca un hueco en la estación de reserva multdiv) */
            busca_estacion(s, RS_MULTDIV);
        } else {
            /*** Busca un hueco en la estación de reserva sumrest) */
            busca_estacion(s, RS_SUMREST);
        }
        if (s < 0) return NO;
        /* No hay sitio en la estación de reserva */

        /*** Reserva el operador virtual */
        
        RS[s].ocupado = SI;
        RS[s].OP = I_OP;
        RS[s].rob = b;

        RS[s].redondeo = I_RM;

        /*** Operando 1 ***/
        
        if (Rfp[I_S1].rob == MARCA_NULA) {
            RS[s].V1 = Rfp[I_S1].valor;
            RS[s].Q1 = MARCA_NULA;
        } else if (RB[Rfp[I_S1].rob].completado) {
            RS[s].V1 = RB[Rfp[I_S1].rob].valor;
            RS[s].Q1 = MARCA_NULA;
        } else {
            RS[s].Q1 = Rfp[I_S1].rob;
        }


        /*** Operando 2 ***/
        
        if (Rfp[I_S2].rob == MARCA_NULA) {
            RS[s].V2 = Rfp[I_S2].valor;
            RS[s].Q2 = MARCA_NULA;
        } else if (RB[Rfp[I_S2].rob].completado) {
            RS[s].V2 = RB[Rfp[I_S2].rob].valor;
            RS[s].Q2 = MARCA_NULA;
        } else {
            RS[s].Q2 = Rfp[I_S2].rob;
        }


        
        /*** Reserva la entrada del ROB */
        
        RB[b].ocupado = SI;
        RB[b].OP = I_OP;
        RB[b].dest = I_D;
        RB[b].completado = NO;

        /*** Reserva del registro destino */
        
        Rfp[I_D].rob = b;

        /*** VISUALIZACIÓN ***/
        visualizacion();

        break;

        // resto de la función
}
```

```c
// f_transferencia_alum.c

void fase_WB_alum() {

    // resto de la función

    /*** Volcado de resultados */
    BUS.condicion = RS[s].condicion;
    BUS.control = RS[s].control;
    
    BUS.codigo = RS[s].rob;
    BUS.valor = RS[s].resultado;


    /*** Libera la RS */
    RS[s].ocupado = NO;

    // ...

    for (s = INICIO_BUFFER_ALMACEN; s <= FIN_BUFFER_ALMACEN; s++) {
        if(RS[s].Q1 == BUS.codigo){
            RS[s].V1 = BUS.valor;
            RS[s].Q1 = MARCA_NULA;
        }
        if(RS[s].Q2 == BUS.codigo){
            RS[s].V2 = BUS.valor;
            RS[s].Q2 = MARCA_NULA;
        }
    }

    // resto de la función
} 
```

Una vez completado el código, se compila el simulador (comando `make` dentro del directorio `codigo`). Se crea el archivo ejecutable `codigo/riscv-ooo`
/**
 * @file f_lanzamiento.c
 *
 * Departamento de Informática de Sistemas y Computadores (DISCA)
 * Universidad Politécnica de Valencia.
 *
 * @author Sergio Sáez <ssaez@disca.upv.es>
 * @author Pedro López <plopez@disca.upv.es>
 *
 * @brief
 *    Contiene la fase de lanzamiento de instrucciones del algoritmo de
 *      Tomasulo con especulación
 *
 * @copyright [CC BY-NC-ND 4.0]
 *   Esta obra está bajo una licencia de Creative Commons Atribución-NoComercial-SinDerivadas
 *   Para evitar dudas, no se tiene permiso bajo esta Licencia para compartir Material Adaptado.
 *   Más detalles en https://creativecommons.org/licenses/by-nc-nd/4.0/legalcode.es
 */


#define f_lanzamiento_alum_C

/***** Includes ***********************************************/

#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>

#include "main.h"
#include "instrucciones.h"
#include "prediccion.h"
#include "presentacion.h"
#include "algoritmo.h"
#include "tipos.h"
#include "depurar.h"


#define visualizacion() do { \
    RS[s].estado = PENDIENTE; \
    RS[s].orden = I_ORDEN; \
    RB[b].orden = I_ORDEN; \
    RB[b].PC = I_PC; \
} while(0)

#define busca_estacion(s, tipo) \
    do {                        \
        int _i;                 \
        (s) = -1;               \
        for (_i = INICIO_##tipo; _i <= FIN_##tipo; _i++) { \
            if (!RS[_i].ocupado) {                         \
                (s) = _i;       \
                break;          \
            }                   \
        }                       \
    } while (0)

#define instruccion_sumrest_fp(op) \
    ((op) == RV32F_FADD_S || (op) == RV32D_FADD_D || \
     (op) == RV32F_FSUB_S || (op) == RV32D_FSUB_D || \
     (op) == RV32F_FSQRT_S || (op) == RV32D_FSQRT_D)

#define instruccion_multdiv_fp(op) \
    ((op) == RV32F_FMUL_S || (op) == RV32D_FMUL_D || \
     (op) == RV32F_FDIV_S || (op) == RV32D_FDIV_D)

#define instruccion_store_fp(op) \
    ((op) == RV32F_FSW || (op) == RV32D_FSD)


/**
 * Fase de lanzamiento de instrucciones
 */
boolean fase_ISS_alum() {

    int s;
    marca_t b;

    /* Decodificación */

#define I_INSTRUC IF_ISS_2.instruccion
#define I_ETIQ IF_ISS_2.etiqueta
#define I_PC IF_ISS_2.PC
#define I_ORDEN IF_ISS_2.orden
#define I_EXC IF_ISS_2.excepcion
#define I_PRED IF_ISS_2.prediccion
#define I_PRED_DATA IF_ISS_2.pred_data

    int codop, opcode, rs1, rs2, rs3, rd, rm, imm;
    formato_t fmt;
    char nemotecnico[256];
    char texto[256];

    if (IF_ISS_2.ignorar) {
        codop = PSEUDO_NOP;
    } else if (I_EXC == EXC_NONE) {
        codop = fn_riscv_decode(I_PC, I_INSTRUC, &fmt, &opcode, &rs1, &rs2, &rs3, &rd, &rm, &imm,
                                nemotecnico, texto);
    } else {
        codop = OP_TRAP;
    }

#define I_OP codop
#define I_S1 rs1
#define I_S2 rs2
#define I_S3 rs3
#define I_D rd
#define I_INM imm
#define I_RM rm

    
    /*** VISUALIZACIÓN ****/
    PC_ISS = I_PC;
    /**********************/

    /*** Si no sale correctamente hay que parar */

    if (IF_ISS_2.ignorar) {
        // Esta instrucción se ha marcado para ser ignorada
        return SI;
    } else if (Cancelar_Activa || IF_ISS_2.cancelar) { /* Este ciclo está cancelado */
        // Cancelar_Activa => Cancelada en Commit
        // IF_ISS_2.cancelar => Esta instrucción del bloque no se debe ejecutar

        /*** VISUALIZACIÓN ****/
        if (IF_ISS_2.excepcion == EXC_NONE) {
            muestra_fase("X", I_ORDEN);
        } else {
            muestra_fase_exception("X", I_ORDEN);
        }
        /**********************/

        return SI;
    } else if (ISSstall) {
        /*** VISUALIZACIÓN ****/
        muestra_fase("i", I_ORDEN);
        /**********************/

        /* Si la instrucción anterior del mismo grupo se ha parado o
         * hay una excepción en el reorder buffer entonces esta
         * instrucción ni siquiera se intenta */

        return NO;
    } /* endif */

    /*** VISUALIZACIÓN ****/
    muestra_fase("<i>I</i>", I_ORDEN);
    /**********************/
    ISSstall = SI; // Si todo va bien se cambia a NO

    /*** Busca un hueco en la cola */

    if (RB_long < CONFIG(tam_reorder)) {
        b = RB_fin;
    } else {
        return NO; /* No hay huecos en el ROB */
    }

    RB[b].excepcion = I_EXC;
    RB[b].prediccion = I_PRED;
    RB[b].pred_data = I_PRED_DATA;

    //printf("===PC %d set %d opcode %d\n", I_PC, SET(I_OP), I_OP);

    /*** Lanza la instrucción */

    if (I_PC == -1) {
        /* No hay instrucciones para lanzar o era una excepción por acceso ilegal a memoria */
        return SI; /* Si es una operación NOP normal no ocupa lugar en el ROB */
    } else if (I_OP == OP_TRAP) {
        /* Se ha producido una excepción */
        RB[b].ocupado = SI;
        RB[b].OP = I_OP;
        RB[b].orden = I_ORDEN;
        RB[b].PC = I_PC;
        RB[b].condicion = NO;
        RB[b].completado = SI; /* La instrucción no se ejecuta */

        if (I_EXC != EXC_NONE && I_EXC <= EXC_USER) {
            RB[b].excepcion = I_EXC;
        } else {
            RB[b].excepcion = EXC_TRAP(I_INM);
        }
        Excepcion_Activa = SI;
    } else {
        switch (opcode) {
            
            case OPCODE_LOAD_FP:
                /*** Busca un hueco en el tampón de lectura */
                busca_estacion(s, BUFFER_CARGA);
                if (s < 0) return NO;
                /* No hay sitio en la estación de reserva */

                /*** Reserva el tampón de lectura */
                LB[s].ocupado = SI;
                LB[s].OP = I_OP;
                LB[s].rob = b;

                /*** Operando 1 (en Rint) ***/
                if (Rint[I_S1].rob == MARCA_NULA) {
                    LB[s].V1 = Rint[I_S1].valor;
                    LB[s].Q1 = MARCA_NULA;
                } else if (RB[Rint[I_S1].rob].completado) {
                    LB[s].V1 = RB[Rint[I_S1].rob].valor;
                    LB[s].Q1 = MARCA_NULA;
                } else {
                    LB[s].Q1 = Rint[I_S1].rob;
                }

                /*** Desplazamiento */
                LB[s].desplazamiento = I_INM;

                /*** Reserva la entrada del ROB */
                RB[b].ocupado = SI;
                RB[b].OP = I_OP;
                RB[b].dest = I_D;
                RB[b].completado = NO;

                /*** Reserva del registro destino */
                Rfp[I_D].rob = b;

                /*** VISUALIZACIÓN ***/
                sprintf(LB[s].etiqueta, "%s", I_ETIQ);
                visualizacion();

                break;

            
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

                
            default:
                fprintf(stderr, "ERROR Issue (%s:%d): Instrucción %x (%s) no implementada\n", __FILE__, __LINE__,
                        I_INSTRUC, texto);
                exit(1);
                break;
        } /* endswitch */
    }

    /*** La instrucción se ha lanzado correctamente */

    if (!Excepcion_Activa) {
        /*** VISUALIZACIÓN ****/
        muestra_fase("I", I_ORDEN);
        /**********************/

        ISSstall = NO;
    } else {
        /*** VISUALIZACIÓN ****/
        muestra_fase_exception("I", I_ORDEN);
        /**********************/

        ISSstall = SI;
    }

    RB_fin = (RB_fin + 1) % CONFIG(tam_reorder);
    RB_long++;

    return SI;

} /* end fase_ISS */

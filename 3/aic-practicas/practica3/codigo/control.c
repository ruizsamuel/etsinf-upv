/***************************************************************************
 *
 * Arquitectura de Computadores.
 * Departamento de Informática de Sistemas y Computadores (DISCA)
 * Universidad Politècnica de Valencia.
 *
 * Autor: Pedro López (plopez@disca.upv.es)
 *        Sergio Sáez (ssaez@disca.upv.es)
 *
 * Fichero: control.c
 *
 * Descripción:
 *   Contiene la funciones XXnop y las comprobaciones de campos válidos en el IR
 *
 ****************************************************************************/


/*** Includes **************************************************************/

#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <inttypes.h>


/*** Definiciones Externas *************************************************/

#include "tipos.h"
#include "main.h"
#include "instrucciones.h"
#include "presentacion-html.h"

#define pasar_NOP_fase1(operador, latencia ,tipo) \
    uword nmask; \
    /* printf("pasar_NOP rd=%d %s\n", ID_##operador##n.IR.rd, __FUNCTION__); */  \
    ID_##operador##n.Fa.fp_d = 0; \
    ID_##operador##n.Fb.fp_d = 0; \
    ID_##operador##n.Ra = 0; \
    ID_##operador##n.Rb = 0; \
    ID_##operador##n.IR = inst_vacia; \
    ID_##operador##n.iPC = -1; \
    ID_##operador##n.orden = -1; \

#define pasar_NOP_fase2(operador, latencia, tipo) \
    uword nmask; \
    /* printf("pasar_NOP2 rd=%d %s\n", operador##n[2].IR.rd, __FUNCTION__); */ \
    /* Si hay una operación en curso */ \
    if (operador##n[2].iPC != -1) { \
        if (R##tipo[operador##n[2].IR.rd].busy == Ciclo + latencia - 2) { \
        /* Liberar registro, si es nuestro */ \
        R##tipo[operador##n[2].IR.rd].busy = 0; \
     	} \
     	/* Anular la escritura en (latencia-2) ciclos: */ \
     	/* x = x & mask = ~(~x | ~mask) */ \
     	nmask = 1 << (latencia - 2); \
     	R##tipo##_writings = ~(~R##tipo##_writings | nmask); \
    } \
    operador##n[2].ALUout.fp_d = 0; \
    operador##n[2].Fa.fp_d = 0; \
    operador##n[2].Fb.fp_d = 0; \
    operador##n[2].Fc.fp_d = 0; \
    operador##n[2].ALUout.int_d = 0; \
    operador##n[2].Ra.int_d = 0; \
    operador##n[2].Rb.int_d = 0; \
    operador##n[2].address = 0; \
    operador##n[2].IR = inst_vacia; \
    operador##n[2].iPC = -1; \
    operador##n[2].orden = -1; \


/*** Variables Globales ****************************************************/

/*** Variables Locales *****************************************************/

/*** Funciones Exportables *************************************************/

/**
 * Envia un -nop- a la fase ID
 *
 *
 */
void pasar_a_ID_NOP(
        ) {
    IF_IDn.IR = inst_vacia;
    IF_IDn.iPC = -1;
    IF_IDn.orden = -1;
}

/**
 * Envia un -nop- a la fase EX
 *
 *
 */
void pasar_a_EX_NOP(
        ) {

    uword nmask;

    ID_EXn.IR = inst_vacia;
    ID_EXn.iPC = -1;
    ID_EXn.orden = -1;
    // Anular la escritura dentro de dos ciclos:
    // x = x & mask = ~(~x | ~masc) = ~(~x | nmask)
    nmask = 1 << 2;
    Rint_writings = ~(~Rint_writings | nmask);
}

/**
 * Envia un -nop- a la fase MEM
 *
 *
 */
void pasar_a_MEM_NOP(
        ) {

    uword nmask;

    EX_MEMn.IR = inst_vacia;
    EX_MEMn.iPC = -1;
    EX_MEMn.orden = -1;
    // Anular la escritura el próximo ciclo:
    // x = x & masc = ~(~x | ~masc)
    // Ej: x = x & 1..101 = ~(~x | ~1..101) = ~(~x | 0..010)
    nmask = 1 << 1;
    Rint_writings = ~(~Rint_writings | nmask);
}

/**
 * Envia un -nop- a la fase WB
 *
 *
 */
void pasar_a_WB_NOP(
        ) {
    MEM_WBn.IR = inst_vacia;
    MEM_WBn.iPC = -1;
    MEM_WBn.orden = -1;
}

/**
 * Envia un -nop- al operador LS, fase1
 *
 *
 */
void pasar_a_FP_LS1_NOP(
        ) {
    pasar_NOP_fase1(FP_LS, FP_LS_LAT, fp);
}

/**
 * Envia un -nop- al operador FP ADD, fase1
 *
 *
 */
void pasar_a_FP_A1_NOP(
        ) {
    pasar_NOP_fase1(FP_ADD, FP_ADD_LAT, fp);
}

/**
 * Envia un -nop- al operador FP MUL, fase1
 *
 *
 *
 *
 *
 */
void pasar_a_FP_M1_NOP(
        ) {
    pasar_NOP_fase1(FP_MUL, FP_MUL_LAT, fp);
}

/**
 * Envia un -nop- al operador iM, fase 1
 *
 *
 */
void pasar_a_iM1_NOP(
        ) {
    pasar_NOP_fase1(INT_MUL, INT_MUL_LAT, int);
}

/**
 * Envia un -nop- al operador MISC, fase 1
 *
 *
 */
void pasar_a_MISC1_NOP(
        ) {
    pasar_NOP_fase1(FP_MISC, FP_MISC_LAT, int);
}

/**
 * Envia un -nop- al operador FP CMP, fase 1
 *
 *
 */
void pasar_a_FP_C1_NOP(
        ) {
    pasar_NOP_fase1(FP_CMP, FP_CMP_LAT, fp);
}

/**
 * Envia un -nop- al operador LS, fase 2
 *
 *
 */
void pasar_a_FP_LS2_NOP(
        ) {
    pasar_NOP_fase2(FP_LS, FP_LS_LAT, fp);
}

/**
 * Envia un -nop- al operador FP ADD, fase 2
 *
 *
 */
void pasar_a_FP_A2_NOP(
        ) {
    pasar_NOP_fase2(FP_ADD, FP_ADD_LAT, fp);
}

/**
 * Envia un -nop- al operador FP MUL, fase 2
 *
 *
 */
void pasar_a_FP_M2_NOP(
        ) {
    pasar_NOP_fase2(FP_MUL, FP_MUL_LAT, fp);
}

/**
 * Envia un -nop- al operador FP CMP, fase 2
 *
 *
 */
void pasar_a_FP_C2_NOP(
        ) {
    pasar_NOP_fase2(FP_CMP, FP_CMP_LAT, fp);
}

/**
 * Envia un -nop- al operador FP iMUL, fase 2
 *
 *
 */
void pasar_a_iM2_NOP(
        ) {
    pasar_NOP_fase2(INT_MUL, INT_MUL_LAT, int);
}

/**
 * Envia un - nop - al operador FP MISC, fase 2
 *
 *
 */
void pasar_a_MISC2_NOP(
        ) {
    pasar_NOP_fase2(FP_MISC, FP_MISC_LAT, int);
}

/***************************************************************
 *
 * Func: pasar_a_ID_WB_NOP
 *
 * Desc: Se entrega un NOP a la fase ID_WB al proximo ciclo
 *
 **************************************************************/
/*
 * void pasar_a_ID_WB_NOP(
        ) {
    ID_WBn.fpALUout.fp_d = 0.0;

    ID_WBn.IR = inst_vacia;
    ID_WBn.iPC = -1;
    ID_WBn.orden = -1;
}
 */

/**
 * Envia un -nop- al operador FP LS, fase WB. Para los stores
 *
 *
 */
void pasar_a_FP_LS_WB_NOP(
        ) {

    FP_LS_WBn.ALUout.fp_d = 0.0;
    FP_LS_WBn.IR = inst_vacia;
    FP_LS_WBn.iPC = -1;
    FP_LS_WBn.orden = -1;
}

/**
 * Devuelve true si la instruccion tiene un Rfte1 valido
 *
 * @param inst Instruccion
 *
 */
static boolean hay_fuente1(instruccion_t inst) {
    return inst_int_Rfte1(inst.codop);
}

/**
 * Devuelve true si la instruccion tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
static boolean hay_fuente2(instruccion_t inst) {
    return inst_int_Rfte2(inst.codop);
}

/**
 * Devuelve true si la instruccion tiene un Rdst valido
 *
 * @param inst Instruccion
 *
 */
static boolean hay_destino(instruccion_t inst) {
    return inst_to_Rint(inst.codop);
}

/**
 * Devuelve true si la instruccion tiene un Rfte1 valido
 *
 * @param inst Instruccion
 *
 */
boolean rs1_valido(instruccion_t inst
) {
    return inst_int_Rfte1(inst.codop) && (inst.rs1 != 0);
}

/**
 * Devuelve true si la instruccion tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
boolean rs2_valido(instruccion_t inst
) {
    return inst_int_Rfte2(inst.codop) && (inst.rs2 != 0);
}

/**
 * Devuelve true si la instruccion tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
boolean rd_valido(instruccion_t inst
) {
    return inst_to_Rint(inst.codop)  && (inst.rd != 0);
}

/**
 * Devuelve true si la instruccion de la fase ID tiene un Rfte1 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente1_ID(
        ) {
    return rs1_valido(IF_ID.IR);
}

/**
 * Devuelve true si la instruccion de la fase ID tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente2_ID(
        ) {
    return rs2_valido(IF_ID.IR);
}

/**
 * Devuelve true si la instruccion de la fase EX tiene un Rfte1 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente1_EX(
        ) {
    return rs1_valido(ID_EX.IR);
}

/**
 * Devuelve true si la instruccion de la fase EX tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente2_EX(
        ) {
    return rs2_valido(ID_EX.IR);
}

/**
 * Devuelve true si la instruccion de la fase EX tiene un Rfdst valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_destino_EX(
        ) {
    return rd_valido(ID_EX.IR);
}

/**
 * Devuelve true si la instruccion de la fase MEM tiene un Rdst valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_destino_MEM(
        ) {
    return rd_valido(EX_MEM.IR);
}

/**
 * Devuelve true si la instruccion de la fase WB tiene un Rdst valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_destino_WB(
        ) {
    return rd_valido(MEM_WB.IR);
}

/**
 * Devuelve true si la instruccion tiene un Rfte1 valido
 *
 * @param inst Instruccion
 *
 */
boolean rfp1_valido(instruccion_t inst
) {
    return inst_fp_Rfte1(inst.codop);
}

/**
 * Devuelve true si la instruccion tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
boolean rfp2_valido(instruccion_t inst
) {
    return inst_fp_Rfte2(inst.codop);
}

/**
 * Devuelve true si la instruccion tiene un Rfte3 valido
 *
 * @param inst Instruccion
 *
 */
boolean rfp3_valido(instruccion_t inst
) {
    return inst_fp_Rfte3(inst.codop);
}

/**
 * Devuelve true si la instruccion tiene un Rfte1 valido
 *
 * @param inst Instruccion
 *
 */
boolean rfpd_valido(instruccion_t inst
) {
    return inst_to_Rfp(inst.codop);
}


/***************************************************************
 *
 * Func: hay_fuente1_FP_ID
 *
 * Desc: Indica si en la fase ID hay una instrucción que
 *       tiene Rfte1
 *
 **************************************************************/
boolean hay_fuente1_FP_ID(
        ) {
    return rfp1_valido(IF_ID.IR);
    //return inst_fp_Rfte1(IF_ID.IR.codop) && (IF_ID.iPC != -1);
} /* hay_fuente1_ID */

/***************************************************************
 *
 * Func: hay_fuente2_ID
 *
 * Desc: Indica si en la fase ID hay una instrucción que
 *       tiene Rfte2
 *
 **************************************************************/
boolean hay_fuente2_FP_ID(
        ) {
    return rfp2_valido(IF_ID.IR);
    //return inst_fp_Rfte2(IF_ID.IR.codop) && (IF_ID.iPC != -1);
}

/***************************************************************
 *
 * Func: hay_fuente3_ID
 *
 * Desc: Indica si en la fase ID hay una instrucción que
 *       tiene Rfte3
 *
 **************************************************************/
boolean hay_fuente3_FP_ID(
        ) {
    return rfp3_valido(IF_ID.IR);
    //return inst_fp_Rfte3(IF_ID.IR.codop) && (IF_ID.iPC != -1);
}

/***************************************************************
 *
 * Func: hay_destino_FP_WB
 *
 * Desc: Indica si en la fase WB hay una instrucción que
 *       tiene Rdst
 *
 **************************************************************/
boolean hay_destino_FP_WB(
        ) {
    return rfpd_valido(FP_WB.IR);
    //return inst_to_Rfp(FP_WB.IR.codop) && (FP_WB.iPC != -1);
}

/**
 * Devuelve true si la instruccion de la fase FP ADD1 tiene un Rfte1 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente1_FP_ADD1(
        ) {
    return rfp1_valido(ID_FP_ADD.IR);
    //return inst_fp_Rfte1(ID_FP_ADD.IR.codop) && (ID_FP_ADD.iPC != -1);
}

/**
 * Devuelve true si la instruccion de la fase FP ADD1 tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente2_FP_ADD1(
        ) {
    return rfp2_valido(ID_FP_ADD.IR);
    //return inst_fp_Rfte2(ID_FP_ADD.IR.codop) && (ID_FP_ADD.iPC != -1);
}

/**
 * Devuelve true si la instruccion de la fase FP MUL1 tiene un Rfte1 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente1_FP_MUL1(
        ) {
    return rfp1_valido(ID_FP_MUL.IR);
    //return inst_fp_Rfte1(ID_FP_MUL.IR.codop) && (ID_FP_MUL.iPC != -1);
}

/**
 * Devuelve true si la instruccion de la fase FP MUL1 tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente2_FP_MUL1(
        ) {
    return rfp2_valido(ID_FP_MUL.IR);
    //return inst_fp_Rfte2(ID_FP_MUL.IR.codop) && (ID_FP_MUL.iPC != -1);
}

/**
 * Devuelve true si la instruccion de la fase FP MUL1 tiene un Rfte3 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente3_FP_MUL1(
        ) {
    return rfp3_valido(ID_FP_MUL.IR);
    //return inst_fp_Rfte3(ID_FP_MUL.IR.codop) && (ID_FP_MUL.iPC != -1);
}

/**
 * Devuelve true si la instruccion de la fase FP CMP1 tiene un Rfte1 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente1_FP_CMP1(
        ) {
    return rfp1_valido(ID_FP_CMP.IR);
    //return inst_fp_Rfte1(ID_FP_CMP.IR.codop) && (ID_FP_CMP.iPC != -1);
}

/**
 * Devuelve true si la instruccion de la fase FP CMP1 tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente2_FP_CMP1(
        ) {
    return rfp2_valido(ID_FP_CMP.IR);
    //return inst_fp_Rfte2(ID_FP_CMP.IR.codop) && (ID_FP_CMP.iPC != -1);
}

/***************************************************************
 *
 * Func: hay_fuente1_M1
 *
 * Desc: Indica si en la fase EX hay una instrucción que
 *       tiene Rfte1
 *
 **************************************************************/
boolean hay_fuente1_iM1(
        ) {
    return rs1_valido(ID_INT_MUL.IR);
    //return inst_int_Rfte1(ID_INT_MUL.IR.codop) && (ID_INT_MUL.iPC != -1);
}

/***************************************************************
 *
 * Func: hay_fuente2_M1
 *
 * Desc: Indica si en la fase EX hay una instrucción que
 *       tiene Rfte1
 *
 **************************************************************/
boolean hay_fuente2_iM1(
        ) {
    return rs2_valido(ID_INT_MUL.IR);
    return inst_int_Rfte2(ID_INT_MUL.IR.codop) && (ID_INT_MUL.iPC != -1);
}

/**
 * Devuelve true si la instruccion de la fase FP ADD1 tiene un Rfte1 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente1_FP_MISC1(
        ) {
    return rfp1_valido(ID_FP_MISC.IR);
    //return inst_fp_Rfte1(ID_FP_MISC.IR.codop) && (ID_FP_MISC.iPC != -1);
}

/**
 * Devuelve true si la instruccion de la fase FP ADD1 tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente2_FP_MISC1(
        ) {
    return rfp2_valido(ID_FP_MISC.IR);
    //return inst_fp_Rfte2(ID_FP_MISC.IR.codop) && (ID_FP_MISC.iPC != -1);
}

/***************************************************************
 *
 * Func: hay_fuente1_LS
 *
 * Desc: Indica si en la fase EX hay una instrucción que
 *       tiene Rfte2
 *
 **************************************************************/
boolean hay_fuente1_LS1(
        ) {
    return rs1_valido(ID_FP_LS.IR);
    //return inst_int_Rfte1(ID_FP_LS.IR.codop)&&(ID_FP_LS.iPC != -1);
}

/***************************************************************
 *
 * Func: hay_fuente2_LS
 *
 * Desc: Indica si en la fase EX hay una instrucción que
 *       tiene Rfte2
 *
 **************************************************************/
boolean hay_fuente2_LS1(
        ) {
    return rfp2_valido(ID_FP_LS.IR);
    //return inst_fp_Rfte2(ID_FP_LS.IR.codop)&& (ID_FP_LS.iPC != -1);
} /* hay_fuente2_EX */

/**
 * Devuelve true si la instruccion de la fase LS2 tiene un Rfte2 valido
 *
 * @param inst Instruccion
 *
 */
boolean hay_fuente2_LS2(
        ) {
    return rfp2_valido(FP_LS[2].IR);
    //return inst_fp_Rfte2(FP_LS[2].IR.codop)&& (FP_LS[2].iPC != -1);
} /* hay_fuente2_LS2 */



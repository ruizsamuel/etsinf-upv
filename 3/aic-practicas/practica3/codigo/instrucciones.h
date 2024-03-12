/*********************************************************************
 *
 * Arquitectura de Computadores.
 * Departamento de Informática de Sistemas y Computadores (DISCA)
 * Universidad Politécnica de Valencia.
 *
 * Autor: Sergio Sáez (ssaez@disca.upv.es)
 *        Pedro López (plopez@disca.upv.es)
 *
 * Fichero: instrucciones.h
 *
 * Descripción:
 *   Contiene los códigos y los campos de función de las instrucciones del RV
 *
 ***********************************************************************/

#ifndef instrucciones_H
#define instrucciones_H

/*** Includes **************************************************************/

#include "tipos.h"
#include "main.h"
#include "codop.h"
#include "g_string.h"

/*
 * The RISC-V Instruction Set Manual
 * Volume I: Unprivileged ISA
 * Document Version 20191213
 * Table 24.1
 */

#define OPCODE_LOAD       0b0000011
#define OPCODE_LOAD_FP    0b0000111
#define OPCODE_CUSTOM_0   0b0001011
#define OPCODE_MISC_MEM   0b0001111
#define OPCODE_OP_IMM     0b0010011
#define OPCODE_AUIPC      0b0010111
#define OPCODE_OP_IMM_32  0b0011011

#define OPCODE_STORE      0b0100011
#define OPCODE_STORE_FP   0b0100111
#define OPCODE_CUSTOM_1   0b0101011
#define OPCODE_AMO        0b0101111
#define OPCODE_OP         0b0110011
#define OPCODE_LUI        0b0110111
#define OPCODE_OP_32      0b0111011

#define OPCODE_MADD       0b1000011
#define OPCODE_MSUB       0b1000111
#define OPCODE_NMSUB      0b1001011
#define OPCODE_NMADD      0b1001111
#define OPCODE_OP_FP      0b1010011
#define OPCODE_RESERVED_1 0b1010111
#define OPCODE_CUSTOM_2   0b1011011

#define OPCODE_BRANCH     0b1100011
#define OPCODE_JALR       0b1100111
#define OPCODE_RESERVED_0 0b1101011
#define OPCODE_JAL        0b1101111
#define OPCODE_SYSTEM     0b1110011
#define OPCODE_RESERVED_2 0b1110111
#define OPCODE_CUSTOM_3   0b1111011

#define OPCODE_PSEUDO     0

#define F3_FENCE    0
#define F3_FENCEI   1
#define F4_FM_NONE  0b0000
#define F4_FM_TSO   0b1000

#define F3_CSRRW    0b001
#define F3_CSRRS    0b010
#define F3_CSRRC    0b011
#define F3_CSRRWI   0b101
#define F3_CSRRSI   0b110
#define F3_CSRRCI   0b111

#define F12_ECALL   0
#define F12_EBREAK  1

#define F3_PRIV 0

#define F6_SRA 0b010000

#define DYNAMIC_RM 0b111

/*** Tipos *****************************************************************/

typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int funct3: 3;
    unsigned int rs1: 5;
    unsigned int rs2: 5;
    unsigned int funct7: 7;
} format_R_type;

// Usada para las instrucciones AMO
// Se puede utilizar esta versión o incluir los campos 'aq' y 'rl' como parte del 'funct7' del formato R
typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int funct3: 3;
    unsigned int rs1: 5;
    unsigned int rs2: 5;
    unsigned int rl: 1;
    unsigned int aq: 1;
    unsigned int funct5: 5;
} format_R_amo_type;

// Usada para las instrucciones de como flotante
// Se puede utilizar esta versión o incluir el campo 'fmt' como parte del 'funct7' del formato R
typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int rm: 3;
    unsigned int rs1: 5;
    unsigned int rs2: 5;
    unsigned int fmt: 2;  // S, D, H Q
    unsigned int funct5: 5;
} format_R_fp_type;

typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int rm: 3;
    unsigned int rs1: 5;
    unsigned int rs2: 5;
    unsigned int fmt: 2;  // S, D, H Q
    unsigned int rs3: 5;
} format_R_fp_4rm_type;

typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int funct3: 3;
    unsigned int rs1: 5;
    int imm11_0: 12;
} format_I_type;

// Usado por las loads de coma flotante
// TODO vale la pena renombrar funct3 por width ?
typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int width: 3;
    unsigned int rs1: 5;
    int imm11_0: 12;
} format_I_fp_type;

typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int funct3: 3;
    unsigned int rs1: 5;
    unsigned int funct12: 12;
} format_I_system_type;

typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int funct3: 3;
    unsigned int rs1: 5;
    unsigned int shamt: 6;
    unsigned int funct6: 6;
} format_I_shift_type;

typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int funct3: 3;
    unsigned int rs1: 5;
    unsigned int csr: 12;
} format_I_csr_R_type;

typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int funct3: 3;
    unsigned int uimm4_0: 5;
    unsigned int csr: 12;
} format_I_csr_I_type;

typedef struct {
    unsigned int opcode: 7;
    unsigned int imm4_0: 5;
    unsigned int funct3: 3;
    unsigned int rs1: 5;
    unsigned int rs2: 5;
    int imm11_5: 7;
} format_S_type;

// Usado por los stores de coma flotante
// TODO vale la pena renombrar funct3 por width ?
typedef struct {
    unsigned int opcode: 7;
    unsigned int imm4_0: 5;
    unsigned int width: 3;
    unsigned int rs1: 5;
    unsigned int rs2: 5;
    int imm11_5: 7;
} format_S_fp_type;

typedef struct {
    unsigned int opcode: 7;
    unsigned int imm11: 1;
    unsigned int imm4_1: 4;
    unsigned int funct3: 3;
    unsigned int rs1: 5;
    unsigned int rs2: 5;
    unsigned int imm10_5: 6;
    int imm12: 1;
} format_B_type;

typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    int imm31_12: 20;
} format_U_type;

typedef struct {
    unsigned int opcode: 7;
    unsigned int rd: 5;
    unsigned int imm19_12: 8;
    unsigned int imm11: 1;
    unsigned int imm10_1: 10;
    int imm20: 1;
} format_J_type;

typedef union {
    // Codificación de la instrucción
    unsigned int valor;
    // Campo opcode común a todos los formatos
    unsigned int opcode: 7;

    // Formato útil para decodificar
    format_decode_type decode; // Definido en codop.h

    // R-type
    format_R_type R;
    // I-type
    format_I_type I;
    // S-type
    format_S_type S;
    // B-type
    format_B_type B;
    // U-type
    format_U_type U;
    // J-type
    format_J_type J;

    // Variantes
    // R-amo-subtype
    format_R_amo_type Ramo;
    // R-fp-subtype
    format_R_fp_type Rfp;
    // R-4rm-subtype
    format_R_fp_4rm_type R4rm;
    // I-shift-subtype
    format_I_shift_type Ishift;
    // I-system-subtype
    format_I_system_type Isystem;
    // I-csr-R-type
    format_I_csr_R_type IcsrR;
    // I-csr-I-type
    format_I_csr_I_type IcsrI;
    // I-type
    format_I_fp_type Ifp;
    // S-type
    format_S_fp_type Sfp;

} formato_instruccion_t;

/*** Macros ****************************************************************/

// Macros para obtener el tipo de instrucción
//#define is_RV32I(i) (SET(i) == RV32I)

// Instrucciones de carga enteras
#define inst_int_load(i) (OPCODE(i) == OPCODE_LOAD)
#define inst_amo_load(i) (((i)==RV32A_LR_W) || ((i)==RV32A_LR_W_AQ) || ((i)==RV32A_LR_W_RL) || ((i)==RV32A_LR_W_AQRL) || \
                    ((i)==RV64A_LR_D) || ((i)==RV64A_LR_D_AQ) || ((i)==RV64A_LR_D_RL) || ((i)==RV64A_LR_D_AQRL))

// Instrucciones de carga fp
#define inst_fps_load(i) ((i) == RV32F_FLW)
#define inst_fpd_load(i)  ((i) == RV32D_FLD)
#define inst_fp_load(i)  (OPCODE(i) == OPCODE_LOAD_FP)

#define inst_load(i) (inst_int_load(i) || inst_fp_load(i) || inst_amo_load(i))
#define es_load(i) (inst_load(i.codop))

// Instrucciones de almacenamiento enteras
#define inst_int_store(i)  (OPCODE(i) == OPCODE_STORE)
#define inst_amo_store(i) (((i)==RV32A_SC_W) || ((i)==RV32A_SC_W_AQ) || ((i)==RV32A_SC_W_RL) || ((i)==RV32A_SC_W_AQRL) || \
                    ((i)==RV64A_SC_D) || ((i)==RV64A_SC_D_AQ) || ((i)==RV64A_SC_D_RL) || ((i)==RV64A_SC_D_AQRL))

// Instrucciones de almacenamiento fp
#define inst_fps_store(i) ((i) == RV32F_FSW)
#define inst_fpd_store(i)  ((i) == RV32D_FSD)
#define inst_fp_store(i)  (OPCODE(i) == OPCODE_STORE_FP)

#define inst_store(i) ( inst_int_store(i) || inst_fp_store(i) || inst_amo_store(i))

// Instrucciones de salto
#define inst_jal(i) (OPCODE(i) == OPCODE_JAL)
#define inst_jalr(i) (OPCODE(i) == OPCODE_JALR)
#define inst_jal_jalr(i) ((OPCODE(i) == OPCODE_JAL) || (OPCODE(i) == OPCODE_JALR) )
#define inst_branch(i)  (OPCODE(i) == OPCODE_BRANCH)
#define inst_salto(i) (inst_branch(i) || inst_jal_jalr(i))

// Instrucciones que leen el PC
#define inst_leePC(i) (inst_branch(i)|| inst_jal(i)|| (OPCODE(i) == OPCODE_AUIPC))

// ecall
#define inst_ecall(i) ((i) == RV32I_ECALL)

#define inst_int(i) ((SET(i) == RV32I) || (SET(i)==RV32M) || (SET(i) == RV32A)|| (SET(i)==RV64I) || (SET(i)==RV64M) || (SET(i) == RV64A))
#define inst_int_simple(i) ((SET(i) == RV32I) || (SET(i) == RV32A)|| (SET(i)==RV64I) || (SET(i) == RV64A))

// Instrucciones enteras que escriben registros enteros
#define inst_int_to_Rint(i) (OPCODE(i)==OPCODE_OP_IMM || OPCODE(i)==OPCODE_LUI|| OPCODE(i)==OPCODE_AUIPC || \
                    OPCODE(i)==OPCODE_OP || OPCODE(i)==OPCODE_LOAD || OPCODE(i)==OPCODE_AMO || \
                    OPCODE(i)==OPCODE_OP_IMM_32 || OPCODE(i)==OPCODE_OP_32 || \
                    inst_jal_jalr(i))

// Instrucciones de multiplicacion y division
#define inst_int_muldiv ((SET(i) == RV32M) || (SET(i) == RV64M))
#define inst_fp_mul_d(i) ((i)==RV32D_FMUL_D)
#define inst_fp_mul_s(i) ((i)==RV32D_FMUL_S)
#define inst_fp_mul(i) (inst_fp_mul_s(i) || inst_fp_mul_d(i))
#define inst_fp_div_d(i) ((i)==RV32D_FDIV_D)
#define inst_fp_div_s(i) ((i)==RV32D_FDIV_S)
#define inst_fp_div(i) (inst_fp_div_s(i) || inst_fp_div_d(i))
#define inst_fp_sqrt_d(i) ((i)==RV32D_FSQRT_D)
#define inst_fp_sqrt_s(i) ((i)==RV32D_FSQRT_S)
#define inst_fp_sqrt(i) (inst_fp_sqrt_s(i) || inst_fp_sqrt_d(i))

// Instrucciones fused madd
#define inst_fp_madd_d(i) ((i)==RV32D_FMADD_D || (i)==RV32D_FMSUB_D || (i)==RV32D_FNMSUB_D || (i)==RV32D_FNMADD_D )
#define inst_fp_madd_s(i) ((i)==RV32F_FMADD_S || (i)==RV32F_FMSUB_S || (i)==RV32F_FNMSUB_S || (i)==RV32F_FNMADD_S )
#define inst_fp_madd(i) (inst_fp_madd_s(i) || inst_fp_madd_d(i) )
// Instrucciones fp dobles
#define inst_fp_d(i) ((SET(i) == RV32D) || (SET(i)==RV64D) ||  inst_fp_madd_d(i) )
// Instrucciones fp simples
#define inst_fp_s(i) ((SET(i) == RV32F) || (SET(i)==RV64F) || inst_fp_madd_s(i) )
// Instrucciones que escriben simd
#define inst_simd(i) ((i)== OP_FP_ADD_PS || (i)== OP_FP_SUB_PS || (i)== OP_FP_MUL_PS || (i)== OP_FP_DIV_PS || (i)== OP_FP_L_PS || (i)== OP_FP_S_PS)

#define inst_fp(i) (inst_fp_d(i) || inst_fp_s(i) )


// Instrucciones fp que operan con un registro entero en Rfte1
#define inst_fps_cvt_from_int(i) ((i)==RV32F_FCVT_S_W || (i)==RV32F_FCVT_S_WU || \
                            (i)==RV64F_FCVT_S_L || (i)==RV64F_FCVT_S_LU)
#define inst_fpd_cvt_from_int(i) ((i)==RV32D_FCVT_D_W || (i)==RV32D_FCVT_D_WU || \
                            (i)==RV64D_FCVT_D_L || (i)==RV64D_FCVT_D_LU)
#define inst_fps_Rfte1_int(i) (inst_fps_cvt_from_int(i) || inst_fps_load(i) || inst_fps_store(i))
#define inst_fpd_Rfte1_int(i) (inst_fpd_cvt_from_int(i) || inst_fpd_load(i) || inst_fpd_store(i))
#define inst_fp_Rfte1_int(i) (inst_fps_Rfte1_int(i) || inst_fpd_Rfte1_int(i))

// Instrucciones de comparación flotante
#define inst_fp_compare(i) ((i)==RV32F_FEQ_S || (i)==RV32F_FLT_S || (i)==RV32F_FLE_S || (i)==RV32D_FEQ_D || (i)==RV32D_FLT_D || (i)==RV32D_FLE_D)
// Instrucciones de clasificación
#define inst_fp_classify(i) ((i)==RV32F_FCLASS_S || (i)==RV32D_FCLASS_D)
// Instrucciones de conversion a entero
#define inst_fps_cvt_to_int(i) ((i)==RV32F_FCVT_W_S || (i)==RV32F_FCVT_WU_S ||  \
                            (i)==RV64F_FCVT_L_S || (i)==RV64F_FCVT_LU_S)
#define inst_fpd_cvt_to_int(i) ((i)==RV32D_FCVT_W_D || (i)==RV32D_FCVT_WU_D || \
                            (i)==RV64D_FCVT_L_D || (i)==RV64D_FCVT_LU_D)
#define inst_fp_cvt_to_int(i) (inst_fps_cvt_to_int(i) || inst_fpd_cvt_to_int(i))
// Instrucciones de conversion a flotante
#define inst_fps_cvt_to_fp(i) (inst_fps_cvt_from_int(i) || (i)==RV32D_FCVT_D_S)
#define inst_fpd_cvt_to_fp(i) (inst_fpd_cvt_from_int(i) || (i)==RV32D_FCVT_S_D)
#define inst_fp_cvt_to_fp(i) (inst_fps_cvt_to_fp(i) || inst_fpd_cvt_to_fp(i))
// Intrucciones de movimiento a entero
#define inst_fp_mv_to_int(i) ((i)==RV32F_FMV_X_W || (i)==RV64D_FMV_X_D)

// Instrucciones fp que escriben registros enteros
#define inst_fp_to_Rint(i) ( inst_fp_compare(i) || inst_fp_cvt_to_int(i) || inst_fp_mv_to_int(i) || inst_fp_classify(i) )

// Instrucciones con operando1 entero en Rfte1
#define inst_int_Rfte1(i) (((SET(i)==RV32I) || (SET(i)==RV64I) || (SET(i)==RV32M) || \
            (SET(i)==RV32A) || (SET(i)==RV64A) || (SET(i)==RV64M) || \
            inst_store(i) || \
            inst_fp_Rfte1_int(i)) && \
            ((OPCODE(i)!=OPCODE_LUI) && \
            ((i)!=RV32I_JAL) && (OPCODE(i)!=OPCODE_SYSTEM)))

// Instrucciones con operando2 entero en Rfte2
#define inst_int_Rfte2(i) (((SET(i)==RV32I) || (SET(i)==RV64I) || (SET(i)==RV32M) || \
            (SET(i)==RV32A) || (SET(i)==RV64A) || (SET(i)==RV64M)) && \
         ((OPCODE(i)!=OPCODE_OP_IMM) && (OPCODE(i)!=OPCODE_OP_IMM_32) && \
         (!inst_int_load(i)) && (!inst_amo_load(i)) && (OPCODE(i)!=OPCODE_LUI) && \
         (OPCODE(i)!=OPCODE_LUI) && (OPCODE(i)!=OPCODE_AUIPC) && \
         (!inst_jal_jalr(i)) && (OPCODE(i)!=OPCODE_SYSTEM)))


// Instrucciones con operando imm
#define inst_imm(i) ((OPCODE(i)==OPCODE_OP_IMM) || (OPCODE(i)==OPCODE_OP_IMM_32) || (OPCODE(i)==OPCODE_AUIPC) || \
(OPCODE(i)==OPCODE_LUI) || (OPCODE(i)==OPCODE_BRANCH) || (OPCODE(i)==OPCODE_JAL) || \
(OPCODE(i)==OPCODE_JAL) || (OPCODE(i)==OPCODE_JALR))

// Instrucciones con operando1 fp
#define inst_fps_Rfte1(i) (((SET(i)==RV32F) || (SET(i)==RV64F)) && \
        !inst_int_Rfte1(i) && !inst_fps_Rfte1_int(i))
#define inst_fpd_Rfte1(i) (((SET(i)==RV32D) || (SET(i)==RV64D)) && \
        !inst_int_Rfte1(i) && !inst_fpd_Rfte1_int(i))
#define inst_fp_Rfte1(i) (inst_fps_Rfte1(i) || inst_fpd_Rfte1(i))

// Instrucciones con operando2 fp
#define inst_fps_Rfte2(i) (((SET(i)==RV32F) || (SET(i)==RV64F) || inst_fps_store(i)) && \
        (!inst_fp_load(i)) && (!inst_int_Rfte2(i)) && \
        (!inst_fps_cvt_to_fp(i)) && (!inst_fps_cvt_to_int(i)) && \
        ((i)!=RV32F_FSQRT_S) && ((i)!=RV32F_FCLASS_S) && ((i)!=RV32F_FMV_X_W))
#define inst_fpd_Rfte2(i) (((SET(i)==RV32D) || (SET(i)==RV64D) || inst_fpd_store(i)) && \
        (!inst_fp_load(i)) && (!inst_int_Rfte2(i)) && \
        (!inst_fpd_cvt_to_fp(i)) && (!inst_fpd_cvt_to_int(i)) && \
        ((i)!=RV32D_FSQRT_D) && ((i)!=RV32D_FCLASS_D) && ((i)!=RV64D_FMV_X_D) && \
        ((i)!=RV32D_FCVT_D_S))
#define inst_fp_Rfte2(i) (inst_fps_Rfte2(i) || inst_fpd_Rfte2(i))

// Instrucciones con operando3 fp
#define inst_fps_Rfte3(i) (inst_fp_madd_s(i))
#define inst_fpd_Rfte3(i) (inst_fp_madd_d(i))
#define inst_fp_Rfte3(i) (inst_fps_Rfte3(i) || inst_fpd_Rfte3(i))

// Instrucciones que escriben en registros enteros
#define inst_to_Rint(i) (inst_int_to_Rint(i) || inst_fp_to_Rint(i))

// Instrucciones que escriben en registros flotantes
#define inst_to_Rfp(i) (inst_fp(i) && !inst_fp_to_Rint(i) && !inst_fp_store(i))

// OP_TRAP es una instruccion "interna" que se genera en caso de excepción
#define OP_TRAP   1001


// MIPS 32
#define OP_FP_ADD_PS  10154
#define OP_FP_SUB_PS  10155
#define OP_FP_MUL_PS  10156
#define OP_FP_DIV_PS  10157

#define OP_FP_L_PS  10180 // No existe en MIPS64
#define OP_FP_S_PS  10181 // No existe en MIPS64



/***** Prototypes *********************************************/

#ifndef CPROTO
#include "instrucciones_api.h"
#endif


void color_instruccion(
        char *color,
        dword PC
);

void imprime_instruccion_color(
        char *instruc,
        dword PC,
        boolean encolor
);

void imprime_formato(
        g_string_t *formato,
        dword PC,
        modo_formato_t format_mode
);

codop_t fn_riscv_decode(dword PC, unsigned int instr, formato_t* fmt, int *opcode,
        int *rs1, int *rs2, int *rs3, int *rd, int *rm, int *imm,
        char* nemo, char* full);

void genera_MInstruccion(
        programa_t *prog
);


#endif /* Include instrucciones.h */

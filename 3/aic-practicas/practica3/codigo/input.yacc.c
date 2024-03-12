/* A Bison parser, made by GNU Bison 3.8.2.  */

/* Bison implementation for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015, 2018-2021 Free Software Foundation,
   Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* DO NOT RELY ON FEATURES THAT ARE NOT DOCUMENTED in the manual,
   especially those whose name start with YY_ or yy_.  They are
   private implementation details that can be changed or removed.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output, and Bison version.  */
#define YYBISON 30802

/* Bison version string.  */
#define YYBISON_VERSION "3.8.2"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* First part of user prologue.  */
#line 1 "input.yacc.y"

    /**************************************************************************
     *
     * Departamento de Informática de Sistema y Computadores (DISCA)
     * Universidad Politécnica de Valencia.
     *
     * Author: Sergio Sáez (ssaez@disca.upv.es)
     *             Pedro López (plopez@disca.upv.es)
     *
     * File: input.yacc.c
     *
     * Date: Tue Feb 18 10:00:00 GMT 2020
     *
     * Description:
     *    Contiene la descripción sintáctica del lenguaje ensamblador
     *
     * This program is free software; you can redistribute it and/or modify
     * it under the terms of the GNU General Public License as published by
     * the Free Software Foundation; either version 2 of the License, or
     * (at your option) any later version.
     *
     * This program is distributed in the hope that it will be useful,
     * but WITHOUT ANY WARRANTY; without even the implied warranty of
     * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     * GNU General Public License for more details.
     *
     * You should have received a copy of the GNU General Public License
     * along with this program; if not, write to the Free Software
     * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
     *
     *************************************************************************/

    /***** Includes ***********************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <stdint.h>

#include "main.h"
#include "tipos.h"
#include "instrucciones.h"
#include "codop.h"
#include "memoria.h"
#include "registros.h"
#include "enlazador.h"

    /*** Declaraciones externas */

    extern FILE * yyin;
    extern int yyerror(const char *);
    extern void free(void*);
    extern int yylex();

   void nueva_instruccion(codop_t codop, formato_t tipo,
    			   int Rdestino, int Rfuente1, int Rfuente2, int Rfuente3,
    			   dword inmediato, char * etiqueta, int Redondeo);

    /*** Variables globales */

    char mensaje[256];
    long linenumber = 1;
    char desplazamiento[256];
    int hay_valor=0;
    int hay_etiq=0;

    tipo_t tipo = t_dword;

    dword pos[num_regiones];
    region_t region = r_invalid;
    mapa_memoria_t * mm = NULL;
    tabla_simbolos_t * ts = NULL;

    int idx_ireg = 1;
    int idx_fpreg = 0;
    int idx_btb = 0;

    boolean no_ireg = NO;
    boolean no_fpreg = NO;
    boolean no_btb = NO;

    int i = 0;
    instruccion_t instruccion;
    valor_t valor;
    valor_t reg_fp;
    int RV_instruccion[5];

    int pending_label = 0;
    region_t pending_region = r_invalid;

    int offset;
    int symbol;
    char etiqueta[256];
    
    int Redondeo_id (char *rm) {
        if (strcmp(rm,"rne")==0)
            return 0;
        else if (strcmp(rm,"rtz")==0)
            return 1;
        else if (strcmp(rm,"rdn")==0)
            return 2;
        else if (strcmp(rm,"rup")==0)
            return 3;
        else if (strcmp(rm,"rmm")==0)
            return 4;
        else return -1;
    }
	
	
    /*****************************************************************************/

    

#line 186 "input.yacc.c"

# ifndef YY_CAST
#  ifdef __cplusplus
#   define YY_CAST(Type, Val) static_cast<Type> (Val)
#   define YY_REINTERPRET_CAST(Type, Val) reinterpret_cast<Type> (Val)
#  else
#   define YY_CAST(Type, Val) ((Type) (Val))
#   define YY_REINTERPRET_CAST(Type, Val) ((Type) (Val))
#  endif
# endif
# ifndef YY_NULLPTR
#  if defined __cplusplus
#   if 201103L <= __cplusplus
#    define YY_NULLPTR nullptr
#   else
#    define YY_NULLPTR 0
#   endif
#  else
#   define YY_NULLPTR ((void*)0)
#  endif
# endif

#include "input.yacc.h"
/* Symbol kind.  */
enum yysymbol_kind_t
{
  YYSYMBOL_YYEMPTY = -2,
  YYSYMBOL_YYEOF = 0,                      /* "end of file"  */
  YYSYMBOL_YYerror = 1,                    /* error  */
  YYSYMBOL_YYUNDEF = 2,                    /* "invalid token"  */
  YYSYMBOL_NL = 3,                         /* NL  */
  YYSYMBOL_T_INT_LIT = 4,                  /* T_INT_LIT  */
  YYSYMBOL_T_FP_LIT = 5,                   /* T_FP_LIT  */
  YYSYMBOL_T_ALPHANUM = 6,                 /* T_ALPHANUM  */
  YYSYMBOL_T_STRING = 7,                   /* T_STRING  */
  YYSYMBOL_FR_MODE = 8,                    /* FR_MODE  */
  YYSYMBOL_REG_INT = 9,                    /* REG_INT  */
  YYSYMBOL_REG_FP = 10,                    /* REG_FP  */
  YYSYMBOL_DE_DATA = 11,                   /* DE_DATA  */
  YYSYMBOL_DE_TEXT = 12,                   /* DE_TEXT  */
  YYSYMBOL_DE_SPACE = 13,                  /* DE_SPACE  */
  YYSYMBOL_DE_GLOBL = 14,                  /* DE_GLOBL  */
  YYSYMBOL_DE_IREG = 15,                   /* DE_IREG  */
  YYSYMBOL_DE_FREG = 16,                   /* DE_FREG  */
  YYSYMBOL_DE_ASCIIZ = 17,                 /* DE_ASCIIZ  */
  YYSYMBOL_DE_BTB = 18,                    /* DE_BTB  */
  YYSYMBOL_DE_BYTE = 19,                   /* DE_BYTE  */
  YYSYMBOL_DE_HALF = 20,                   /* DE_HALF  */
  YYSYMBOL_DE_WORD = 21,                   /* DE_WORD  */
  YYSYMBOL_DE_DWORD = 22,                  /* DE_DWORD  */
  YYSYMBOL_DE_FLOAT = 23,                  /* DE_FLOAT  */
  YYSYMBOL_DE_DOUBLE = 24,                 /* DE_DOUBLE  */
  YYSYMBOL_FORM_INT_RRI = 25,              /* FORM_INT_RRI  */
  YYSYMBOL_FORM_INT_RRSH = 26,             /* FORM_INT_RRSH  */
  YYSYMBOL_FORM_INT_RRR = 27,              /* FORM_INT_RRR  */
  YYSYMBOL_FORM_INT_RA = 28,               /* FORM_INT_RA  */
  YYSYMBOL_FORM_INT_RI = 29,               /* FORM_INT_RI  */
  YYSYMBOL_FORM_J_RA = 30,                 /* FORM_J_RA  */
  YYSYMBOL_FORM_J_RRA = 31,                /* FORM_J_RRA  */
  YYSYMBOL_FORM_INT_B_RRA = 32,            /* FORM_INT_B_RRA  */
  YYSYMBOL_FORM_J_A = 33,                  /* FORM_J_A  */
  YYSYMBOL_FORM_INT_L_RSB = 34,            /* FORM_INT_L_RSB  */
  YYSYMBOL_FORM_INT_S_RSB = 35,            /* FORM_INT_S_RSB  */
  YYSYMBOL_FORM_INT_ECALL = 36,            /* FORM_INT_ECALL  */
  YYSYMBOL_FORM_INT_EBREAK = 37,           /* FORM_INT_EBREAK  */
  YYSYMBOL_FORM_FP_FFF = 38,               /* FORM_FP_FFF  */
  YYSYMBOL_FORM_FP_FFFr = 39,              /* FORM_FP_FFFr  */
  YYSYMBOL_FORM_FP_FFFFr = 40,             /* FORM_FP_FFFFr  */
  YYSYMBOL_FORM_FP_FFr = 41,               /* FORM_FP_FFr  */
  YYSYMBOL_FORM_FP_RFF = 42,               /* FORM_FP_RFF  */
  YYSYMBOL_FORM_FP_RFr = 43,               /* FORM_FP_RFr  */
  YYSYMBOL_FORM_FP_RF = 44,                /* FORM_FP_RF  */
  YYSYMBOL_FORM_FP_FR = 45,                /* FORM_FP_FR  */
  YYSYMBOL_FORM_FP_FRr = 46,               /* FORM_FP_FRr  */
  YYSYMBOL_FORM_FP_FF = 47,                /* FORM_FP_FF  */
  YYSYMBOL_FORM_FP_L_FSB = 48,             /* FORM_FP_L_FSB  */
  YYSYMBOL_FORM_FP_S_FSB = 49,             /* FORM_FP_S_FSB  */
  YYSYMBOL_FORM_INT_RB = 50,               /* FORM_INT_RB  */
  YYSYMBOL_FORM_INT_RRB = 51,              /* FORM_INT_RRB  */
  YYSYMBOL_INST_FENCE = 52,                /* INST_FENCE  */
  YYSYMBOL_INST_PRUEBA = 53,               /* INST_PRUEBA  */
  YYSYMBOL_FORM_CSR_RxR = 54,              /* FORM_CSR_RxR  */
  YYSYMBOL_FORM_CSR_RxI = 55,              /* FORM_CSR_RxI  */
  YYSYMBOL_FORM_INM = 56,                  /* FORM_INM  */
  YYSYMBOL_FORM_INM_L = 57,                /* FORM_INM_L  */
  YYSYMBOL_FORM_INM_S = 58,                /* FORM_INM_S  */
  YYSYMBOL_FORM_INM_DI = 59,               /* FORM_INM_DI  */
  YYSYMBOL_FORM_INM_B = 60,                /* FORM_INM_B  */
  YYSYMBOL_FORM_INM_B_FI = 61,             /* FORM_INM_B_FI  */
  YYSYMBOL_FORM_INM_B_FFI = 62,            /* FORM_INM_B_FFI  */
  YYSYMBOL_FORM_INM_T = 63,                /* FORM_INM_T  */
  YYSYMBOL_FORM_PSEUDO_NOP = 64,           /* FORM_PSEUDO_NOP  */
  YYSYMBOL_FORM_PSEUDO_RR = 65,            /* FORM_PSEUDO_RR  */
  YYSYMBOL_FORM_PSEUDO_BRA = 66,           /* FORM_PSEUDO_BRA  */
  YYSYMBOL_FORM_PSEUDO_J = 67,             /* FORM_PSEUDO_J  */
  YYSYMBOL_FORM_PSEUDO_JR = 68,            /* FORM_PSEUDO_JR  */
  YYSYMBOL_FORM_PSEUDO_RET = 69,           /* FORM_PSEUDO_RET  */
  YYSYMBOL_FORM_PSEUDO_TAIL = 70,          /* FORM_PSEUDO_TAIL  */
  YYSYMBOL_FORM_PSEUDO_R = 71,             /* FORM_PSEUDO_R  */
  YYSYMBOL_FORM_PSEUDO_CSRR = 72,          /* FORM_PSEUDO_CSRR  */
  YYSYMBOL_FORM_PSEUDO_CSRW = 73,          /* FORM_PSEUDO_CSRW  */
  YYSYMBOL_FORM_PSEUDO_CSRS = 74,          /* FORM_PSEUDO_CSRS  */
  YYSYMBOL_FORM_PSEUDO_CSRC = 75,          /* FORM_PSEUDO_CSRC  */
  YYSYMBOL_FORM_PSEUDO_CSRWI = 76,         /* FORM_PSEUDO_CSRWI  */
  YYSYMBOL_FORM_PSEUDO_CSRSI = 77,         /* FORM_PSEUDO_CSRSI  */
  YYSYMBOL_FORM_PSEUDO_CSRCI = 78,         /* FORM_PSEUDO_CSRCI  */
  YYSYMBOL_FORM_PSEUDO_FRCSR = 79,         /* FORM_PSEUDO_FRCSR  */
  YYSYMBOL_FORM_PSEUDO_FSCSR = 80,         /* FORM_PSEUDO_FSCSR  */
  YYSYMBOL_FORM_PSEUDO_FRRM = 81,          /* FORM_PSEUDO_FRRM  */
  YYSYMBOL_FORM_PSEUDO_FSRM = 82,          /* FORM_PSEUDO_FSRM  */
  YYSYMBOL_FORM_PSEUDO_FRFLAGS = 83,       /* FORM_PSEUDO_FRFLAGS  */
  YYSYMBOL_FORM_PSEUDO_FSFLAGS = 84,       /* FORM_PSEUDO_FSFLAGS  */
  YYSYMBOL_FORM_PSEUDO_BRRA = 85,          /* FORM_PSEUDO_BRRA  */
  YYSYMBOL_FORM_PSEUDO_RRi = 86,           /* FORM_PSEUDO_RRi  */
  YYSYMBOL_FORM_PSEUDO_FF = 87,            /* FORM_PSEUDO_FF  */
  YYSYMBOL_FORM_PSEUDO_LLA = 88,           /* FORM_PSEUDO_LLA  */
  YYSYMBOL_FORM_PSEUDO_L = 89,             /* FORM_PSEUDO_L  */
  YYSYMBOL_FORM_PSEUDO_S = 90,             /* FORM_PSEUDO_S  */
  YYSYMBOL_FORM_PSEUDO_FL = 91,            /* FORM_PSEUDO_FL  */
  YYSYMBOL_FORM_PSEUDO_FS = 92,            /* FORM_PSEUDO_FS  */
  YYSYMBOL_FORM_PSEUDO_LI = 93,            /* FORM_PSEUDO_LI  */
  YYSYMBOL_FORM_PSEUDO_CALL = 94,          /* FORM_PSEUDO_CALL  */
  YYSYMBOL_FORM_PSEUDO_TRAP = 95,          /* FORM_PSEUDO_TRAP  */
  YYSYMBOL_INST_RET = 96,                  /* INST_RET  */
  YYSYMBOL_INST_SYSCALL = 97,              /* INST_SYSCALL  */
  YYSYMBOL_FORM_REG = 98,                  /* FORM_REG  */
  YYSYMBOL_FORM_REG_FF = 99,               /* FORM_REG_FF  */
  YYSYMBOL_FORM_INM_FP_L = 100,            /* FORM_INM_FP_L  */
  YYSYMBOL_FORM_INM_FP_S = 101,            /* FORM_INM_FP_S  */
  YYSYMBOL_FORM_FP_REG = 102,              /* FORM_FP_REG  */
  YYSYMBOL_FORM_FP_REG_FF = 103,           /* FORM_FP_REG_FF  */
  YYSYMBOL_FORM_FP_REG_DF = 104,           /* FORM_FP_REG_DF  */
  YYSYMBOL_FORM_REG_R_FP = 105,            /* FORM_REG_R_FP  */
  YYSYMBOL_FORM_REG_FP_R = 106,            /* FORM_REG_FP_R  */
  YYSYMBOL_FORM_J = 107,                   /* FORM_J  */
  YYSYMBOL_FORM_J_F = 108,                 /* FORM_J_F  */
  YYSYMBOL_LABEL = 109,                    /* LABEL  */
  YYSYMBOL_PINST_LA = 110,                 /* PINST_LA  */
  YYSYMBOL_PINST_LI = 111,                 /* PINST_LI  */
  YYSYMBOL_M_LO = 112,                     /* M_LO  */
  YYSYMBOL_M_HI = 113,                     /* M_HI  */
  YYSYMBOL_M_DISP = 114,                   /* M_DISP  */
  YYSYMBOL_DE_PC = 115,                    /* DE_PC  */
  YYSYMBOL_FORM_INTERNAL_NOP = 116,        /* FORM_INTERNAL_NOP  */
  YYSYMBOL_117_ = 117,                     /* ','  */
  YYSYMBOL_118_ = 118,                     /* '+'  */
  YYSYMBOL_119_ = 119,                     /* '-'  */
  YYSYMBOL_120_ = 120,                     /* '='  */
  YYSYMBOL_121_ = 121,                     /* '&'  */
  YYSYMBOL_122_ = 122,                     /* '['  */
  YYSYMBOL_123_ = 123,                     /* ']'  */
  YYSYMBOL_124_ = 124,                     /* '('  */
  YYSYMBOL_125_ = 125,                     /* ')'  */
  YYSYMBOL_YYACCEPT = 126,                 /* $accept  */
  YYSYMBOL_r_inicio = 127,                 /* r_inicio  */
  YYSYMBOL_128_1 = 128,                    /* $@1  */
  YYSYMBOL_r_linea_ensamblador = 129,      /* r_linea_ensamblador  */
  YYSYMBOL_130_2 = 130,                    /* $@2  */
  YYSYMBOL_131_3 = 131,                    /* $@3  */
  YYSYMBOL_r_directiva = 132,              /* r_directiva  */
  YYSYMBOL_133_4 = 133,                    /* $@4  */
  YYSYMBOL_134_5 = 134,                    /* $@5  */
  YYSYMBOL_135_6 = 135,                    /* $@6  */
  YYSYMBOL_136_7 = 136,                    /* $@7  */
  YYSYMBOL_137_8 = 137,                    /* $@8  */
  YYSYMBOL_138_9 = 138,                    /* $@9  */
  YYSYMBOL_139_10 = 139,                   /* $@10  */
  YYSYMBOL_140_11 = 140,                   /* $@11  */
  YYSYMBOL_141_12 = 141,                   /* $@12  */
  YYSYMBOL_r_lista_enteros = 142,          /* r_lista_enteros  */
  YYSYMBOL_143_13 = 143,                   /* $@13  */
  YYSYMBOL_r_lista_enteros_cont = 144,     /* r_lista_enteros_cont  */
  YYSYMBOL_r_lista_reales = 145,           /* r_lista_reales  */
  YYSYMBOL_146_14 = 146,                   /* $@14  */
  YYSYMBOL_147_15 = 147,                   /* $@15  */
  YYSYMBOL_r_lista_reales_cont = 148,      /* r_lista_reales_cont  */
  YYSYMBOL_r_offset = 149,                 /* r_offset  */
  YYSYMBOL_r_indice_ireg = 150,            /* r_indice_ireg  */
  YYSYMBOL_r_valores_enteros = 151,        /* r_valores_enteros  */
  YYSYMBOL_152_16 = 152,                   /* $@16  */
  YYSYMBOL_153_17 = 153,                   /* $@17  */
  YYSYMBOL_r_valores_enteros_cont = 154,   /* r_valores_enteros_cont  */
  YYSYMBOL_r_indice_fpreg = 155,           /* r_indice_fpreg  */
  YYSYMBOL_r_valores_fp = 156,             /* r_valores_fp  */
  YYSYMBOL_157_18 = 157,                   /* $@18  */
  YYSYMBOL_r_valores_fp_cont = 158,        /* r_valores_fp_cont  */
  YYSYMBOL_r_valores_btb = 159,            /* r_valores_btb  */
  YYSYMBOL_r_inmediato_disp = 160,         /* r_inmediato_disp  */
  YYSYMBOL_r_inmediato_hi = 161,           /* r_inmediato_hi  */
  YYSYMBOL_r_posicion = 162,               /* r_posicion  */
  YYSYMBOL_r_posicion_larga = 163,         /* r_posicion_larga  */
  YYSYMBOL_r_instruccion = 164             /* r_instruccion  */
};
typedef enum yysymbol_kind_t yysymbol_kind_t;




#ifdef short
# undef short
#endif

/* On compilers that do not define __PTRDIFF_MAX__ etc., make sure
   <limits.h> and (if available) <stdint.h> are included
   so that the code can choose integer types of a good width.  */

#ifndef __PTRDIFF_MAX__
# include <limits.h> /* INFRINGES ON USER NAME SPACE */
# if defined __STDC_VERSION__ && 199901 <= __STDC_VERSION__
#  include <stdint.h> /* INFRINGES ON USER NAME SPACE */
#  define YY_STDINT_H
# endif
#endif

/* Narrow types that promote to a signed type and that can represent a
   signed or unsigned integer of at least N bits.  In tables they can
   save space and decrease cache pressure.  Promoting to a signed type
   helps avoid bugs in integer arithmetic.  */

#ifdef __INT_LEAST8_MAX__
typedef __INT_LEAST8_TYPE__ yytype_int8;
#elif defined YY_STDINT_H
typedef int_least8_t yytype_int8;
#else
typedef signed char yytype_int8;
#endif

#ifdef __INT_LEAST16_MAX__
typedef __INT_LEAST16_TYPE__ yytype_int16;
#elif defined YY_STDINT_H
typedef int_least16_t yytype_int16;
#else
typedef short yytype_int16;
#endif

/* Work around bug in HP-UX 11.23, which defines these macros
   incorrectly for preprocessor constants.  This workaround can likely
   be removed in 2023, as HPE has promised support for HP-UX 11.23
   (aka HP-UX 11i v2) only through the end of 2022; see Table 2 of
   <https://h20195.www2.hpe.com/V2/getpdf.aspx/4AA4-7673ENW.pdf>.  */
#ifdef __hpux
# undef UINT_LEAST8_MAX
# undef UINT_LEAST16_MAX
# define UINT_LEAST8_MAX 255
# define UINT_LEAST16_MAX 65535
#endif

#if defined __UINT_LEAST8_MAX__ && __UINT_LEAST8_MAX__ <= __INT_MAX__
typedef __UINT_LEAST8_TYPE__ yytype_uint8;
#elif (!defined __UINT_LEAST8_MAX__ && defined YY_STDINT_H \
       && UINT_LEAST8_MAX <= INT_MAX)
typedef uint_least8_t yytype_uint8;
#elif !defined __UINT_LEAST8_MAX__ && UCHAR_MAX <= INT_MAX
typedef unsigned char yytype_uint8;
#else
typedef short yytype_uint8;
#endif

#if defined __UINT_LEAST16_MAX__ && __UINT_LEAST16_MAX__ <= __INT_MAX__
typedef __UINT_LEAST16_TYPE__ yytype_uint16;
#elif (!defined __UINT_LEAST16_MAX__ && defined YY_STDINT_H \
       && UINT_LEAST16_MAX <= INT_MAX)
typedef uint_least16_t yytype_uint16;
#elif !defined __UINT_LEAST16_MAX__ && USHRT_MAX <= INT_MAX
typedef unsigned short yytype_uint16;
#else
typedef int yytype_uint16;
#endif

#ifndef YYPTRDIFF_T
# if defined __PTRDIFF_TYPE__ && defined __PTRDIFF_MAX__
#  define YYPTRDIFF_T __PTRDIFF_TYPE__
#  define YYPTRDIFF_MAXIMUM __PTRDIFF_MAX__
# elif defined PTRDIFF_MAX
#  ifndef ptrdiff_t
#   include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  endif
#  define YYPTRDIFF_T ptrdiff_t
#  define YYPTRDIFF_MAXIMUM PTRDIFF_MAX
# else
#  define YYPTRDIFF_T long
#  define YYPTRDIFF_MAXIMUM LONG_MAX
# endif
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif defined __STDC_VERSION__ && 199901 <= __STDC_VERSION__
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned
# endif
#endif

#define YYSIZE_MAXIMUM                                  \
  YY_CAST (YYPTRDIFF_T,                                 \
           (YYPTRDIFF_MAXIMUM < YY_CAST (YYSIZE_T, -1)  \
            ? YYPTRDIFF_MAXIMUM                         \
            : YY_CAST (YYSIZE_T, -1)))

#define YYSIZEOF(X) YY_CAST (YYPTRDIFF_T, sizeof (X))


/* Stored state numbers (used for stacks). */
typedef yytype_int16 yy_state_t;

/* State numbers in computations.  */
typedef int yy_state_fast_t;

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif


#ifndef YY_ATTRIBUTE_PURE
# if defined __GNUC__ && 2 < __GNUC__ + (96 <= __GNUC_MINOR__)
#  define YY_ATTRIBUTE_PURE __attribute__ ((__pure__))
# else
#  define YY_ATTRIBUTE_PURE
# endif
#endif

#ifndef YY_ATTRIBUTE_UNUSED
# if defined __GNUC__ && 2 < __GNUC__ + (7 <= __GNUC_MINOR__)
#  define YY_ATTRIBUTE_UNUSED __attribute__ ((__unused__))
# else
#  define YY_ATTRIBUTE_UNUSED
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YY_USE(E) ((void) (E))
#else
# define YY_USE(E) /* empty */
#endif

/* Suppress an incorrect diagnostic about yylval being uninitialized.  */
#if defined __GNUC__ && ! defined __ICC && 406 <= __GNUC__ * 100 + __GNUC_MINOR__
# if __GNUC__ * 100 + __GNUC_MINOR__ < 407
#  define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN                           \
    _Pragma ("GCC diagnostic push")                                     \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")
# else
#  define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN                           \
    _Pragma ("GCC diagnostic push")                                     \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")              \
    _Pragma ("GCC diagnostic ignored \"-Wmaybe-uninitialized\"")
# endif
# define YY_IGNORE_MAYBE_UNINITIALIZED_END      \
    _Pragma ("GCC diagnostic pop")
#else
# define YY_INITIAL_VALUE(Value) Value
#endif
#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif

#if defined __cplusplus && defined __GNUC__ && ! defined __ICC && 6 <= __GNUC__
# define YY_IGNORE_USELESS_CAST_BEGIN                          \
    _Pragma ("GCC diagnostic push")                            \
    _Pragma ("GCC diagnostic ignored \"-Wuseless-cast\"")
# define YY_IGNORE_USELESS_CAST_END            \
    _Pragma ("GCC diagnostic pop")
#endif
#ifndef YY_IGNORE_USELESS_CAST_BEGIN
# define YY_IGNORE_USELESS_CAST_BEGIN
# define YY_IGNORE_USELESS_CAST_END
#endif


#define YY_ASSERT(E) ((void) (0 && (E)))

#if 1

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's 'empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
             && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* 1 */

#if (! defined yyoverflow \
     && (! defined __cplusplus \
         || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yy_state_t yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (YYSIZEOF (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (YYSIZEOF (yy_state_t) + YYSIZEOF (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)                           \
    do                                                                  \
      {                                                                 \
        YYPTRDIFF_T yynewbytes;                                         \
        YYCOPY (&yyptr->Stack_alloc, Stack, yysize);                    \
        Stack = &yyptr->Stack_alloc;                                    \
        yynewbytes = yystacksize * YYSIZEOF (*Stack) + YYSTACK_GAP_MAXIMUM; \
        yyptr += yynewbytes / YYSIZEOF (*yyptr);                        \
      }                                                                 \
    while (0)

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, YY_CAST (YYSIZE_T, (Count)) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYPTRDIFF_T yyi;                      \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (0)
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  113
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   359

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  126
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  39
/* YYNRULES -- Number of rules.  */
#define YYNRULES  127
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  325

/* YYMAXUTOK -- Last valid token kind.  */
#define YYMAXUTOK   371


/* YYTRANSLATE(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex, with out-of-bounds checking.  */
#define YYTRANSLATE(YYX)                                \
  (0 <= (YYX) && (YYX) <= YYMAXUTOK                     \
   ? YY_CAST (yysymbol_kind_t, yytranslate[YYX])        \
   : YYSYMBOL_YYUNDEF)

/* YYTRANSLATE[TOKEN-NUM] -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex.  */
static const yytype_int8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,   121,     2,
     124,   125,     2,   118,   117,   119,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,   120,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,   122,     2,   123,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50,    51,    52,    53,    54,
      55,    56,    57,    58,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    81,    82,    83,    84,
      85,    86,    87,    88,    89,    90,    91,    92,    93,    94,
      95,    96,    97,    98,    99,   100,   101,   102,   103,   104,
     105,   106,   107,   108,   109,   110,   111,   112,   113,   114,
     115,   116
};

#if YYDEBUG
/* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_int16 yyrline[] =
{
       0,   193,   193,   192,   217,   221,   227,   226,   232,   231,
     236,   239,   242,   247,   252,   257,   263,   262,   274,   273,
     285,   284,   296,   295,   307,   306,   318,   317,   328,   347,
     375,   374,   387,   386,   399,   398,   413,   412,   481,   485,
     491,   490,   530,   529,   570,   574,   579,   585,   591,   600,
     606,   611,   616,   615,   635,   634,   645,   649,   654,   659,
     664,   663,   685,   689,   694,   709,   715,   727,   735,   743,
     753,   758,   770,   778,   786,   796,   801,   809,   814,   823,
     829,   851,   877,   901,   908,   914,   941,   961,   982,  1005,
    1018,  1030,  1042,  1050,  1056,  1062,  1068,  1074,  1080,  1086,
    1092,  1098,  1106,  1119,  1126,  1138,  1144,  1150,  1156,  1162,
    1169,  1175,  1182,  1188,  1194,  1200,  1206,  1212,  1218,  1224,
    1230,  1236,  1242,  1250,  1258,  1266,  1274,  1281
};
#endif

/** Accessing symbol of state STATE.  */
#define YY_ACCESSING_SYMBOL(State) YY_CAST (yysymbol_kind_t, yystos[State])

#if 1
/* The user-facing name of the symbol whose (internal) number is
   YYSYMBOL.  No bounds checking.  */
static const char *yysymbol_name (yysymbol_kind_t yysymbol) YY_ATTRIBUTE_UNUSED;

/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "\"end of file\"", "error", "\"invalid token\"", "NL", "T_INT_LIT",
  "T_FP_LIT", "T_ALPHANUM", "T_STRING", "FR_MODE", "REG_INT", "REG_FP",
  "DE_DATA", "DE_TEXT", "DE_SPACE", "DE_GLOBL", "DE_IREG", "DE_FREG",
  "DE_ASCIIZ", "DE_BTB", "DE_BYTE", "DE_HALF", "DE_WORD", "DE_DWORD",
  "DE_FLOAT", "DE_DOUBLE", "FORM_INT_RRI", "FORM_INT_RRSH", "FORM_INT_RRR",
  "FORM_INT_RA", "FORM_INT_RI", "FORM_J_RA", "FORM_J_RRA",
  "FORM_INT_B_RRA", "FORM_J_A", "FORM_INT_L_RSB", "FORM_INT_S_RSB",
  "FORM_INT_ECALL", "FORM_INT_EBREAK", "FORM_FP_FFF", "FORM_FP_FFFr",
  "FORM_FP_FFFFr", "FORM_FP_FFr", "FORM_FP_RFF", "FORM_FP_RFr",
  "FORM_FP_RF", "FORM_FP_FR", "FORM_FP_FRr", "FORM_FP_FF", "FORM_FP_L_FSB",
  "FORM_FP_S_FSB", "FORM_INT_RB", "FORM_INT_RRB", "INST_FENCE",
  "INST_PRUEBA", "FORM_CSR_RxR", "FORM_CSR_RxI", "FORM_INM", "FORM_INM_L",
  "FORM_INM_S", "FORM_INM_DI", "FORM_INM_B", "FORM_INM_B_FI",
  "FORM_INM_B_FFI", "FORM_INM_T", "FORM_PSEUDO_NOP", "FORM_PSEUDO_RR",
  "FORM_PSEUDO_BRA", "FORM_PSEUDO_J", "FORM_PSEUDO_JR", "FORM_PSEUDO_RET",
  "FORM_PSEUDO_TAIL", "FORM_PSEUDO_R", "FORM_PSEUDO_CSRR",
  "FORM_PSEUDO_CSRW", "FORM_PSEUDO_CSRS", "FORM_PSEUDO_CSRC",
  "FORM_PSEUDO_CSRWI", "FORM_PSEUDO_CSRSI", "FORM_PSEUDO_CSRCI",
  "FORM_PSEUDO_FRCSR", "FORM_PSEUDO_FSCSR", "FORM_PSEUDO_FRRM",
  "FORM_PSEUDO_FSRM", "FORM_PSEUDO_FRFLAGS", "FORM_PSEUDO_FSFLAGS",
  "FORM_PSEUDO_BRRA", "FORM_PSEUDO_RRi", "FORM_PSEUDO_FF",
  "FORM_PSEUDO_LLA", "FORM_PSEUDO_L", "FORM_PSEUDO_S", "FORM_PSEUDO_FL",
  "FORM_PSEUDO_FS", "FORM_PSEUDO_LI", "FORM_PSEUDO_CALL",
  "FORM_PSEUDO_TRAP", "INST_RET", "INST_SYSCALL", "FORM_REG",
  "FORM_REG_FF", "FORM_INM_FP_L", "FORM_INM_FP_S", "FORM_FP_REG",
  "FORM_FP_REG_FF", "FORM_FP_REG_DF", "FORM_REG_R_FP", "FORM_REG_FP_R",
  "FORM_J", "FORM_J_F", "LABEL", "PINST_LA", "PINST_LI", "M_LO", "M_HI",
  "M_DISP", "DE_PC", "FORM_INTERNAL_NOP", "','", "'+'", "'-'", "'='",
  "'&'", "'['", "']'", "'('", "')'", "$accept", "r_inicio", "$@1",
  "r_linea_ensamblador", "$@2", "$@3", "r_directiva", "$@4", "$@5", "$@6",
  "$@7", "$@8", "$@9", "$@10", "$@11", "$@12", "r_lista_enteros", "$@13",
  "r_lista_enteros_cont", "r_lista_reales", "$@14", "$@15",
  "r_lista_reales_cont", "r_offset", "r_indice_ireg", "r_valores_enteros",
  "$@16", "$@17", "r_valores_enteros_cont", "r_indice_fpreg",
  "r_valores_fp", "$@18", "r_valores_fp_cont", "r_valores_btb",
  "r_inmediato_disp", "r_inmediato_hi", "r_posicion", "r_posicion_larga",
  "r_instruccion", YY_NULLPTR
};

static const char *
yysymbol_name (yysymbol_kind_t yysymbol)
{
  return yytname[yysymbol];
}
#endif

#define YYPACT_NINF (-236)

#define yypact_value_is_default(Yyn) \
  ((Yyn) == YYPACT_NINF)

#define YYTABLE_NINF (-67)

#define yytable_value_is_error(Yyn) \
  0

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
static const yytype_int16 yypact[] =
{
     159,   159,  -236,  -236,     4,    14,  -236,  -236,    16,  -236,
    -236,  -236,  -236,  -236,  -236,  -236,    17,    29,    30,    32,
      33,    34,    35,    36,    37,    38,  -236,  -236,    39,    40,
      41,    42,    44,    45,    46,    47,    48,    49,    50,    51,
      53,    54,  -236,    55,    56,     8,    57,  -236,    61,    59,
      60,    62,    64,    65,    69,    52,  -236,    70,  -236,    68,
      73,  -236,  -236,  -236,    71,    67,  -236,    72,    75,    75,
      75,    75,    31,    31,   -80,   -77,   -36,   -35,   -34,   -33,
     -32,   -31,   -30,   -29,   -28,   -27,   -26,   -25,   -24,   -23,
     -22,   -21,   -20,   -19,   -18,   -17,   -16,   -15,   -14,   -13,
    -236,  -236,  -236,  -236,  -236,   -12,   -11,   -10,    -9,    -8,
    -236,  -236,   258,  -236,  -236,  -236,   -72,    27,  -236,    -7,
     105,  -236,     1,  -236,  -236,  -236,  -236,  -236,  -236,  -236,
    -236,  -236,  -236,   102,   103,   109,   132,     3,     8,   128,
     129,     9,    12,   130,   131,   133,   134,   135,   136,   137,
     139,   140,   141,    15,    18,    26,   143,   145,    28,   146,
     147,   148,   151,   138,  -236,   159,   159,  -236,  -236,     1,
    -236,  -236,  -236,   155,   156,    43,    22,    94,    94,    95,
      96,    97,  -236,  -236,  -236,    93,    98,    99,  -236,  -236,
     100,   101,  -236,    92,   108,   110,   111,   107,   117,   112,
     118,   120,   121,   122,   123,   124,  -236,  -236,   125,  -236,
     119,  -236,   126,   142,   127,   152,   149,  -236,  -236,  -236,
    -236,   150,  -236,  -236,  -236,  -236,  -236,  -236,   174,  -236,
     193,  -236,  -236,   162,    75,  -236,    31,  -236,  -236,    21,
     188,   154,   213,   216,   242,    28,    28,   243,   249,   250,
     248,   251,   253,   254,   255,   301,   304,   303,   306,   307,
     308,   309,   310,   191,   196,    28,    71,  -236,   174,    67,
    -236,   200,  -236,  -236,  -236,  -236,  -236,   206,   207,   208,
    -236,  -236,   209,   210,   211,   212,  -236,   214,  -236,   218,
     219,  -236,  -236,  -236,  -236,   215,  -236,   217,  -236,   329,
    -236,  -236,  -236,  -236,   252,  -236,  -236,  -236,  -236,  -236,
    -236,  -236,  -236,   333,   337,  -236,  -236,   223,     1,  -236,
     232,  -236,  -236,   342,  -236
};

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
static const yytype_int8 yydefact[] =
{
       5,     5,    13,    14,     0,     0,    30,    32,     0,    34,
      16,    18,    20,    22,    24,    26,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   105,   106,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    79,     0,     0,     0,     0,    85,     0,     0,
       0,     0,     0,     0,     0,     0,     2,     0,     4,    11,
      12,    10,    28,    15,    51,    59,    29,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      77,    78,    83,    84,    91,     0,     0,     0,     0,     0,
      90,    92,     0,     1,     6,     8,     0,     0,    31,     0,
       0,    33,    49,    35,    36,    17,    19,    21,    23,    42,
      40,    25,    27,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     3,     5,     5,    50,    52,    49,
      58,    60,    48,     0,     0,     0,    39,    45,    45,     0,
       0,     0,    97,    70,    71,     0,     0,     0,    96,    98,
       0,     0,    65,   102,     0,     0,     0,     0,    66,     0,
       0,     0,     0,   113,     0,   119,   120,   117,   116,   114,
     123,    66,     0,    65,     0,     0,     0,    80,    75,    76,
      81,     0,    87,    82,    89,    88,     7,     9,    57,    54,
      63,    46,    47,     0,     0,    37,     0,    43,    41,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    51,    53,    57,    59,
      61,     0,    38,    44,    93,    94,    95,     0,     0,     0,
      99,   100,     0,     0,     0,     0,   104,     0,   107,   109,
       0,   112,   121,   118,   115,     0,   125,     0,   126,     0,
      86,    56,    55,    62,     0,    73,    72,    74,    68,    67,
      69,   101,   103,     0,     0,   122,   124,     0,    49,   108,
     111,   127,    64,     0,   110
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -236,     2,  -236,   244,  -236,  -236,  -236,  -236,  -236,  -236,
    -236,  -236,  -236,  -236,  -236,  -236,   -69,  -236,  -236,   -67,
    -236,  -236,    85,  -165,  -236,    88,  -236,  -236,    87,  -236,
      89,  -236,  -236,  -236,  -125,  -236,  -235,   221,  -236
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
       0,    57,   112,    58,   165,   166,    59,    68,    69,    70,
      71,    72,    73,    64,    65,    67,   125,   176,   235,   131,
     178,   177,   237,   175,   117,   118,   228,   268,   267,   120,
     121,   230,   270,   123,   197,   188,   220,   102,    60
};

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_int16 yytable[] =
{
     126,   127,   128,    61,   229,   172,   132,   183,    62,   184,
     280,   281,   100,   192,   101,   193,   192,   199,   198,   210,
      63,   211,   213,    66,   211,   192,    74,   211,   212,   214,
     300,   168,   218,   169,   219,   129,   130,   133,    75,    76,
     134,    77,    78,    79,    80,    81,    82,    83,   167,    84,
      85,    86,    87,    88,    89,    90,   111,    91,    92,    93,
      94,    95,    96,    97,    98,    99,   103,   104,   105,   106,
     113,   114,   107,   108,   109,   110,   115,   119,   122,   124,
     116,   135,   136,   137,   138,   139,   140,   141,   142,   143,
     144,   145,   146,   147,   148,   149,   150,   151,   152,   153,
     154,   155,   156,   157,   158,   159,   160,   161,   162,   163,
     171,   179,   180,   170,   274,   185,   186,   187,   181,   173,
     174,   194,   195,   196,   194,   195,   196,   194,   195,   196,
     194,   195,   196,   194,   195,   196,   182,   190,   191,   234,
     200,   201,   225,   202,   203,   204,   205,   206,   207,   208,
     215,   209,   216,   322,   217,   221,   222,   224,   223,   231,
     232,   263,     1,   276,   233,   272,   271,   226,   227,   273,
       2,     3,     4,     5,     6,     7,     8,     9,    10,    11,
      12,    13,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,   275,    24,    25,    26,    27,    28,    29,    30,
      31,    32,    33,    34,    35,    36,    37,    38,    39,    40,
      41,   236,   239,   240,   241,   242,   -66,   245,   246,   277,
     243,   244,   278,    42,    43,    44,    45,    46,    47,    48,
     247,   250,   248,   249,   251,   253,   252,   254,   255,   256,
     257,   258,   259,   -65,    49,    50,    51,    52,   279,   282,
     260,   262,    53,    54,    55,   283,   284,   285,   318,   261,
     286,     1,   287,   238,   288,   289,   264,   265,    56,     2,
       3,     4,     5,     6,     7,     8,     9,    10,    11,    12,
      13,    14,    15,    16,    17,    18,    19,    20,    21,    22,
      23,   266,    24,    25,    26,    27,    28,    29,    30,    31,
      32,    33,    34,    35,    36,    37,    38,    39,    40,    41,
     269,   290,   291,   292,   293,   294,   298,   295,   296,   297,
     299,   304,    42,    43,    44,    45,    46,    47,    48,   305,
     306,   307,   308,   309,   310,   313,   314,   311,   317,   312,
     315,   319,   316,    49,    50,    51,    52,   320,   321,   323,
     324,    53,    54,    55,   301,   302,   164,     0,   303,   189
};

static const yytype_int16 yycheck[] =
{
      69,    70,    71,     1,   169,     4,    73,     4,     4,     6,
     245,   246,     4,     4,     6,     6,     4,   142,     6,     4,
       6,     6,     4,     7,     6,     4,     9,     6,   153,   154,
     265,     4,     4,     6,     6,     4,     5,   117,     9,     9,
     117,     9,     9,     9,     9,     9,     9,     9,   120,    10,
      10,    10,    10,     9,     9,     9,     4,    10,    10,    10,
      10,    10,     9,     9,     9,     9,     9,     6,     9,     9,
       0,     3,    10,     9,     9,     6,     3,    10,     6,     4,
       9,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
       5,     9,     9,   120,   239,   112,   113,   114,     9,   118,
     119,   112,   113,   114,   112,   113,   114,   112,   113,   114,
     112,   113,   114,   112,   113,   114,     4,     9,     9,   117,
      10,    10,     4,    10,    10,    10,    10,    10,     9,     9,
     124,    10,     9,   318,     9,     9,     9,     6,    10,     4,
       4,     9,     3,     9,   121,   234,     4,   165,   166,   236,
      11,    12,    13,    14,    15,    16,    17,    18,    19,    20,
      21,    22,    23,    24,    25,    26,    27,    28,    29,    30,
      31,    32,     4,    34,    35,    36,    37,    38,    39,    40,
      41,    42,    43,    44,    45,    46,    47,    48,    49,    50,
      51,   117,   117,   117,   117,   122,   124,   117,   117,     6,
     122,   122,     6,    64,    65,    66,    67,    68,    69,    70,
     122,   124,   122,   122,   117,   117,   124,   117,   117,   117,
     117,   117,   117,   124,    85,    86,    87,    88,     6,     6,
     124,   124,    93,    94,    95,     6,     6,     9,     6,   117,
       9,     3,     9,   178,    10,    10,   117,   117,   109,    11,
      12,    13,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    30,    31,
      32,   117,    34,    35,    36,    37,    38,    39,    40,    41,
      42,    43,    44,    45,    46,    47,    48,    49,    50,    51,
     117,    10,     8,    10,     8,     8,   125,     9,     9,     9,
     124,   121,    64,    65,    66,    67,    68,    69,    70,   123,
     123,   123,   123,   123,   123,   117,   117,   125,     9,   125,
     125,     8,   125,    85,    86,    87,    88,    10,   125,   117,
       8,    93,    94,    95,   266,   268,   112,    -1,   269,   138
};

/* YYSTOS[STATE-NUM] -- The symbol kind of the accessing symbol of
   state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     3,    11,    12,    13,    14,    15,    16,    17,    18,
      19,    20,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    34,    35,    36,    37,    38,    39,
      40,    41,    42,    43,    44,    45,    46,    47,    48,    49,
      50,    51,    64,    65,    66,    67,    68,    69,    70,    85,
      86,    87,    88,    93,    94,    95,   109,   127,   129,   132,
     164,   127,     4,     6,   139,   140,     7,   141,   133,   134,
     135,   136,   137,   138,     9,     9,     9,     9,     9,     9,
       9,     9,     9,     9,    10,    10,    10,    10,     9,     9,
       9,    10,    10,    10,    10,    10,     9,     9,     9,     9,
       4,     6,   163,     9,     6,     9,     9,    10,     9,     9,
       6,     4,   128,     0,     3,     3,     9,   150,   151,    10,
     155,   156,     6,   159,     4,   142,   142,   142,   142,     4,
       5,   145,   145,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   129,   130,   131,   120,     4,     6,
     120,     5,     4,   118,   119,   149,   143,   147,   146,     9,
       9,     9,     4,     4,     6,   112,   113,   114,   161,   163,
       9,     9,     4,     6,   112,   113,   114,   160,     6,   160,
      10,    10,    10,    10,    10,    10,    10,     9,     9,    10,
       4,     6,   160,     4,   160,   124,     9,     9,     4,     6,
     162,     9,     9,    10,     6,     4,   127,   127,   152,   149,
     157,     4,     4,   121,   117,   144,   117,   148,   148,   117,
     117,   117,   122,   122,   122,   117,   117,   122,   122,   122,
     124,   117,   124,   117,   117,   117,   117,   117,   117,   117,
     124,   117,   124,     9,   117,   117,   117,   154,   153,   117,
     158,     4,   142,   145,   160,     4,     9,     6,     6,     6,
     162,   162,     6,     6,     6,     9,     9,     9,    10,    10,
      10,     8,    10,     8,     8,     9,     9,     9,   125,   124,
     162,   151,   154,   156,   121,   123,   123,   123,   123,   123,
     123,   125,   125,   117,   117,   125,   125,     9,     6,     8,
      10,   125,   149,   117,     8
};

/* YYR1[RULE-NUM] -- Symbol kind of the left-hand side of rule RULE-NUM.  */
static const yytype_uint8 yyr1[] =
{
       0,   126,   128,   127,   127,   127,   130,   129,   131,   129,
     129,   129,   129,   132,   132,   132,   133,   132,   134,   132,
     135,   132,   136,   132,   137,   132,   138,   132,   132,   132,
     139,   132,   140,   132,   141,   132,   143,   142,   144,   144,
     146,   145,   147,   145,   148,   148,   149,   149,   149,   149,
     150,   150,   152,   151,   153,   151,   154,   154,   155,   155,
     157,   156,   158,   158,   159,   160,   160,   160,   160,   160,
     161,   161,   161,   161,   161,   162,   162,   163,   163,   164,
     164,   164,   164,   164,   164,   164,   164,   164,   164,   164,
     164,   164,   164,   164,   164,   164,   164,   164,   164,   164,
     164,   164,   164,   164,   164,   164,   164,   164,   164,   164,
     164,   164,   164,   164,   164,   164,   164,   164,   164,   164,
     164,   164,   164,   164,   164,   164,   164,   164
};

/* YYR2[RULE-NUM] -- Number of symbols on the right-hand side of rule RULE-NUM.  */
static const yytype_int8 yyr2[] =
{
       0,     2,     0,     3,     1,     0,     0,     4,     0,     4,
       2,     1,     1,     1,     1,     2,     0,     3,     0,     3,
       0,     3,     0,     3,     0,     3,     0,     3,     2,     2,
       0,     3,     0,     3,     0,     3,     0,     3,     2,     0,
       0,     3,     0,     3,     2,     0,     2,     2,     1,     0,
       2,     0,     0,     4,     0,     5,     2,     0,     2,     0,
       0,     4,     2,     0,     7,     1,     1,     4,     4,     4,
       1,     1,     4,     4,     4,     1,     1,     1,     1,     1,
       4,     4,     4,     2,     2,     1,     6,     4,     4,     4,
       2,     2,     2,     6,     6,     6,     4,     4,     4,     6,
       6,     7,     4,     7,     6,     1,     1,     6,     8,     6,
      10,     8,     6,     4,     4,     6,     4,     4,     6,     4,
       4,     6,     7,     4,     7,     6,     6,     8
};


enum { YYENOMEM = -2 };

#define yyerrok         (yyerrstatus = 0)
#define yyclearin       (yychar = YYEMPTY)

#define YYACCEPT        goto yyacceptlab
#define YYABORT         goto yyabortlab
#define YYERROR         goto yyerrorlab
#define YYNOMEM         goto yyexhaustedlab


#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                    \
  do                                                              \
    if (yychar == YYEMPTY)                                        \
      {                                                           \
        yychar = (Token);                                         \
        yylval = (Value);                                         \
        YYPOPSTACK (yylen);                                       \
        yystate = *yyssp;                                         \
        goto yybackup;                                            \
      }                                                           \
    else                                                          \
      {                                                           \
        yyerror (YY_("syntax error: cannot back up")); \
        YYERROR;                                                  \
      }                                                           \
  while (0)

/* Backward compatibility with an undocumented macro.
   Use YYerror or YYUNDEF. */
#define YYERRCODE YYUNDEF


/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)                        \
do {                                            \
  if (yydebug)                                  \
    YYFPRINTF Args;                             \
} while (0)




# define YY_SYMBOL_PRINT(Title, Kind, Value, Location)                    \
do {                                                                      \
  if (yydebug)                                                            \
    {                                                                     \
      YYFPRINTF (stderr, "%s ", Title);                                   \
      yy_symbol_print (stderr,                                            \
                  Kind, Value); \
      YYFPRINTF (stderr, "\n");                                           \
    }                                                                     \
} while (0)


/*-----------------------------------.
| Print this symbol's value on YYO.  |
`-----------------------------------*/

static void
yy_symbol_value_print (FILE *yyo,
                       yysymbol_kind_t yykind, YYSTYPE const * const yyvaluep)
{
  FILE *yyoutput = yyo;
  YY_USE (yyoutput);
  if (!yyvaluep)
    return;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YY_USE (yykind);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}


/*---------------------------.
| Print this symbol on YYO.  |
`---------------------------*/

static void
yy_symbol_print (FILE *yyo,
                 yysymbol_kind_t yykind, YYSTYPE const * const yyvaluep)
{
  YYFPRINTF (yyo, "%s %s (",
             yykind < YYNTOKENS ? "token" : "nterm", yysymbol_name (yykind));

  yy_symbol_value_print (yyo, yykind, yyvaluep);
  YYFPRINTF (yyo, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

static void
yy_stack_print (yy_state_t *yybottom, yy_state_t *yytop)
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)                            \
do {                                                            \
  if (yydebug)                                                  \
    yy_stack_print ((Bottom), (Top));                           \
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

static void
yy_reduce_print (yy_state_t *yyssp, YYSTYPE *yyvsp,
                 int yyrule)
{
  int yylno = yyrline[yyrule];
  int yynrhs = yyr2[yyrule];
  int yyi;
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %d):\n",
             yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr,
                       YY_ACCESSING_SYMBOL (+yyssp[yyi + 1 - yynrhs]),
                       &yyvsp[(yyi + 1) - (yynrhs)]);
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)          \
do {                                    \
  if (yydebug)                          \
    yy_reduce_print (yyssp, yyvsp, Rule); \
} while (0)

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args) ((void) 0)
# define YY_SYMBOL_PRINT(Title, Kind, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


/* Context of a parse error.  */
typedef struct
{
  yy_state_t *yyssp;
  yysymbol_kind_t yytoken;
} yypcontext_t;

/* Put in YYARG at most YYARGN of the expected tokens given the
   current YYCTX, and return the number of tokens stored in YYARG.  If
   YYARG is null, return the number of expected tokens (guaranteed to
   be less than YYNTOKENS).  Return YYENOMEM on memory exhaustion.
   Return 0 if there are more than YYARGN expected tokens, yet fill
   YYARG up to YYARGN. */
static int
yypcontext_expected_tokens (const yypcontext_t *yyctx,
                            yysymbol_kind_t yyarg[], int yyargn)
{
  /* Actual size of YYARG. */
  int yycount = 0;
  int yyn = yypact[+*yyctx->yyssp];
  if (!yypact_value_is_default (yyn))
    {
      /* Start YYX at -YYN if negative to avoid negative indexes in
         YYCHECK.  In other words, skip the first -YYN actions for
         this state because they are default actions.  */
      int yyxbegin = yyn < 0 ? -yyn : 0;
      /* Stay within bounds of both yycheck and yytname.  */
      int yychecklim = YYLAST - yyn + 1;
      int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
      int yyx;
      for (yyx = yyxbegin; yyx < yyxend; ++yyx)
        if (yycheck[yyx + yyn] == yyx && yyx != YYSYMBOL_YYerror
            && !yytable_value_is_error (yytable[yyx + yyn]))
          {
            if (!yyarg)
              ++yycount;
            else if (yycount == yyargn)
              return 0;
            else
              yyarg[yycount++] = YY_CAST (yysymbol_kind_t, yyx);
          }
    }
  if (yyarg && yycount == 0 && 0 < yyargn)
    yyarg[0] = YYSYMBOL_YYEMPTY;
  return yycount;
}




#ifndef yystrlen
# if defined __GLIBC__ && defined _STRING_H
#  define yystrlen(S) (YY_CAST (YYPTRDIFF_T, strlen (S)))
# else
/* Return the length of YYSTR.  */
static YYPTRDIFF_T
yystrlen (const char *yystr)
{
  YYPTRDIFF_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
# endif
#endif

#ifndef yystpcpy
# if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#  define yystpcpy stpcpy
# else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
static char *
yystpcpy (char *yydest, const char *yysrc)
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
# endif
#endif

#ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYPTRDIFF_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYPTRDIFF_T yyn = 0;
      char const *yyp = yystr;
      for (;;)
        switch (*++yyp)
          {
          case '\'':
          case ',':
            goto do_not_strip_quotes;

          case '\\':
            if (*++yyp != '\\')
              goto do_not_strip_quotes;
            else
              goto append;

          append:
          default:
            if (yyres)
              yyres[yyn] = *yyp;
            yyn++;
            break;

          case '"':
            if (yyres)
              yyres[yyn] = '\0';
            return yyn;
          }
    do_not_strip_quotes: ;
    }

  if (yyres)
    return yystpcpy (yyres, yystr) - yyres;
  else
    return yystrlen (yystr);
}
#endif


static int
yy_syntax_error_arguments (const yypcontext_t *yyctx,
                           yysymbol_kind_t yyarg[], int yyargn)
{
  /* Actual size of YYARG. */
  int yycount = 0;
  /* There are many possibilities here to consider:
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yyctx->yytoken != YYSYMBOL_YYEMPTY)
    {
      int yyn;
      if (yyarg)
        yyarg[yycount] = yyctx->yytoken;
      ++yycount;
      yyn = yypcontext_expected_tokens (yyctx,
                                        yyarg ? yyarg + 1 : yyarg, yyargn - 1);
      if (yyn == YYENOMEM)
        return YYENOMEM;
      else
        yycount += yyn;
    }
  return yycount;
}

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return -1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return YYENOMEM if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYPTRDIFF_T *yymsg_alloc, char **yymsg,
                const yypcontext_t *yyctx)
{
  enum { YYARGS_MAX = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULLPTR;
  /* Arguments of yyformat: reported tokens (one for the "unexpected",
     one per "expected"). */
  yysymbol_kind_t yyarg[YYARGS_MAX];
  /* Cumulated lengths of YYARG.  */
  YYPTRDIFF_T yysize = 0;

  /* Actual size of YYARG. */
  int yycount = yy_syntax_error_arguments (yyctx, yyarg, YYARGS_MAX);
  if (yycount == YYENOMEM)
    return YYENOMEM;

  switch (yycount)
    {
#define YYCASE_(N, S)                       \
      case N:                               \
        yyformat = S;                       \
        break
    default: /* Avoid compiler warnings. */
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
#undef YYCASE_
    }

  /* Compute error message size.  Don't count the "%s"s, but reserve
     room for the terminator.  */
  yysize = yystrlen (yyformat) - 2 * yycount + 1;
  {
    int yyi;
    for (yyi = 0; yyi < yycount; ++yyi)
      {
        YYPTRDIFF_T yysize1
          = yysize + yytnamerr (YY_NULLPTR, yytname[yyarg[yyi]]);
        if (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM)
          yysize = yysize1;
        else
          return YYENOMEM;
      }
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return -1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yytname[yyarg[yyi++]]);
          yyformat += 2;
        }
      else
        {
          ++yyp;
          ++yyformat;
        }
  }
  return 0;
}


/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

static void
yydestruct (const char *yymsg,
            yysymbol_kind_t yykind, YYSTYPE *yyvaluep)
{
  YY_USE (yyvaluep);
  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yykind, yyvaluep, yylocationp);

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YY_USE (yykind);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}


/* Lookahead token kind.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;
/* Number of syntax errors so far.  */
int yynerrs;




/*----------.
| yyparse.  |
`----------*/

int
yyparse (void)
{
    yy_state_fast_t yystate = 0;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus = 0;

    /* Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* Their size.  */
    YYPTRDIFF_T yystacksize = YYINITDEPTH;

    /* The state stack: array, bottom, top.  */
    yy_state_t yyssa[YYINITDEPTH];
    yy_state_t *yyss = yyssa;
    yy_state_t *yyssp = yyss;

    /* The semantic value stack: array, bottom, top.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs = yyvsa;
    YYSTYPE *yyvsp = yyvs;

  int yyn;
  /* The return value of yyparse.  */
  int yyresult;
  /* Lookahead symbol kind.  */
  yysymbol_kind_t yytoken = YYSYMBOL_YYEMPTY;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYPTRDIFF_T yymsg_alloc = sizeof yymsgbuf;

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yychar = YYEMPTY; /* Cause a token to be read.  */

  goto yysetstate;


/*------------------------------------------------------------.
| yynewstate -- push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;


/*--------------------------------------------------------------------.
| yysetstate -- set current state (the top of the stack) to yystate.  |
`--------------------------------------------------------------------*/
yysetstate:
  YYDPRINTF ((stderr, "Entering state %d\n", yystate));
  YY_ASSERT (0 <= yystate && yystate < YYNSTATES);
  YY_IGNORE_USELESS_CAST_BEGIN
  *yyssp = YY_CAST (yy_state_t, yystate);
  YY_IGNORE_USELESS_CAST_END
  YY_STACK_PRINT (yyss, yyssp);

  if (yyss + yystacksize - 1 <= yyssp)
#if !defined yyoverflow && !defined YYSTACK_RELOCATE
    YYNOMEM;
#else
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYPTRDIFF_T yysize = yyssp - yyss + 1;

# if defined yyoverflow
      {
        /* Give user a chance to reallocate the stack.  Use copies of
           these so that the &'s don't force the real ones into
           memory.  */
        yy_state_t *yyss1 = yyss;
        YYSTYPE *yyvs1 = yyvs;

        /* Each stack pointer address is followed by the size of the
           data in use in that stack, in bytes.  This used to be a
           conditional around just the two extra args, but that might
           be undefined if yyoverflow is a macro.  */
        yyoverflow (YY_("memory exhausted"),
                    &yyss1, yysize * YYSIZEOF (*yyssp),
                    &yyvs1, yysize * YYSIZEOF (*yyvsp),
                    &yystacksize);
        yyss = yyss1;
        yyvs = yyvs1;
      }
# else /* defined YYSTACK_RELOCATE */
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
        YYNOMEM;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
        yystacksize = YYMAXDEPTH;

      {
        yy_state_t *yyss1 = yyss;
        union yyalloc *yyptr =
          YY_CAST (union yyalloc *,
                   YYSTACK_ALLOC (YY_CAST (YYSIZE_T, YYSTACK_BYTES (yystacksize))));
        if (! yyptr)
          YYNOMEM;
        YYSTACK_RELOCATE (yyss_alloc, yyss);
        YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
        if (yyss1 != yyssa)
          YYSTACK_FREE (yyss1);
      }
# endif

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YY_IGNORE_USELESS_CAST_BEGIN
      YYDPRINTF ((stderr, "Stack size increased to %ld\n",
                  YY_CAST (long, yystacksize)));
      YY_IGNORE_USELESS_CAST_END

      if (yyss + yystacksize - 1 <= yyssp)
        YYABORT;
    }
#endif /* !defined yyoverflow && !defined YYSTACK_RELOCATE */


  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;


/*-----------.
| yybackup.  |
`-----------*/
yybackup:
  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either empty, or end-of-input, or a valid lookahead.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token\n"));
      yychar = yylex ();
    }

  if (yychar <= YYEOF)
    {
      yychar = YYEOF;
      yytoken = YYSYMBOL_YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else if (yychar == YYerror)
    {
      /* The scanner already issued an error message, process directly
         to error recovery.  But do not keep the error token as
         lookahead, it is too special and may lead us to an endless
         loop in error recovery. */
      yychar = YYUNDEF;
      yytoken = YYSYMBOL_YYerror;
      goto yyerrlab1;
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);
  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  /* Discard the shifted token.  */
  yychar = YYEMPTY;
  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     '$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
  case 2: /* $@1: %empty  */
#line 193 "input.yacc.y"
{
    if (es_region_datos(region)) {
    	if (pending_label) {
            ts_inserta_etiqueta(ts, etiqueta, pos[pending_region], pending_region);
            pending_label= 0;
        }
        // Las etiquetas en zonas de datos se quedan diferidas por si la dirección se tiene que alinear
        pending_label = 1;
        pending_region = region;
        sprintf (etiqueta, "%s", (yyvsp[0].cvalue));
    } else if (es_region_codigo(region)) {
        // TODO revisar si esto es necesario
	if (pending_label) {
           ts_inserta_etiqueta(ts, etiqueta, pos[pending_region], pending_region);
           pending_label= 0;
        }
        ts_inserta_etiqueta(ts, (yyvsp[0].cvalue), pos[region], region);
        break;
    } else {
        yyerror("Etiqueta indefinida");
    } /* endif */}
#line 1868 "input.yacc.c"
    break;

  case 3: /* r_inicio: LABEL $@1 r_linea_ensamblador  */
#line 215 "input.yacc.y"
{}
#line 1874 "input.yacc.c"
    break;

  case 4: /* r_inicio: r_linea_ensamblador  */
#line 218 "input.yacc.y"
{}
#line 1880 "input.yacc.c"
    break;

  case 5: /* r_inicio: %empty  */
#line 221 "input.yacc.y"
{
}
#line 1887 "input.yacc.c"
    break;

  case 6: /* $@2: %empty  */
#line 227 "input.yacc.y"
{}
#line 1893 "input.yacc.c"
    break;

  case 7: /* r_linea_ensamblador: r_directiva NL $@2 r_inicio  */
#line 229 "input.yacc.y"
{}
#line 1899 "input.yacc.c"
    break;

  case 8: /* $@3: %empty  */
#line 232 "input.yacc.y"
{}
#line 1905 "input.yacc.c"
    break;

  case 9: /* r_linea_ensamblador: r_instruccion NL $@3 r_inicio  */
#line 234 "input.yacc.y"
{}
#line 1911 "input.yacc.c"
    break;

  case 10: /* r_linea_ensamblador: NL r_inicio  */
#line 237 "input.yacc.y"
{}
#line 1917 "input.yacc.c"
    break;

  case 11: /* r_linea_ensamblador: r_directiva  */
#line 240 "input.yacc.y"
{}
#line 1923 "input.yacc.c"
    break;

  case 12: /* r_linea_ensamblador: r_instruccion  */
#line 243 "input.yacc.y"
{}
#line 1929 "input.yacc.c"
    break;

  case 13: /* r_directiva: DE_DATA  */
#line 248 "input.yacc.y"
{
    region = r_data;
}
#line 1937 "input.yacc.c"
    break;

  case 14: /* r_directiva: DE_TEXT  */
#line 253 "input.yacc.y"
{
    region = r_text;
}
#line 1945 "input.yacc.c"
    break;

  case 15: /* r_directiva: DE_GLOBL T_ALPHANUM  */
#line 258 "input.yacc.y"
{
    ts_inserta_simbolo(ts, (yyvsp[0].cvalue));
}
#line 1953 "input.yacc.c"
    break;

  case 16: /* $@4: %empty  */
#line 263 "input.yacc.y"
{
    if (!es_region_datos(region)) {
        sprintf(mensaje, "No estás en una sección de datos (%s)", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
    tipo = t_byte;
}
#line 1965 "input.yacc.c"
    break;

  case 17: /* r_directiva: DE_BYTE $@4 r_lista_enteros  */
#line 271 "input.yacc.y"
{}
#line 1971 "input.yacc.c"
    break;

  case 18: /* $@5: %empty  */
#line 274 "input.yacc.y"
{
    if (!es_region_datos(region)) {
        sprintf(mensaje, "No estás en una sección de datos (%s)", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
    tipo = t_half;
}
#line 1983 "input.yacc.c"
    break;

  case 19: /* r_directiva: DE_HALF $@5 r_lista_enteros  */
#line 282 "input.yacc.y"
{}
#line 1989 "input.yacc.c"
    break;

  case 20: /* $@6: %empty  */
#line 285 "input.yacc.y"
{
    if (!es_region_datos(region)) {
        sprintf(mensaje, "No estás en una sección de datos (%s)", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
    tipo = t_word;
}
#line 2001 "input.yacc.c"
    break;

  case 21: /* r_directiva: DE_WORD $@6 r_lista_enteros  */
#line 293 "input.yacc.y"
{}
#line 2007 "input.yacc.c"
    break;

  case 22: /* $@7: %empty  */
#line 296 "input.yacc.y"
{
    if (!es_region_datos(region)) {
        sprintf(mensaje, "No estás en una sección de datos (%s)", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
    tipo = t_dword;
}
#line 2019 "input.yacc.c"
    break;

  case 23: /* r_directiva: DE_DWORD $@7 r_lista_enteros  */
#line 304 "input.yacc.y"
{}
#line 2025 "input.yacc.c"
    break;

  case 24: /* $@8: %empty  */
#line 307 "input.yacc.y"
{
    if (!es_region_datos(region)) {
        sprintf(mensaje, "No estás en una sección de datos (%s)", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
    tipo = t_float;
}
#line 2037 "input.yacc.c"
    break;

  case 25: /* r_directiva: DE_FLOAT $@8 r_lista_reales  */
#line 315 "input.yacc.y"
{}
#line 2043 "input.yacc.c"
    break;

  case 26: /* $@9: %empty  */
#line 318 "input.yacc.y"
{
    if (!es_region_datos(region)) {
        sprintf(mensaje, "No estás en una sección de datos (%s)", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
    tipo = t_double;
}
#line 2055 "input.yacc.c"
    break;

  case 27: /* r_directiva: DE_DOUBLE $@9 r_lista_reales  */
#line 326 "input.yacc.y"
{}
#line 2061 "input.yacc.c"
    break;

  case 28: /* r_directiva: DE_SPACE T_INT_LIT  */
#line 329 "input.yacc.y"
{
    if (!es_region_datos(region)) {
        sprintf(mensaje, "No estás en una sección de datos (%s)", mm->regiones[region].nombre);
        yyerror(mensaje);
    }

    if (pending_label) {
        ts_inserta_etiqueta(ts, etiqueta, pos[pending_region], pending_region);
        pending_label= 0;
    }
    pos[region] += (yyvsp[0].ivalue);

    if (pos[region] > tam_region[region]) {
    	sprintf(mensaje, "Excedido el tamaño de la memoria: %s", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
}
#line 2083 "input.yacc.c"
    break;

  case 29: /* r_directiva: DE_ASCIIZ T_STRING  */
#line 348 "input.yacc.y"
{
    if (!es_region_datos(region)) {
        sprintf(mensaje, "No estás en una sección de datos (%s)", mm->regiones[region].nombre);
        yyerror(mensaje);
    }

    if (!esta_alineada_dir_byte(pos[region]))
        pos[region] = mem_direccion_alineada(pos[region], sizeof(byte));
    if (pending_label) {
        ts_inserta_etiqueta(ts, etiqueta, pos[pending_region], pending_region);
        pending_label=0;
    }

    for (i = 0; i <= (strlen((yyvsp[0].cvalue)) + 1); i++) // Sumo 1 por el null terminated
    {
        valor.int_d = (byte) (yyvsp[0].cvalue)[i];
        mem_escribe_region_datos(mm, region, pos[region] + i, valor, t_byte, NO);
    }
    pos[region] += sizeof (byte) * strlen((yyvsp[0].cvalue)) + 1; // Sumo 1 por el null terminated

    if (pos[region] > tam_region[region]) {
    	sprintf(mensaje, "Excedido el tamaño de la memoria: %s", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
}
#line 2113 "input.yacc.c"
    break;

  case 30: /* $@10: %empty  */
#line 375 "input.yacc.y"
{
    if (no_ireg) {
    	sprintf(mensaje, "Los registros enteros ya han sido inicializados en otro módulo");
        yyerror(mensaje);
    }
}
#line 2124 "input.yacc.c"
    break;

  case 31: /* r_directiva: DE_IREG $@10 r_valores_enteros  */
#line 382 "input.yacc.y"
{
  init_ireg = SI;
}
#line 2132 "input.yacc.c"
    break;

  case 32: /* $@11: %empty  */
#line 387 "input.yacc.y"
{
    if (no_fpreg) {
    	sprintf(mensaje, "Los registros de como flotante ya han sido inicializados en otro módulo");
        yyerror(mensaje);
    }
}
#line 2143 "input.yacc.c"
    break;

  case 33: /* r_directiva: DE_FREG $@11 r_valores_fp  */
#line 394 "input.yacc.y"
{
  init_fpreg = SI;
}
#line 2151 "input.yacc.c"
    break;

  case 34: /* $@12: %empty  */
#line 399 "input.yacc.y"
{
    if (no_btb) {
    	sprintf(mensaje, "La BTB ya ha sido inicializada en otro módulo");
        yyerror(mensaje);
    }
}
#line 2162 "input.yacc.c"
    break;

  case 35: /* r_directiva: DE_BTB $@12 r_valores_btb  */
#line 406 "input.yacc.y"
{
  init_btb = SI;
}
#line 2170 "input.yacc.c"
    break;

  case 36: /* $@13: %empty  */
#line 413 "input.yacc.y"
{
    switch (tipo) {
        case t_byte:
            if (!esta_alineada_dir_byte(pos[region]))
                pos[region] = mem_direccion_alineada(pos[region], sizeof(byte));
            if ((yyvsp[0].ivalue) > MAX_BYTE)
                yyerror("Literal fuera del rango del tipo 'byte'");
            if (pending_label) {
            	ts_inserta_etiqueta(ts, etiqueta, pos[region], r_data);
            	pending_label=0;
            }
            valor.int_d = (byte) (yyvsp[0].ivalue);
            mem_escribe_region_datos(mm, region, pos[region], valor, t_byte, NO);
            pos[region] += sizeof (byte);
            break;
        case t_half:
            if (!esta_alineada_dir_half(pos[region]))
                pos[region] = mem_direccion_alineada(pos[region], sizeof(half));
            if ((yyvsp[0].ivalue) > MAX_HALF)
                yyerror("Literal fuera del rango del tipo 'half'");
            if (pending_label) {
            	ts_inserta_etiqueta(ts, etiqueta, pos[region], r_data);
            	pending_label=0;
            }
            valor.int_d = (half) (yyvsp[0].ivalue);
            mem_escribe_region_datos(mm, region, pos[region], valor, t_half, NO);
            pos[region] += sizeof (half);
            break;
        case t_word:
            if (!esta_alineada_dir_word(pos[region]))
                pos[region] = mem_direccion_alineada(pos[region], sizeof(word));
            if ((yyvsp[0].ivalue) > MAX_WORD)
                yyerror("Literal fuera del rango del tipo 'word'");
            if (pending_label) {
            	ts_inserta_etiqueta(ts, etiqueta, pos[region], r_data);
            	pending_label=0;
            }
            valor.int_d = (word) (yyvsp[0].ivalue);
            mem_escribe_region_datos(mm, region, pos[region], valor, t_word, NO);
            pos[region] += sizeof (word);
            break;
        case t_dword:
            if (!esta_alineada_dir_dword(pos[region]))
                pos[region] = mem_direccion_alineada(pos[region], sizeof(dword));
            if (pending_label) {
            	ts_inserta_etiqueta(ts, etiqueta, pos[region], r_data);
            	pending_label=0;
            }
            valor.int_d = (dword) (yyvsp[0].ivalue);
            mem_escribe_region_datos(mm, region, pos[region], valor, t_dword, NO);
            pos[region] += sizeof (dword);
            break;
        default:
            yyerror("PÁNICO");
            break;
    } /* endswitch */

    if (pos[region] > tam_region[region]) {
    	sprintf(mensaje, "Excedido el tamaño de la memoria: %s", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
}
#line 2237 "input.yacc.c"
    break;

  case 37: /* r_lista_enteros: T_INT_LIT $@13 r_lista_enteros_cont  */
#line 477 "input.yacc.y"
{}
#line 2243 "input.yacc.c"
    break;

  case 38: /* r_lista_enteros_cont: ',' r_lista_enteros  */
#line 482 "input.yacc.y"
{}
#line 2249 "input.yacc.c"
    break;

  case 39: /* r_lista_enteros_cont: %empty  */
#line 485 "input.yacc.y"
{
}
#line 2256 "input.yacc.c"
    break;

  case 40: /* $@14: %empty  */
#line 491 "input.yacc.y"
{
    switch (tipo) {
        case t_float:
            if (!esta_alineada_dir_float(pos[region]))
                pos[region] = mem_direccion_alineada(pos[region], sizeof(float));
            if (pending_label) {
            	ts_inserta_etiqueta(ts, etiqueta, pos[region], r_data);
            	pending_label=0;
            }
            valor.fp_s = (float) (yyvsp[0].fvalue);
            mem_escribe_region_datos(mm, region, pos[region], valor, t_float, NO);
            pos[region] += sizeof (float);
            break;
        case t_double:
            if (!esta_alineada_dir_double(pos[region]))
                pos[region] = mem_direccion_alineada(pos[region], sizeof(double));
            if (pending_label) {
            	ts_inserta_etiqueta(ts, etiqueta, pos[region], r_data);
            	pending_label=0;
            }
            valor.fp_d = (double) (yyvsp[0].fvalue);
            mem_escribe_region_datos(mm, region, pos[region], valor, t_double, NO);
            pos[region] += sizeof (double);
            break;
        default:
            yyerror("PÁNICO");
            break;
    } /* endswitch */

    if (pos[region] > tam_region[region]) {
    	sprintf(mensaje, "Excedido el tamaño de la memoria: %s", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
}
#line 2295 "input.yacc.c"
    break;

  case 41: /* r_lista_reales: T_FP_LIT $@14 r_lista_reales_cont  */
#line 527 "input.yacc.y"
{}
#line 2301 "input.yacc.c"
    break;

  case 42: /* $@15: %empty  */
#line 530 "input.yacc.y"
{
    switch (tipo) {
        case t_float:
            if (!esta_alineada_dir_float(pos[region]))
                pos[region] = mem_direccion_alineada(pos[region], sizeof(float));
            if (pending_label) {
            	ts_inserta_etiqueta(ts, etiqueta, pos[region], r_data);
            	pending_label=0;
            }
	    valor.fp_s = (float) (yyvsp[0].ivalue);
	    mem_escribe_region_datos(mm, region, pos[region], valor, t_float, NO);
            pos[region] += sizeof (float);
            break;
        case t_double:
            if (!esta_alineada_dir_double(pos[region]))
                pos[region] = mem_direccion_alineada(pos[region], sizeof(double));
            if (pending_label) {
            	ts_inserta_etiqueta(ts, etiqueta, pos[region], r_data);
            	pending_label=0;
            }
	    valor.fp_d = (double) (yyvsp[0].ivalue);
	    mem_escribe_region_datos(mm, region, pos[region], valor, t_double, NO);
            pos[region] += sizeof (double);
            break;
        default:
            yyerror("PÁNICO");
            break;
    } /* endswitch */

    if (pos[region] > tam_region[region]) {
    	sprintf(mensaje, "Excedido el tamaño de la memoria: %s", mm->regiones[region].nombre);
        yyerror(mensaje);
    }
}
#line 2340 "input.yacc.c"
    break;

  case 43: /* r_lista_reales: T_INT_LIT $@15 r_lista_reales_cont  */
#line 566 "input.yacc.y"
{}
#line 2346 "input.yacc.c"
    break;

  case 44: /* r_lista_reales_cont: ',' r_lista_reales  */
#line 571 "input.yacc.y"
{}
#line 2352 "input.yacc.c"
    break;

  case 45: /* r_lista_reales_cont: %empty  */
#line 574 "input.yacc.y"
{
}
#line 2359 "input.yacc.c"
    break;

  case 46: /* r_offset: '+' T_INT_LIT  */
#line 580 "input.yacc.y"
{
    (yyval.ivalue) = (yyvsp[0].ivalue);
}
#line 2367 "input.yacc.c"
    break;

  case 47: /* r_offset: '-' T_INT_LIT  */
#line 586 "input.yacc.y"
{
    (yyval.ivalue) = - ((yyvsp[0].ivalue));
}
#line 2375 "input.yacc.c"
    break;

  case 48: /* r_offset: T_INT_LIT  */
#line 592 "input.yacc.y"
{
    if ((yyvsp[0].ivalue) >= 0) {
      yyerror("Syntax error. Se esperaba el operador aritmético '+' o '-' o el separador ','.");
    }
    (yyval.ivalue) = (yyvsp[0].ivalue);
}
#line 2386 "input.yacc.c"
    break;

  case 49: /* r_offset: %empty  */
#line 600 "input.yacc.y"
{
    (yyval.ivalue) = 0;
}
#line 2394 "input.yacc.c"
    break;

  case 50: /* r_indice_ireg: REG_INT '='  */
#line 607 "input.yacc.y"
{
    idx_ireg = (yyvsp[-1].ivalue);
}
#line 2402 "input.yacc.c"
    break;

  case 51: /* r_indice_ireg: %empty  */
#line 611 "input.yacc.y"
  {}
#line 2408 "input.yacc.c"
    break;

  case 52: /* $@16: %empty  */
#line 616 "input.yacc.y"
{
    if (idx_ireg >= TAM_REGISTROS)
        yyerror("Excedido el numero de registros");
    if (idx_ireg < 0) {
        /*
        Es un registro en formato xnn
        El analizador lexico nos lo devuelve con un valor negativo para distinguirlo
        Hay que regenerar el valor correcto
        */
        idx_ireg  += TAM_REGISTROS;
    }
    escribe_int_reg(idx_ireg, (dword) (yyvsp[0].ivalue), t_dword, NO);
    idx_ireg++;}
#line 2426 "input.yacc.c"
    break;

  case 53: /* r_valores_enteros: r_indice_ireg T_INT_LIT $@16 r_valores_enteros_cont  */
#line 631 "input.yacc.y"
{}
#line 2432 "input.yacc.c"
    break;

  case 54: /* $@17: %empty  */
#line 635 "input.yacc.y"
{
    if (idx_ireg >= TAM_REGISTROS)
        yyerror("Excedido el numero de registros");
    ts_inserta_direccion(ts, (yyvsp[-1].cvalue), idx_ireg, (yyvsp[0].ivalue), DIR_REG);
    idx_ireg++;}
#line 2442 "input.yacc.c"
    break;

  case 55: /* r_valores_enteros: r_indice_ireg T_ALPHANUM r_offset $@17 r_valores_enteros_cont  */
#line 641 "input.yacc.y"
{}
#line 2448 "input.yacc.c"
    break;

  case 56: /* r_valores_enteros_cont: ',' r_valores_enteros  */
#line 646 "input.yacc.y"
{}
#line 2454 "input.yacc.c"
    break;

  case 57: /* r_valores_enteros_cont: %empty  */
#line 649 "input.yacc.y"
{
}
#line 2461 "input.yacc.c"
    break;

  case 58: /* r_indice_fpreg: REG_FP '='  */
#line 655 "input.yacc.y"
{
    idx_fpreg = (yyvsp[-1].ivalue);
}
#line 2469 "input.yacc.c"
    break;

  case 59: /* r_indice_fpreg: %empty  */
#line 659 "input.yacc.y"
  {}
#line 2475 "input.yacc.c"
    break;

  case 60: /* $@18: %empty  */
#line 664 "input.yacc.y"
{
    if (idx_fpreg >= TAM_REGISTROS)
        yyerror("Excedido el numero de registros");
   if (idx_fpreg < 0) {
        /*
        Es un registro en formato xnn
        El analizador lexico nos lo devuelve con un valor negativo para distinguirlo
        Hay que regenerar el valor correcto
        */
        idx_fpreg  += TAM_REGISTROS;
    }

    reg_fp.fp_d= (double) (yyvsp[0].fvalue);
    escribe_fp_reg(idx_fpreg, reg_fp, t_double, NO);
    idx_fpreg++;
    }
#line 2496 "input.yacc.c"
    break;

  case 61: /* r_valores_fp: r_indice_fpreg T_FP_LIT $@18 r_valores_fp_cont  */
#line 681 "input.yacc.y"
{}
#line 2502 "input.yacc.c"
    break;

  case 62: /* r_valores_fp_cont: ',' r_valores_fp  */
#line 686 "input.yacc.y"
{}
#line 2508 "input.yacc.c"
    break;

  case 63: /* r_valores_fp_cont: %empty  */
#line 689 "input.yacc.y"
{
}
#line 2515 "input.yacc.c"
    break;

  case 64: /* r_valores_btb: T_ALPHANUM r_offset '&' T_INT_LIT '&' T_ALPHANUM r_offset  */
#line 695 "input.yacc.y"
{
   if (idx_btb >= TAM_BUFFER_PREDIC)
      yyerror("Excedido el número de entradas en la BTB");

   ts_inserta_direccion(ts, (yyvsp[-6].cvalue), idx_btb, (yyvsp[-5].ivalue), DIR_BTB_PC);
   BTB[idx_btb].orden = idx_btb+1;
   BTB[idx_btb].estado = (yyvsp[-3].ivalue);
   ts_inserta_direccion(ts, (yyvsp[-1].cvalue), idx_btb, (yyvsp[0].ivalue), DIR_BTB_DEST);

   idx_btb++;
   }
#line 2531 "input.yacc.c"
    break;

  case 65: /* r_inmediato_disp: T_INT_LIT  */
#line 710 "input.yacc.y"
{
    // sprintf(desplazamiento, F_DWORD, $1);
    desplazamiento[0] = '\0';
    (yyval.ivalue) = (yyvsp[0].ivalue);}
#line 2540 "input.yacc.c"
    break;

  case 66: /* r_inmediato_disp: T_ALPHANUM  */
#line 716 "input.yacc.y"
{
    hay_valor=0;
    // Por omisión usa %%disp()
    if (f_macros)
        sprintf(desplazamiento,"%%disp(%s)",(yyvsp[0].cvalue));
    else
        strcpy(desplazamiento, (yyvsp[0].cvalue));
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], DATA_REL_LO_12, tr_data);
    (yyval.ivalue) = 0; // Referencia por resolver
    }
#line 2555 "input.yacc.c"
    break;

  case 67: /* r_inmediato_disp: M_HI '[' T_ALPHANUM ']'  */
#line 728 "input.yacc.y"
{
    hay_valor=0;
    sprintf(desplazamiento,"%%hi(%s)",(yyvsp[-1].cvalue));
    ts_inserta_referencia(ts, (yyvsp[-1].cvalue), region, pos[region], ABS_HI_20, tr_data);
    (yyval.ivalue) = 0; // Referencia por resolver
    }
#line 2566 "input.yacc.c"
    break;

  case 68: /* r_inmediato_disp: M_LO '[' T_ALPHANUM ']'  */
#line 736 "input.yacc.y"
{
    hay_valor=0;
    sprintf(desplazamiento,"%%lo(%s)",(yyvsp[-1].cvalue));
    ts_inserta_referencia(ts, (yyvsp[-1].cvalue), region, pos[region], ABS_LO_12, tr_data);
    (yyval.ivalue) = 0; // Referencia por resolver
    }
#line 2577 "input.yacc.c"
    break;

  case 69: /* r_inmediato_disp: M_DISP '[' T_ALPHANUM ']'  */
#line 744 "input.yacc.y"
{
    hay_valor=0;
    sprintf(desplazamiento,"%%disp(%s)",(yyvsp[-1].cvalue));
    ts_inserta_referencia(ts, (yyvsp[-1].cvalue), region, pos[region], DATA_REL_LO_12, tr_data);
    (yyval.ivalue) = 0; // Referencia por resolver
    }
#line 2588 "input.yacc.c"
    break;

  case 70: /* r_inmediato_hi: T_INT_LIT  */
#line 754 "input.yacc.y"
{
    sprintf(desplazamiento, F_DWORD, (yyvsp[0].ivalue));
    (yyval.ivalue) = (yyvsp[0].ivalue);}
#line 2596 "input.yacc.c"
    break;

  case 71: /* r_inmediato_hi: T_ALPHANUM  */
#line 759 "input.yacc.y"
{
    // Por omisión usa %%hi()
    hay_valor=0;
    if (f_macros)
        sprintf(desplazamiento,"%%hi(%s)",(yyvsp[0].cvalue));
    else
        strcpy(desplazamiento, (yyvsp[0].cvalue));
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], ABS_HI_20, tr_data);
    (yyval.ivalue) = 0; // Referencia por resolver
    }
#line 2611 "input.yacc.c"
    break;

  case 72: /* r_inmediato_hi: M_HI '[' T_ALPHANUM ']'  */
#line 771 "input.yacc.y"
{
    hay_valor=0;
    sprintf(desplazamiento,"%%hi(%s)",(yyvsp[-1].cvalue));
    ts_inserta_referencia(ts, (yyvsp[-1].cvalue), region, pos[region], ABS_HI_20, tr_data);
    (yyval.ivalue) = 0; // Referencia por resolver
    }
#line 2622 "input.yacc.c"
    break;

  case 73: /* r_inmediato_hi: M_LO '[' T_ALPHANUM ']'  */
#line 779 "input.yacc.y"
{
    hay_valor=0;
    sprintf(desplazamiento,"%%lo(%s)",(yyvsp[-1].cvalue));
    ts_inserta_referencia(ts, (yyvsp[-1].cvalue), region, pos[region], ABS_LO_12, tr_data);
    (yyval.ivalue) = 0; // Referencia por resolver
    }
#line 2633 "input.yacc.c"
    break;

  case 74: /* r_inmediato_hi: M_DISP '[' T_ALPHANUM ']'  */
#line 787 "input.yacc.y"
{
    hay_valor=0;
    sprintf(desplazamiento,"%%disp(%s)",(yyvsp[-1].cvalue));
    ts_inserta_referencia(ts, (yyvsp[-1].cvalue), region, pos[region], DATA_REL_LO_12, tr_data);
    (yyval.ivalue) = 0; // Referencia por resolver
    }
#line 2644 "input.yacc.c"
    break;

  case 75: /* r_posicion: T_INT_LIT  */
#line 797 "input.yacc.y"
{
    (yyval.ivalue) = (yyvsp[0].ivalue);
}
#line 2652 "input.yacc.c"
    break;

  case 76: /* r_posicion: T_ALPHANUM  */
#line 802 "input.yacc.y"
{
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], PC_REL_LO_12_SHIFT, tr_code);
    (yyval.ivalue) = 0; // Referencia por resolver
}
#line 2661 "input.yacc.c"
    break;

  case 77: /* r_posicion_larga: T_INT_LIT  */
#line 810 "input.yacc.y"
{
    (yyval.ivalue) = (yyvsp[0].ivalue);
}
#line 2669 "input.yacc.c"
    break;

  case 78: /* r_posicion_larga: T_ALPHANUM  */
#line 815 "input.yacc.y"
{
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], PC_REL_LO_20_SHIFT, tr_code);
    (yyval.ivalue) = 0; // Referencia por resolver
}
#line 2678 "input.yacc.c"
    break;

  case 79: /* r_instruccion: FORM_PSEUDO_NOP  */
#line 824 "input.yacc.y"
{
    nueva_instruccion(RV32I_ADDI, FormatoI, 0, 0, 0, 0, 0, "", 0);
}
#line 2686 "input.yacc.c"
    break;

  case 80: /* r_instruccion: FORM_PSEUDO_RR REG_INT ',' REG_INT  */
#line 830 "input.yacc.y"
{
    switch ((yyvsp[-3].ivalue)) {
    	case PSEUDO_NEG:
                nueva_instruccion(RV32I_SUB, FormatoR, (yyvsp[-2].ivalue), _zero, (yyvsp[0].ivalue), 0, 0, "", 0);
        	break;
 	case PSEUDO_NEGW:
                nueva_instruccion(RV64I_SUBW, FormatoR, (yyvsp[-2].ivalue), _zero, (yyvsp[0].ivalue), 0, 0, "", 0);
    		break;
    	case PSEUDO_SNEZ:
    		nueva_instruccion(RV32I_SLTU, FormatoR, (yyvsp[-2].ivalue), _zero, (yyvsp[0].ivalue), 0, 0, "", 0);
    		break;
    	case PSEUDO_SLTZ:
                nueva_instruccion(RV32I_SLT, FormatoR, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), _zero, 0, 0, "", 0);
    		break;
    	case PSEUDO_SGTZ:
                nueva_instruccion(RV32I_SLT, FormatoR, (yyvsp[-2].ivalue), _zero, (yyvsp[0].ivalue), 0, 0, "", 0);
    		break;
    }
}
#line 2710 "input.yacc.c"
    break;

  case 81: /* r_instruccion: FORM_PSEUDO_BRA REG_INT ',' r_posicion  */
#line 852 "input.yacc.y"
{
     switch ((yyvsp[-3].ivalue)) {
    	case PSEUDO_BEQZ:
                nueva_instruccion(RV32I_BEQ, FormatoB, 0, (yyvsp[-2].ivalue), 0, 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
    		break;
    	case PSEUDO_BNEZ:
                nueva_instruccion(RV32I_BNE, FormatoB, 0, (yyvsp[-2].ivalue), 0, 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
    		//instruccion.codop = RV32I_BNE;
    		break;
    	case PSEUDO_BLEZ:
                nueva_instruccion(RV32I_BGE, FormatoB, 0, 0, (yyvsp[-2].ivalue), 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
    		break;
    	case PSEUDO_BGEZ:
                nueva_instruccion(RV32I_BGE, FormatoB, 0, (yyvsp[-2].ivalue), 0, 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
    		break;
    	case PSEUDO_BLTZ:
                nueva_instruccion(RV32I_BLT, FormatoB, 0, (yyvsp[-2].ivalue), 0, 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
                break;
         case PSEUDO_BGTZ:
                nueva_instruccion(RV32I_BLT, FormatoB, 0, 0, (yyvsp[-2].ivalue), 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
    		break;
    }
}
#line 2738 "input.yacc.c"
    break;

  case 82: /* r_instruccion: FORM_PSEUDO_FF REG_FP ',' REG_FP  */
#line 878 "input.yacc.y"
{
    switch ((yyvsp[-3].ivalue)) {
    	case PSEUDO_FMV_S:
                nueva_instruccion(RV32F_FSGNJ_S, FormatoR, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), (yyvsp[0].ivalue), 0, 0, "", 0);
    		break;
    	case PSEUDO_FABS_S:
                nueva_instruccion(RV32F_FSGNJX_S, FormatoR, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), (yyvsp[0].ivalue), 0, 0, "", 0);
    		break;
    	case PSEUDO_FNEG_S:
                nueva_instruccion(RV32F_FSGNJN_S, FormatoR, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), (yyvsp[0].ivalue), 0, 0, "", 0);
    		break;
    	case PSEUDO_FMV_D:
                nueva_instruccion(RV32D_FSGNJ_D, FormatoR, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), (yyvsp[0].ivalue), 0, 0, "", 0);
    		break;
    	case PSEUDO_FABS_D:
                nueva_instruccion(RV32D_FSGNJX_D, FormatoR, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), (yyvsp[0].ivalue), 0, 0, "", 0);
    		break;
    	case PSEUDO_FNEG_D:
                nueva_instruccion(RV32D_FSGNJN_D, FormatoR, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), (yyvsp[0].ivalue), 0, 0, "", 0);
 	}
}
#line 2764 "input.yacc.c"
    break;

  case 83: /* r_instruccion: FORM_PSEUDO_J r_posicion_larga  */
#line 902 "input.yacc.y"
{
    nueva_instruccion(RV32I_JAL, FormatoJ, 0, 0, 0, 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
}
#line 2772 "input.yacc.c"
    break;

  case 84: /* r_instruccion: FORM_PSEUDO_JR REG_INT  */
#line 909 "input.yacc.y"
{
    nueva_instruccion(RV32I_JALR, FormatoI, (yyvsp[0].ivalue), 0, 0, 0, 0, desplazamiento, 0);
}
#line 2780 "input.yacc.c"
    break;

  case 85: /* r_instruccion: FORM_PSEUDO_RET  */
#line 915 "input.yacc.y"
{
    nueva_instruccion(RV32I_JALR, FormatoI, 0, 1, 0, 0, 0, "", 0);
}
#line 2788 "input.yacc.c"
    break;

  case 86: /* r_instruccion: FORM_PSEUDO_BRRA REG_INT ',' REG_INT ',' r_posicion  */
#line 942 "input.yacc.y"
{
    bzero(&instruccion, sizeof (instruccion_t));
    switch ((yyvsp[-5].ivalue)) {
    	case PSEUDO_BGT:
            nueva_instruccion(RV32I_BLT, FormatoB, 0, (yyvsp[-2].ivalue), (yyvsp[-4].ivalue), 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
            break;
    	case PSEUDO_BLE:
            nueva_instruccion(RV32I_BGE, FormatoB, 0, (yyvsp[-2].ivalue), (yyvsp[-4].ivalue), 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
            break;
    	case PSEUDO_BGTU:
            break;
            nueva_instruccion(RV32I_BLTU, FormatoB, 0, (yyvsp[-2].ivalue), (yyvsp[-4].ivalue), 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
    	case PSEUDO_BLEU:
            nueva_instruccion(RV32I_BGEU, FormatoB, 0, (yyvsp[-2].ivalue), (yyvsp[-4].ivalue), 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
            break;
    }
}
#line 2810 "input.yacc.c"
    break;

  case 87: /* r_instruccion: FORM_PSEUDO_RRi REG_INT ',' REG_INT  */
#line 962 "input.yacc.y"
{
    bzero(&instruccion, sizeof (instruccion_t));
    switch ((yyvsp[-3].ivalue)) {
    	case PSEUDO_MV:
            nueva_instruccion(RV32I_ADDI, FormatoI, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), 0, 0, 0, "", 0);
            break;
    	case PSEUDO_NOT:
            nueva_instruccion(RV32I_XORI, FormatoI, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), 0, 0, -1, "", 0);
            break;
	case PSEUDO_SEXT_W:
            nueva_instruccion(RV64I_ADDIW, FormatoI, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), 0, 0, 0, "", 0);
            break;
 	case PSEUDO_SEQZ:
            nueva_instruccion(RV32I_SLTIU, FormatoI, (yyvsp[-2].ivalue), (yyvsp[0].ivalue), 0, 0, 1, "", 0);
            break;
    }
}
#line 2832 "input.yacc.c"
    break;

  case 88: /* r_instruccion: FORM_PSEUDO_LI REG_INT ',' T_INT_LIT  */
#line 984 "input.yacc.y"
{
    if (((yyvsp[0].ivalue) >= MIN_IMM) && ((yyvsp[0].ivalue) < MAX_IMM)) {
	sprintf(desplazamiento, F_DWORD, (yyvsp[0].ivalue));
	nueva_instruccion(RV32I_ORI, FormatoI, (yyvsp[-2].ivalue), 0, 0, 0, (yyvsp[0].ivalue), "", 0);
    } else {
       udword valor;

       sprintf(desplazamiento,"%%hi[" F_DWORD "]",valor);	
       valor = (yyvsp[0].ivalue) >> 12;
       valor &= 0xFFFFF;
       nueva_instruccion(RV32I_LUI, FormatoU, (yyvsp[-2].ivalue), 0, 0, 0, valor, desplazamiento, 0);

       sprintf(desplazamiento,"%%lo[" F_DWORD "]",(yyvsp[0].ivalue));
       valor = (yyvsp[0].ivalue);
       valor &= 0xFFF;
       nueva_instruccion(RV32I_ORI, FormatoI, (yyvsp[-2].ivalue), (yyvsp[-2].ivalue), 0, 0, valor, desplazamiento, 0);
    }
}
#line 2855 "input.yacc.c"
    break;

  case 89: /* r_instruccion: FORM_PSEUDO_LLA REG_INT ',' T_ALPHANUM  */
#line 1006 "input.yacc.y"
{
    sprintf(desplazamiento,"%%pcrel_hi(%s)",(yyvsp[0].cvalue));
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], PC_REL_HI_20, tr_data);
    nueva_instruccion(RV32I_AUIPC, FormatoU, (yyvsp[-2].ivalue), 0, 0, 0, 0, desplazamiento, 0);

    sprintf(desplazamiento,"%%pcrel_lo(%s)",(yyvsp[0].cvalue));
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], PC_REL_LO_12, tr_data);
    nueva_instruccion(RV32I_ADDI, FormatoI, (yyvsp[-2].ivalue), (yyvsp[-2].ivalue), 0, 0, 0, desplazamiento, 0);
}
#line 2869 "input.yacc.c"
    break;

  case 90: /* r_instruccion: FORM_PSEUDO_CALL T_ALPHANUM  */
#line 1019 "input.yacc.y"
{
    sprintf(desplazamiento,"%%pcrel_hi(%s)",(yyvsp[0].cvalue));
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], PC_REL_HI_20, tr_data);
    nueva_instruccion(RV32I_AUIPC, FormatoU, 1, 0, 0, 0, 0, desplazamiento, 0);

    sprintf(desplazamiento,"%%pcrel_lo(%s)",(yyvsp[0].cvalue));
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], PC_REL_LO_12, tr_data);
    nueva_instruccion(RV32I_JALR, FormatoI, 1, 1, 0, 0, 0, desplazamiento, 0);
}
#line 2883 "input.yacc.c"
    break;

  case 91: /* r_instruccion: FORM_PSEUDO_TAIL T_ALPHANUM  */
#line 1031 "input.yacc.y"
{
    sprintf(desplazamiento,"%%pcrel_hi(%s)",(yyvsp[0].cvalue));
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], PC_REL_HI_20, tr_data);
    nueva_instruccion(RV32I_AUIPC, FormatoU, 6, 0, 0, 0, 0, desplazamiento, 0);

    sprintf(desplazamiento,"%%pcrel_lo(%s)",(yyvsp[0].cvalue));
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], PC_REL_LO_12, tr_data);
    nueva_instruccion(RV32I_JALR, FormatoI, 0, 6, 0, 0, 0, desplazamiento, 0);
}
#line 2897 "input.yacc.c"
    break;

  case 92: /* r_instruccion: FORM_PSEUDO_TRAP T_INT_LIT  */
#line 1043 "input.yacc.y"
{
    nueva_instruccion(RV32I_ORI, FormatoI, _a7, 0, 0, 0, 10, "", 0);
    nueva_instruccion(RV32I_ECALL, FormatoI, 0, 0, 0, 0, 0, "", 0);
}
#line 2906 "input.yacc.c"
    break;

  case 93: /* r_instruccion: FORM_INT_RRI REG_INT ',' REG_INT ',' r_inmediato_disp  */
#line 1051 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoI, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue), 0, 0, (yyvsp[0].ivalue), desplazamiento, 0);
}
#line 2914 "input.yacc.c"
    break;

  case 94: /* r_instruccion: FORM_INT_RRSH REG_INT ',' REG_INT ',' T_INT_LIT  */
#line 1057 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoIshift, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue), 0, 0, (yyvsp[0].ivalue), "", 0);
}
#line 2922 "input.yacc.c"
    break;

  case 95: /* r_instruccion: FORM_INT_RRR REG_INT ',' REG_INT ',' REG_INT  */
#line 1063 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoR, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue), (yyvsp[0].ivalue), 0, 0, "", 0);
}
#line 2930 "input.yacc.c"
    break;

  case 96: /* r_instruccion: FORM_INT_RI REG_INT ',' r_inmediato_hi  */
#line 1069 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-3].ivalue), FormatoU, (yyvsp[-2].ivalue), 0, 0, 0, (yyvsp[0].ivalue), desplazamiento, 0);
}
#line 2938 "input.yacc.c"
    break;

  case 97: /* r_instruccion: FORM_INT_RA REG_INT ',' T_INT_LIT  */
#line 1075 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-3].ivalue), FormatoU, (yyvsp[-2].ivalue), 0, 0, 0, (yyvsp[0].ivalue), desplazamiento, 0);
}
#line 2946 "input.yacc.c"
    break;

  case 98: /* r_instruccion: FORM_J_RA REG_INT ',' r_posicion_larga  */
#line 1081 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-3].ivalue), FormatoJ, (yyvsp[-2].ivalue), 0, 0, 0, ((yyvsp[0].ivalue) - pos[region])/2, desplazamiento, 0);
}
#line 2954 "input.yacc.c"
    break;

  case 99: /* r_instruccion: FORM_J_RRA REG_INT ',' REG_INT ',' r_posicion  */
#line 1087 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoI, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue), 0, 0, (yyvsp[0].ivalue), desplazamiento, 0);
}
#line 2962 "input.yacc.c"
    break;

  case 100: /* r_instruccion: FORM_INT_B_RRA REG_INT ',' REG_INT ',' r_posicion  */
#line 1093 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoB, 0, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue), 0, (yyvsp[0].ivalue) - pos[region], desplazamiento, 0);
}
#line 2970 "input.yacc.c"
    break;

  case 101: /* r_instruccion: FORM_INT_L_RSB REG_INT ',' r_inmediato_disp '(' REG_INT ')'  */
#line 1099 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-6].ivalue), FormatoI, (yyvsp[-5].ivalue), (yyvsp[-1].ivalue), 0, 0, (yyvsp[-3].ivalue), desplazamiento, 0);
}
#line 2978 "input.yacc.c"
    break;

  case 102: /* r_instruccion: FORM_INT_L_RSB REG_INT ',' T_ALPHANUM  */
#line 1107 "input.yacc.y"
{
    sprintf(desplazamiento,"%%pcrel_hi(%s)",(yyvsp[0].cvalue));
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], PC_REL_HI_20, tr_data);
    nueva_instruccion(RV32I_AUIPC, FormatoU, (yyvsp[-2].ivalue), 0, 0, 0, 0, desplazamiento, 0);

    sprintf(desplazamiento,"%%pcrel_lo(%s)",(yyvsp[0].cvalue));
    ts_inserta_referencia(ts, (yyvsp[0].cvalue), region, pos[region], PC_REL_LO_12, tr_data);
    nueva_instruccion((yyvsp[-3].ivalue), FormatoI, (yyvsp[-2].ivalue), (yyvsp[-2].ivalue), 0, 0, 0, desplazamiento, 0);
}
#line 2992 "input.yacc.c"
    break;

  case 103: /* r_instruccion: FORM_INT_S_RSB REG_INT ',' r_inmediato_disp '(' REG_INT ')'  */
#line 1120 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-6].ivalue), FormatoS, 0, (yyvsp[-1].ivalue), (yyvsp[-5].ivalue), 0, (yyvsp[-3].ivalue), desplazamiento, 0 );
}
#line 3000 "input.yacc.c"
    break;

  case 104: /* r_instruccion: FORM_INT_S_RSB REG_INT ',' T_ALPHANUM ',' REG_INT  */
#line 1127 "input.yacc.y"
{
    sprintf(desplazamiento,"%%pcrel_hi(%s)",(yyvsp[-2].cvalue));
    ts_inserta_referencia(ts, (yyvsp[-2].cvalue), region, pos[region], PC_REL_HI_20, tr_data);
    nueva_instruccion(RV32I_AUIPC, FormatoU, (yyvsp[0].ivalue), 0, 0, 0, 0, desplazamiento, 0);

    sprintf(desplazamiento,"%%pcrel_lo(%s)",(yyvsp[-2].cvalue));
    ts_inserta_referencia(ts, (yyvsp[-2].cvalue), region, pos[region], PC_REL_LO_12, tr_data);
    nueva_instruccion((yyvsp[-5].ivalue), FormatoS, 0, (yyvsp[0].ivalue), (yyvsp[-4].ivalue), 0, 0, desplazamiento, 0);
}
#line 3014 "input.yacc.c"
    break;

  case 105: /* r_instruccion: FORM_INT_ECALL  */
#line 1139 "input.yacc.y"
{
    nueva_instruccion((yyvsp[0].ivalue), FormatoI, 0, 0, 0, 0, FUNC7((yyvsp[0].ivalue)), "", 0);
}
#line 3022 "input.yacc.c"
    break;

  case 106: /* r_instruccion: FORM_INT_EBREAK  */
#line 1145 "input.yacc.y"
{
   nueva_instruccion((yyvsp[0].ivalue), FormatoI, 0, 0, 0, 0, FUNC7((yyvsp[0].ivalue)), "", 0);
}
#line 3030 "input.yacc.c"
    break;

  case 107: /* r_instruccion: FORM_FP_FFF REG_FP ',' REG_FP ',' REG_FP  */
#line 1151 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoR, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue), (yyvsp[0].ivalue), 0, 0, "", 0);
}
#line 3038 "input.yacc.c"
    break;

  case 108: /* r_instruccion: FORM_FP_FFFr REG_FP ',' REG_FP ',' REG_FP ',' FR_MODE  */
#line 1157 "input.yacc.y"
{
      nueva_instruccion((yyvsp[-7].ivalue), FormatoR3rm, (yyvsp[-6].ivalue), (yyvsp[-4].ivalue), (yyvsp[-2].ivalue), 0, 0, "", Redondeo_id((yyvsp[0].cvalue)));
}
#line 3046 "input.yacc.c"
    break;

  case 109: /* r_instruccion: FORM_FP_FFFr REG_FP ',' REG_FP ',' REG_FP  */
#line 1163 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoR3rm, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue), (yyvsp[0].ivalue), 0, 0, "", DYNAMIC_RM);
}
#line 3054 "input.yacc.c"
    break;

  case 110: /* r_instruccion: FORM_FP_FFFFr REG_FP ',' REG_FP ',' REG_FP ',' REG_FP ',' FR_MODE  */
#line 1170 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-9].ivalue), FormatoR4rm, (yyvsp[-8].ivalue), (yyvsp[-6].ivalue), (yyvsp[-4].ivalue), (yyvsp[-2].ivalue), 0, "", Redondeo_id((yyvsp[0].cvalue)));
}
#line 3062 "input.yacc.c"
    break;

  case 111: /* r_instruccion: FORM_FP_FFFFr REG_FP ',' REG_FP ',' REG_FP ',' REG_FP  */
#line 1176 "input.yacc.y"
{    
    nueva_instruccion((yyvsp[-7].ivalue), FormatoR4rm, (yyvsp[-6].ivalue), (yyvsp[-4].ivalue), (yyvsp[-2].ivalue), (yyvsp[0].ivalue), 0, "", DYNAMIC_RM);
}
#line 3070 "input.yacc.c"
    break;

  case 112: /* r_instruccion: FORM_FP_FFr REG_FP ',' REG_FP ',' FR_MODE  */
#line 1183 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoR2rm, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue),  FUNC5((yyvsp[-5].ivalue)), 0, 0, "", Redondeo_id((yyvsp[0].cvalue)));
}
#line 3078 "input.yacc.c"
    break;

  case 113: /* r_instruccion: FORM_FP_FFr REG_FP ',' REG_FP  */
#line 1189 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-3].ivalue), FormatoR2rm, (yyvsp[-2].ivalue), (yyvsp[0].ivalue),  FUNC5((yyvsp[-3].ivalue)), 0, 0, "", DYNAMIC_RM);
}
#line 3086 "input.yacc.c"
    break;

  case 114: /* r_instruccion: FORM_FP_FF REG_FP ',' REG_FP  */
#line 1195 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-3].ivalue), FormatoR, (yyvsp[-2].ivalue), (yyvsp[0].ivalue),  FUNC5((yyvsp[-3].ivalue)), 0, 0, "", 0);
}
#line 3094 "input.yacc.c"
    break;

  case 115: /* r_instruccion: FORM_FP_FRr REG_FP ',' REG_INT ',' FR_MODE  */
#line 1201 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoR2rm, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue),  FUNC5((yyvsp[-5].ivalue)), 0, 0, "", Redondeo_id((yyvsp[0].cvalue)));
}
#line 3102 "input.yacc.c"
    break;

  case 116: /* r_instruccion: FORM_FP_FRr REG_FP ',' REG_INT  */
#line 1207 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-3].ivalue), FormatoR2rm, (yyvsp[-2].ivalue), (yyvsp[0].ivalue),  FUNC5((yyvsp[-3].ivalue)), 0, 0, "",DYNAMIC_RM);
}
#line 3110 "input.yacc.c"
    break;

  case 117: /* r_instruccion: FORM_FP_FR REG_FP ',' REG_INT  */
#line 1213 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-3].ivalue), FormatoR, (yyvsp[-2].ivalue), (yyvsp[0].ivalue),  FUNC5((yyvsp[-3].ivalue)), 0, 0, "",0);
}
#line 3118 "input.yacc.c"
    break;

  case 118: /* r_instruccion: FORM_FP_RFr REG_INT ',' REG_FP ',' FR_MODE  */
#line 1219 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoR2rm, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue),  FUNC5((yyvsp[-5].ivalue)), 0, 0, "",  Redondeo_id((yyvsp[0].cvalue))); 
}
#line 3126 "input.yacc.c"
    break;

  case 119: /* r_instruccion: FORM_FP_RFr REG_INT ',' REG_FP  */
#line 1225 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-3].ivalue), FormatoR2rm, (yyvsp[-2].ivalue), (yyvsp[0].ivalue),  FUNC5((yyvsp[-3].ivalue)), 0, 0, "",  DYNAMIC_RM); 
}
#line 3134 "input.yacc.c"
    break;

  case 120: /* r_instruccion: FORM_FP_RF REG_INT ',' REG_FP  */
#line 1231 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-3].ivalue), FormatoR, (yyvsp[-2].ivalue), (yyvsp[0].ivalue),  FUNC5((yyvsp[-3].ivalue)), 0, 0, "",  0); 
}
#line 3142 "input.yacc.c"
    break;

  case 121: /* r_instruccion: FORM_FP_RFF REG_INT ',' REG_FP ',' REG_FP  */
#line 1237 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoR, (yyvsp[-4].ivalue), (yyvsp[-2].ivalue),  (yyvsp[0].ivalue), 0, 0, "",  0); 
}
#line 3150 "input.yacc.c"
    break;

  case 122: /* r_instruccion: FORM_FP_L_FSB REG_FP ',' r_inmediato_disp '(' REG_INT ')'  */
#line 1243 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-6].ivalue), FormatoI, (yyvsp[-5].ivalue), (yyvsp[-1].ivalue), 0, 0, (yyvsp[-3].ivalue), desplazamiento, 0);
}
#line 3158 "input.yacc.c"
    break;

  case 123: /* r_instruccion: FORM_FP_L_FSB REG_FP ',' T_INT_LIT  */
#line 1251 "input.yacc.y"
{
    symbol = (yyvsp[0].ivalue) - pos[region];
    nueva_instruccion(RV32I_AUIPC, FormatoU, (yyvsp[-2].ivalue), 0, 0, 0, symbol >> 12, desplazamiento, 0);
    nueva_instruccion((yyvsp[-3].ivalue), FormatoI, (yyvsp[-2].ivalue), (yyvsp[-2].ivalue), 0, 0, symbol & 0b111111111111, desplazamiento, 0);
}
#line 3168 "input.yacc.c"
    break;

  case 124: /* r_instruccion: FORM_FP_S_FSB REG_FP ',' r_inmediato_disp '(' REG_INT ')'  */
#line 1259 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-6].ivalue), FormatoS, 0, (yyvsp[-1].ivalue), (yyvsp[-5].ivalue), 0, (yyvsp[-3].ivalue), desplazamiento, 0 );
}
#line 3176 "input.yacc.c"
    break;

  case 125: /* r_instruccion: FORM_FP_S_FSB REG_FP ',' T_INT_LIT ',' REG_INT  */
#line 1267 "input.yacc.y"
{
    symbol = (yyvsp[-2].ivalue) - pos[region];
    nueva_instruccion(RV32I_AUIPC, FormatoU, (yyvsp[0].ivalue), 0, 0, 0, symbol >> 12, desplazamiento, 0);  
    nueva_instruccion((yyvsp[-5].ivalue), FormatoS, 0, (yyvsp[0].ivalue), (yyvsp[-4].ivalue), 0, symbol & 0b111111111111, desplazamiento, 0 );
}
#line 3186 "input.yacc.c"
    break;

  case 126: /* r_instruccion: FORM_INT_RB REG_INT ',' '(' REG_INT ')'  */
#line 1275 "input.yacc.y"
{
    nueva_instruccion((yyvsp[-5].ivalue), FormatoR, (yyvsp[-4].ivalue), (yyvsp[-1].ivalue), FUNC5((yyvsp[-5].ivalue)), 0, 0, "", 0);
}
#line 3194 "input.yacc.c"
    break;

  case 127: /* r_instruccion: FORM_INT_RRB REG_INT ',' REG_INT ',' '(' REG_INT ')'  */
#line 1282 "input.yacc.y"
{
   nueva_instruccion((yyvsp[-7].ivalue), FormatoR, (yyvsp[-6].ivalue), (yyvsp[-4].ivalue), (yyvsp[-1].ivalue), 0, 0, "", 0);
}
#line 3202 "input.yacc.c"
    break;


#line 3206 "input.yacc.c"

      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", YY_CAST (yysymbol_kind_t, yyr1[yyn]), &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;

  *++yyvsp = yyval;

  /* Now 'shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */
  {
    const int yylhs = yyr1[yyn] - YYNTOKENS;
    const int yyi = yypgoto[yylhs] + *yyssp;
    yystate = (0 <= yyi && yyi <= YYLAST && yycheck[yyi] == *yyssp
               ? yytable[yyi]
               : yydefgoto[yylhs]);
  }

  goto yynewstate;


/*--------------------------------------.
| yyerrlab -- here on detecting error.  |
`--------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYSYMBOL_YYEMPTY : YYTRANSLATE (yychar);
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
      {
        yypcontext_t yyctx
          = {yyssp, yytoken};
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = yysyntax_error (&yymsg_alloc, &yymsg, &yyctx);
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == -1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = YY_CAST (char *,
                             YYSTACK_ALLOC (YY_CAST (YYSIZE_T, yymsg_alloc)));
            if (yymsg)
              {
                yysyntax_error_status
                  = yysyntax_error (&yymsg_alloc, &yymsg, &yyctx);
                yymsgp = yymsg;
              }
            else
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = YYENOMEM;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == YYENOMEM)
          YYNOMEM;
      }
    }

  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

      if (yychar <= YYEOF)
        {
          /* Return failure if at end of input.  */
          if (yychar == YYEOF)
            YYABORT;
        }
      else
        {
          yydestruct ("Error: discarding",
                      yytoken, &yylval);
          yychar = YYEMPTY;
        }
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:
  /* Pacify compilers when the user code never invokes YYERROR and the
     label yyerrorlab therefore never appears in user code.  */
  if (0)
    YYERROR;
  ++yynerrs;

  /* Do not reclaim the symbols of the rule whose action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;      /* Each real token shifted decrements this.  */

  /* Pop stack until we find a state that shifts the error token.  */
  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
        {
          yyn += YYSYMBOL_YYerror;
          if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYSYMBOL_YYerror)
            {
              yyn = yytable[yyn];
              if (0 < yyn)
                break;
            }
        }

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
        YYABORT;


      yydestruct ("Error: popping",
                  YY_ACCESSING_SYMBOL (yystate), yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", YY_ACCESSING_SYMBOL (yyn), yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturnlab;


/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturnlab;


/*-----------------------------------------------------------.
| yyexhaustedlab -- YYNOMEM (memory exhaustion) comes here.  |
`-----------------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  goto yyreturnlab;


/*----------------------------------------------------------.
| yyreturnlab -- parsing is finished, clean up and return.  |
`----------------------------------------------------------*/
yyreturnlab:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule whose action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
                  YY_ACCESSING_SYMBOL (+*yyssp), yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
  return yyresult;
}

#line 1290 "input.yacc.y"


/*** Definición de código ***********************************************/

void nueva_instruccion(
    codop_t codop,
    formato_t tipo,
    int Rdestino,
    int Rfuente1,
    int Rfuente2,
    int Rfuente3,
    dword inmediato,
    char * etiqueta,
    int Redondeo
) {
    if (!es_region_codigo(region)) {
        yyerror("Se debe abrir una sección de código (v.g. .text) antes de insertar instrucciones");
    }

    bzero(&instruccion, sizeof (instruccion_t));

    instruccion.codop = codop;
    instruccion.tipo = tipo;
    instruccion.rd = (Rdestino < 0) ? Rdestino + TAM_REGISTROS : Rdestino;
    instruccion.rd_usa_abi = (Rdestino < 0) ? NO : SI;
    instruccion.rs1 = (Rfuente1 < 0) ? Rfuente1 + TAM_REGISTROS : Rfuente1;
    instruccion.rs1_usa_abi = (Rfuente1 < 0) ? NO : SI;
    instruccion.rs2 = (Rfuente2 < 0) ? Rfuente2 + TAM_REGISTROS : Rfuente2;
    instruccion.rs2_usa_abi = (Rfuente2 < 0) ? NO : SI;
    instruccion.rs3 = (Rfuente3 < 0) ? Rfuente3 + TAM_REGISTROS : Rfuente3;
    instruccion.rs3_usa_abi = (Rfuente3 < 0) ? NO : SI;
    instruccion.imm = inmediato;
    //printf("inmediato: %d %u %x\n", inmediato, inmediato, inmediato);
    instruccion.rm = Redondeo;
    //strcpy(instruccion.etiqueta, etiqueta);
    strcpy(instruccion.etiqueta, etiqueta);
    mem_escribe_region_instruc(mm, region, pos[region], instruccion);
    pos[region] += 4;
}

int yyerror
(
    const char *s
        ) {
    fprintf(stderr, "%s:%ld. Error: %s\n", ts->fichero, linenumber, s);
    exit(1);
} /* end yyerror */


void ensamblador (
    fichero_objeto_t * obj
      ) {

    FILE * f_input;

    /* Inicializamos variables, por si hacemos dos pasadas de ensamblador */
    linenumber = 1;
    hay_valor=0;
    hay_etiq=0;

    /* Si están inicializados en otro módulo no se pueden volver a inicializar */
    no_ireg = init_ireg;
    no_fpreg = init_fpreg;
    no_btb = init_btb;

    idx_ireg = 1;
    idx_fpreg = 0;
    idx_btb = 0;
    i = 0;

    mm = &obj->memoria;
    ts = &obj->ts;

    for (i= 0; i< num_regiones; i++) {
       pos[i] = 0;
    }
    region = r_invalid;

    f_input = fopen(obj->ts.fichero, "r");
    if (f_input == NULL) {
       fprintf(stderr, "Error abriendo el fichero '%s'.\n", obj->ts.fichero);
       exit(1);
    } /* endif */

    yyin = f_input;
    if (yyin == NULL) return;

    /*** Realiza el 'parsing' del fichero de entrada ***/

    yyparse();

    // Ajustando los límites de las regiones utilizadas por el objeto
    for (i= 0; i< num_regiones; i++) {
    	  mm->regiones[i].final = pos[i];
    }

    fclose(f_input);

} /* end ensamblador */


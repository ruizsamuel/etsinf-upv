/**************************************************************************
 *
 * Departamento de Informática de Sistema y Computadores (DISCA)
 * Universidad Politécnica de Valencia.
 *
 * Autor: Pedro López (plopez@disca.upv.es)
 *        Sergio Sáez (ssaez@disca.upv.es)
 *
 * File: main.c
 *
 * Description:
 *    Módulo principal del simulador
 *
 *************************************************************************/

#define main_C

/***** Includes ***********************************************/

#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <libgen.h>
#include <sys/stat.h>



/*** Definiciones Externas *************************************************/

#include "main.h"
#include "tipos.h"
#include "instrucciones.h"
#include "presentacion-txt.h"
#include "presentacion-html.h"
#include "riscv.h"
#include "riscv_fp.h"
#include "memoria.h"

/*** Definiciones **********************************************************/

#define SIMUL_NAME      "RiscV-m"
#define SIMUL_VERSION   "1.0"
#define SIMUL_DATE      "2023-09-01"

extern void ensamblador(fichero_objeto_t *obj);


extern int pos_datos;

#define init_ID_x(operador) \
    ID##_##operador.IR = inst_vacia; \
    ID##_##operador.iPC = -1; \
    ID##_##operador.orden = -1; \
    ID##_##operador##n = ID_##operador \

#define init_OP_x(operador) \
   for (i = 1; i <= operador##_LAT; i++) { \
        operador[i].IR = inst_vacia; \
        operador[i].iPC = -1; \
        operador[i].orden = -1; \
        operador##n[i] = operador [i]; \
    }

#define init_WB_x(operador) \
    operador##_WB.IR = inst_vacia; \
    operador##_WB.iPC = -1; \
    operador##_WB.orden = -1; \
    operador##_WB##n = operador##_WB; \

/*** Funciones Locales *****************************************************/

/***************************************************************
 *
 * Func: inicializa_estructuras
 *
 * Desc: Inicializa las estructuras del RISCV
 *
 **************************************************************/

static void inicializa_estructuras(
) {
    /*************************************/
    /*  Variables locales                */
    /*************************************/

    int i;

    /*************************************/
    /*  Cuerpo función                   */
    /*************************************/

    /* Instruccion NOP*/
    inst_vacia.codop = PSEUDO_NOP;
    //inst_vacia.codop = RV32I_ADDI;
    inst_vacia.tipo = FormatoI;

    inst_vacia.rs1 = 0; /* Índice del registro fuente 1 */
    inst_vacia.rs2 = 0; /* Índice del registro fuente 2 */
    inst_vacia.rs3 = 0; /* Índice del registro fuente 3 */
    inst_vacia.rm = 0; /* Índice del modo de rendondeo */
    inst_vacia.rd = 0; /* Índice del registro destino */
    inst_vacia.imm = 0; /* Valor Inmediato. RiscV R32I usa hasta 20 bits */

    inst_vacia.PC = -1; /* Inst. inválida es -1 */
    inst_vacia.valor = 0; /* Codificacion */

    sprintf(inst_vacia.etiqueta, "%s", ""); /* Posible etiqueta */

    /* Registros */

    PC = 0;

    for (i = 0; i < TAM_REGISTROS; i++) // +1 por el FPSR
    {
        Rint[i].valor.int_d = 0;
        Rint[i].busy = 0;
        Rint[i].dirty = NO;
        Rint[i].tipo = t_ninguno;
    } /* endfor */

    for (i = 0; i < TAM_REGISTROS; i++) // +1 por el FPSR
    {
        Rfp[i].valor.fp_d = 0;
        Rfp[i].busy = 0;
        Rfp[i].dirty = NO;
        Rfp[i].tipo = t_ninguno;
    } /* endfor */

    /* Registros intermedios ud 5 etapas */
    IF_ID.IR = inst_vacia;
    IF_ID.NPC = 0;
    IF_ID.iPC = -1;
    IF_ID.orden = -1;
    IF_IDn = IF_ID;

    ID_EX.IR = inst_vacia;
    ID_EX.NPC = 0;
    ID_EX.Ra = 0;
    ID_EX.Rb = 0;
    ID_EX.Imm = 0;
    ID_EX.iPC = -1;
    ID_EX.orden = -1;
    ID_EXn = ID_EX;

    EX_MEM.IR = inst_vacia;
    EX_MEM.ALUout = 0;
    EX_MEM.data = 0;
    EX_MEM.cond = NO;
    EX_MEM.iPC = -1;
    EX_MEM.orden = -1;
    EX_MEMn = EX_MEM;

    MEM_WB.IR = inst_vacia;
    MEM_WB.ALUout = 0;
    MEM_WB.MEMout = 0;
    MEM_WB.iPC = -1;
    MEM_WB.orden = -1;
    MEM_WBn = MEM_WB;

    iWB.IR = inst_vacia;
    iWB.ALUout = 0;
    iWB.MEMout = 0;
    iWB.iPC = -1;
    iWB.orden = -1;
    iWBn = iWB;

    IFstall = NO;
    IDstall = NO;
    EXstall = NO;
    IFnop = NO;
    IDnop = NO;
    EXnop = NO;

    /* Registros intermedios ID/operadores multiciclo */
    init_ID_x(INT_MUL);
    init_ID_x(FP_LS);
    init_ID_x(FP_ADD);
    init_ID_x(FP_MUL);
    init_ID_x(FP_CMP);
    init_ID_x(FP_MISC);

    /*
    ID_INT_MUL.IR = inst_vacia;
    ID_INT_MUL.iPC = -1;
    ID_INT_MUL.orden = -1;
    ID_INT_MULn = ID_INT_MUL;

    ID_FP_MISC.IR = inst_vacia;
    ID_FP_MISC.iPC = -1;
    ID_FP_MISC.orden = -1;
    ID_FP_MISCn = ID_FP_MISC;

    ID_LS.IR = inst_vacia;
    ID_LS.iPC = -1;
    ID_LS.orden = -1;
    ID_LSn = ID_LS;

    ID_FP_ADD.IR = inst_vacia;
    ID_FP_ADD.iPC = -1;
    ID_FP_ADD.orden = -1;
    ID_FP_ADDn = ID_FP_ADD;

    ID_FP_CMP.IR = inst_vacia;
    ID_FP_CMP.iPC = -1;
    ID_FP_CMP.orden = -1;
    ID_FP_CMPn = ID_FP_CMP;

    ID_FP_MUL.IR = inst_vacia;
    ID_FP_MUL.iPC = -1;
    ID_FP_MUL.orden = -1;
    ID_FP_MULn = ID_FP_MUL;
     */

    /* Registros intermedios operadores segmentados ud multiciclo */
    init_OP_x(FP_LS);
    init_OP_x(FP_ADD);
    init_OP_x(FP_MUL);
    init_OP_x(FP_CMP);
    init_OP_x(FP_MISC);
    init_OP_x(INT_MUL);


    /*
        for (i = 1; i <= FP_LS_LAT; i++) {
            FP_LS[i].IR = inst_vacia;
            FP_LS[i].iPC = -1;
            FP_LS[i].orden = -1;
            FP_LSn[i] = FP_LS[i];
        }

        for (i = 1; i <= FP_ADD_LAT; i++) {
            FP_ADD[i].IR = inst_vacia;
            FP_ADD[i].iPC = -1;
            FP_ADD[i].orden = -1;
            FP_ADDn[i] = FP_ADD[i];
        }

        for (i = 1; i <= FP_MUL_LAT; i++) {
            FP_MUL[i].IR = inst_vacia;
            FP_MUL[i].iPC = -1;
            FP_MUL[i].orden = -1;
            FP_MULn[i] = FP_MUL[i];
        }

        for (i = 1; i <= FP_CMP_LAT; i++) {
            FP_CMP[i].IR = inst_vacia;
            FP_CMP[i].iPC = -1;
            FP_CMP[i].orden = -1;
            FP_CMPn[i] = FP_CMP[i];
        }

        for (i = 1; i <= INT_MUL_LAT; i++) {
            INT_MUL[i].IR = inst_vacia;
            INT_MUL[i].iPC = -1;
            INT_MUL[i].orden = -1;
            INT_MULn[i] = INT_MUL[i];
        }
     */

    /* Registros intermedios operadores multiciclo/WB */
    init_WB_x(FP_LS);
    init_WB_x(FP_ADD);
    init_WB_x(FP_MUL);
    init_WB_x(FP_CMP);
    init_WB_x(FP_MISC);
    init_WB_x(INT_MUL);

    /* mux a la entrada FP regs */
    init_WB_x(FP);

    /* mux a la entrada INT regs */
    iWB.IR = inst_vacia;
    iWB.iPC = -1;
    iWB.orden = -1;
    iWBn = iWB;

    /*    
        FP_LS_WB.IR = inst_vacia;
        FP_LS_WB.iPC = -1;
        FP_LS_WB.orden = -1;
        FP_LS_WBn = FP_LS_WB;

        FP_ADD_WB.IR = inst_vacia;
        FP_ADD_WB.iPC = -1;
        FP_ADD_WB.orden = -1;
        FP_ADD_WBn = FP_ADD_WB;

        FP_MUL_WB.IR = inst_vacia;
        FP_MUL_WB.iPC = -1;
        FP_MUL_WB.orden = -1;
        FP_MUL_WBn = FP_MUL_WB;

    
        FP_WB.IR = inst_vacia;
        FP_WB.iPC = -1;
        FP_WB.orden = -1;
        FP_WBn = FP_WB;

        FP_CMP_WB.IR = inst_vacia;
        FP_CMP_WB.iPC = -1;
        FP_CMP_WB.orden = -1;
        FP_CMP_WBn = FP_CMP_WB;

        INT_MUL_WB.IR = inst_vacia;
        INT_MUL_WB.iPC = -1;
        INT_MUL_WB.orden = -1;
        INT_MUL_WBn = INT_MUL_WB;

        FP_MISC_WB.IR = inst_vacia;
        FP_MISC_WB.iPC = -1;
        FP_MISC_WB.orden = -1;
        FP_MISC_WBn = FP_MISC_WB;
     */

    iWB.IR = inst_vacia;
    iWB.iPC = -1;
    iWB.orden = -1;
    iWBn = iWB;


} /* end inicializa_estructuras */

static const char *
simul_logo() {
    return
"    ____  _          _    __\n"
"   / __ \\(_)________| |  / /           ____ ___\n"
"  / /_/ / / ___/ ___/ | / /  ______   / __ `__ \\\n"
" / _, _/ (__  ) /__ | |/ /  /_____/  / / / / / /\n"
"/_/ |_/_/____/\\___/ |___/           /_/ /_/ /_/\n";
}


static const char * show_usage() {
    return
            YELLOW_TEXT
    "Uso:\n"
    COLOR_RESET
    "\triscv-m [options] {-f input.s}+\n"
    YELLOW_TEXT
    "\nOpciones:\n"
    COLOR_RESET
    GREEN_TEXT
    "\t-s resultado <mode>\n"
    COLOR_RESET
    "\t            Especifíca cómo se deben visualizar el resultado (def. html).\n"
    "\t              tiempo: Se muestra sólo el tiempo de ejecución en la consola.\n"
    "\t              final: Se muestra el tiempo de ejecución y estado en la consola.\n"
    "\t              html(*): Se genera un archivo html por ciclo.\n "
    "\t              html-final: Se genera un único archivo html con el resultado.\n\n"
    GREEN_TEXT
    "\t-d r.datos <mode> (def. c)\n"
    COLOR_RESET
    "\t            Especifíca cómo se resuelven los riesgos de datos (def. c).\n"
    "\t              n: No se resuelven\n"
    "\t              p: Se insertan ciclos parada\n"
    "\t              c(*): Se utilizan cortocircuitos\n"
    GREEN_TEXT
    "\t-c r. control <mode> (def. pnt3)\n"
    COLOR_RESET
    "\t            Especifíca cómo se resuelven los riesgos de control (def. pnt3).\n"
    "\t              s3: Se insertan ciclos de parada (latencia salto=3)\n"
    "\t              s2: Se insertan ciclos de parada (latencia salto=2)\n"
    "\t              s1: Se insertan ciclos de parada (latencia salto=1)\n"
    "\t              pnt3(*): Se utiliza predict-not-taken (latencia salto=3)\n"
    "\t              pnt2: Se utiliza predict-not-taken (latencia salto=2)\n"
    "\t              pnt1: Se utiliza predict-not-taken (latencia salto=1)\n"
    GREEN_TEXT
    "\t-l <lat>\n"
    COLOR_RESET
    "\t            Especifica la latencia del operador de LS (def. 2)\n"
    GREEN_TEXT
    "\t-a <lat>\n"
    COLOR_RESET
    "\t            Especifica la latencia del operador ADD (def. 4)\n"
    GREEN_TEXT
    "\t-m <lat>\n"
    COLOR_RESET
    "\t            Especifica la latencia del operador MUL (def. 7)\n"
    GREEN_TEXT
    "\t--cmp <lat>\n"
    COLOR_RESET
    "\t            Especifica la latencia del operador CMP (def. 4)\n"
    GREEN_TEXT
    "\t--misc <lat>\n"
    COLOR_RESET
    "\t            Especifica la latencia del operador MISC (def. 4)\n"
    GREEN_TEXT
    "\t-j\n"
    COLOR_RESET
    "\t            Genera un único archivo htm con javascript\n"
    GREEN_TEXT
    "\t--opt-int\n"
    COLOR_RESET
    "\t            Elimina WB en STORE INT\n"
    GREEN_TEXT
    "\t--reg <mode>\n"
    COLOR_RESET
    "\t            Especifíca cómo se deben visualizar los registros (def. auto).\n"
    "\t              auto(*): Se muestra lo que haya utilizado el programador.\n"
    "\t              ar: Se muestran los registros en formato arquitectónico (xNN y fNN).\n"
    "\t              abi: Se muestran los registros con los nombre definidos en el ABI.\n"
    GREEN_TEXT
    "\t--format <mode>\n"
    COLOR_RESET
    "\t            Indica cómo se deben visualizar el formato de instrucción (def. full).\n"
    "\t              full(*): Se muestra el formato en binario y la descripción de los campos.\n"
    "\t              compact: Se muestran los campos con el código binario dentro.\n"
    "\t              none: No se muestra el formato de las instrucciones.\n"
    GREEN_TEXT
    "\t--no-show-IT\n"
    COLOR_RESET
    "\t            No muestra cronograma.\n"
    GREEN_TEXT
    "\t--no-show-DP\n"
    COLOR_RESET
    "\t            No muestra ruta de datos.\n"
    GREEN_TEXT
    "\t--no-show-R\n"
    COLOR_RESET
    "\t            No muestra registros.\n"
    GREEN_TEXT
    "\t--no-show-M\n"
    COLOR_RESET
    "\t            No muestra memoria.\n"
    GREEN_TEXT
    "\t--no-show-LOG\n"
    COLOR_RESET
    "\t            No muestra LOG.\n"
    GREEN_TEXT
    "\t--no-show-C\n"
    COLOR_RESET
    "\t            No muestra CONSOLA.\n"
    GREEN_TEXT
    "\t-n\n"
    COLOR_RESET
    "\t            No borra archivos html antes de la ejecución.\n"
    GREEN_TEXT
    "\t-f <fichero>\n"
    COLOR_RESET
    "\t            Especifica el nombre del fichero en ensamblador.\n";
}

static void construye_programa(
        programa_t *prog
) {
    /* Fichero objeto */
    for (int i = 0; i < prog->n_objs; ++i) {
        ensamblador(&prog->l_objs[i]);
    }

    /*** Enlazar ***/

    inicializa_memoria(&prog->memoria);

    // Variable global para simplificar la implementación
    mmem = &prog->memoria;

    /*** Enlazar ***/

    enlazador(prog);

    /*** Cargar ***/

    cargador(prog);

    /*** Generar hexadecimal */

    genera_MInstruccion(prog);


}

/***************************************************************
 *
 * Func: ejecutar_codigo
 *
 *
 * Retorna:  RC  (int)
 *
 * Descripción:
 *   Ejecuta el bucle principal del computador
 *
 **************************************************************/

static int ejecutar_codigo(
) {
    /*************************************/
    /*  Variables locales                */
    /*************************************/

    int RC; /* Valor de retorno */
    int i;


    boolean OVF_HTML = NO;
    //    boolean OVF_TXT = NO;

    /*  boolean	html = NO; */

    /*************************************/
    /*  Cuerpo función                   */
    /*************************************/

    RC = 0;

    /*** Inicializacion del computador */

    Ciclo = 0;
    //Instrucciones = 0;
    estat.instrucciones = 0;
    estat.ciclos = 0;
    estat.enteras = 0;
    estat.multiciclo = 0;
    estat.flops = 0;


    orden = 1;

    if ((!hay_float) && (!hay_double))
        small_screen_flag = 1;


    init_presentacion();

    if (salida == html) {
        imprime_inicio_css();

    }

    if (salida == html_final) {
        imprime_inicio_css();
    }

    /* Directiva de ensamblador .pc */
    PC = PC_inicial;


    /*** Bucle principal */

    while (!final) {

        if ((salida == res_final) || (salida == res_tiempo) || (salida == html_final)) {
            if ((Ciclo > 0) && (Ciclo % 2000) == 0) {
                printf(".");
                fflush(stdout);
            };
            if ((Ciclo > 0) && (Ciclo % 10000) == 0) {
                printf("%5uK", Ciclo / 1000);
                fflush(stdout);
            };
            if ((Ciclo > 0) && (Ciclo % 50000) == 0) {
                printf("\n");
            };
        }

        /*** Enteros ******************************/

        /*** Fase: WB **************/

        final = fase_escritura();

        /*** Fase: MEM **************/

        fase_memoria();

        /*** Fase: EX *************/

        fase_ejecucion();

        /*** Fase: WB **************/

        fase_escritura_FP();


        /*** Fase: Ejecución multiciclo **************/

        i = FP_LS_LAT;
        while (i > 0) {
            fase_LS(i);
            i = i - 1;
        }

        i = FP_ADD_LAT;
        while (i > 0) {
            fase_A(i);
            i = i - 1;
        }

        i = FP_MUL_LAT;
        while (i > 0) {
            fase_M(i);
            i = i - 1;
        }

        i = FP_CMP_LAT;
        while (i > 0) {
            fase_C(i);
            i = i - 1;
        }

        i = INT_MUL_LAT;
        while (i > 0) {
            fase_iM(i);
            i = i - 1;
        }

        i = FP_MISC_LAT;
        while (i > 0) {
            fase_MISC(i);
            i = i - 1;
        }

        /*** Fase: ID **************/

        fase_decodificacion();

        /*** Fase: IF ***********/

        fase_busqueda();




        /* Incrementa el número de ciclos ejecutados */

        Ciclo++;
        estat.ciclos++;

        if (Ciclo >= MAX_CICLOS_TOTAL) {
            fprintf(stderr, "\nATENCION: Numero de ciclos (%d) excesivo\n Ejecución detenida\n\n", MAX_CICLOS_TOTAL);
            final = SI;
        }

        if ((salida == html) && (Ciclo >= MAX_CICLOS_HTML) && !OVF_HTML) {
            fprintf(stderr, "\nATENCION: Numero de ciclos (%d) excesivo\n No se generan mas archivos html\n\n",
                    MAX_CICLOS_HTML);
            OVF_HTML = SI;
        };

        if (OVF_HTML) {
            if ((Ciclo > 0) && (Ciclo % 2000) == 0) {
                printf(".");
                fflush(stdout);
            };
            if ((Ciclo > 0) && (Ciclo % 10000) == 0) {
                printf("%5uK", Ciclo / 1000);
                fflush(stdout);
            };
            if ((Ciclo > 0) && (Ciclo % 50000) == 0) {
                printf("\n");
            };
        }

        /*
        if ((salida != html) && (Ciclo >= MAX_CICLOS_TXT) && !OVF_TXT)
        {
            fprintf(stderr, "\nATENCION: Numero de ciclos excesivo\n No se muestran mas ciclos\n\n");
                        OVF_TXT = SI;
        } ;
         */


        /*** Imprime el estado -- interfaz de texto  */
        /*
        if ((salida == res_ciclo)  && (!OVF_TXT)) {
            imprime_etapas_txt ();
            imprime_reg_txt ();
            imprime_memdatos_txt ();
        }
         */
        /*** Imprime el estado del los operadores, etc.  */

        if ((salida == html) && (!OVF_HTML)) {
            actualiza_crono();
            imprime_css();
        }

        impulso_reloj();


    } /* endwhile */


    if (salida == res_tiempo) {
        //imprime_inicio_txt();
        imprime_final_txt();
        //        imprime_reg_txt ();
        //        imprime_memdatos_txt ();
    }

    if (salida == res_final) {
        printf("Archivo: %s\n\n", nombre_fich);
        imprime_inicio_txt();
        imprime_final_txt();
        imprime_reg_txt();
        imprime_memdatos_txt();
    }

    if (salida == html) {
        imprime_final_txt();
        imprime_final_css();

    }

    if (salida == html_final) {
        imprime_final_txt();
        imprime_final_css();
    }


    return (RC);

} /* end ejecutar_codigo */

/**
 * Gestiona un error fatal
 *
 * @param msg_text Mensaje a mostrar
 * @param msg_arg1 Mensaje a mostrar
 * @param msg_arg2 Mensaje a mostrar
 *
 */
static void error_fatal
        (
                char *msg_text,
                char *msg_arg1,
                char *msg_arg2
        ) {
    fprintf(stderr, RED_TEXT "%s %s %s\n\n" COLOR_RESET, msg_text,msg_arg1,msg_arg2);
    fprintf(stderr, "%s\n", show_usage());
    exit(1);
} /* end error_fatal */

/**
 * Comprueba si existe el archivo
 *
 * @param filename Nombre del archivo
 *
 */
static int file_exists(char *filename) {
    struct stat buffer;

    return (stat(filename, &buffer) == 0);
}

/*** Funciones Exportables *************************************************/

#ifndef LIBRISCV
#define LIBRISCV 0

/***************************************************************
 *
 * Func: main
 *
 * Parametros:
 *	argc  (int) ; N˙mero de parametros en linea
 *	argv[]  (char*) ; Texto de los parametros en linea
 *
 * Retorna:  RC  (int)
 *
 * Descripción:
 *   Función principal
 *
 **************************************************************/

int main(
        int argc,
        char *argv[]
) {

#else
#define LIBRISCV 1

    /***************************************************************
     *
     * Func: libriscv_main
     *
     * Parametros:
     *      ensamblador_s (char*): Cadena con programa ensamblador a simular
     *      config_en_s (int): Leer la configuracion de la primera linea de ensamblador_s
     *      solucion_riesgos_control_p (int): 0 (stalls) / 1 (predict-not-taken) / 2 (delay slot)
     *      latencia_salto_p (int): Latencia de actualización del PC en caso de salto efectivo
     *      solucion_riesgos_datos_p (int): 0 (ninguno) / 1 (stalls) / 2 (cortocircuitos)
     *      FP_ADD_LAT_p, FP_CMP_LAT_p, FP_LS_LAT_p, FP_MUL_LAT_p (int): Latencias de operadores multiciclo
     *
     * Retorna: resultado_htm (char*): Resultado de la simulacion (malloc). Es responsabilidad del que llama liberar con free.
     *
     * Descripcion:
     *   Funcion principal de la libreria (reemplaza a main)
     *
     **************************************************************/

    char* libriscv_main
    (
            char* ensamblador_s,
            int config_en_s,
            int solucion_riesgos_control_p, int latencia_salto_p,
            int solucion_riesgos_datos_p,
            int FP_ADD_LAT_p, int FP_CMP_LAT_p, int FP_LS_LAT_p, int FP_MUL_LAT_p
            ) {

        /*** Utiliza parametros _p si la configuracion no esta en ensamblador_s */

        if (!config_en_s) {

            switch (solucion_riesgos_control_p) {
                case 0:
                    switch (latencia_salto_p) { /* stalls */
                        case 1: solucion_riesgos_control = stall1;
                            break;
                        case 2: solucion_riesgos_control = stall2;
                            break;
                        case 3: solucion_riesgos_control = stall3;
                            break;
                    }
                    break;
                case 1:
                    switch (latencia_salto_p) { /* predict-not-taken */
                        case 1: solucion_riesgos_control = pnt1;
                            break;
                        case 2: solucion_riesgos_control = pnt2;
                            break;
                        case 3: solucion_riesgos_control = pnt3;
                            break;
                    }
                    break;
                case 2:
                    switch (latencia_salto_p) { /* delay slot */
                        case 1: solucion_riesgos_control = ds1;
                            break;
                        case 2: solucion_riesgos_control = ds2;
                            break;
                        case 3: solucion_riesgos_control = ds3;
                            break;
                    }
                    break;
            }

            switch (solucion_riesgos_datos_p) {
                case 0: solucion_riesgos_datos = ninguno;
                    break;
                case 1: solucion_riesgos_datos = parada;
                    break;
                case 2: solucion_riesgos_datos = cortocircuito;
                    break;
            }

            FP_ADD_LAT = FP_ADD_LAT_p >= MAX_FP_LAT ? MAX_FP_LAT : FP_ADD_LAT_p;
            FP_CMP_LAT = FP_CMP_LAT_p >= MAX_FP_LAT ? MAX_FP_LAT : FP_CMP_LAT_p;
            FP_LS_LAT = FP_LS_LAT_p >= MAX_FP_LAT ? MAX_FP_LAT : FP_LS_LAT_p;
            FP_MUL_LAT = FP_MUL_LAT_p >= MAX_FP_LAT ? MAX_FP_LAT : FP_MUL_LAT_p;

        }

        char** argv;
        int argc = 0;

        /*** Si la configuracion esta en ensamblador_s construye argc y argv a partir de la primera linea de ensamblador_s */

        if (config_en_s) {

            char* i;

            for (i = ensamblador_s; *i != '#' && *i != '\n' && *i != 0; i++);

            if (*i == '#') { /* La primera linea debe ser un comentario */

                char* f;

                i++;
                for (f = i; *f != '\n' && *f != 0; f++);

                if (*f == '\n') { /* No continua si ensamblador_s no tiene al menos una linea */

                    /* Aisla la primera linea */
                    *f = 0;
                    ensamblador_s = f + 1;

                    /* main espera que argv tenga como primer componente el nombre del ejecutable */
                    argv = malloc(sizeof (char*));
                    argv[0] = "riscv-m";
                    argc++;

                    /* Separa y anade a argv los argumentos encontrados en la primera linea */
                    char* p;

                    for (p = strtok(i, " \t"); p != 0; p = strtok(0, " \t")) {
                        argv = realloc(argv, sizeof (char*) * (argc + 1));
                        argv[argc] = p;
                        argc++;
                    }
                    argv = realloc(argv, sizeof (char*) * (argc + 1));
                    argv[argc] = 0;
                }
            }
        } /* endif (config_en_s) */

        /*** Crea el fichero de entrada para el simulador a partir de ensamblador_s */

        {
            FILE* f_entrada;

            f_entrada = fopen("fichero.s", "w");
            fputs(ensamblador_s, f_entrada);
            fclose(f_entrada);
        }

#endif /* #ifndef LIBRISCV */

    /*************************************/
    /*  Variables locales                */
    /*************************************/

    int RC; /* Valor de retorno */
    int np;
    int param;


    int argn;


    boolean borrar_html = 1;
    boolean f_dump = NO;

    FILE *f_aux;

    static FILE * f_consola;
    static FILE * f_log;

    //char aux[128];

    char *ficheros_entrada[MAX_FICHEROS];
    int num_ficheros = 0;

    /*************************************/
    /*  Cuerpo función                   */
    /*************************************/

    nombre_fich[0] = '\0';

    /*** Lectura de parametros */

    if (!LIBRISCV || (LIBRISCV && argc > 0)) {

        solucion_riesgos_datos = cortocircuito;
        solucion_riesgos_control = pnt3;
        salida = html;


        argn = 1;
        if (argc < 2) {
            error_fatal("Error: ","Faltan argumentos","");
        } /* endif */

        fprintf(stdout, "%s%s%s\n", BLUE_TEXT, simul_logo(), COLOR_RESET);
        fprintf(stdout, "%s%s%s", GREEN_TEXT, SIMUL_NAME, COLOR_RESET);
        fprintf(stdout, " version %s%s%s", YELLOW_TEXT, SIMUL_VERSION,COLOR_RESET);
        fprintf(stdout, " %s\n\n", SIMUL_DATE);
        //fprintf(stdout, "Simulador del procesador RISC-V multiciclo (v%3.1f)\n\n", VERSION);
        while (argn < argc) {
            if (strcmp(argv[argn], "-f") == 0) {
                char str[64 * MAX_FICHEROS];
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                ficheros_entrada[num_ficheros] = argv[argn];
                if (!file_exists(ficheros_entrada[num_ficheros])) {
                    fprintf(stderr, "Fichero de entrada '%s' no encontrado.\n",
                            ficheros_entrada[num_ficheros]);
                    error_fatal("Error en opción", "-f:", "falta nombre del fichero");
                } /* endif */

                sprintf(str, "%s %s", nombre_fich, basename(argv[argn]));
                strcpy(nombre_fich, str);
                num_ficheros++;
            } else if (strcmp(argv[argn], "-s") == 0) {
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                if (strcmp(argv[argn], "tiempo") == 0) {
                    salida = res_tiempo;
                } else if (strcmp(argv[argn], "final") == 0) {
                    salida = res_final;
                } else if (strcmp(argv[argn], "html") == 0) {
                    salida = html;
                } else if (strcmp(argv[argn], "html-final") == 0) {
                    salida = html_final;
                } else {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }
            } else if (strcmp(argv[argn], "-d") == 0) {
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                if (strcmp(argv[argn], "n") == 0) {
                    solucion_riesgos_datos = ninguno;
                } else if (strcmp(argv[argn], "p") == 0) {
                    solucion_riesgos_datos = parada;
                } else if (strcmp(argv[argn], "c") == 0) {
                    solucion_riesgos_datos = cortocircuito;
                } else {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }
            } else if (strcmp(argv[argn], "-c") == 0) {
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                if (strcmp(argv[argn], "s3") == 0) {
                    solucion_riesgos_control = stall3;
                } else if (strcmp(argv[argn], "s2") == 0) {
                    solucion_riesgos_control = stall2;
                } else if (strcmp(argv[argn], "s1") == 0) {
                    solucion_riesgos_control = stall1;
                } else if (strcmp(argv[argn], "pnt3") == 0) {
                    solucion_riesgos_control = pnt3;
                } else if (strcmp(argv[argn], "pnt2") == 0) {
                    solucion_riesgos_control = pnt2;
                } else if (strcmp(argv[argn], "pnt1") == 0) {
                    solucion_riesgos_control = pnt1;
                } else if (strcmp(argv[argn], "ds3") == 0) {
                    solucion_riesgos_control = ds3;
                } else if (strcmp(argv[argn], "ds2") == 0) {
                    solucion_riesgos_control = ds2;
                } else if (strcmp(argv[argn], "ds1") == 0) {
                    solucion_riesgos_control = ds1;
                } else {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }
            } else if (strcmp(argv[argn], "-a") == 0) {
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                np = sscanf(argv[argn], "%d", &param);
                if (np != 1) {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }

                if (param> 1) {
                    if (param >= MAX_FP_LAT) {
                        error_fatal("Error en opción", argv[argn-1], argv[argn]);
                    }
                    FP_ADD_LAT = param;
                } else {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }
            } else if (strcmp(argv[argn], "--cmp") == 0) {
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                np = sscanf(argv[argn], "%d", &param);
                if (np != 1) {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }

                if (param> 1) {
                    if (param >= MAX_FP_LAT) {
                        error_fatal("Error en opción", argv[argn-1], argv[argn]);
                    }
                    FP_CMP_LAT = param;
                } else {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }
            } else if (strcmp(argv[argn], "--misc") == 0) {
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                np = sscanf(argv[argn], "%d", &param);
                if (np != 1) {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }

                if (param> 1) {
                    if (param >= MAX_FP_LAT) {
                        error_fatal("Error en opción", argv[argn-1], argv[argn]);
                    }
                    FP_MISC_LAT = param;
                } else {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }
            } else if (strcmp(argv[argn], "-l") == 0) {
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                np = sscanf(argv[argn], "%d", &param);
                if (np != 1) {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }

                if (param> 1) {
                    if (param >= MAX_FP_LAT) {
                        error_fatal("Error en opción", argv[argn-1], argv[argn]);
                    }
                    FP_LS_LAT = param;
                } else {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }
            } else if (strcmp(argv[argn], "-m") == 0) {
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                np = sscanf(argv[argn], "%d", &param);
                if (np != 1) {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }

                if (param> 1) {
                    if (param >= MAX_FP_LAT) {
                        error_fatal("Error en opción", argv[argn-1], argv[argn]);
                    }
                    FP_MUL_LAT = param;
                } else {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }
            } else if (strcmp(argv[argn], "--reg")==0) {
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                if (strcmp(argv[argn], "auto") == 0) {
                    SHOW_ABI_REG = reg_auto;
                    printf("reg argn %d argv[argn]: %s\n", argn,argv[argn]);
                } else if (strcmp(argv[argn], "ar") == 0) {
                    SHOW_ABI_REG = reg_ar;
                    printf("reg argn %d argv[argn]: %s\n", argn,argv[argn]);
                } else if (strcmp(argv[argn], "abi") == 0) {
                    SHOW_ABI_REG = reg_abi;
                    printf("reg argn %d argv[argn]: %s\n", argn,argv[argn]);
                } else {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }
            } else if (strcmp(argv[argn], "--format")==0) {
                argn++;
                if (argn == argc)
                    error_fatal("Error en opción",argv[argn-1],"");
                if (!strcmp(argv[argn], "full")) {
                    show_format = format_full;
                } else if (!strcmp(argv[argn], "compact")) {
                    show_format = format_compact;
                } else if (!strcmp(argv[argn], "none")) {
                    show_format = format_none;
                } else {
                    error_fatal("Error en opción", argv[argn-1], argv[argn]);
                }
                printf("HEY %s\n",argv[argn] );
            } else if (strcmp(argv[argn], "-n") == 0) {
                borrar_html = 0;
            } else if (strcmp(argv[argn], "-j") == 0) {
                html_merge = SI;
            } else if (strcmp(argv[argn], "--dump") == 0) {
                f_dump = SI;
            } else if (strcmp(argv[argn], "--opt-int") == 0) {
                AJUSTAR_INT = SI;
            } else if (strcmp(argv[argn], "--small-screen") == 0) {
                small_screen_flag = 1;
            } else if (strcmp(argv[argn], "--no-show-IT") == 0) {
                SHOW_DIT = NO;
            } else if (strcmp(argv[argn], "--no-show-DP") == 0) {
                SHOW_DATAPATH = NO;
            } else if (strcmp(argv[argn], "--no-show-R") == 0) {
                SHOW_REG = NO;
            } else if (strcmp(argv[argn], "--no-show-M") == 0) {
                SHOW_MEM = NO;
            } else if (strcmp(argv[argn], "--no-show-LOG") == 0) {
                SHOW_LOG = NO;
            } else if (strcmp(argv[argn], "--no-show-CONSOLE") == 0) {
                SHOW_CONSOLE = NO;
            } else {
                error_fatal("Error en opción",argv[argn],"");
            }
            argn++;
        }
        /*
        while (1) {
            break;
            static struct option long_options[] = {


                    //{"no-w3css", no_argument, &w3css_flag, 0},
                    {"help",           no_argument,       0,                  '?'},
                    {"no-borrar-html", no_argument,       0,                  'n'},
                    {"opt-int",        no_argument,       &AJUSTAR_INT,       SI},
                    {"javascript",     no_argument,       0,                  'j'},
                    {"small-screen",   no_argument,       &small_screen_flag, 1},

                    {"no-show-IT",     no_argument,       &SHOW_DIT,          0},
                    {"no-show-DP",     no_argument,       &SHOW_DATAPATH,     0},
                    {"no-show-R",      no_argument,       &SHOW_REG,          0},
                    {"no-show-M",      no_argument,       &SHOW_MEM,          0},
                    {"no-show-LOG",    no_argument,       &SHOW_LOG,          0},
                    {"no-show-C",      no_argument,       &SHOW_CONSOLE,      0},

                    {"salida",         required_argument, 0,                  's'},
                    {"rdatos",         required_argument, 0,                  'd'},
                    {"rcontrol",       required_argument, 0,                  'c'},
                    {"FPadd",          required_argument, 0,                  'a'},
                    {"FPmul",          required_argument, 0,                  'm'},
                    {"FPls",           required_argument, 0,                  'l'},
                    {"FPcmp",          required_argument, 0,                  'k'},
                    {"programa",       required_argument, 0,                  'f'},
                    {"reg",            required_argument, 0,                  'r'},
                    {0, 0,                                0,                  0}
            };

            int option_index = 0;

            c = getopt_long(argc, argv, "?a:c:d:f:k:l:m:s:nj",
                            long_options, &option_index);

            if (c == -1)
                break;

            switch (c) {
                case '?':
                    fprintf(stderr, uso, argv[0]);
                    exit(1);
                    break;

                case 0:

                    if (long_options[option_index].flag != 0)
                        break;
                    printf("option %s", long_options[option_index].name);
                    if (optarg)
                        printf(" with arg %s", optarg);
                    printf("\n");
                    break;

                case 'c':
                    if (strncmp(optarg, "p", 4) == 0) { // Por compatibilidad previa
                        solucion_riesgos_control = stall3;
                    } else if (strncmp(optarg, "s3", 4) == 0) {
                        solucion_riesgos_control = stall3;
                    } else if (strncmp(optarg, "s2", 4) == 0) {
                        solucion_riesgos_control = stall2;
                    } else if (strncmp(optarg, "s1", 4) == 0) {
                        solucion_riesgos_control = stall1;
                    } else if (strncmp(optarg, "t", 4) == 0) { // Por compatibilidad previa
                        solucion_riesgos_control = pnt3;
                    } else if (strncmp(optarg, "pnt3", 4) == 0) {
                        solucion_riesgos_control = pnt3;
                    } else if (strncmp(optarg, "pnt2", 4) == 0) {
                        solucion_riesgos_control = pnt2;
                    } else if (strncmp(optarg, "pnt1", 4) == 0) {
                        solucion_riesgos_control = pnt1;
                    } else if (strncmp(optarg, "ds3", 4) == 0) {
                        solucion_riesgos_control = ds3;
                    } else if (strncmp(optarg, "ds2", 4) == 0) {
                        solucion_riesgos_control = ds2;
                    } else if (strncmp(optarg, "ds1", 4) == 0) {
                        solucion_riesgos_control = ds1;
                    } else {
                        fprintf(stderr, "Error en argumento %s %s\n", "-c", optarg);
                        fprintf(stderr, uso, argv[0]);
                        exit(1);
                    }
                    break;

                case 'd':
                    if (strncmp(optarg, "n", 1) == 0) {
                        solucion_riesgos_datos = ninguno;
                    } else if (strncmp(optarg, "p", 1) == 0) {
                        solucion_riesgos_datos = parada;
                    } else if (strncmp(optarg, "c", 1) == 0) {
                        solucion_riesgos_datos = cortocircuito;
                    } else {
                        fprintf(stderr, "\nError en argumento %s %s\n", "-d", optarg);
                        fprintf(stderr, uso, argv[0]);
                        exit(1);
                    }
                    break;

                case 'f':
                    ficheros_entrada[num_ficheros] = optarg;
                    if (!file_exists(ficheros_entrada[num_ficheros])) {
                        fprintf(stderr, "%s: Fichero de entrada '%s' no encontrado.\n", argv[0],
                                ficheros_entrada[num_ficheros]);
                        exit(1);
                    }

                    sprintf(str, "%s %s", nombre_fich, basename(optarg));
                    strcpy(nombre_fich, str);
                    num_ficheros++;

                    break;

                case 'a':
                    if (isdigit(*optarg) && (atoi(optarg) > 1)) {
                        if (atoi(optarg) >= MAX_FP_LAT) {
                            fprintf(stderr, "\nError. La latencia máxima del operador es %d\n", MAX_FP_LAT - 1);
                            exit(1);
                        }
                        FP_ADD_LAT = atoi(optarg);
                    } else {
                        fprintf(stderr, "\nError en argumento %s %s\n", "-a", optarg);
                        fprintf(stderr, uso, argv[0]);
                        exit(1);
                    }
                    break;

                case 'k':
                    if (isdigit(*optarg) && (atoi(optarg) > 1)) {
                        if (atoi(optarg) >= MAX_FP_LAT) {
                            fprintf(stderr, "\nError. La latencia máxima del operador es %d\n", MAX_FP_LAT - 1);
                            exit(1);
                        }
                        FP_CMP_LAT = atoi(optarg);
                    } else {
                        fprintf(stderr, "\nError en argumento %s %s\n", "-k", optarg);
                        fprintf(stderr, uso, argv[0]);
                        exit(1);
                    }
                    break;

                case 'l':
                    if (isdigit(*optarg) && (atoi(optarg) > 1)) {
                        if (atoi(optarg) >= MAX_FP_LAT) {
                            fprintf(stderr, "\nError. La latencia máxima del operador es %d\n", MAX_FP_LAT - 1);
                            exit(1);
                        }
                        FP_LS_LAT = atoi(optarg);
                    } else {
                        fprintf(stderr, "\nError en argumento %s %s\n", "-l", optarg);
                        fprintf(stderr, uso, argv[0]);
                        exit(1);
                    }
                    break;


                case 'm':
                    if (isdigit(*optarg) && (atoi(optarg) > 1)) {
                        if (atoi(optarg) >= MAX_FP_LAT) {
                            fprintf(stderr, "\nError. La latencia máxima del operador es %d\n", MAX_FP_LAT - 1);
                            exit(1);
                        }
                        FP_MUL_LAT = atoi(optarg);
                        INT_MUL_LAT = FP_MUL_LAT;
                    } else {
                        fprintf(stderr, "\nError en argumento %s %s\n", "-m", optarg);
                        fprintf(stderr, uso, argv[0]);
                        exit(1);
                    }
                    break;


                case 's':
                    if (strncmp(optarg, "tiempo", 6) == 0) {
                        salida = res_tiempo;
                    } else if (strncmp(optarg, "final", 5) == 0) {
                        salida = res_final;
                    } else if (strncmp(optarg, "html", 10) == 0) {
                        salida = html;
                    } else if (strncmp(optarg, "html-final", 10) == 0) {
                        salida = html_final;
                    } else {
                        fprintf(stderr, "\nError en argumento %s %s\n", "-s", optarg);
                        fprintf(stderr, uso, argv[0]);
                        exit(1);
                    }
                    break;

                case 'n':
                    borrar_html = 0;
                    break;

                case 'j':
                    html_merge = SI;
                    break;

                case 'r':
                    if (strncmp(optarg, "auto", 4) == 0) {
                        SHOW_ABI_REG = reg_auto;
                    } else if (strncmp(optarg, "ar", 2) == 0) {
                        SHOW_ABI_REG = reg_ar;
                    } else if (strncmp(optarg, "abi", 3) == 0) {
                        SHOW_ABI_REG = reg_abi;
                    } else {
                        fprintf(stderr, "\nError en argumento %s %s\n", "-s", optarg);
                        fprintf(stderr, uso, argv[0]);
                        exit(1);
                    }
                    break;

                default:
                    abort();
            }
        }*/




    } /* endif (!LIBRISCV || LIBRISCV && argc > 0) */


    if (LIBRISCV) {
/* Corrige configuracion si se usa libriscv */

        strcpy(ficheros_entrada[num_ficheros],
               "fichero.s");
        num_ficheros = 1;
        f_etiquetas = NO;
        borrar_html = NO;
        html_merge = SI;

        salida = html;
    }

    if (num_ficheros == 0) {
        error_fatal("Error en opción", "-f: ", "faltan nombres de archivos .s");
    }



    fprintf(stdout,
            "Archivos: %s\n\n", nombre_fich);

    if (borrar_html) {
        system("rm *.html 2>/dev/null");
    }

    inicializa_programa(&programa, num_ficheros, ficheros_entrada);


/* Si existe el archivo en el /tmp, se utilizará su contenido para poner
 un enlace HOME en la salida html */
    if (file_exists("/tmp/_url.txt")) {
        f_aux = fopen("/tmp/_url.txt", "r");
        fgets(HOME_LINK,
              128, f_aux);
        home_link_flag = 1;
        fclose(f_aux);
    }

    /* Borra archivo de consola */
    f_consola = fopen(console_name, "w");
    fclose(f_consola);

    /* Borra archivo de log */
    f_log = fopen(log_name, "w");
    fclose(f_log);

/*** Inicializacion de estructuras */

    inicializa_estructuras();


/*** Carga el codigo en lenguaje ensamblador */

    construye_programa(&programa);

/*** Imprime mapa de memoria */

    imprime_mapa_memoria();


/*** Llama al bucle principal */

    RC = ejecutar_codigo();

    /*** Vuelca el contenido de la memoria ***/
    if (f_dump) {
        vuelca_memoria(&programa);
    }

#if !LIBRISCV

    return (RC);

#else

    /*** libriscv devuelve una cadena con el contenido del fichero .htm de salida */

    FILE* f_salida = fopen("index.htm", "r");
    long htm_size;
    char* result;

    fseek(f_salida, 0, SEEK_END);
    htm_size = ftell(f_salida);
    rewind(f_salida);

    result = malloc(htm_size + 1);
    fread(result, htm_size, 1, f_salida);
    result[htm_size] = 0;

    fclose(f_salida);

    /*** Libera argv si se construyo al inicio */

    if (argc > 0) {
        free(argv);
    }

    return result;

#endif /* LIBRISCV */

} /* end main */


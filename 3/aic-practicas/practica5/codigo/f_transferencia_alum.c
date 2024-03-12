/**
 * @file f_transferencia.c
 *
 * Departamento de Informática de Sistemas y Computadores (DISCA)
 * Universidad Politécnica de Valencia.
 *
 * @author Sergio Sáez <ssaez@disca.upv.es>
 * @author Pedro López <plopez@disca.upv.es>
 *
 * @brief
 *    Contiene la fase de transferencia por el bus común de datos para el
 *      algoritmo de Tomasulo con especulación.
 *
 * @copyright [CC BY-NC-ND 4.0]
 *   Esta obra está bajo una licencia de Creative Commons Atribución-NoComercial-SinDerivadas
 *   Para evitar dudas, no se tiene permiso bajo esta Licencia para compartir Material Adaptado.
 *   Más detalles en https://creativecommons.org/licenses/by-nc-nd/4.0/legalcode.es
 */


#define f_transferencia_alum_C

/***** Includes ***********************************************/

#include "main.h"
#include "presentacion.h"
#include "algoritmo.h"


/**
 * Fase de transferencia por el bus común de datos
 */
void fase_WB_alum() {

    marca_t i, s;

    ciclo_t orden;

    /*** VISUALIZACIÓN ****/
    for (i = 0; i < CONFIG(tam_estaciones); i++) {
        if (RS[i].ocupado && RS[i].estado == FINALIZADA) {
            muestra_fase("-", RS[i].orden);
        } /* endif */
    } /* endif */
    /**********************/

    
    /*** Busca RS con resultados disponibles */

    orden = MAX_ORDEN;
    s = 0;

    for (i = 0; i < CONFIG(tam_estaciones); i++) {
        if (RS[i].ocupado && RS[i].estado == FINALIZADA && RS[i].orden < orden) {
            s = i;
            orden = RS[i].orden;
        } /* endif */
    } /* endif */

    if (orden >= MAX_ORDEN) return; /* No hay ninguna RS con resultados disponibles */

    /*** Volcado de resultados */
    BUS.condicion = RS[s].condicion;
    BUS.control = RS[s].control;
    
     BUS.codigo = RS[s].rob;
     BUS.valor = RS[s].resultado;
              /* INSERTAR CÓDIGO */


    /*** Libera la RS */
    RS[s].ocupado = NO;
              /* INSERTAR CÓDIGO */


    /*** VISUALIZACIÓN ****/
    RS[s].estado = PENDIENTE;
    if (BUS.control != EXCEPCION) {
        muestra_fase("WB", RS[s].orden);
    } else {
        muestra_fase("<font color=\"red\">WB</font>", RS[s].orden);
    }
    /**********************/

    /*** Lectura de resultados */
    RB[BUS.codigo].completado = SI;

    /* Reorder buffer */
    switch (BUS.control) {
        case DIRECCION:
            RB[BUS.codigo].direccion = BUS.valor.int_d;
            RB[BUS.codigo].condicion = BUS.condicion;
            return;

        case CONDICION:
            RB[BUS.codigo].condicion = BUS.condicion;
            return;

        case EXCEPCION:
            RB[BUS.codigo].excepcion = BUS.valor.int_d;
            return;

        default:
            RB[BUS.codigo].valor = BUS.valor;

    }

    /* Estaciones de reserva */

    for (s = INICIO_RS_ENTEROS; s <= FIN_RS_ENTEROS; s++) {
        
              if(RS[s].Q1 == BUS.codigo){
                  RS[s].V1 = BUS.valor;
                  RS[s].Q1 = MARCA_NULA;
              }
              if(RS[s].Q2 == BUS.codigo){
                  RS[s].V2 = BUS.valor;
                  RS[s].Q2 = MARCA_NULA;
            }


    } /* endfor */

    for (s = INICIO_RS_SUMREST; s <= FIN_RS_SUMREST; s++) {
        
               if(RS[s].Q1 == BUS.codigo){
                  RS[s].V1 = BUS.valor;
                  RS[s].Q1 = MARCA_NULA;
              }
              if(RS[s].Q2 == BUS.codigo){
                  RS[s].V2 = BUS.valor;
                  RS[s].Q2 = MARCA_NULA;
            }

    } /* endfor */

    for (s = INICIO_RS_MULTDIV; s <= FIN_RS_MULTDIV; s++) {
        
               if(RS[s].Q1 == BUS.codigo){
                  RS[s].V1 = BUS.valor;
                  RS[s].Q1 = MARCA_NULA;
              }
              if(RS[s].Q2 == BUS.codigo){
                  RS[s].V2 = BUS.valor;
                  RS[s].Q2 = MARCA_NULA;
            }

    } /* endfor */

    for (s = INICIO_BUFFER_CARGA; s <= FIN_BUFFER_CARGA; s++) {
        
              if(RS[s].Q1 == BUS.codigo){
                  RS[s].V1 = BUS.valor;
                  RS[s].Q1 = MARCA_NULA;
               }

    } /* endfor */

    for (s = INICIO_BUFFER_ALMACEN; s <= FIN_BUFFER_ALMACEN; s++) {
        
              if(RS[s].Q1 == BUS.codigo){
                  RS[s].V1 = BUS.valor;
                  RS[s].Q1 = MARCA_NULA;
              }
              if(RS[s].Q2 == BUS.codigo){
                  RS[s].V2 = BUS.valor;
                  RS[s].Q2 = MARCA_NULA;
              }
    } /* endfor */

} /* end fase_WB */
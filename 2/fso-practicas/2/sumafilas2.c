#include <stdio.h> 

#define TAM_FILA 100
#define NUM_FILAS 10
struct FILA {
    float datos[TAM_FILA];
    float suma;
};

void suma_fila(struct FILA *pf) {
    float temp = 0;

    int i;
    for (i = 0; i < TAM_FILA; i++) {
        temp += pf->datos[i];
    }
    pf->suma = temp;
}

void inicia_filas(struct FILA *pf) {
    int i, j;
    for (i = 0; i < NUM_FILAS; i++) {
        for (j = 0; j < TAM_FILA; j++) {
            (pf + i)->datos[j] = (float)i*j;
        }
    }
}
int main() { 
    int i;
    float suma_total;

    struct FILA filas[NUM_FILAS], *P;

    P = filas;

    inicia_filas(P);
    suma_total = 0;

    for (i = 0; i < NUM_FILAS; i++) {
        suma_fila(P + i);
        printf ("La suma de la fila %u es %f\n", i, (P + i)->suma);
        suma_total += (P + i)->suma;
    }
    printf("La suma final es %f\n", suma_total);

    return 0;
}


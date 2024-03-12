#include <stdio.h> 

#define TAM_FILA 100
#define NUM_FILAS 10
struct FILA {
    float datos[TAM_FILA];
    float suma;
};
// A) Define una variable filas que sea un vector de estructuras FILA de tamaño NUM_FILAS
struct FILA filas[NUM_FILAS];

void suma_fila(struct FILA *pf) {
    // B) Implemente suma_fila
    float temp = 0;

    int i;
    for (i = 0; i < TAM_FILA; i++) {
        temp += pf->datos[i];
    }
    pf->suma = temp;
}

// Inicia las filas con el valor i*j
void inicia_filas() {
    int i, j;
    for (i = 0; i < NUM_FILAS; i++) {
        for (j = 0; j < TAM_FILA; j++) {
            filas[i].datos[j] = (float)i*j;
        }
    }
}
int main() { 
    int i;
    float suma_total;
    
    struct FILA *P;

    P = filas;

    inicia_filas();
    // C) Completar bucle
    suma_total = 0;

    for (i = 0; i < NUM_FILAS; i++) {
        // Llamar a suma_fila
        suma_fila(P + i);
        printf ("La suma de la fila %u es %f\n", i, (P + i)->suma);
        // sumar la fila a suma_total
        suma_total += (P + i)->suma;
    }
    printf("La suma final es %f\n", suma_total);

    return 0;
}


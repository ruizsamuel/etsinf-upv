#include <stdio.h>
#include <math.h>
#include <limits.h>
#include <omp.h>

typedef unsigned long long Entero_grande;
#define ENTERO_MAS_GRANDE  ULLONG_MAX

int primo(Entero_grande n)
{
    volatile int p = (n % 2 != 0 || n == 2);
    Entero_grande s = sqrt(n);  // límite superior de búsqueda

    if (p) {
        #pragma omp parallel
        {
            int num_threads = omp_get_num_threads();
            int current_thread = omp_get_thread_num();
            Entero_grande i, start, end, chunk_size;

            // Calculamos el tamaño de cada "chunk" o subrango
            chunk_size = (s - 3) / (2 * num_threads); // Solo impares, por eso /2

            // Definimos el inicio y fin de este hilo
            start = 3 + current_thread * 2 * chunk_size;
            end = start + 2 * chunk_size;

            // Si es el último hilo, le damos hasta el final
            if (current_thread == num_threads - 1) {
                end = s;
            }

            // Revisamos los números impares en el intervalo [start, end]
            for (i = start; i <= end && p; i += 2) {
                if (n % i == 0) {
                    p = 0;  // Encontramos un divisor, no es primo
                }
            }
        }
    }

    return p;
}

int main()
{
    Entero_grande n;

    double t1, t2;

    t1 = omp_get_wtime();
    for (n = ENTERO_MAS_GRANDE; !primo(n); n -= 2) {
        /* NADA */
    }
    t2 = omp_get_wtime();

    printf("El mayor primo que cabe en %lu bytes es %llu.\n",
            sizeof(Entero_grande), n);

    printf("Y se ha tardado en encontrarlo %f segundos.\n", t2-t1);

    return 0;
}

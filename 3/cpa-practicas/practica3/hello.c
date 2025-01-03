#include <stdio.h>
#include <mpi.h>
int main (int argc, char *argv[])
{
    MPI_Init(&argc, &argv);
    int p, k;
    MPI_Comm_size(MPI_COMM_WORLD, &p);
    MPI_Comm_rank(MPI_COMM_WORLD, &k);
    printf("Hola mundo, soy el proceso %d de %d\n", k, p);
    MPI_Finalize();
    return 0;
}

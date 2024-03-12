#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) { 

    int i;
    for (i = 0; i < 5; i++) {
        int val_return = fork();
        if (val_return == 0) {
            printf("Hijo creado en iteración=%d \n",i);
            exit(i);
        }
    }
    sleep(10);
    exit(0);
}
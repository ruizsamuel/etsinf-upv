#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define MAX_PROC  10000

pid_t val_return;
int final_state;

int main(int argc, char *argv[]) { 

    int i;
    for (i = 0; i < MAX_PROC; i++) {
        val_return = fork();
        if (val_return == 0) {
            printf("Hijo creado en iteración=%d \n",i);
        } else {
            printf("Padre %ld, iteración %d: ", (long)getpid(),i);
            printf("He creado un hijo %ld\n", (long)val_return);
            break;
        }
    }
    while(wait(&final_state) > 0) {
        printf("Padre %ld, iteración %d: ",(long) getpid(),i);
        printf("Mi hijo dijo %d\n", WEXITSTATUS(final_state));
    }
    sleep(10);
    exit(i);
}
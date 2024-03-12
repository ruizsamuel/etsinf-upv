#include <stdio.h>

int x = 2;
void m();

int main() {
    int x = 3;
    m();
    printf(" %d \n", x);

    return 0;
}

void m() {
    x = 4;
}

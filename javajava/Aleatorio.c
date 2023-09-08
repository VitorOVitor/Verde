#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdbool.h>

void processInput();
void Regerar(char *palavra, int i);
bool Comparar(const char *palavra);

int main() {
    processInput();
    printf("FIM\n");
    return 0;
}

void processInput() {
    char palavra[100];
    srand(4); 
    fgets(palavra, sizeof(palavra), stdin);
//Tudo o que estaria na main está aqui para facilitar a chamada do método nele mesmo.
    if (Comparar(palavra)) {
        return;
    } else {//Se a palavra for FIM, ele se encerra, se não, continua indo.
        Regerar(palavra, 0); 
        printf("%s", palavra); 
        processInput();
    }
}

void Regerar(char *palavra, int i) {
    int tamanho = strlen(palavra);
    if (i < tamanho - 1) { 
        char letra1 = 'a' + (rand() % 26);
        char letra2 = 'a' + (rand() % 26);
        if (palavra[i] == letra1) {
            palavra[i] = letra2;
        }//Duas letras são sorteadas, caso a primeira apareça, é substituída pela segunda.
        i++;
        Regerar(palavra, i);
    }
}

bool Comparar(const char *palavra) {//Compara se a palavra é igual a FIM.
    return strcmp(palavra, "FIM\n") == 0; 
}






#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool Comparar(const char *palavra, int i);
bool IsPalindromo(const char *palavra, int i);


int main() {
    processInput();
    printf("FIM\n");
    return 0;
}


void processInput() {
    char palavra[100];
    if (fgets(palavra, sizeof(palavra), stdin) != NULL) {
        int i = 0;
   
        if (!Comparar(palavra, i)) {
            if (IsPalindromo(palavra, i)) {
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
            processInput();
        }
    }
}

bool Comparar(const char *palavra, int i) {
    const char *palavra2 = "FIM";
    if (i < strlen(palavra)) {
        if (i >= strlen(palavra) || palavra[i] != palavra2[i]) {
            return false;
        }
        i++;
        return Comparar(palavra, i);
    }//Compara se a palavra é igual a FIM, letra por letra e pelo tamanho
    return true;
}

bool IsPalindromo(const char *palavra, int i) {
    if (i < strlen(palavra)) {
        if (palavra[i] == palavra[strlen(palavra) - 1 - i]) {
            i++;
            return IsPalindromo(palavra, i);
        } else {
            return false;
        }
    }//Checa se uma palavra é um palíndromo ao checar letra por letra.
    return true;
}


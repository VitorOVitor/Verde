#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool Comparar(char *palavra); 

bool IsPalindromo(char *palavra);

int main() {
    char palavra[100];
    fgets(palavra, sizeof(palavra), stdin);


    while (Comparar(palavra) == false) {
        if (IsPalindromo(palavra) == true) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
        fgets(palavra, sizeof(palavra), stdin);
        palavra[strlen(palavra) - 1] = '\0'; 
    }

    printf("FIM\n");
    return 0;
}

bool Comparar(char *palavra) {
    char palavra2[] = "FIM";
    if (strlen(palavra) != strlen(palavra2)) {
        return false;
    } else {
        for (int i = 0; i < strlen(palavra); i++) {
            if (palavra[i] != palavra2[i]) {
                return false;
            }
        }
    }//Compara se a palavra escrita é FIM por comparar letra por letra, e o tamanho para ver se ambas são iguais.
    return true;
}

bool IsPalindromo(char *palavra) {
    bool resp = true;
    for (int i = 0; i < strlen(palavra); i++) {
        if (palavra[i] != palavra[strlen(palavra) - 1 - i]) {
            resp = false;
            return resp;
        }
    }//Vê se a palavra é um palíndromo ao checar letra por letra, retornando true se fôr, e false se não fôr.
    return resp;
}

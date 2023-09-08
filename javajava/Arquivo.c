#include <stdio.h>
#include <stdlib.h>

int main() {
    int n;
    scanf("%d", &n);

    double *numeros = malloc(n * sizeof(double));//Aloca um espaço equivalente a n*double para não correr o risco de ocorrer um estouro de memória no algoritmo.
    FILE *file = fopen("Texto.txt", "w");
  
    for (int i = 0; i < n; i++) {
        scanf("%lf", &numeros[i]); //Digita cada numero no loop, e imprime cada um no arquivo
        fprintf(file, "%lf\n", numeros[i]);
    }
    
    fclose(file);

    file = fopen("Texto.txt", "r");
   //Abre no modo leitura
    for (int i = n - 1; i >= 0; i--) {
        fseek(file, 0, SEEK_SET); 
        for (int j = 0; j < i; j++) {
            fscanf(file, "%lf\n", &numeros[j]);
        }//Ao colocar o print rodando no reverso, no indíce n, pode-se colocar o fseek no começo, pois, teoricamente, ele estará no "final" do arquivo. Novamente, como i começa do final do arquivo, j pode seguir normalmente, pois já estará lendo os números do final.
        printf("%.3lf\n", numeros[i]);
    }

    fclose(file);
    free(numeros);
    return 0;
}


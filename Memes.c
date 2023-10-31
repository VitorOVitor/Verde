#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Definição da struct Jogador
typedef struct {
    int id;
    char nome[100];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[100];
    char estadoNascimento[100];
} Jogador;

void imprimir(Jogador* jogador) {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
        jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
}
char* isEmpty(char str []){
    if( !str || strcmp(str, "\0") == 0 )
    return "nao informado";
    else
    return str;
}

void ler( char linha[], Jogador* jogador) {
        char* token = strsep(&linha, ",");
        jogador->id = atoi(token);

        token = strsep(&linha, ",");
        strcpy(jogador->nome, token);

        token = strsep(&linha, ",");
        jogador->altura = atoi(token);

        token = strsep(&linha, ",");
        jogador->peso = atoi(token);

        token = strsep(&linha, ",");
        strcpy(jogador->universidade, isEmpty(token));

        token = strsep(&linha, ",");
        jogador->anoNascimento = atoi(token);

        token = strsep(&linha, ",");
        strcpy(jogador->cidadeNascimento, isEmpty(token));

        token = strsep(&linha, "\n");
        strcpy(jogador->estadoNascimento, isEmpty(token));
    }

    void lerJogadores(Jogador* time[]){
        FILE *fp = NULL;
        fp = fopen("/tmp/players.csv", "r");
        char *linha;
         linha = (char*) malloc(sizeof(char) * 500);
        fgets(linha, 500, fp);
        for (int i = 0; i < 3922; i++) {
            fgets(linha, 500, fp);
            time[i] = (Jogador*)malloc(sizeof(Jogador));
            ler(linha, time[i]);
        
    } 

    }



int main() {

    Jogador *time [3922];
    lerJogadores(time);
    char acharId[4];
    scanf("%s", acharId);
    while (strcmp(acharId, "FIM") != 0) {
        int findId = atoi(acharId);
        imprimir(time[findId]);
        scanf("%s", acharId);
    }

    return 0;
}
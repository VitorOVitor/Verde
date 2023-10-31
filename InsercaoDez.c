#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
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

void imprimir(Jogador *jogadores[], int i) {

  for (int j = 0; j < 10; j++) {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogadores[j]->id,
           jogadores[j]->nome, jogadores[j]->altura, jogadores[j]->peso,
           jogadores[j]->anoNascimento, jogadores[j]->universidade,
           jogadores[j]->cidadeNascimento, jogadores[j]->estadoNascimento);
  }
}

char *isEmpty(char str[]) {
  if (!str || strcmp(str, "\0") == 0)
    return "nao informado";
  else
    return str;
}

void lerLinha(char linha[], Jogador *jogador) {
  char *token = strsep(&linha, ",");
  jogador->id = atoi(token);

  token = strsep(&linha, ",");
  if (token != NULL)
    strcpy(jogador->nome, token);

  token = strsep(&linha, ",");
  if (token != NULL)
    jogador->altura = atoi(token);

  token = strsep(&linha, ",");
  if (token != NULL)
    jogador->peso = atoi(token);

  token = strsep(&linha, ",");
  if (token != NULL)
    strcpy(jogador->universidade, isEmpty(token));

  token = strsep(&linha, ",");
  if (token != NULL)
    jogador->anoNascimento = atoi(token);

  token = strsep(&linha, ",");
  if (token != NULL)
    strcpy(jogador->cidadeNascimento, isEmpty(token));

  token = strsep(&linha, "\n");
  if (token != NULL)
    strcpy(jogador->estadoNascimento, isEmpty(token));
}

void lerJogadores(Jogador *time[]) {
  FILE *arquivo = NULL;
  arquivo = fopen("/tmp/players.csv", "r");
  char *linha;
  linha = (char *)malloc(sizeof(char) * 500);
  fgets(linha, 500, arquivo);
  for (int i = 0; i < 3922; i++) {
    fgets(linha, 500, arquivo);
    time[i] = (Jogador *)malloc(sizeof(Jogador));
    lerLinha(linha, time[i]);
  }
}

void insertionSort(Jogador *lidos[], int n, int trocas) {
  for (int i = 1; i < n; i++) {
    Jogador *k = lidos[i];
    int j = i - 1;
//Separa um número. Se ele fôr menor que o anterior a ele, ele anda para trás, e o outro para frente. Para quando acha seu lugar no array, ou seja, o anterior sendo menor.
    while (j >= 0 && (lidos[j]->anoNascimento > k->anoNascimento ||
                      (lidos[j]->anoNascimento == k->anoNascimento &&
                       strcmp(lidos[j]->nome, k->nome) > 0))) {
      lidos[j + 1] = lidos[j];
      j--;
    }

    lidos[j + 1] = k;
  }
}

void criarLog(int trocas,double tempoExecucao) {
    FILE *arquivo;
    arquivo = fopen("801516_InsercaoDez.txt", "w"); // Abre o arquivo para escrita (cria se não existir
     int matricula= 801516;
    // Escreve as informações no arquivo de log
    fprintf(arquivo, "Matrícula: %d\n", matricula);
    fprintf(arquivo, "Número de Comparações: %d\n", trocas);
    fprintf(arquivo, "Tempo de Execução (segundos): %.2lf\n", tempoExecucao);

    fclose(arquivo); // Fecha o arquivo
}

int main() {
  Jogador *time[3922];
  Jogador *lidos[3922];
  lerJogadores(time);
  char acharId[4];
  int i = 0;
  int trocas=0;
  double tempoExecucao=0;
  scanf("%s", acharId);
  while (strcmp(acharId, "FIM") != 0) {
    int findId = atoi(acharId);
    lidos[i] = time[findId];
    i++;
    scanf("%s", acharId);
  }
clock_t inicio= clock();
  insertionSort(lidos, i, trocas);
  imprimir(lidos, i);
  clock_t final= clock();
  tempoExecucao=(double)(final-inicio)/CLOCKS_PER_SEC;
  criarLog(tempoExecucao, trocas);
  return 0;
}
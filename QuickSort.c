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

  for (int j = 0; j < i; j++) {
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

int particao(Jogador *lidos[], int baixo, int alto) {
  Jogador *pivo = lidos[alto]; //Declaração do pivô.
  int i = baixo - 1;

  for (int j = baixo; j < alto; j++) {

    int estadoCompare = strcmp(lidos[j]->estadoNascimento, pivo->estadoNascimento);
        
    if (estadoCompare < 0 ||
        (estadoCompare == 0 && strcmp(lidos[j]->nome, pivo->nome) <= 0)) {
      i++;  //Compara o nome, se não estiver em ordem alfabética troca
      Jogador *temp = lidos[i];
      lidos[i] = lidos[j];
      lidos[j] = temp;
    }
  }

  Jogador *temp = lidos[i + 1];
  lidos[i + 1] = lidos[alto];
  lidos[alto] = temp; //Troca sendo feita

  return i + 1;
}

void quicksort(Jogador *lidos[], int baixo, int alto, int trocas) {
  if (baixo < alto) {
    int pivo = particao(lidos, baixo, alto);
    quicksort(lidos, baixo, pivo - 1, trocas); //Chamada para continuar da esquerda
    quicksort(lidos, pivo + 1, alto, trocas);  //Chamada para continuar da direita
    trocas++;
  }
}

void criarLog(int trocas,double tempoExecucao) {
    FILE *arquivo;
    arquivo = fopen("801516_quicksort.txt", "w"); // Abre o arquivo para escrita (cria se não existir
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
  int trocas=0;
  double tempoExecucao=0;
  int i = 0;
  scanf("%s", acharId);
  while (strcmp(acharId, "FIM") != 0) {
    int findId = atoi(acharId);
    lidos[i] = time[findId];
    i++;
    scanf("%s", acharId);
  }
  clock_t inicio=clock();
  quicksort(lidos, 0, i - 1, trocas);
  imprimir(lidos, i);
  clock_t final=clock();
  tempoExecucao=(double)(final-inicio)/CLOCKS_PER_SEC;
  criarLog(tempoExecucao, trocas);
  return 0;
}

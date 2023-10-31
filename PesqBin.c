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
int pesquisaBinaria(Jogador *lidos[], int n, int findId);

void imprimir(Jogador *lidos[], int i) {

  for (int j = 0; j < i; j++) {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", lidos[j]->id,
           lidos[j]->nome, lidos[j]->altura, lidos[j]->peso,
           lidos[j]->anoNascimento, lidos[j]->universidade,
           lidos[j]->cidadeNascimento, lidos[j]->estadoNascimento);
  }
}

char *isEmpty(char str[]) {
  if (!str || strcmp(str, "\0") == 0)
    return "nao informado";
  else // Checa para ver se a string é vazia. Se fôr, substitui por nao
       // informado.
    return str;
}

void ler(char linha[], Jogador *jogador) {
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
  FILE *fp = NULL;
  fp = fopen("/tmp/players.csv", "r");
  char *linha; // Abre o arquivo e lê, mandando as linhas para serem "divididas"
               // no método ler.
  linha = (char *)malloc(sizeof(char) * 500);
  fgets(linha, 500, fp);
  for (int i = 0; i < 3922; i++) {
    fgets(linha, 500, fp);
    time[i] = (Jogador *)malloc(sizeof(Jogador));
    ler(linha, time[i]);
  }
}

void BubbleSort(Jogador *lidos[], int i, int trocas) {
  for (int j = 0; j < i; j++) {
    for (int k = 0; k < i - j - 1; k++) {
      if (lidos[k]->anoNascimento > lidos[k + 1]->anoNascimento) {
        Jogador *temp = lidos[k];
        lidos[k] = lidos[k + 1];
        lidos[k + 1] = temp;
      } else if (lidos[k]->anoNascimento == lidos[k + 1]->anoNascimento) {
        if (strcmp(lidos[k]->nome, lidos[k + 1]->nome) > 0) {
          Jogador *temp = lidos[k];
          lidos[k] = lidos[k + 1]; //Bubblesort para ordenar o array.
          lidos[k + 1] = temp;
          trocas++;
        }
      }
    }
  }
}

int pesquisaBinaria(Jogador *lidos[], int n, int findId) {
  int esquerda = 0;
  int direita = n - 1;

  while (esquerda <= direita) {
    int meio = (esquerda + direita) / 2;
    if (lidos[meio]->id == findId) {
      if (isEmpty(lidos[meio]->nome) == 0) {
        printf("NAO\n");
      } else {  //Pesquida o elemnto requisitado. Se tiber um nome, retorna SIM, caso esteja vazio, retorna NAO.
        printf("SIM\n");
      }
      return meio; 
    } else if (lidos[meio]->id < findId) {
      esquerda = meio + 1;
    } else {
      direita = meio - 1;
    }
  }
  printf("NAO\n"); 
  return -1;
}

void criarLog(int trocas,double tempoExecucao) {
    FILE *arquivo;
    arquivo = fopen("801516_shellsort.txt", "w"); // Abre o arquivo para escrita (cria se não existir
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
  int i = 0;
int trocas=0;
double tempoExecucao=0;
  char acharId[4];
  int findId;

  scanf("%s", acharId);
  while (strcmp(acharId, "FIM") != 0) {
    findId = atoi(acharId);
   
    for (int j = 0; j < 3922; j++) {
      lidos[j] = time[j];
    }
    clock_t inicio= clock();
    BubbleSort(lidos, 3922, trocas);

    pesquisaBinaria(lidos, 3922, findId);
   clock_t final=clock();
   tempoExecucao=(double)(final-inicio)/CLOCKS_PER_SEC;
    scanf("%s", acharId);
  }
criarLog(tempoExecucao, trocas);
  return 0;
}

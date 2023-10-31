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
void inserirNaPosicaoCorreta(Jogador *lidos[], int n);
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

void shellSort(Jogador *lidos[], int n, int trocas) {
  int gap, i, j;
  Jogador *temp;

  for (gap = n / 2; gap > 0; gap /= 2) { //Controla o tamanho do intervalo
    for (i = gap; i < n; i++) {
      temp = lidos[i]; //Armazena o jogadores em temp

      for (j = i; j >= gap && (lidos[j - gap]->peso > temp->peso ||
                               (lidos[j - gap]->peso == temp->peso &&  //Comparações de peso e nomes, caso necessário.
                                strcmp(lidos[j - gap]->nome, temp->nome) > 0));
           j -= gap) {
        lidos[j] = lidos[j - gap]; //Move os jogadores para a posição certa.
      }

      lidos[j] = temp;
      trocas++;
    }
  }
}

void criarLog(int trocas,double tempoExecucao) {
    FILE *arquivo;
    arquivo = fopen("801516_shellsort.txt", "w"); // Abre o arquivo para escrita (cria se não existir)
    time_t tempoAtual;
    struct tm *infoTempo;
    char dataHora[30];
    time(&tempoAtual);
    infoTempo = localtime(&tempoAtual);
    strftime(dataHora, 30, "%Y-%m-%d %H:%M:%S", infoTempo);
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
  int trocas=0;
  double tempoExecucao=0;
  lerJogadores(time);
  char acharId[4];
  int i = 0;
  scanf("%s", acharId);
  while (strcmp(acharId, "FIM") != 0) {
    int findId = atoi(acharId);
    lidos[i] = time[findId];
    i++;
    scanf("%s", acharId);
  }
  clock_t inicio = clock();
  shellSort(lidos, i, trocas);
  imprimir(lidos, i);
   clock_t fim = clock();
   double tempoDecorrido = (double)(fim - inicio) / CLOCKS_PER_SEC;
  criarLog(tempoDecorrido, trocas);
  return 0;
}
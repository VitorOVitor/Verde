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

void insercaoRecursiva(Jogador *lidos[], int n, int trocas) {
  if (n <= 1) {
    return;
  }

  insercaoRecursiva(lidos, n - 1, trocas);
  inserirNaPosicaoCorreta(lidos, n - 1);
  trocas++;
}

void inserirNaPosicaoCorreta(Jogador *lidos[], int n) {
  if (n <= 0) {
    return; //Se N fôr 0, para.
  }

  Jogador *ultimo = lidos[n];
  int j = n - 1;

  while (j >= 0 && strcmp(lidos[j]->nome, ultimo->nome) > 0) {
    lidos[j + 1] = lidos[j];
    j--; //Enquanto o número isolado não fôr maior que o número atrás, ele anda para trás até chegar em seu lugar.
  }

  lidos[j + 1] = ultimo;
 
  inserirNaPosicaoCorreta(lidos, j);
}

// Função para chamar a ordenação por inserção recursiva
void ordenacaoInsercaoRecursiva(Jogador *lidos[], int n, int trocas) {
  insercaoRecursiva(lidos, n, trocas);
}

void criarLog(int trocas,double tempoExecucao) {
    FILE *arquivo;
    arquivo = fopen("801516_SequencialRecursivo.txt", "w"); // Abre o arquivo para escrita (cria se não existir
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
  long tempoExecucao=0;
  int i = 0;
  scanf("%s", acharId);
  while (strcmp(acharId, "FIM") != 0) {
    int findId = atoi(acharId);
    lidos[i] = time[findId];
    i++;
    scanf("%s", acharId);
  }
  clock_t inicio= clock();
  insercaoRecursiva(lidos, i, trocas);
  imprimir(lidos, i);
  clock_t fim=clock();
  double tempoDecorrido = (double)(fim-inicio)/ CLOCKS_PER_SEC; 
  criarLog(tempoExecucao, trocas);
  return 0;
}
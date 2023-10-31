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

// Função para ajustar um heap
void heapify(Jogador *lidos[], int n, int i) {
    int menor = i;
    int esquerda = 2 * i + 1;
    int direita = 2 * i + 2;


    if (esquerda < n && lidos[esquerda]->anoNascimento < lidos[menor]->anoNascimento) {
        menor = esquerda; //Compara os elemnetos no heap esquerdo;
    }
  
    else if (esquerda < n && lidos[esquerda]->anoNascimento == lidos[menor]->anoNascimento) {
        if (strcmp(lidos[esquerda]->nome, lidos[menor]->nome) < 0) {
            menor = esquerda;
        }
    }

  
    if (direita < n && lidos[direita]->anoNascimento < lidos[menor]->anoNascimento) {
        menor = direita;
    } //Compara elementos no heap direito.
  
    else if (direita < n && lidos[direita]->anoNascimento == lidos[menor]->anoNascimento) {
        if (strcmp(lidos[direita]->nome, lidos[menor]->nome) < 0) {
            menor = direita;
        }
    }


    if (menor != i) {
        Jogador *temp = lidos[i];
        lidos[i] = lidos[menor];
        lidos[menor] = temp;

      
        heapify(lidos, n, menor);
    }
}


void heapsort(Jogador *lidos[], int n, int trocas) {
    int k = 10;
    if (n < k) {
        k = n;
    }

  
    for (int i = k / 2 - 1; i >= 0; i--) {
        heapify(lidos, k, i); // Construção do heap
    }

 
    for (int i = k; i < n; i++) {
    // Extrair elementos um por um da max-heap
        if (lidos[i]->anoNascimento < lidos[0]->anoNascimento ||
            (lidos[i]->anoNascimento == lidos[0]->anoNascimento &&
             strcmp(lidos[i]->nome, lidos[0]->nome) < 0)) { // Move a raíz atual pro fim do array
          
            Jogador *temp = lidos[0];
            lidos[0] = lidos[i];
            lidos[i] = temp;
          trocas++;
            heapify(lidos, k, 0); // Chama heapify na subárvore reduzida, para continuar criando heaps.
        }
    }
}

void criarLog(int trocas,double tempoExecucao) {
    FILE *arquivo;
    arquivo = fopen("801516_heapsort.txt", "w"); // Abre o arquivo para escrita (cria se não existir
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
  heapsort(lidos, i, trocas);
  imprimir(lidos, i);
  clock_t final=clock();
  criarLog(tempoExecucao, trocas);
  return 0;
}

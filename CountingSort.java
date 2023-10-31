
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.ArrayList;


public class CountingSort{
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        String arquivoCSV="/tmp/players.csv"; //Nome do arquivo a ser lido
        Jogador jogador = new Jogador();
        int trocas=0;
        long tempoExecucao=0;
        String acharId=teclado.nextLine();
         ArrayList<Jogador> time= new ArrayList<Jogador>();
        while(!acharId.equals("FIM")){
            long tempoInicio=System.currentTimeMillis();
        jogador.ler(arquivoCSV,acharId,time);
        long tempoFinal=System.currentTimeMillis();
        tempoExecucao=tempoFinal-tempoInicio;
        acharId=teclado.nextLine();
        }
        Jogador.countingSort(time, trocas);
        Jogador.imprimir(time);
        criarLog(tempoExecucao, trocas);
        teclado.close();
    }

    public static void criarLog(long tempoExecucao, int numerotrocas) {
        try {
            RandomAccessFile escritor = new RandomAccessFile("801516_CountingSort.txt", "rw");
            escritor.writeBytes("801516\t" + tempoExecucao + "\t" + numerotrocas);
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Jogador{
    private int id;
    private String nome;
    private int altura; //Atributos da classe Jogador.
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
    //Costrutor vazio.
}

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento){
        this.id= id;
        this.nome=nome;
        this.altura=altura;//Construtor preenchido
        this.peso=peso;
        this.universidade=universidade;
        this.anoNascimento=anoNascimento;
        this.cidadeNascimento=cidadeNascimento;
        this.estadoNascimento=estadoNascimento;
    }

    public int getId(){
        return id; //Getters
    }

     public String getNome(){
        return nome;
    }

    public int getAltura(){
        return altura;
    }

     public int getPeso(){
        return peso;
    }

    public String getUniversidade(){
        return universidade;
    }

     public int getAnoNascimento(){
        return anoNascimento;
    }

    public String getCidadeNascimento(){
        return cidadeNascimento;
    }

    public String getEstadoNascimento(){
        return estadoNascimento;
    }

    public void setId(int id){
    this.id = id; //Setters.
}

public void setNome(String nome){
    this.nome = nome;
}

public void setAltura(int altura){
    this.altura = altura;
}

public void setPeso(int peso){
    this.peso = peso;
}

public void setUniversidade(String universidade){
    this.universidade = universidade;
}

public void setAnoNascimento(int anoNascimento){
    this.anoNascimento = anoNascimento;
}

public void setCidadeNascimento(String cidadeNascimento){
    this.cidadeNascimento = cidadeNascimento;
}

public void setEstadoNascimento(String estadoNascimento){
    this.estadoNascimento = estadoNascimento;
}

public Jogador clone() {
    return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
}//Método clonagem.



public static void imprimir(ArrayList<Jogador> time) {
    for(int i=0; i<time.size(); i++){
            System.out.println("[" + time.get(i).getId() + " ## " + time.get(i).getNome() +" ## "+ time.get(i).getAltura() +" ## "+ time.get(i).getPeso() +" ## "+ time.get(i).getAnoNascimento() +" ## "+ time.get(i).getUniversidade() +" ## "+ time.get(i).getCidadeNascimento() +" ## "+ time.get(i).getEstadoNascimento() +"]");
    }
}
public static void countingSort(ArrayList<Jogador> time, int trocas) {
    int n = time.size();
    
    // Encontrar a altura máxima
    int maxAltura = 0;
    for (Jogador jogador : time) {
        if (jogador.getAltura() > maxAltura) {
            maxAltura = jogador.getAltura();
        }
    }
    
    // Crie um array de contagem para as alturas, e conta cada altura.
    int[] count = new int[maxAltura + 1];
    
  
    for (Jogador jogador : time) {
        count[jogador.getAltura()]++;
    }
    
    //Cnosntantemente atualiza o array para conseguir a posição correta da altura.
    for (int i = 1; i <= maxAltura; i++) {
        count[i] += count[i - 1];
    }
    
    // array ordenado para armazenar os jogadores obtidos.
    Jogador[] sorted = new Jogador[n];
    
    // Preenche o array com base na altura crescente.
    for (int i = n - 1; i >= 0; i--) {
        Jogador jogador = time.get(i);
        int altura = jogador.getAltura();
        int pos = count[altura] - 1;
        sorted[pos] = jogador;
        count[altura]--;
        trocas++;
    }
    
    // Copia os jogadores de volta para o arrayList time.
    for (int i = 0; i < n; i++) {
        time.set(i, sorted[i]);
    }
    
  
}


 public void ler(String arquivoCSV, String acharId, ArrayList<Jogador> time) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))){ //ABre o arquivo com buffered Reader para a leitura.
            String linha="";
            int findId=Integer.parseInt(acharId);
           
            br.readLine();//Lê a primeira linha manualmente para pular o cabeçalho
            while ((linha = br.readLine()) != null){
            String palavras[]=linha.split(",",8);


            for (int i = 0; i < palavras.length; i++) {
                if (palavras[i].isEmpty()) {
               palavras[i] = "nao informado"; // Atribua a nova string de volta ao array
              }
            }


            setId(Integer.parseInt(palavras[0]));
            setNome(palavras[1]); //QUando palavras[] tiver o valor dos determinados atributos da classe Jogador, eles serão transformados nos atributos e lidos.
            setAltura(Integer.parseInt(palavras[2]));
            setPeso(Integer.parseInt(palavras[3]));
            setUniversidade(palavras[4]);
            setAnoNascimento(Integer.parseInt(palavras[5]));
            setCidadeNascimento(palavras[6]);
            setEstadoNascimento(palavras[7]);
                 

                Jogador jogador= new Jogador(getId(), getNome(), getAltura(), getPeso(), getUniversidade(), getAnoNascimento(), getCidadeNascimento(), getEstadoNascimento());//É criado um objeto jogador para mandar para o construtor imprimir.
                if(jogador.getId()==findId){
                     time.add(jogador);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException e) {
             e.printStackTrace();
        } 
 }

}
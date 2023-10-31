import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.ArrayList;
public class Sequencial {
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        String arquivoCSV="/tmp/players.csv"; //Nome do arquivo a ser lido
        Jogador jogador = new Jogador();
       int trocas=0;
       long tempoExecucao=0;
        String acharId=teclado.nextLine();
        while(!acharId.equals("FIM")){
            long tempoInicial = System.currentTimeMillis(); // Registra o tempo inicial
            jogador.ler(arquivoCSV, acharId, trocas);
            long tempoFinal = System.currentTimeMillis(); // Registra o tempo final
            tempoExecucao = tempoFinal - tempoInicial; // Calcula o tempo de execução
            acharId = teclado.nextLine();
        }
        teclado.close();
        criarLog(tempoExecucao, trocas);
    }

       public static void criarLog(long tempoExecucao, int numerotrocas) {
        try {
            RandomAccessFile escritor = new RandomAccessFile("801516_sequencial.txt", "rw");
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



public void SequencialSearch(ArrayList<Jogador> time, int findId, int trocas) {
    for (int i = 0; i < time.size(); i++) {
        if (time.get(i).getNome().isEmpty()) {
            System.out.println("SIM");
            trocas++;
        } else { //Vê se um nome está vazio. Se estiver, retorna NAO. Se tiver algo, retorna SIM.
            System.out.println("NAO");
            trocas++;
        }
    }
}


 public void ler(String arquivoCSV, String acharId, int trocas) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))){ //ABre o arquivo com buffered Reader para a leitura.
            String linha="";
            int findId=Integer.parseInt(acharId);
            ArrayList<Jogador> time= new ArrayList<Jogador>();
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
                    jogador.SequencialSearch(time, findId, trocas);
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
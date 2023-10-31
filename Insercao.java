import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.ArrayList;
public class Insercao {
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

        Jogador.InsertionSort(time, trocas);
        Jogador.Imprimir(time);
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


public static void Imprimir(ArrayList<Jogador> time){
 for(int i=0; i<time.size(); i++){
            System.out.println("[" + time.get(i).getId() + " ## " + time.get(i).getNome() +" ## "+ time.get(i).getAltura() +" ## "+ time.get(i).getPeso() +" ## "+ time.get(i).getAnoNascimento() +" ## "+ time.get(i).getUniversidade() +" ## "+ time.get(i).getCidadeNascimento() +" ## "+ time.get(i).getEstadoNascimento() +"]");
    }
}


public static void InsertionSort(ArrayList<Jogador> time, int trocas) {
    for (int i = 1; i < time.size(); i++) {
        Jogador jogadorAtual = time.get(i); //Separa um número. Se ele fôr menor que o anterior a ele, ele anda para trás, e o outro para frente. Para quando acha seu lugar no array, ou seja, o anterior sendo menor.
        int anoAtual = jogadorAtual.getAnoNascimento();
        String nomeAtual = jogadorAtual.getNome();
        int j = i - 1;
        // Compara o ano de nascimento primeiro
        while (j >= 0 && time.get(j).getAnoNascimento() > anoAtual) {
            time.set(j + 1, time.get(j));
            j--;
        }
        // Em caso de empate no ano de nascimento, compara por nome
        while (j >= 0 && time.get(j).getAnoNascimento() == anoAtual && time.get(j).getNome().compareTo(nomeAtual) > 0) {
            time.set(j + 1, time.get(j));
            j--;
        }
        time.set(j + 1, jogadorAtual);
        trocas++;
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
                if (palavras[i].equals(",")) {
                    if(palavras[i+1].equals(",")){
                        palavras[i].replace(palavras[i],"nao informado");
                    }
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
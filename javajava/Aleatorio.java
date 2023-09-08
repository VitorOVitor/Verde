import java.util.Random;
public class Aleatorio{
    public static void main(String[] args){
        Random gerador= new Random();
        gerador.setSeed(4);
        String palavra=MyIO.readLine();
        while(Comparar(palavra)==false){
        Regerar(palavra, gerador);
        System.out.print("\n");
        palavra=MyIO.readLine();
        }
        MyIO.println("FIM");
    }

    public static boolean Comparar(String palavra){
        String palavra2="FIM";
        if(palavra.length()!=palavra2.length()){//Compara a palavra mandada letra por letra, e pelo tamanho para identificar se a mesma é FIM.
            return false;
        }
        else{
        for(int i=0; i<palavra.length(); i++){
            if(palavra.charAt(i)!=palavra2.charAt(i)){
            return false;
            }
        }
      }
      return true;
    }

    public static void Regerar(String palavra, Random gerador){
        char letra1=((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
        char letra2=((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
        char temp;
        //Gera duas letras aleatórias por meio do gerador, e troca todas as ocorrências da primeira letra pela segunda.
        for(int i=0; i<palavra.length(); i++){
            if(palavra.charAt(i)==letra1){
            temp=letra1;
            letra1=letra2;
            letra2=temp;
            MyIO.print(letra1);
            }
            else{
                MyIO.print(palavra.charAt(i));
            }
        }
    }
}
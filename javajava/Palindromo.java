
public class Palindromo {
    public static void main(String[] args) {
       
      String palavra=MyIO.readLine();
        while(Comparar(palavra)==false){ //Condição para a iteratividade, enquanto fôr true, o loop se manterá.
       if( IsPalindromo(palavra)==true){
        System.out.println("SIM");
        palavra=MyIO.readLine();
       }
       else{
        System.out.println("NAO");
        palavra=MyIO.readLine();
       }
      }
      System.out.println("FIM");
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

    static boolean IsPalindromo(String palavra){
        boolean resp=true;
      for(int i=0; i<palavra.length(); i++){
      if(palavra.charAt(i)!=palavra.charAt(palavra.length()-1-i)){
        resp=false;
        return resp; //Compara letra por letra para ver se a palavra é um palíndromo
      }
      }
      return resp;
      
    }
}





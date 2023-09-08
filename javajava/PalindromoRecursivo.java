
public class PalindromoRecursivo {
    public static void main(String[] args) {
      processInput();
      MyIO.print("FIM");
    }

    public static void processInput() {
        String palavra = MyIO.readLine();
        int i=0;
        if (!Comparar(palavra, i)){
            if(IsPalindromo(palavra, i)==true){
                MyIO.println("SIM");
            }
            else{
                MyIO.println("NAO");
            } //Método para chamar permitir que a main "se chame" dentro dela mesma.
            processInput();
        } 
    }

    public static boolean Comparar(String palavra, int i){
        String palavra2="FIM";
        if(i<palavra.length()){
            if(i>=palavra.length() || palavra.charAt(i) != palavra2.charAt(i)){
                return false;
            } //Compara o tamanho e letra por letra das duas palavras para ver se uma delas e igual a FIM.
            i++;
            return Comparar(palavra,i);
        }
        return true;
    }


    static boolean IsPalindromo(String palavra, int i){
        if(i<palavra.length()){
        if(palavra.charAt(i)==palavra.charAt(palavra.length()-1-i)){
            i++;
            return IsPalindromo(palavra,i);
        }
        else{
            return false;
        }
        }//Checa letra por letra para ver se a palavra é um palíndromo
      return true;
    }
}

public class MetodoIsRecursiv{
    public static void main(String[] args) {
        processInput();
        System.out.println("FIM");
    }

    public static void processInput() {
        String palavra = MyIO.readLine();
        if (!Comparar(palavra)) {
            boolean isVogal = IsVogal(palavra, 0);
            boolean isConsoante = IsConsoante(palavra, 0);
            boolean isInteiro = IsInteiro(palavra, 0);
            boolean isReal = IsReal(palavra, 0);

            String resultado = (isVogal ? "SIM" : "NAO") + " " +
                               (isConsoante ? "SIM" : "NAO") + " " +
                               (isInteiro ? "SIM" : "NAO") + " " +
                               (isReal ? "SIM" : "NAO")+ " ";

            System.out.println(resultado);
            processInput(); //As coisas que estariam na main estão aqui, para que ela possa se chamar de forma recursiva
        }
    }

    public static boolean IsVogal(String palavra, int i) {
        if (i < palavra.length()) {
            char c = palavra.charAt(i);
            if (!isVogal(c)) {
                return false;
            }//Chama um método para verificar se as strings são compostas por vogais
            i++;
            return IsVogal(palavra, i); 
        }
        return true;
    }

    public static boolean IsConsoante(String palavra, int i) {
        if (i < palavra.length()) {
            char c = palavra.charAt(i);
            if (!isConsoante(c)) {
                return false;
            }
            i++;//Chama um método para verificar se as strings são compostas por consoantes
            return IsConsoante(palavra, i); 
        }
        return true;
    }

    public static boolean IsInteiro(String palavra, int i) {
        if (i < palavra.length()) {
            char c = palavra.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }//Vê se o método é um número inteiro, sem o uso de try catch
            i++;
            return IsInteiro(palavra, i); 
        }
        return true;
    }

    public static boolean IsReal(String palavra, int i) {
        if (i < palavra.length()) {
            char c = palavra.charAt(i);
            if (!Character.isDigit(c) && c != '.' && c != '-') {
                return false;
            }
            i++;
            return IsReal(palavra, i); //Vê se o método é um número real, sem o uso de try catch
        }
        return true;
    }

    public static boolean Comparar(String palavra) {
      String palavra2="FIM";
      if(palavra.length()!=palavra2.length()){
          return false;
      }//
      else{
          for(int i=0; i<palavra.length(); i++){
              if(palavra.charAt(i)!=palavra2.charAt(i)){
                  return false;
              }
          }
      }
      return true;
    }

    public static boolean isVogal(char c) {
        c = Character.toLowerCase(c);//Joga todas as letras para minúsculo para poder faciltar a leitura, e checa para ver se são iguais as vogais
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static boolean isConsoante(char c) {
        c = Character.toLowerCase(c);//Joga todas as letras para minúsculo para poder faciltar a leitura, e checa para ver se são iguais as consoantes
        return c >= 'b' && c <= 'z' && !isVogal(c);
    }
}
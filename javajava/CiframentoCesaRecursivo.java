public class CiframentoCesaRecursivo {
    public static void main(String[] args) {
        processInput();
    }

    public static void processInput() {
        String palavra = MyIO.readLine();
        if (!Comparar(palavra, 0)) {
            String palavraCesar = Cesar(palavra, 0);
            MyIO.println(palavraCesar); //Método usado para repetir a escolha da palavra caso não seja FIM
            processInput();
        } else {
            MyIO.println("FIM");
        }
    }

    public static boolean Comparar(String palavra, int i) {
        String palavra2 = "FIM";
        if (i < palavra2.length()) {
            if (i >= palavra.length() || palavra.charAt(i) != palavra2.charAt(i)) {
                return false;
            }//Compara cada letra para ver se é igual a FIM em tamanho e por cada letra
            i++;
            return Comparar(palavra, i);
        }
        return true;
    }

    public static String Cesar(String palavra, int i) {
        int mudar = 3;
        String palavraCesar = "";
        if (i < palavra.length()) {
            char letra = palavra.charAt(i);
            char cesar = (char) (letra + mudar);//Avança todas as letras em 3, codificando-as.
            palavraCesar += cesar;
            i++;
            palavraCesar += Cesar(palavra, i); //Junta os chars, formando uma string
        }
        return palavraCesar;
    }
}
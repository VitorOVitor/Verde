public class MetodosIs {
    public static void main(String[] args) {
        String palavra = MyIO.readLine();
        while(Comparar(palavra)==false){
        boolean isVogal = IsVogal(palavra);
        boolean isConsoante = IsConsoante(palavra);
        boolean isInteiro = IsInteiro(palavra);
        boolean isReal = IsReal(palavra);

        String resultado = (isVogal ? "SIM" : "NAO") + " " +
                           (isConsoante ? "SIM" : "NAO") + " " +
                           (isInteiro ? "SIM" : "NAO") + " " +
                           (isReal ? "SIM" : "NAO")+" ";

        System.out.println(resultado);
        palavra=MyIO.readLine();
        }
        MyIO.println("FIM");
    }

    public static boolean IsVogal(String palavra) {
        for (int i = 0; i < palavra.length(); i++) {
            char c = palavra.charAt(i);
            if (!isVogal(c)) {
                return false;
            }
        }//Verifica se a palavra é composta de vogais, invocando um método que realiza este processo, retornando false caso não seja, e true caso seja.
        return true;
    }

    public static boolean IsConsoante(String palavra) {
        for (int i = 0; i < palavra.length(); i++) {
            char c = palavra.charAt(i);
            if (!isConsoante(c)) {
                return false;
            }
        }//Verifica se a palavra é composta de consoantes, invocando um método que realiza este processo, retornando false caso não seja, e true caso seja.
        return true;
    }

    public static boolean IsInteiro(String palavra) {
        try {//O método parseint tenta transformar a palavra em um inteiro. Caso consiga, retorna true. Caso não, false.
            Integer.parseInt(palavra);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean IsReal(String palavra) {
        try {//O método parseint tenta transformar a palavra em um número real. Caso consiga, retorna true. Caso não, false.
            Double.parseDouble(palavra);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean Comparar(String palavra){
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
        c = Character.toLowerCase(c);//Colocar todas as vogais recebidas em lower case para facilitar a escrita do código, e recebe vogais.
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static boolean isConsoante(char c) {
        c = Character.toLowerCase(c);//Colocar todas as consoantes recebidas em lower case para facilitar a escrita do código, 
        return c >= 'b' && c <= 'z' && !isVogal(c);
    }
}
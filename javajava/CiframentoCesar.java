

public class CiframentoCesar{
    public static void main(String[] args){
        String palavra=MyIO.readLine();
        while(Comparar(palavra)==false){
        Comparar(palavra);
        Cesar(palavra); 
        palavra=MyIO.readLine();
        }
    }

public static boolean Comparar(String palavra){
    String palavra2="FIM";
    if(palavra.length()!=palavra2.length()){
        return false;
    }//Compara a palavra mandada com a palavra FIM para ver se são iguais em caracteres e por letra.
    else{
    for(int i=0; i<palavra.length(); i++){
        if(palavra.charAt(i)!=palavra2.charAt(i)){
        return false;
        }
    }
}
    return true;
}

    public static void Cesar(String palavra){
        int mudar=3;
        String palavraCesar="";
        for(int i=0; i<palavra.length(); i++){
            char letra=palavra.charAt(i);
            char cesar=(char)(letra+mudar);
            palavraCesar+=cesar;
        }//Recebe a palavra, aumenta as letras em 3, e as junta para formar a palavra criptografada.
        MyIO.println(palavraCesar);
    }
}

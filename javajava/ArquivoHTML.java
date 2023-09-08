import java.io.*;
import java.net.*;

public class ArquivoHTML {
    public static void main(String[] args) {
        String nomeSite;
        String url;
        nomeSite = MyIO.readLine();
        while (!Comparar(nomeSite)) {
            url = MyIO.readLine();
            String conteudo = LerSite(url);
            Saida(nomeSite, conteudo);
            nomeSite = MyIO.readLine();
        }
    }

    public static boolean urlCorreta(String url) {
        return (url.matches("^https?://.*")); // Verifica se a url enviada é válida.
    }

    public static void Saida(String nomeSite, String url) {
        int contadorA = 0;
        int contadorE = 0;
        int contadorI = 0;
        int contadorO = 0;
        int contadorU = 0;
        int contador00E1 = 0; // Á
        int contador00E9 = 0; // É
        int contador00ED = 0; // Í
        int contador00F3 = 0; // Ó
        int contador00FA = 0; // Ú
        int contador00F2 = 0; // À
        int contador00F9 = 0; // Ù
        int contador00E0 = 0; // À
        int contador00E8 = 0; // È
        int contador00EE = 0; // Î
        int contador00EC = 0; // Ì
        int contador00E3 = 0; // Ã
        int contador00F5 = 0; // Õ
        int contador00E2 = 0; // Â
        int contador00EA = 0; // Ê
        int contador00F4 = 0; // Ô
        int contador00FB = 0; // û
        int contadorConsoante = 0;
        int contadorBr = 0;
        int contadorTable = 0;

        for (int i = 0; i < url.length(); i++) {
            char c = url.charAt(i);

            if (c == 'a') {
                contadorA++;
            } else if (c == 'e') {
                contadorE++;
            } else if (c == 'i') {
                contadorI++;
            } else if (c == 'o') {
                contadorO++;
            } else if (c == 'u') {
                contadorU++;//São usados os valores da tabela asc para letras acentuadas afim de facilitar o processo.
            } else if (c == '\u00E1') { // Á
                contador00E1++;
            } else if (c == '\u00E9') { // É
                contador00E9++;
            } else if (c == '\u00ED') { // Í
                contador00ED++;
            } else if (c == '\u00F3') { // Ó
                contador00F3++;
            } else if (c == '\u00FA') { // Ú
                contador00FA++;
            } else if (c == '\u00E0') { // À
                contador00E0++;
            } else if (c == '\u00E8') { // È
                contador00E8++;
            } else if (c == '\u00EC') { // Ì
                contador00EC++;
            } else if (c == '\u00F2') { // Ò
                contador00F2++;
            } else if (c == '\u00F9') { // Ù
                contador00F9++;
            } else if (c == '\u00E3') { // Ã
                contador00E3++;
            } else if (c == '\u00F5') { // Õ
                contador00F5++;
            } else if (c == '\u00E2') { // Â
                contador00E2++;
            } else if (c == '\u00EA') { // Ê
                contador00EA++;
            } else if (c == '\u00EE') { // Î
                contador00EE++;
            } else if (c == '\u00F4') { // Ô
                contador00F4++;
            } else if (c == '\u00FB') { // Û
                contador00FB++;
            } else if (Consoante(c)) {
                contadorConsoante++;
            }

            if (i + 2 < url.length() && url.charAt(i) == '<' && url.charAt(i + 1) == 'b' && url.charAt(i + 2) == 'r') {
                contadorBr++;
                i += 2;
            } else if (i + 6 < url.length() && url.charAt(i) == '<' && url.charAt(i + 1) == 't' && url.charAt(i + 2) == 'a' &&
                    url.charAt(i + 3) == 'b' && url.charAt(i + 4) == 'l' && url.charAt(i + 5) == 'e' &&
                    url.charAt(i + 6) == '>') {//Mesma coisa aqui, são usados valores da tabela asc para achar os elementos que compõem <br> e <table>. Depois, aumenta o índice para não correr o risco de ter um erro de IndexOutofBounds.
                contadorTable++;
                i += 6;
            }
        }

        MyIO.println("a(" + contadorA + ") e(" + contadorE + ") i(" + contadorI + ") o(" + contadorO + ") u(" + contadorU + ") á(" + contador00E1 + ") é(" + contador00E9 + ") í(" + contador00ED + ") ó(" + contador00F3 + ") ú(" + contador00FA + ") à(" + contador00E0 + ") è(" + contador00E8 + ") ì(" + contador00EC + ") ò(" + contador00F2 + ") ù(" + contador00F9 + ") ã(" + contador00E3 + ") õ(" + contador00F5 + ") â(" + contador00E2 + ") ê(" + contador00EA + ") î(" + contador00EE + ") ô(" + contador00F4 + ") û(" + contador00FB + ") consoantes(" + contadorConsoante + ") <br>(" + contadorBr + ") <table>(" + contadorTable + ") " + nomeSite);
    }

    public static String LerSite(String url) {
        String linha;
        String juntador = "";
        URL url2;
        InputStream abrir = null;
        BufferedReader br;
        try {
            url2 = new URL(url);//Cria um objeto url2 para acêssá-la depois.
            abrir = url2.openStream(); //Com o objteo cirado, é possível ter acesso aos conteúdos da url escolhida.
            br = new BufferedReader(new InputStreamReader(abrir));//E aqui, os conteúdos são lidos.
            while ((linha = br.readLine()) != null) {//Enquanto tiverem linhas na ulr, o loop irá ler
                juntador += linha + "\n";//String vazia juntaodr para concatenar os elementos.
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (abrir != null) {
                    abrir.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return juntador;
    }

    public static boolean Comparar(String nomeSite) {
        String palavra2 = "FIM";
        if (nomeSite.length() != palavra2.length()) {
            return false;
        }//Compara letra por letra para ver se é igual a FIM.
        for (int i = 0; i < nomeSite.length(); i++) {
            if (nomeSite.charAt(i) != palavra2.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    public static boolean Consoante(char c) {
        c = Character.toLowerCase(c);
        return c >= 'b' && c <= 'z';//Vê se a letra é uma consoante.
    }
}
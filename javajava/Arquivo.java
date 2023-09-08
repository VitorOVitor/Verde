import java.io.IOException;
import java.io.RandomAccessFile;

public class Arquivo {
    public static void main(String[] args) {
        int n = MyIO.readInt();
        double numeros;

        try {
            RandomAccessFile file = new RandomAccessFile("Texto.txt", "rw");

           //Abre um arquivo raf e escreve os números nele.
            for (int i = 0; i < n; i++) {
                numeros = MyIO.readDouble();
                file.writeDouble(numeros);
            }

            file.close();
            // Abre o arquivo no modo de leitura.
            file = new RandomAccessFile("Texto.txt", "r");
            long tamanho = file.length();
            int numDoubles = (int) (tamanho / 8); //transforma os bytes em inteiros para ser usado de parâmetro, dividindo o tamanho por 8 pelos números colocados serem doubles.

            for (int i = numDoubles - 1; i >= 0; i--) {
                file.seek(i * 8); // O loop começa em numDoubles, o fim do arquivo, e vai até o final, lendo ao reverso. O file seek marca aonde o arquivo está, multiplicando o índice i por 8 por, novamente, os numeros usados serem doubles.
                numeros = file.readDouble();
                System.out.println(numeros);
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
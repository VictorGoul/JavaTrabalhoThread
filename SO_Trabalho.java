// Importando a biblioteca de randomização
import java.util.Random;

public class SO_Trabalho {
    public static int[] numerosAleatorios(int[] arrayValores){
        // Instanciando o objeto da classe Random sem utilizar uma semente
        Random gerador = new Random();
        // Populando o array com números aleatórios
        for (int i = 0; i < arrayValores.length; i++) {
            // Limitando o gerador até o número 1000 e para gerar só positivos
            arrayValores[i] = gerador.nextInt(1001);
        }
        return arrayValores;
    }

    public static void main(String[] args){
        // Inicializando o array para a alocação dos valores aleatórios
        final int[] arrayValores = new int[1000];
        numerosAleatorios(arrayValores);
        
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run(){
                float soma =0, media;
                for (int i = 0; i < arrayValores.length; i++){
                    soma = arrayValores[i] + soma;
                }
                media = soma/1000;
                System.out.printf("A média é: %.2f", media);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run(){
                
            }
        });
        t1.start();
    }
}
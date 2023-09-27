// Importando a biblioteca de randomização
import java.util.Random;
// Importando a biblioteca de ordenação de array
import java.util.Arrays;

public class SO_Trabalho{
    // Função para os números aleatórios
    public static int[] numerosAleatorios(int[] arrayValores){
        // Instanciando o objeto da classe Random sem utilizar uma semente pré determinada
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
        // Chamando a função para popular o array com os números aleatórios
        numerosAleatorios(arrayValores);
        
        // Cálculo da média
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run(){
                // Inicializando as variaveis e poppulacionando a varaivel soma, deve ser inicializada antes
                float soma = 0, media;
                // Percorre o array e soma cada elemento 
                for(int i = 0; i < arrayValores.length; i++){
                    soma += arrayValores[i];
                }
                // Calculo da média, média = (soma de todos os valores)/(quantidade de valores somados)
                media = soma/1000;
                System.out.printf("A média é: %.2f\n", media);
            }
        });

        // Identificação do maior e menor valor
        Thread t2 = new Thread(new Runnable(){
            // Inicializando e populacionando as variaveis de menor e maior valor
            int menorValor = arrayValores[0], maiorValor = arrayValores[0];
            public void run(){
                for(int i = 1; i < arrayValores.length; i++){
                    // Teste para vê se é menor ou não
                    if((menorValor > arrayValores[i]) && (menorValor != 0)){
                        menorValor = arrayValores[i];
                    }
                    // Teste para vê se é maior ou não
                    if((maiorValor < arrayValores[i]) && (maiorValor != 1000)){
                        maiorValor = arrayValores[i];
                    }
                    // Já sai do loop, atingiu os menores valores, devido a limitação da geração (0---|1000)
                    if((menorValor == 0) && (maiorValor == 1000)){
                        break;
                    }
                }
                System.out.printf("O menor valor é: %d e o maior valor é %d\n", menorValor, maiorValor);
            }
        });

        // Soma dos números pares
        Thread t3 = new Thread(new Runnable(){
            public void run(){
                // Inicializando a variavel e poppulacionando, deve ser inicializada antes
                int soma = 0;
                // Percorre o array
                for(int i = 0; i < arrayValores.length; i++){
                    // Se o resto da divisão for igual a 0, o número é par
                    if((arrayValores[i] % 2) == 0){
                        soma += arrayValores[i];
                    }
                }
                System.out.println("O valor da soma de todos os números pares é: " + soma);
            }
        });

        // Contagem dos números primos
        Thread t4 = new Thread(new Runnable(){
            public void run(){
                // Inicializando e populando a variavel, deve ser inicializada antes
                int contadorPrimos = 0, contadorPrimos1 = 0;
                for(int i = 0; i < arrayValores.length; i++){
                    // Laço para teste de número primo
                    for(int contador = 2; contador <= arrayValores[i]; contador++){
                        // Teste número primo
                        if(((arrayValores[i] % contador) == 0) && (arrayValores[i] != 2)){
                            break;
                        }
                        // Se o contador chegar ao valor do número ele é primo e por conta da lógica utilizada se for igual a 2 ele é primo
                        if(((contador + 1) == arrayValores[i]) || (arrayValores[i] == 2)){
                            contadorPrimos++;
                        }
                        // Se o contador/divisor chegar a metade do número, já é impossivel ele ser primo e por conta da lógica utilizada se for igual a 2 ele é primo
                        if((contador == (Math.ceil(arrayValores[i]/2 + 0.5))) || (arrayValores[i] == 2)){
                            contadorPrimos1++;
                        }
                    }
                }
                System.out.println("Tiveram " + contadorPrimos + " números primos " + contadorPrimos1);
            }
        });
        
        // Ordenação do array em forma ascendente
        Thread t5 = new Thread(new Runnable(){
            public void run(){
                int array1[];
                /* Fazendo um clone da array, se não fizer pode alterar a conta dos números primos, a soma de pares e a média
                Por contar mais de uma vez cada elemento ou por não passar por um elemento
                */
                array1 = arrayValores.clone();
                // Comando de para ordenar de forma ascendente
                Arrays.sort(array1);
            }
        });

        // Ordenação do array e mediana
        Thread t6 = new Thread(new Runnable(){
            public void run(){
                // Inicializando e populando a variavel
                float mediana;
                int array2[];
                array2 = arrayValores.clone();
                Arrays.sort(array2);
                // Calculo de mediana, mediana(quando a quantidade de números for par) = ((quantidade de números/2) + (valor do número anterior + 1))/2
                mediana = (arrayValores[499] + arrayValores[500])/2;
                System.out.printf("O valor da mediana é: %.2f\n", mediana);
            }
        });

        // Chama o método e inicia
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        // Aguarda todos finalizarem para ler o valor já totalizada de cada um
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        }catch(InterruptedException ex){ // Tratamento de erro
            ex.printStackTrace();
        }
    }
}

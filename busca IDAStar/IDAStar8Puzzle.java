import java.util.Arrays;

public class IDAStar8Puzzle {

    private static final int TAMANHO = 3;
    private static final int[] OBJETIVO = {1,2,3,4,5,6,7,8,0};
    private static final int ENCONTRADO = -1;

    static class No {
        int[] tabuleiro;
        int posicaoZero;
        int custo;          // custo até o nó
        String caminho;     // sequência de movimentos

        No(int[] tabuleiro, int posicaoZero, int custo, String caminho) {
            this.tabuleiro = tabuleiro;
            this.posicaoZero = posicaoZero;
            this.custo = custo;
            this.caminho = caminho;
        }
    }

    public static void main(String[] args) {

//        int[] inicial = {2,8,3,1,6,4,7,0,5}; explosão do espaço de estados
        int[] inicial = {1,2,3,4,0,6,7,5,8};
        resolver(inicial);
    }

    public static void resolver(int[] tabuleiroInicial) {

        if (!ehSolucionavel(tabuleiroInicial)) {
            System.out.println("Estado inicial sem solução.");
            return;
        }

        long tempoInicio = System.currentTimeMillis();

        int posicaoZero = encontrarZero(tabuleiroInicial);
        No raiz = new No(tabuleiroInicial.clone(), posicaoZero, 0, "");

        int limite = manhattan(tabuleiroInicial);

        while (true) {

            int t = busca(raiz, limite, -1);

            if (t == ENCONTRADO) {
                long tempoFim = System.currentTimeMillis();
                System.out.println("Tempo: " + (tempoFim - tempoInicio) + " ms");
                return;
            }

            if (t == Integer.MAX_VALUE) {
                System.out.println("Sem solução.");
                return;
            }

            limite = t;
        }
    }

    private static int busca(No no, int limite, int movimentoAnterior) {

        int f = no.custo + manhattan(no.tabuleiro);

        if (f > limite)
            return f;

        if (ehObjetivo(no.tabuleiro)) {
            System.out.println("Solução encontrada!");
            System.out.println("Movimentos: " + no.caminho);
            System.out.println("Número de passos: " + no.custo);
            return ENCONTRADO;
        }

        int minimo = Integer.MAX_VALUE;

        int linha = no.posicaoZero / TAMANHO;
        int coluna = no.posicaoZero % TAMANHO;

        // Movimentos: 0=Cima, 1=Baixo, 2=Esquerda, 3=Direita
        int[][] movimentos = {
                {-1, 0}, // Cima
                { 1, 0}, // Baixo
                { 0,-1}, // Esquerda
                { 0, 1}  // Direita
        };

        for (int i = 0; i < movimentos.length; i++) {

            // Evita desfazer movimento anterior
            if ((movimentoAnterior == 0 && i == 1) ||
                (movimentoAnterior == 1 && i == 0) ||
                (movimentoAnterior == 2 && i == 3) ||
                (movimentoAnterior == 3 && i == 2)) {
                continue;
            }

            int novaLinha = linha + movimentos[i][0];
            int novaColuna = coluna + movimentos[i][1];

            if (novaLinha >= 0 && novaLinha < TAMANHO &&
                novaColuna >= 0 && novaColuna < TAMANHO) {

                int novaPosicaoZero = novaLinha * TAMANHO + novaColuna;

                int[] novoTabuleiro = no.tabuleiro.clone();
                novoTabuleiro[no.posicaoZero] = novoTabuleiro[novaPosicaoZero];
                novoTabuleiro[novaPosicaoZero] = 0;

                String nomeMovimento = switch (i) {
                    case 0 -> "U ";
                    case 1 -> "D ";
                    case 2 -> "L ";
                    case 3 -> "R ";
                    default -> "";
                };

                No filho = new No(
                        novoTabuleiro,
                        novaPosicaoZero,
                        no.custo + 1,
                        no.caminho + nomeMovimento
                );

                int t = busca(filho, limite, i);

                if (t == ENCONTRADO)
                    return ENCONTRADO;

                if (t < minimo)
                    minimo = t;
            }
        }

        return minimo;
    }

    // ------------------------
    // Heurística Manhattan
    // ------------------------
    private static int manhattan(int[] tabuleiro) {

        int distancia = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            int valor = tabuleiro[i];

            if (valor != 0) {
                int indiceObjetivo = valor - 1;

                int linhaAtual = i / TAMANHO;
                int colunaAtual = i % TAMANHO;

                int linhaObjetivo = indiceObjetivo / TAMANHO;
                int colunaObjetivo = indiceObjetivo % TAMANHO;

                distancia += Math.abs(linhaAtual - linhaObjetivo)
                           + Math.abs(colunaAtual - colunaObjetivo);
            }
        }

        return distancia;
    }

    // ------------------------
    // Verificação de objetivo
    // ------------------------
    private static boolean ehObjetivo(int[] tabuleiro) {
        return Arrays.equals(tabuleiro, OBJETIVO);
    }

    // ------------------------
    // Encontra posição do zero
    // ------------------------
    private static int encontrarZero(int[] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[i] == 0)
                return i;
        }
        return -1;
    }

    // ------------------------
    // Verifica solvabilidade
    // ------------------------
    private static boolean ehSolucionavel(int[] tabuleiro) {

        int inversoes = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = i + 1; j < tabuleiro.length; j++) {
                if (tabuleiro[i] != 0 &&
                    tabuleiro[j] != 0 &&
                    tabuleiro[i] > tabuleiro[j]) {
                    inversoes++;
                }
            }
        }

        // Para 3x3 (largura ímpar)
        return inversoes % 2 == 0;
    }
}
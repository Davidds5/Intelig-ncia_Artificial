import java.util.*;

class RBFS {

    static class Resultado {
        Cidade solucao;
        int f;

        Resultado(Cidade s, int f) {
            this.solucao = s;
            this.f = f;
        }
    }

    public static Resultado rbfs(Cidade noAtual, int limiteF, int profundidade) {

        imprimirPilha(noAtual, profundidade);

        System.out.println("Visitando: " + noAtual.nome +
                " | g=" + noAtual.g +
                " | h=" + noAtual.h +
                " | f=" + noAtual.f +
                " | limite=" + limiteF);

        if (noAtual.nome.equals("Bucharest")) {
            System.out.println("OBJETIVO ENCONTRADO!\n");
            return new Resultado(noAtual, noAtual.f);
        }

        List<Cidade> sucessores = new ArrayList<>();

        for (Cidade.Estrada estrada : noAtual.vizinhos) {

            Cidade base = estrada.destino;

            Cidade s = new Cidade(base.nome, base.h);
            s.vizinhos = base.vizinhos;

            s.pai = noAtual;
            s.g = noAtual.g + estrada.custo;
            s.f = s.g + s.h;

            sucessores.add(s);

            System.out.println("  Gerado -> "
                    + s.nome
                    + " g=" + s.g
                    + " h=" + s.h
                    + " f=" + s.f);
        }

        if (sucessores.isEmpty()) {
            return new Resultado(null, Integer.MAX_VALUE);
        }

        while (true) {

            sucessores.sort(Comparator.comparingInt(n -> n.f));

            Cidade melhor = sucessores.get(0);

            if (melhor.f > limiteF) {

                System.out.println("BACKTRACK! "
                        + melhor.nome
                        + " excedeu limite.\n");

                return new Resultado(null, melhor.f);
            }

            int alternativa = sucessores.size() > 1
                    ? sucessores.get(1).f
                    : Integer.MAX_VALUE;

            System.out.println("Melhor: "
                    + melhor.nome
                    + " | alternativa=" + alternativa);

            Resultado resultado =
                    rbfs(melhor, Math.min(limiteF, alternativa), profundidade + 1);

            melhor.f = resultado.f;

            if (resultado.solucao != null)
                return resultado;
        }
    }

    static void imprimirPilha(Cidade cidade, int profundidade) {

        System.out.println("\nPILHA RECURSIVA:");

        Cidade atual = cidade;

        List<String> caminho = new ArrayList<>();

        while (atual != null) {
            caminho.add(atual.nome);
            atual = atual.pai;
        }

        Collections.reverse(caminho);

        for (int i = 0; i < caminho.size(); i++) {

            for (int j = 0; j < i; j++)
                System.out.print("   ");

            if (i == 0)
                System.out.println(caminho.get(i));
            else
                System.out.println("└─ " + caminho.get(i));
        }

        System.out.println();
    }
}
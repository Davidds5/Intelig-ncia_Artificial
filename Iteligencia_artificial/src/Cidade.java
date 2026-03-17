import java.util.*;

class Cidade {

    String nome;

    int g; // custo acumulado
    int h; // heurística
    int f; // avaliação

    Cidade pai;

    List<Estrada> vizinhos = new ArrayList<>();

    public Cidade(String nome, int h) {
        this.nome = nome;
        this.h = h;
    }

    public void adicionarVizinho(Cidade destino, int custo) {
        vizinhos.add(new Estrada(destino, custo));
    }

    static class Estrada {
        Cidade destino;
        int custo;

        Estrada(Cidade destino, int custo) {
            this.destino = destino;
            this.custo = custo;
        }
    }
}
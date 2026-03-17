import java.util.*;

public class AulaRBFS {

    public static void main(String[] args) {

        Cidade arad = new Cidade("Arad", 366);
        Cidade sibiu = new Cidade("Sibiu", 253);
        Cidade timisoara = new Cidade("Timisoara", 329);
        Cidade zerind = new Cidade("Zerind", 374);

        Cidade fagaras = new Cidade("Fagaras", 176);
        Cidade rimnicu = new Cidade("RimnicuVilcea", 193);

        Cidade pitesti = new Cidade("Pitesti", 100);
        Cidade bucharest = new Cidade("Bucharest", 0);

        arad.adicionarVizinho(sibiu, 140);
        arad.adicionarVizinho(timisoara, 118);
        arad.adicionarVizinho(zerind, 75);

        sibiu.adicionarVizinho(fagaras, 99);
        sibiu.adicionarVizinho(rimnicu, 80);

        rimnicu.adicionarVizinho(pitesti, 97);

        fagaras.adicionarVizinho(bucharest, 211);

        pitesti.adicionarVizinho(bucharest, 101);

        arad.g = 0;
        arad.f = arad.h;

        RBFS.Resultado resultado =
                RBFS.rbfs(arad, Integer.MAX_VALUE, 0);

        System.out.println("\nCAMINHO FINAL:");

        imprimirCaminho(resultado.solucao);
    }

    static void imprimirCaminho(Cidade cidade) {

        List<String> caminho = new ArrayList<>();

        while (cidade != null) {
            caminho.add(cidade.nome);
            cidade = cidade.pai;
        }

        Collections.reverse(caminho);

        for (String c : caminho)
            System.out.println(c);
    }
}
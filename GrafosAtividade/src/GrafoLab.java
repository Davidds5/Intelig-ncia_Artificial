import java.io.*;
import java.util.*;

public class GrafoLab {

    static int[][] matriz;
    static int n;

    // metodo para rodar codigo
    public static void main(String[] args) throws Exception {

        lerArquivo("entrada.txt");

        System.out.println("ARESTAS:");
        listarArestas();

        System.out.println("\nDirecionado? " + ehDirecionado());

        System.out.println("\nGRAU DOS VÉRTICES:");
        grauVertices();

        System.out.println("\nGRAFO CONEXO? " + ehConexo());

        System.out.println("\nGRAFO CÍCLICO? " + temCiclo());

        System.out.println("\nLISTA DE ADJACÊNCIA:");
        listaAdjacencia();
    }


    static void lerArquivo(String nome) throws Exception {

        Scanner sc = new Scanner(new File(nome));

        n = sc.nextInt();
        matriz = new int[n][n];

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                matriz[i][j] = sc.nextInt();
    }

    static void listarArestas(){

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(matriz[i][j] == 1)
                    System.out.println(i + " -> " + j);
    }

    static boolean ehDirecionado(){

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(matriz[i][j] != matriz[j][i])
                    return true;

        return false;
    }

    static void grauVertices(){

        for(int i=0;i<n;i++){
            int grau = 0;

            for(int j=0;j<n;j++)
                grau += matriz[i][j];

            System.out.println("Vértice " + i + ": " + grau);
        }
    }

    static boolean ehConexo(){

        boolean[] visitado = new boolean[n];
        dfs(0, visitado);

        for(boolean v : visitado)
            if(!v) return false;

        return true;
    }

    static void dfs(int v, boolean[] visitado){

        visitado[v] = true;

        for(int i=0;i<n;i++)
            if(matriz[v][i] == 1 && !visitado[i])
                dfs(i, visitado);
    }

    static boolean temCiclo(){

        boolean[] visitado = new boolean[n];
        boolean[] pilha = new boolean[n];

        for(int i=0;i<n;i++)
            if(cicloDFS(i, visitado, pilha))
                return true;

        return false;
    }

    static boolean cicloDFS(int v, boolean[] visitado, boolean[] pilha){

        if(pilha[v]) return true;
        if(visitado[v]) return false;

        visitado[v] = true;
        pilha[v] = true;

        for(int i=0;i<n;i++)
            if(matriz[v][i] == 1)
                if(cicloDFS(i, visitado, pilha))
                    return true;

        pilha[v] = false;
        return false;
    }

    static void listaAdjacencia(){

        for(int i=0;i<n;i++){

            System.out.print(i + ": ");

            for(int j=0;j<n;j++)
                if(matriz[i][j] == 1)
                    System.out.print(j + " ");

            System.out.println();
        }
    }
}

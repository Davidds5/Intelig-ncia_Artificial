import java.util.*;

public class GrafoCompleto {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] matriz = new int[n][n];

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(i != j)
                    matriz[i][j] = 1;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                System.out.print(matriz[i][j] + " ");
            System.out.println();
        }
    }
}
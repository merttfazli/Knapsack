import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Knapsack {
    public static void main(String[] args) throws Exception {
        long beginTime = 0, endTime = 0;
        beginTime = System.currentTimeMillis();

        URL url = new URL("https://raw.githubusercontent.com/merttfazli/Knapsack/main/ks_19_0");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String[] firstLine = reader.readLine().split("\\s+");
        int n = Integer.parseInt(firstLine[0]);
        int capacity = Integer.parseInt(firstLine[1]);
        int[] weights = new int[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split("\\s+");
            values[i] = Integer.parseInt(line[0]);
            weights[i] = Integer.parseInt(line[1]);
        }
        reader.close();

        // dinamik programlama algoritmasını kullanarak maksimum değeri ve optimal çözümü hesapla
        int[][] K = knapsack(capacity, weights, values, n);
        int maxVal = K[n][capacity];
        ArrayList<Integer> includedItems = new ArrayList<>();
        for (int i = n, j = capacity; i > 0 && maxVal > 0; i--) {
            if (maxVal != K[i - 1][j]) {
                includedItems.add(i);
                maxVal -= values[i - 1];
                j -= weights[i - 1];
            }
        }
        includedItems.sort(null);

        // Sonucu yazdır
        System.out.println("Optimal value değeri: " + K[n][capacity]);
        System.out.print("Optimal çözüm : ");
        for (int i = 1; i <= n; i++) {
            System.out.print(includedItems.contains(i) ? "1 " : "0 ");
        }
        System.out.println();
        System.out.print("Optimal çözüme dahil edilen itemler: ");
        for (int item : includedItems) {
            System.out.print(values[item - 1] + " ");
        }
        System.out.println();
        endTime = System.currentTimeMillis();
        System.out.println("Çalışma Süresi : " + ((double) (endTime - beginTime)) / 1000+" Sn");
    }

    public static int[][] knapsack(int W, int[] wt, int[] val, int n) {
        int[][] K = new int[n + 1][W + 1];
        // Tabloyu doldurma
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (wt[i - 1] <= j) {
                    K[i][j] = Math.max(val[i - 1] + K[i - 1][j - wt[i - 1]], K[i - 1][j]);
                } else {
                    K[i][j] = K[i - 1][j];
                }
            }
        }
        // Tabloyu döndür
        return K;
    }
}


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Knapsack10000 {
    public static void main(String[] args) throws Exception {
        long beginTime = 0, endTime = 0;
        beginTime = System.currentTimeMillis();
        URL url = new URL("https://raw.githubusercontent.com/merttfazli/Knapsack/main/ks_10000_0");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String[] firstLine = reader.readLine().split("\\s+");
        int n = Integer.parseInt(firstLine[0]);
        int capacity = Integer.parseInt(firstLine[1]);
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split("\\s+");
            int value = Integer.parseInt(line[0]);
            int weight = Integer.parseInt(line[1]);
            items.add(new Item(value, weight));
        }
        reader.close();

        // Eşyaları ağırlık/fayda oranına göre sırala
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double r1 = o1.value / (double) o1.weight;
                double r2 = o2.value / (double) o2.weight;
                return Double.compare(r2, r1);
            }
        });

        // Greedy algoritmasını kullanarak maksimum değeri ve optimal çözümü hesapla
        int maxVal = 0;
        ArrayList<Integer> includedIndexes = new ArrayList<>();
        for (Item item : items) {
            if (item.weight <= capacity) {
                includedIndexes.add(items.indexOf(item) + 1);
                maxVal += item.value;
                capacity -= item.weight;
            }
        }

        // Sonucu yazdır
        System.out.println("Optimal value değeri: " + maxVal);
        System.out.print("Optimal çözüme dahil edilen itemler: ");
        for (int i = 0; i < includedIndexes.size(); i++) {
            System.out.print(includedIndexes.get(i));
            if (i != includedIndexes.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
        endTime = System.currentTimeMillis();
        System.out.println("Çalışma Süresi : " + ((double) (endTime - beginTime)) / 1000 + " Sn");
    }

    static class Item {
        int value;
        int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }
}

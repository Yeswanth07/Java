import java.util.*;

public class FrequencyAnalyzer {
    static int[] numbers = {7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};

    public static void findTopKFrequentNumbers(int K) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (a[1] == b[1]) ? b[0] - a[0] : b[1] - a[1]
        );

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            maxHeap.add(new int[]{entry.getKey(), entry.getValue()});
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            result.add(maxHeap.poll()[0]);
        }

        System.out.println("Top " + K + " numbers with highest occurrences:");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {
        int K = 4;
        findTopKFrequentNumbers(K);
    }
}

import java.util.Scanner;
import java.util.concurrent.*;

class CoinCombinationsTask implements Callable<Integer> {
    private final int[] coins;
    private final int sum;
    private final int start;

    public CoinCombinationsTask(int[] coins, int sum, int start) {
        this.coins = coins;
        this.sum = sum;
        this.start = start;
    }

    @Override
    public Integer call() {
        return countWays(coins, sum, start);
    }

    private int countWays(int[] coins, int sum, int start) {
        if (sum == 0) {
            return 1;
        }
        if (sum < 0 || start >= coins.length) {
            return 0;
        }
        return countWays(coins, sum - coins[start], start) + countWays(coins, sum, start + 1);
    }
}

public class CoinCombinationsMultithreaded {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of coin denominations:");
        int n = scanner.nextInt();
        int[] coins = new int[n];

        System.out.println("Enter the coin denominations:");
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }

        System.out.println("Enter the sum to be made:");
        int sum = scanner.nextInt();
        int numThreads = coins.length;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        Future<Integer>[] results = new Future[numThreads];

        for (int i = 0; i < numThreads; i++) {
            results[i] = executor.submit(new CoinCombinationsTask(coins, sum, i));
        }

        int totalWays = 0;
        for (Future<Integer> result : results) {
            totalWays += result.get();
        }

        executor.shutdown();

        System.out.println("Number of ways to make sum " + sum + ": " + totalWays);
    }
}

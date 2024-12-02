import java.util.Scanner;
import java.util.concurrent.*;

public class CoinChangeMultiThreaded {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the number of coins (N): ");
        int N = scanner.nextInt();

        System.out.print("Enter the target sum: ");
        int targetSum = scanner.nextInt();

        System.out.println("Enter the coin denominations: ");
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = scanner.nextInt();
        }

        scanner.close();


        int result = countWaysMultithreaded(coins, targetSum);
        System.out.println("Number of ways to make the sum: " + result);
    }

    public static int countWaysMultithreaded(int[] coins, int targetSum) throws InterruptedException, ExecutionException {

        int[] dp = new int[targetSum + 1];
        dp[0] = 1; 


        int numThreads = Math.min(coins.length, Runtime.getRuntime().availableProcessors());
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);


        for (int coin : coins) {
            executor.submit(() -> {
                
                for (int j = coin; j <= targetSum; j++) {
                    synchronized (dp) {
                        dp[j] += dp[j - coin];
                    }
                }
            });
        }

        
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        return dp[targetSum];};
}

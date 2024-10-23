public class ShareTrader {
    static int maxProfit;


    public static int findMaxProfit(int[] prices) {
        int n = prices.length;
        
        if (n < 2) {
            return 0;
        }

        int[] leftProfit = new int[n];
        int[] rightProfit = new int[n];

        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        int maxPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightProfit[i] = Math.max(rightProfit[i + 1], maxPrice - prices[i]);
            maxPrice = Math.max(maxPrice, prices[i]);
        }

        maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, leftProfit[i] + rightProfit[i]);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {10, 22, 5, 75, 65, 80};
        System.out.println("Maximum profit: " + findMaxProfit(prices1)); 

        int[] prices2 = {2, 30, 15, 10, 8, 25, 80};
        System.out.println("Maximum profit: " + findMaxProfit(prices2));
    }
}

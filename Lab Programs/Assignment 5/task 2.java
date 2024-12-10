import java.util.Scanner;

interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}

abstract class RainySeasonConservation implements WaterConservationSystem {
    public abstract int calculateTrappedWater(int[] blockHeights);
}

class CityBlockConservation extends RainySeasonConservation {
    @Override
    public int calculateTrappedWater(int[] blockHeights) {
        int n = blockHeights.length;
        if (n == 0) return 0;

        int totalWater = 0;

        for (int i = 0; i < n; i++) {
            int leftMax = 0;
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, blockHeights[j]);
            }

            int rightMax = 0;
            for (int j = i; j < n; j++) {
                rightMax = Math.max(rightMax, blockHeights[j]);
            }

            totalWater += Math.min(leftMax, rightMax) - blockHeights[i];
        }

        return totalWater;
    }
}



public class WaterConservationTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of blocks: ");
        int n = scanner.nextInt();
        int[] blockHeights = new int[n];

        System.out.println("Enter the heights of the blocks: ");
        for (int i = 0; i < n; i++) {
            blockHeights[i] = scanner.nextInt();
        }

        WaterConservationSystem system = new CityBlockConservation();
        int result = system.calculateTrappedWater(blockHeights);
        System.out.println("Total water trapped: " + result);
    }
}

import java.util.Scanner;

abstract class Robber {
    public abstract int RowHouses(int[] houses);
    public abstract int RoundHouses(int[] houses);
    public abstract int SquareHouse(int[] houses);
    public abstract int MultiHouseBuilding(int[]... buildings);

    public void MachineLearning() {
        System.out.println("I love MachineLearning.");
    }

    public void RobbingClass() {
        System.out.println("MScAI&ML");
    }
}

class JAVAProfessionalRobber extends Robber {
    @Override
    public int RowHouses(int[] houses) {
        if (houses.length == 0) return 0;
        int prev = 0, curr = 0;
        for (int amount : houses) {
            int temp = curr;
            curr = Math.max(curr, prev + amount);
            prev = temp;
        }
        return curr;
    }

    @Override
    public int RoundHouses(int[] houses) {
        if (houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];
        return Math.max(rob(houses, 0, houses.length - 2), rob(houses, 1, houses.length - 1));
    }

    private int rob(int[] houses, int start, int end) {
        int prev = 0, curr = 0;
        for (int i = start; i <= end; i++) {
            int temp = curr;
            curr = Math.max(curr, prev + houses[i]);
            prev = temp;
        }
        return curr;
    }

    @Override
    public int SquareHouse(int[] houses) {
        return RowHouses(houses);
    }

    @Override
    public int MultiHouseBuilding(int[]... buildings) {
        int totalMax = 0;
        for (int[] building : buildings) {
            totalMax += RowHouses(building);
        }
        return totalMax;
    }

    public static void main(String[] args) {
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the amounts for Row Houses : ");
        int[] rowHouses = parseInput(scanner.nextLine());
        System.out.println("Maximum amount robbed from Row Houses: " + robber.RowHouses(rowHouses));

        System.out.println("Enter the amounts for Round Houses : ");
        int[] roundHouses = parseInput(scanner.nextLine());
        System.out.println("Maximum amount robbed from Round Houses: " + robber.RoundHouses(roundHouses));

        System.out.println("Enter the amounts for Square Houses : ");
        int[] squareHouses = parseInput(scanner.nextLine());
        System.out.println("Maximum amount robbed from Square Houses: " + robber.SquareHouse(squareHouses));

        System.out.println("Enter the number of building types for Multi-House Building: ");
        int buildingTypes = Integer.parseInt(scanner.nextLine());
        int[][] multiHouseBuildings = new int[buildingTypes][];
        for (int i = 0; i < buildingTypes; i++) {
            System.out.println("Enter the amounts for Building Type " + (i + 1) + ": ");
            multiHouseBuildings[i] = parseInput(scanner.nextLine());
        }
        System.out.println("Maximum amount robbed from Multi-House Building: " + robber.MultiHouseBuilding(multiHouseBuildings));

        robber.RobbingClass();
        robber.MachineLearning();

        scanner.close();
    }

    private static int[] parseInput(String input) {
        String[] parts = input.split(",");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i].trim());
        }
        return result;
    }
}

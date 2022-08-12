import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int totalFuelCost = 0;
        int lowestFuelCost = Integer.MAX_VALUE;
        int difference = 0;
        try (Scanner myReader = new Scanner(new File("Input.txt"))) {
            int[] allNumbers = Arrays.stream(myReader.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < Arrays.stream(allNumbers).max().getAsInt();j++) {
                for (int allNumber : allNumbers) {
                    difference = Math.abs(j - allNumber);
                    if (difference > 0) {
                        totalFuelCost += (1 + difference) * difference / 2;
                    }
                }
                if (totalFuelCost < lowestFuelCost) {
                    lowestFuelCost = totalFuelCost;
                }
                totalFuelCost = 0;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        System.out.println(lowestFuelCost);


    }
}
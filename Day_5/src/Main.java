import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int largestX = 1000;
        int largestY = 1000;
        int[][] field = new int[largestX][largestY];
        Point pointOne = new Point();
        Point pointTwo = new Point();
        int count = 0;



        try (Scanner myReader = new Scanner(new File("Input.txt"))) {
            while (myReader.hasNextLine()) {
                int[] coordinatesArray = Arrays.stream(myReader.nextLine().split(",| -> ")).mapToInt(Integer::parseInt).toArray();
                pointOne.setLocation(coordinatesArray[0], coordinatesArray[1]);
                pointTwo.setLocation(coordinatesArray[2], coordinatesArray[3]);
                if (pointOne.x == pointTwo.x) {
                    int largest = Math.max(pointOne.y, pointTwo.y);
                    int smallest = Math.min(pointOne.y, pointTwo.y);
                    for (int i = smallest; i <= largest; i++) {
                        field[pointOne.x][i] += 1;
                    }
                } else if (pointOne.y == pointTwo.y) {
                    int largest = Math.max(pointOne.x, pointTwo.x);
                    int smallest = Math.min(pointOne.x, pointTwo.x);
                    for (int i = smallest; i <= largest; i++) {
                        field[i][pointOne.y] += 1;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        for (int i = 0;i < largestX;i++) {
            for (int j = 0;j < largestY;j++) {
                if (field[i][j] > 1) {
                    count++;
                }
            }
        }
        System.out.println(count);

        
    }
}